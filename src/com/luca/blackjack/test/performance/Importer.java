package com.luca.blackjack.test.performance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.luca.blackjack.Blackjack;
import com.luca.blackjack.StandardBlackjackGame;
import com.luca.blackjack.card.StandardDeck;
import com.luca.blackjack.game.StandardRules;
import com.luca.blackjack.game.VegasTable;
import com.luca.blackjack.strategy.betting.DefaultBettingStrategy;
import com.luca.blackjack.strategy.game.DefaultDealerGameStrategy;
import com.luca.blackjack.strategy.game.DefaultPlayerGameStrategy;
import com.luca.blackjack.strategy.game.GenericDealerGameStrategy;
import com.luca.blackjack.strategy.game.SimpleStrategy;
import com.luca.blackjack.strategy.insurance.AlwaysInsurance;
import com.luca.blackjack.strategy.insurance.NeverInsurance;
import com.luca.blackjack.user.SimpleDealer;
import com.luca.blackjack.user.SimplePlayer;

@RunWith(Parameterized.class)
public class Importer {

	private ThreadMXBean threadMXBean;

	Unmarshaller unmarshaller;
	Blackjack blackjack;

	File xml;
	double avg;
	double delta;

	private final long nanoToSec = 1000000000;

	public Importer(File xml, List<Double> expectation) {
		this.xml = xml;
		this.avg = expectation.get(0);
		this.delta = expectation.get(1);

		threadMXBean = ManagementFactory.getThreadMXBean();
		assertTrue(threadMXBean.isThreadCpuTimeSupported());
		assertTrue(threadMXBean.isCurrentThreadCpuTimeSupported());
	}

	@Before
	public final void setup() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(
				StandardBlackjackGame.class, VegasTable.class,
				StandardDeck.class, SimpleDealer.class, StandardRules.class,
				SimplePlayer.class, DefaultBettingStrategy.class,
				GenericDealerGameStrategy.class,
				DefaultDealerGameStrategy.class,
				DefaultPlayerGameStrategy.class, SimpleStrategy.class,
				NeverInsurance.class, AlwaysInsurance.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		blackjack = null;

		threadMXBean.setThreadContentionMonitoringEnabled(true);
		threadMXBean.setThreadCpuTimeEnabled(true);
		assertTrue(threadMXBean.isThreadCpuTimeEnabled());
	}

	@After
	public final void tearDown() {
		threadMXBean.setThreadContentionMonitoringEnabled(false);
		threadMXBean.setThreadCpuTimeEnabled(false);
		assertFalse(threadMXBean.isThreadCpuTimeEnabled());

		blackjack = null;
		unmarshaller = null;
	}

	@Parameters
	public static Collection<Object[]> data() {

		File file0 = new File("data/test-data-v1.0-000.xml");
		List<Double> expectation0 = new ArrayList<Double>();
		expectation0.addAll(Arrays.asList(0.1, 0.1));

		return Arrays.asList(new Object[][] { { file0, expectation0 } });
	}

	@Test
	public final void unmarshaller() throws JAXBException {

		long startTime = threadMXBean.getCurrentThreadCpuTime();

		// monitor begin

		blackjack = (Blackjack) unmarshaller.unmarshal(xml);

		// monitor end

		long endTime = threadMXBean.getCurrentThreadCpuTime();

		double actual = new Double((endTime - startTime)) / nanoToSec;

		assertEquals(avg, actual, delta);
	}
}
