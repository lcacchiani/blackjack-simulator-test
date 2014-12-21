package com.luca.blackjack.test.unit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.luca.blackjack.Result;
import com.luca.blackjack.card.Card;
import com.luca.blackjack.card.Deck;
import com.luca.blackjack.game.Rules;
import com.luca.blackjack.strategy.betting.BettingStrategy;
import com.luca.blackjack.strategy.game.PlayerGameStrategy;
import com.luca.blackjack.strategy.insurance.InsuranceStrategy;
import com.luca.blackjack.user.SimplePlayer;

@RunWith(Parameterized.class)
public class SimplePlayerPayoutInitial {

	private Mockery context = new JUnit4Mockery();

	SimplePlayer player;

	List<Result> results;
	double finalBalance;
	double bet;
	Rules rules;
	Deck deck;
	String name;
	double initialBalance;
	BettingStrategy bettingStrategy;
	InsuranceStrategy insuranceStrategy;
	PlayerGameStrategy gameStrategy;
	Card c1;
	Card c2;
	Card c3;
	Card c4;

	public SimplePlayerPayoutInitial(List<Result> results, double finalBalance) {
		this.results = results;
		this.finalBalance = finalBalance;
		this.bet = 10d;
		this.name = "Test";
		this.initialBalance = 100d;
		this.deck = context.mock(Deck.class);
		this.bettingStrategy = context.mock(BettingStrategy.class);
		this.insuranceStrategy = context.mock(InsuranceStrategy.class);
		this.gameStrategy = context.mock(PlayerGameStrategy.class);
		this.rules = context.mock(Rules.class);
		this.c1 = Card.ACE_OF_HEARTS;
		this.c2 = Card.ACE_OF_DIAMONDS;
		this.c3 = Card.NINE_OF_HEARTS;
		this.c4 = Card.TEN_OF_CLUBS;
	}

	@Before
	public final void setup() {
		context.checking(new Expectations() {
			{
				oneOf(bettingStrategy).getNextBet(initialBalance, null, rules);
				will(returnValue(bet));
				oneOf(deck).getCard();
				will(returnValue(c1));
				oneOf(deck).getCard();
				will(returnValue(c2));
			}
		});
		player = new SimplePlayer();
		player.setName(name);
		player.setBalance(initialBalance);
		player.setBettingStrategy(bettingStrategy);
		player.setInsuranceStrategy(insuranceStrategy);
		player.setGameStrategy(gameStrategy);
		player.initialise();
		player.bet(rules, bet, bet);
		player.hit(deck);
		player.hit(deck);
	}

	@After
	public final void tearDown() {
		player = null;
	}

	@Parameters
	public static Collection<Object[]> data() {

		List<Result> results0 = new ArrayList<Result>();
		results0.addAll(Arrays.asList(Result.SURRENDERED));
		double finalBalance0 = 95d;

		List<Result> results1 = new ArrayList<Result>();
		results1.addAll(Arrays.asList(Result.LOST_DEALER_BLACKJACK));
		double finalBalance1 = 90d;

		List<Result> results2 = new ArrayList<Result>();
		results2.addAll(Arrays.asList(Result.WON_BLACKJACK));
		double finalBalance2 = 115d;

		List<Result> results3 = new ArrayList<Result>();
		results3.addAll(Arrays.asList(Result.STANDOFF));
		double finalBalance3 = 100d;

		return Arrays.asList(new Object[][] { { results0, finalBalance0 },
				{ results1, finalBalance1 }, { results2, finalBalance2 },
				{ results3, finalBalance3 } });
	}

	@Test
	public final void initialPayout() {
		context.checking(new Expectations() {
			{
				oneOf(rules).getBlackjackPayoutValue();
				will(returnValue(1.5d));
			}
		});
		Result result = results.get(0);
		player.setResult(result, rules);

		double actual = player.getBalance();
		double expected = finalBalance;
		assertEquals(expected, actual, 0.001);
	}
}
