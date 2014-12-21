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

import com.luca.blackjack.StandardBlackjackGame;
import com.luca.blackjack.card.StandardDeck;
import com.luca.blackjack.game.StandardRules;
import com.luca.blackjack.game.Table;
import com.luca.blackjack.strategy.betting.DefaultBettingStrategy;
import com.luca.blackjack.strategy.game.DefaultDealerGameStrategy;
import com.luca.blackjack.strategy.game.DefaultPlayerGameStrategy;
import com.luca.blackjack.strategy.game.SimpleStrategy;
import com.luca.blackjack.strategy.insurance.AlwaysInsurance;
import com.luca.blackjack.strategy.insurance.NeverInsurance;
import com.luca.blackjack.user.SimpleDealer;
import com.luca.blackjack.user.SimplePlayer;

@RunWith(Parameterized.class)
public class VegasTable {

	private ThreadMXBean threadMXBean;

	com.luca.blackjack.game.VegasTable table;

	File xml;
	List<List<Double>> expectation;

	private final long nanoToSec = 1000000000;

	public VegasTable(File xml, List<List<Double>> expectation) {
		this.xml = xml;
		this.expectation = expectation;

		threadMXBean = ManagementFactory.getThreadMXBean();
		assertTrue(threadMXBean.isThreadCpuTimeSupported());
		assertTrue(threadMXBean.isCurrentThreadCpuTimeSupported());
	}

	@Before
	public final void setup() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(
				StandardBlackjackGame.class,
				com.luca.blackjack.game.VegasTable.class, StandardDeck.class,
				SimpleDealer.class, StandardRules.class, SimplePlayer.class,
				DefaultBettingStrategy.class, DefaultDealerGameStrategy.class,
				DefaultPlayerGameStrategy.class, SimpleStrategy.class,
				NeverInsurance.class, AlwaysInsurance.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		StandardBlackjackGame standardBlackjackGame = (StandardBlackjackGame) unmarshaller
				.unmarshal(xml);
		List<Table> tables = standardBlackjackGame.getTables();
		table = (com.luca.blackjack.game.VegasTable) tables.get(0);

		threadMXBean.setThreadContentionMonitoringEnabled(true);
		threadMXBean.setThreadCpuTimeEnabled(true);
		assertTrue(threadMXBean.isThreadCpuTimeEnabled());
	}

	@After
	public final void tearDown() {
		threadMXBean.setThreadContentionMonitoringEnabled(false);
		threadMXBean.setThreadCpuTimeEnabled(false);
		assertFalse(threadMXBean.isThreadCpuTimeEnabled());

		table = null;
	}

	@Parameters
	public static Collection<Object[]> data() {

		File file0 = new File("data/test-data-v1.0.xml");
		List<List<Double>> expectation0 = new ArrayList<List<Double>>();
		List<Double> expectation00 = new ArrayList<Double>();
		expectation00.addAll(Arrays.asList(0.05, 0.2));
		List<Double> expectation01 = new ArrayList<Double>();
		expectation01.addAll(Arrays.asList(0.25, 1d));
		expectation0.addAll(Arrays.asList(expectation00, expectation01));

		return Arrays.asList(new Object[][] { { file0, expectation0 } });
	}

	@Test
	public final void playGame1Run() {

		List<Double> run1 = expectation.get(0);
		double avg = run1.get(0);
		double delta = run1.get(1);

		long startTime = threadMXBean.getCurrentThreadCpuTime();

		// monitor begin

		table.initialise();
		table.playGame();

		// monitor end

		long endTime = threadMXBean.getCurrentThreadCpuTime();

		double actual = new Double((endTime - startTime)) / nanoToSec;

		assertEquals(avg, actual, delta);
	}

	@Test
	public final void playGame5Run() {

		List<Double> run5 = expectation.get(1);
		double avg = run5.get(0);
		double delta = run5.get(1);

		long startTime = threadMXBean.getCurrentThreadCpuTime();

		// monitor begin

		table.initialise();
		for (int i = 0; i < 5; i++)
			table.playGame();

		// monitor end

		long endTime = threadMXBean.getCurrentThreadCpuTime();

		double actual = new Double((endTime - startTime)) / nanoToSec;

		assertEquals(avg, actual, delta);
	}
}
