package com.luca.blackjack.test.unit;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.luca.blackjack.Result;
import com.luca.blackjack.card.ComplexHand;
import com.luca.blackjack.card.HandUtils.Status;
import com.luca.blackjack.game.Rules;
import com.luca.blackjack.strategy.betting.BettingStrategy;
import com.luca.blackjack.strategy.game.PlayerGameStrategy;
import com.luca.blackjack.strategy.insurance.InsuranceStrategy;
import com.luca.blackjack.user.Player;

@RunWith(JMock.class)
public class SimplePlayer {

	private Mockery context = new JUnit4Mockery();

	Player player;

	String name;
	double initialBalance;
	BettingStrategy bettingStrategy;
	PlayerGameStrategy gameStrategy;
	InsuranceStrategy insuranceStrategy;
	ComplexHand hand;
	Rules rules;

	public SimplePlayer() {
		this.name = "Player";
		this.initialBalance = 10;
		this.bettingStrategy = context.mock(BettingStrategy.class);
		this.gameStrategy = context.mock(PlayerGameStrategy.class);
		this.insuranceStrategy = context.mock(InsuranceStrategy.class);
		this.hand = context.mock(ComplexHand.class);
		this.rules = context.mock(Rules.class);
	}

	@Before
	public final void setup() {
		player = new com.luca.blackjack.user.SimplePlayer();
		player.setName(name);
		player.setBalance(initialBalance);
		player.setBettingStrategy(bettingStrategy);
		player.setGameStrategy(gameStrategy);
		player.setInsuranceStrategy(insuranceStrategy);
		player.setHand(hand);
		player.resetLastResults();
	}

	@After
	public final void tearDown() {
		player = null;
	}

	@Test(expected = IllegalArgumentException.class)
	public final void hitNoCard() {
		player.hit(null);
	}

	@Test(expected = IllegalStateException.class)
	public final void playOnlyWhenHandIsActive() {
		context.checking(new Expectations() {
			{
				oneOf(hand).getStatus();
				will(returnValue(Status.CLOSED));
				oneOf(hand).isSplit();
				will(returnValue(false));
			}
		});
		player.play(null, null);
	}
	
	@Test
	public final void isObjectInitialised() {
		context.checking(new Expectations() {
			{
				oneOf(hand).isInitialised();
				will(returnValue(true));
			}
		});
		assertEquals(true, player.isInitialised());
	}

	@Test
	public final void setResultBlackJack() {
		final double handBet = 5;
		final double payout = 3;
		context.checking(new Expectations() {
			{
				oneOf(hand).getBet();
				will(returnValue(handBet));
				oneOf(hand).getStatus();
				will(returnValue(Status.CLOSED));
				oneOf(rules).getBlackjackPayoutValue();
				will(returnValue(payout));
				oneOf(hand).setStatus(Status.EVALUATED);
			}
		});
		assertEquals(initialBalance, player.getBalance(), 0.001);
		assertEquals(null, player.getTopResult());

		player.setResult(Result.WON_BLACKJACK, rules);

		double balanceExpected = initialBalance + handBet * payout;
		assertEquals(balanceExpected, player.getBalance(), 0.001);
		assertEquals(Result.WON_BLACKJACK, player.getTopResult());
	}

	@Test
	public final void setResultDealerBustedOut() {
		final double handBet = 5;
		final double payout = 3;
		context.checking(new Expectations() {
			{
				oneOf(hand).getBet();
				will(returnValue(handBet));
				oneOf(hand).getStatus();
				will(returnValue(Status.CLOSED));
				oneOf(rules).getWinPayoutValue();
				will(returnValue(payout));
				oneOf(hand).setStatus(Status.EVALUATED);
			}
		});
		assertEquals(initialBalance, player.getBalance(), 0.001);
		assertEquals(null, player.getTopResult());

		player.setResult(Result.WON_DEALER_BUSTED_OUT, rules);

		double balanceExpected = initialBalance + handBet * payout;
		assertEquals(balanceExpected, player.getBalance(), 0.001);
		assertEquals(Result.WON_DEALER_BUSTED_OUT, player.getTopResult());
	}

