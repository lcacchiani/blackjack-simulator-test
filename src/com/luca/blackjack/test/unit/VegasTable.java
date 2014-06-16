package com.luca.blackjack.test.unit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.luca.blackjack.card.Deck;
import com.luca.blackjack.game.Rules;
import com.luca.blackjack.game.Table;
import com.luca.blackjack.user.Dealer;
import com.luca.blackjack.user.Player;

@RunWith(JMock.class)
public class VegasTable {

	private Mockery context = new JUnit4Mockery();

	Table table;

	double minimumBet;
	double maximumBet;
	String name;
	Rules rules;
	Dealer dealer;
	Deck deck;
	Player player;
	List<Player> players;

	public VegasTable() {
		this.minimumBet = 1;
		this.maximumBet = 2;
		this.name = "Help";
		this.rules = context.mock(Rules.class);
		this.dealer = context.mock(Dealer.class);
		this.deck = context.mock(Deck.class);
		this.player = context.mock(Player.class, "Generic Player");
		this.players = new ArrayList<Player>();
		this.players.add(player);		
	}

	@Before
	public final void setup() {
		table = new com.luca.blackjack.game.VegasTable();
		table.setMinimumBet(minimumBet);
		table.setMaximumBet(maximumBet);
		table.setName(name);
		table.setRules(rules);
		table.setDealer(dealer);
		table.setDeck(deck);
		table.setPlayers(players);
	}

	@After
	public final void tearDown() {
		table = null;
	}

	@Test(expected = IllegalStateException.class)
	public final void addPlayer() {
		Player player1 = context.mock(Player.class, "Player 1");
		table.removePlayer(player);
		assertEquals(0, table.getPlayers().size());
		table.addPlayer(player1);
		table.addPlayer(player1);
	}

	@Test(expected = IllegalStateException.class)
	public final void removePlayer() {
		table.removePlayer(player);
		Player player1 = context.mock(Player.class, "Player 1");
		assertEquals(0, table.getPlayers().size());
		table.removePlayer(player1);
	}
	
	@Test
	public final void isObjectInitialised() {
		context.checking(new Expectations() {
			{
				oneOf(rules).isInitialised();
				will(returnValue(true));
				oneOf(deck).isInitialised();
				will(returnValue(true));
			}
		});
		assertEquals(true, table.isInitialised());
	}
	

	@Test
	public final void getPlayers() {
		Player player1 = context.mock(Player.class, "Player 1");
		Player player2 = context.mock(Player.class, "Player 2");
		table.removePlayer(player);
		assertEquals(0, table.getPlayers().size());
		table.addPlayer(player1);
		assertEquals(1, table.getPlayers().size());
		table.removePlayer(player1);
		assertEquals(0, table.getPlayers().size());
		table.addPlayer(player1);
		table.addPlayer(player2);
		assertEquals(2, table.getPlayers().size());
		assertEquals(player1, table.getPlayers().get(0));
		assertEquals(player2, table.getPlayers().get(1));
	}

}
