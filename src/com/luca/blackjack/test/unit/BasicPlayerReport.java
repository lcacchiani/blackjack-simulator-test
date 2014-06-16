package com.luca.blackjack.test.unit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.luca.blackjack.report.PlayerReport;

public class BasicPlayerReport {

	PlayerReport report;

	public BasicPlayerReport() {
	}

	@Before
	public final void setup() {
		report = new com.luca.blackjack.report.BasicPlayerReport();
	}

	@After
	public final void tearDown() {
		report = null;
	}

	@Test(expected = IllegalArgumentException.class)
	public final void setNameNoName() {
		report.setName(null);
	}

	@Test
	public final void setGetName() {
		String name = "report";
		report.setName(name);
		assertEquals(name, report.getName());
	}

	@Test(expected = IllegalStateException.class)
	public final void getInitialBalanceNull() {
		report.getInitialBalance();
	}

	@Test
	public final void setGetInitialBalance() {
		double balance = 1d;
		report.setInitialBalance(balance);
		assertEquals(balance, report.getInitialBalance(), 0.001);
	}
	
	@Test(expected = IllegalStateException.class)
	public final void getFinalBalanceNull() {
		report.getFinalBalance();
	}

	@Test
	public final void setGetFinalBalance() {
		double balance = 1d;
		report.setFinalBalance(balance);
		assertEquals(balance, report.getFinalBalance(), 0.001);
	}
	
	@Test(expected = IllegalStateException.class)
	public final void getTopUpNoNull() {
		report.getTopUpNo();
	}

	@Test
	public final void setGetTopUpNo() {
		int topUpNo = 1;
		report.setTopUpNo(topUpNo);
		assertEquals(topUpNo, report.getTopUpNo());
	}
}