	@Test
	public final void setResultHigherScore() {
		final double handBet = 5;
		final double payout = 3;
		context.checking(new Expectations() {
			{
				oneOf(hand).getBet();
				will(returnValue(handBet));
				oneOf(hand).getStatus();
				will(returnValue(Status.CLOSED));
				oneOf(rules).getWinPayoutValue();
				will(returnValue(payout));
				oneOf(hand).setStatus(Status.EVALUATED);
			}
		});
		assertEquals(initialBalance, player.getBalance(), 0.001);
		assertEquals(null, player.getTopResult());

		player.setResult(Result.WON_HIGHER_SCORE, rules);

		double balanceExpected = initialBalance + handBet * payout;
		assertEquals(balanceExpected, player.getBalance(), 0.001);
		assertEquals(Result.WON_HIGHER_SCORE, player.getTopResult());
	}

	@Test
	public final void setResultPush() {
		final double handBet = 5;
		context.checking(new Expectations() {
			{
				oneOf(hand).getBet();
				will(returnValue(handBet));
				oneOf(hand).getStatus();
				will(returnValue(Status.CLOSED));
				oneOf(hand).setStatus(Status.EVALUATED);
			}
		});
		assertEquals(initialBalance, player.getBalance(), 0.001);
		assertEquals(null, player.getTopResult());

		player.setResult(Result.PUSH, rules);

		double balanceExpected = initialBalance + handBet;
		assertEquals(balanceExpected, player.getBalance(), 0.001);
		assertEquals(Result.PUSH, player.getTopResult());
	}

	@Test
	public final void setResultBustedOut() {
		context.checking(new Expectations() {
			{
				oneOf(hand).getStatus();
				will(returnValue(Status.CLOSED));
				oneOf(hand).setStatus(Status.EVALUATED);
			}
		});
		assertEquals(initialBalance, player.getBalance(), 0.001);
		assertEquals(null, player.getTopResult());

		player.setResult(Result.LOST_BUSTED_OUT, rules);

		assertEquals(initialBalance, player.getBalance(), 0.001);
		assertEquals(Result.LOST_BUSTED_OUT, player.getTopResult());
	}

	@Test
	public final void setResultDealerBlackJack() {
		context.checking(new Expectations() {
			{
				oneOf(hand).getStatus();
				will(returnValue(Status.ACTIVE));
				oneOf(hand).setStatus(Status.EVALUATED);
			}
		});
		assertEquals(initialBalance, player.getBalance(), 0.001);
		assertEquals(null, player.getTopResult());

		player.setResult(Result.LOST_DEALER_BLACKJACK, rules);

		assertEquals(initialBalance, player.getBalance(), 0.001);
		assertEquals(Result.LOST_DEALER_BLACKJACK, player.getTopResult());
	}

	@Test
	public final void setResultLowerScore() {
		context.checking(new Expectations() {
			{
				oneOf(hand).getStatus();
				will(returnValue(Status.CLOSED));
				oneOf(hand).setStatus(Status.EVALUATED);
			}
		});
		assertEquals(initialBalance, player.getBalance(), 0.001);
		assertEquals(null, player.getTopResult());

		player.setResult(Result.LOST_LOWER_SCORE, rules);

		assertEquals(initialBalance, player.getBalance(), 0.001);
		assertEquals(Result.LOST_LOWER_SCORE, player.getTopResult());
	}

	@Test
	public final void getTopResultLostLowerScore() {
		context.checking(new Expectations() {
			{
				allowing(hand).getStatus();
				will(returnValue(Status.CLOSED));
				allowing(hand).setStatus(Status.EVALUATED);
			}
		});
		assertEquals(null, player.getTopResult());

		player.setResult(Result.LOST_LOWER_SCORE, rules);
		assertEquals(Result.LOST_LOWER_SCORE, player.getTopResult());
	}
	
	@Test
	public final void getTopResultLostDealerBlackjack() {
		context.checking(new Expectations() {
			{
				allowing(hand).getStatus();
				will(returnValue(Status.ACTIVE));
				allowing(hand).setStatus(Status.EVALUATED);
			}
		});
		assertEquals(null, player.getTopResult());

		player.setResult(Result.LOST_DEALER_BLACKJACK, rules);
		assertEquals(Result.LOST_DEALER_BLACKJACK, player.getTopResult());
	}
	
	@Test
	public final void bet() {
		final double bet = 1d;
		assertEquals(initialBalance, player.getBalance(), 0.001);
		context.checking(new Expectations() {
			{
				oneOf(bettingStrategy).getNextBet(initialBalance, null, rules);
				will(returnValue(bet));
				oneOf(hand).setBet(bet);
			}
		});
		double actual = player.bet(rules, 0, 1);
		assertEquals(initialBalance - bet, player.getBalance(), 0.001);
		assertEquals(bet, actual, 0.001);
	}
}
