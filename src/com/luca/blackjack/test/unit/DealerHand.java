package com.luca.blackjack.test.unit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.luca.blackjack.card.Hand;

public class DealerHand {

	Hand hand;

	public DealerHand() {

	}

	@Before
	public final void setup() {
		hand = new com.luca.blackjack.card.DealerHand();
		assertNull(hand.getCards());
	}

	@After
	public final void tearDown() {
		hand = null;
	}
	
	@Test(expected = IllegalStateException.class)
	public final void getSumCardValueCardsNull() {
		hand.getSumCardValue();
	}

	@Test(expected = IllegalStateException.class)
	public final void getSumCardValueCardsEmpty() {
		hand.initialise();
		hand.getSumCardValue();
	}

	@Test
	public final void initialise() {
		hand.initialise();
		assertNotNull(hand.getCards());
		assertTrue(hand.getCards().isEmpty());
	}

	@Test
	public final void isInitialised() {
		hand.initialise();
		assertTrue(hand.isInitialised());
		assertNotNull(hand.getCards());
		assertNotNull(hand.getStatus());
	}
}
