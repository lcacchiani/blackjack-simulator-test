package com.luca.blackjack.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.luca.blackjack.report.PlayerReport;
import com.luca.blackjack.report.Record;
import com.luca.blackjack.report.Report;
import com.luca.blackjack.user.Player;

@RunWith(JMock.class)
public class StandardReport {

	private Mockery context = new JUnit4Mockery();

	Report report;
	Record record;
	Player player;

	public StandardReport() {
		record = context.mock(Record.class);
		player = context.mock(Player.class);
	}

	@Before
	public final void setup() {
		report = new com.luca.blackjack.report.StandardReport();
	}

	@After
	public final void tearDown() {
		report = null;
	}

	@Test
	public final void initialisePlayerReport() {
		final String name = "player";
		final double balance = 1d;
		context.checking(new Expectations() {
			{
				exactly(2).of(player).getName();
				will(returnValue(name));
				oneOf(player).getBalance();
				will(returnValue(balance));
			}
		});
		report.initialisePlayerReport(player);
		List<PlayerReport> reports = report.getPlayerReports();
		assertFalse(reports.isEmpty());
		assertEquals(1, reports.size());
		PlayerReport report = reports.get(0);
		assertEquals(name, report.getName());
		assertEquals(balance, report.getInitialBalance(), 0.001);
	}
	
	@Test
	public final void finalisePlayerReport() {
		final String name = "player";
		final double initialBalance = 1d;
		final double finalBalance = 2d;
		final int topUpNo = 3;
		context.checking(new Expectations() {
			{
				exactly(4).of(player).getName();
				will(returnValue(name));
				oneOf(player).getBalance();
				will(returnValue(initialBalance));
				oneOf(player).getBalance();
				will(returnValue(finalBalance));
				oneOf(player).getTopUpNo();
				will(returnValue(topUpNo));
			}
		});
		report.initialisePlayerReport(player);
		report.finalisePlayerReport(player);
		List<PlayerReport> reports = report.getPlayerReports();
		assertFalse(reports.isEmpty());
		assertEquals(1, reports.size());
		PlayerReport report = reports.get(0);
		assertEquals(name, report.getName());
		assertEquals(initialBalance, report.getInitialBalance(), 0.001);
		assertEquals(finalBalance, report.getFinalBalance(), 0.001);
		assertEquals(topUpNo, report.getTopUpNo());
	}
}
