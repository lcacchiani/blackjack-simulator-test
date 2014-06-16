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

import com.luca.blackjack.card.Hand;
import com.luca.blackjack.card.HandUtils.Status;
import com.luca.blackjack.strategy.game.DealerGameStrategy;
import com.luca.blackjack.user.Dealer;

@RunWith(JMock.class)
public class SimpleDealer {

	private Mockery context = new JUnit4Mockery();

	Dealer dealer;

	String name;
	DealerGameStrategy gameStrategy;
	Hand hand;

	public SimpleDealer() {
		this.name = "Dealer";
		this.gameStrategy = context.mock(DealerGameStrategy.class);
		this.hand = context.mock(Hand.class);
	}

	@Before
	public final void setup() {
		dealer = new com.luca.blackjack.user.SimpleDealer();
		dealer.setName(name);
		dealer.setGameStrategy(gameStrategy);
		dealer.setHand(hand);
	}

	@After
	public final void tearDown() {
		dealer = null;
	}
	
	@Test 
	public final void isObjectInitialised() {
		context.checking(new Expectations() {
			{
				oneOf(hand).isInitialised();
				will(returnValue(true));
			}
		});
		assertEquals(true, dealer.isInitialised());
	}

	@Test
	public final void hasActiveHand() {
		context.checking(new Expectations() {
			{
				oneOf(hand).getStatus();
				will(returnValue(Status.CLOSED));
				oneOf(hand).getStatus();
				will(returnValue(Status.ACTIVE));
			}
		});
		assertEquals(false, dealer.hasActiveHand());
		assertEquals(true, dealer.hasActiveHand());
	}

	@Test
	public final void hasClosedHand() {
		context.checking(new Expectations() {
			{
				oneOf(hand).getStatus();
				will(returnValue(Status.ACTIVE));
				oneOf(hand).getStatus();
				will(returnValue(Status.CLOSED));
			}
		});
		assertEquals(false, dealer.hasClosedHand());
		assertEquals(true, dealer.hasClosedHand());
	}

	@Test(expected = IllegalArgumentException.class)
	public final void hitNoCard() {
		dealer.hit(null);
	}

	@Test(expected = IllegalStateException.class)
	public final void playOnlyWhenHandIsActive() {
		context.checking(new Expectations() {
			{
				oneOf(hand).getStatus();
				will(returnValue(Status.CLOSED));
			}
		});
		dealer.play(null, null);
	}
}
