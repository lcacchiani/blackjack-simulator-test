package com.luca.blackjack.test.unit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.luca.blackjack.Blackjack;
import com.luca.blackjack.game.Table;

public class StandardBlackjackGame {

	Blackjack blackjack;

	public StandardBlackjackGame() {
	}

	@Before
	public final void setup() {
		blackjack = new com.luca.blackjack.StandardBlackjackGame();
	}

	@After
	public final void tearDown() {
		blackjack = null;
	}

	@Test(expected = IllegalArgumentException.class)
	public final void setVersionNoVersion() {
		blackjack.setVersion(null);
	}
	
	@Test
	public final void setGetName() {
		String version = "version";
		blackjack.setVersion(version);
		assertEquals(version, blackjack.getVersion());
	}

	@Test(expected = IllegalArgumentException.class)
	public final void setTablesNoTables() {
		blackjack.setTables(null);
	}
	
	@Test
	public final void setGetTables() {
		List<Table> tables = new ArrayList<Table>(); 
		blackjack.setTables(tables);
		assertEquals(tables, blackjack.getTables());
	}
}
