package com.luca.blackjack.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.luca.blackjack.card.ComplexHand;

public class PlayerHand {

	ComplexHand hand;

	public PlayerHand() {

	}

	@Before
	public final void setup() {
		hand = new com.luca.blackjack.card.PlayerHand();
		assertTrue(!hand.isInitialised());
		hand.initialise();
		assertTrue(hand.isInitialised());
	}

	@After
	public final void tearDown() {
		hand = null;
	}

	@Test(expected = IllegalStateException.class)
	public final void isSplitSplitNull() {
		ComplexHand hand = new com.luca.blackjack.card.PlayerHand();
		hand.isSplit();
	}

	@Test(expected = IllegalStateException.class)
	public final void isInsuredInsuredNull() {
		ComplexHand hand = new com.luca.blackjack.card.PlayerHand();
		hand.isInsured();
	}

	@Test(expected = IllegalStateException.class)
	public final void setInsuranceHandNotActive() {
		ComplexHand hand = new com.luca.blackjack.card.PlayerHand();
		hand.initialise();
		hand.setInsurance(123);
	}

	@Test(expected = IllegalStateException.class)
	public final void isRootRootNull() {
		ComplexHand hand = new com.luca.blackjack.card.PlayerHand();
		hand.isRoot();
	}

	@Test(expected = IllegalStateException.class)
	public final void splitHandNotActive() {
		ComplexHand hand = new com.luca.blackjack.card.PlayerHand();
		hand.initialise();
		hand.split();
	}

	@Test(expected = IllegalStateException.class)
	public final void getSplitHandsNoSplit() {
		hand.getSplitHands();
	}

	@Test(expected = IllegalStateException.class)
	public final void isDoubleDoubleNull() {
		ComplexHand hand = new com.luca.blackjack.card.PlayerHand();
		hand.isDouble();
	}

	@Test(expected = IllegalArgumentException.class)
	public final void setBetEqualTo0() {
		double bet = 0;
		ComplexHand hand = new com.luca.blackjack.card.PlayerHand();
		hand.initialise();
		hand.setBet(bet);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void setBetLowerThan0() {
		double bet = -1;
		ComplexHand hand = new com.luca.blackjack.card.PlayerHand();
		hand.initialise();
		hand.setBet(bet);
	}
	
	@Test
	public final void setGetDouble() {
		double bet = 2;
		ComplexHand hand = new com.luca.blackjack.card.PlayerHand();
		hand.initialise();
		hand.setBet(bet);
		hand.doubleBet();
		assertTrue(hand.isDouble());
		assertEquals(bet * 2, hand.getBet(), 0.001);
	}

	@Test
	public final void setGetBet() {
		double bet = 2;
		ComplexHand hand = new com.luca.blackjack.card.PlayerHand();
		hand.initialise();
		hand.setBet(bet);
		assertFalse(hand.isDouble());
		assertEquals(bet, hand.getBet(), 0.001);
	}

	@Test
	public final void initialise() {
		ComplexHand hand = new com.luca.blackjack.card.PlayerHand();
		hand.initialise();
		assertNotNull(hand.getCards());
		assertTrue(hand.getCards().isEmpty());
		assertTrue(hand.isRoot());
		assertFalse(hand.isInsured());
		assertFalse(hand.isSplit());
	}

	@Test
	public final void isInitialised() {
		assertTrue(hand.isInitialised());
		assertNotNull(hand.getCards());
		assertNotNull(hand.getStatus());
		assertNotNull(hand.isRoot());
		assertNotNull(hand.isInsured());
		assertNotNull(hand.isSplit());
	}

	@Test(expected = IllegalStateException.class)
	public final void getSumCardValueCardsNull() {
		hand.getSumCardValue();
	}
}
