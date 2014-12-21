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
import com.luca.blackjack.strategy.game.Move;
import com.luca.blackjack.strategy.game.PlayerGameStrategy;
import com.luca.blackjack.strategy.insurance.InsuranceStrategy;
import com.luca.blackjack.user.SimplePlayer;

@RunWith(Parameterized.class)
public class SimplePlayerPayoutFinal {

	private Mockery context = new JUnit4Mockery();

	SimplePlayer player;

	List<Result> results;
	List<Double> finalBalances;
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

	public SimplePlayerPayoutFinal(List<Result> results,
			List<Double> finalBalances) {
		this.results = results;
		this.finalBalances = finalBalances;
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

		List<Result> results2 = new ArrayList<Result>();
		results2.addAll(Arrays.asList(Result.LOST_LOWER_SCORE));
		List<Double> finalBalances2 = new ArrayList<Double>();
		finalBalances2.addAll(Arrays.asList(90d, 80d, 95d));

		List<Result> results3 = new ArrayList<Result>();
		results3.addAll(Arrays.asList(Result.LOST_BUSTED_OUT));
		List<Double> finalBalances3 = new ArrayList<Double>();
		finalBalances3.addAll(Arrays.asList(90d, 80d, 95d));

		List<Result> results4 = new ArrayList<Result>();
		results4.addAll(Arrays.asList(Result.PUSH));
		List<Double> finalBalances4 = new ArrayList<Double>();
		finalBalances4.addAll(Arrays.asList(100d, 100d, 100d));

		List<Result> results5 = new ArrayList<Result>();
		results5.addAll(Arrays.asList(Result.WON_HIGHER_SCORE));
		List<Double> finalBalances5 = new ArrayList<Double>();
		finalBalances5.addAll(Arrays.asList(110d, 120d, 105d));

		List<Result> results6 = new ArrayList<Result>();
		results6.addAll(Arrays.asList(Result.WON_DEALER_BUSTED_OUT));
		List<Double> finalBalances6 = new ArrayList<Double>();
		finalBalances6.addAll(Arrays.asList(110d, 120d, 105d));

		return Arrays.asList(new Object[][] { { results2, finalBalances2 },
				{ results3, finalBalances3 }, { results4, finalBalances4 },
				{ results5, finalBalances5 }, { results6, finalBalances6 } });
	}

	@Test
	public final void stand() {
		context.checking(new Expectations() {
			{
				oneOf(rules).getWinPayoutValue();
				will(returnValue(1d));
			}
		});
		player.play(Move.STAND, deck);
		Result result = results.get(0);
		player.setResult(result, rules);

		double actual = player.getBalance();
		double expected = finalBalances.get(0);
		assertEquals(expected, actual, 0.001);
	}

	@Test
	public final void doubleAndStand() {
		context.checking(new Expectations() {
			{
				oneOf(bettingStrategy).getNextBet(initialBalance, null, rules);
				will(returnValue(bet));
				oneOf(deck).getCard();
				will(returnValue(c3));
				oneOf(rules).getWinPayoutValue();
				will(returnValue(1d));
			}
		});
		player.play(Move.DOUBLE, deck);
		player.play(Move.STAND, deck);
		Result result = results.get(0);
		player.setResult(result, rules);

		double actual = player.getBalance();
		double expected = finalBalances.get(1);
		assertEquals(expected, actual, 0.001);
	}

	@Test
	public final void hitAndStand() {
		context.checking(new Expectations() {
			{
				oneOf(rules).getWinPayoutValue();
				will(returnValue(1d));
				oneOf(deck).getCard();
				will(returnValue(c3));
			}
		});
		player.play(Move.HIT, deck);
		player.play(Move.STAND, deck);
		Result result = results.get(0);
		player.setResult(result, rules);

		double actual = player.getBalance();
		double expected = finalBalances.get(0);
		assertEquals(expected, actual, 0.001);
	}

	@Test
	public final void split() {
		context.checking(new Expectations() {
			{
				oneOf(deck).getCard();
				will(returnValue(c3));
				oneOf(deck).getCard();
				will(returnValue(c4));
				oneOf(rules).getWinPayoutValue();
				will(returnValue(1d));
			}
		});
		player.play(Move.SPLIT, deck);
		player.play(Move.STAND, deck);
		player.play(Move.STAND, deck);
		player.setResult(Result.PUSH, rules);

		Result result = results.get(0);
		player.setResult(result, rules);

		double actual = player.getBalance();
		double expected = finalBalances.get(2);
		assertEquals(expected, actual, 0.001);
	}
}
