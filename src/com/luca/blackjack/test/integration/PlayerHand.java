package com.luca.blackjack.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.luca.blackjack.card.Card;
import com.luca.blackjack.card.ComplexHand;
import com.luca.blackjack.card.HandUtils.Status;

@RunWith(Parameterized.class)
public class PlayerHand {

	ComplexHand hand;

	List<Card> cards;
	double bet;
	double insurance;
	List<Object> results;

	@SuppressWarnings("unchecked")
	public PlayerHand(List<Object> inputs, List<Object> results) {
		this.cards = (List<Card>) inputs.get(0);
		this.bet = (Integer) inputs.get(1);
		this.insurance = (Integer) inputs.get(2);
		this.results = results;
	}

	@Before
	public final void setup() {
		hand = new com.luca.blackjack.card.PlayerHand();
		hand.initialise();
		assertTrue(hand.getCards() != null);
		assertTrue(hand.getCards().isEmpty());
		assertTrue(hand.getStatus().equals(Status.INITIALISED));
		assertTrue(hand.isRoot());
		assertTrue(!hand.isSplit());
		assertTrue(!hand.isDouble());
		assertTrue(!hand.isInsured());
	}

	@After
	public final void tearDown() {
		hand = null;
	}

	@Parameters
	public static Collection<Object[]> data() {

		ArrayList<Object> input0 = new ArrayList<Object>();
		List<Card> card0 = new ArrayList<Card>();
		card0.addAll(Arrays.asList(Card.ACE_OF_HEARTS, Card.FOUR_OF_SPADES,
				Card.THREE_OF_CLUBS, Card.ACE_OF_DIAMONDS));
		input0.addAll(Arrays.asList(card0, 100, 10));
		List<Object> result0 = new ArrayList<Object>();
		result0.addAll(Arrays.asList(19, 100, 200, 10));

		ArrayList<Object> input1 = new ArrayList<Object>();
		List<Card> card1 = new ArrayList<Card>();
		card1.addAll(Arrays.asList(Card.EIGHT_OF_CLUBS, Card.SIX_OF_DIAMONDS));
		input1.addAll(Arrays.asList(card1, 100, 10));
		List<Object> result1 = new ArrayList<Object>();
		result1.addAll(Arrays.asList(14, 100, 200, 10));

		ArrayList<Object> input2 = new ArrayList<Object>();
		List<Card> card2 = new ArrayList<Card>();
		card2.addAll(Arrays.asList(Card.THREE_OF_HEARTS, Card.TWO_OF_SPADES,
				Card.EIGHT_OF_CLUBS, Card.FOUR_OF_DIAMONDS));
		input2.addAll(Arrays.asList(card2, 52, 102));
		List<Object> result2 = new ArrayList<Object>();
		result2.addAll(Arrays.asList(17, 52, 104, 102));

		ArrayList<Object> input3 = new ArrayList<Object>();
		List<Card> card3 = new ArrayList<Card>();
		card3.addAll(Arrays.asList(Card.TEN_OF_CLUBS, Card.ACE_OF_DIAMONDS));
		input3.addAll(Arrays.asList(card3, 100, 10));
		List<Object> result3 = new ArrayList<Object>();
		result3.addAll(Arrays.asList(21, 100, 200, 10));

		ArrayList<Object> input4 = new ArrayList<Object>();
		List<Card> card4 = new ArrayList<Card>();
		card4.addAll(Arrays.asList(Card.SEVEN_OF_HEARTS, Card.THREE_OF_SPADES,
				Card.SEVEN_OF_CLUBS, Card.SIX_OF_DIAMONDS));
		input4.addAll(Arrays.asList(card4, 100, 10));
		List<Object> result4 = new ArrayList<Object>();
		result4.addAll(Arrays.asList(23, 100, 200, 10));

		ArrayList<Object> input5 = new ArrayList<Object>();
		List<Card> card5 = new ArrayList<Card>();
		card5.addAll(Arrays.asList(Card.SIX_OF_CLUBS, Card.EIGHT_OF_DIAMONDS,
				Card.TEN_OF_HEARTS, Card.TWO_OF_SPADES));
		input5.addAll(Arrays.asList(card5, 100, 10));
		List<Object> result5 = new ArrayList<Object>();
		result5.addAll(Arrays.asList(26, 100, 200, 10));

		ArrayList<Object> input6 = new ArrayList<Object>();
		List<Card> card6 = new ArrayList<Card>();
		card6.addAll(Arrays.asList(Card.TWO_OF_HEARTS, Card.FIVE_OF_SPADES,
				Card.THREE_OF_CLUBS, Card.FOUR_OF_DIAMONDS,
				Card.FIVE_OF_HEARTS, Card.ACE_OF_CLUBS));
		input6.addAll(Arrays.asList(card6, 100, 10));
		List<Object> result6 = new ArrayList<Object>();
		result6.addAll(Arrays.asList(20, 100, 200, 10));

		ArrayList<Object> input7 = new ArrayList<Object>();
		List<Card> card7 = new ArrayList<Card>();
		card7.addAll(Arrays.asList(Card.ACE_OF_CLUBS, Card.ACE_OF_DIAMONDS,
				Card.ACE_OF_HEARTS));
		input7.addAll(Arrays.asList(card7, 100, 10));
		List<Object> result7 = new ArrayList<Object>();
		result7.addAll(Arrays.asList(13, 100, 200, 10));

		ArrayList<Object> input8 = new ArrayList<Object>();
		List<Card> card8 = new ArrayList<Card>();
		card8.addAll(Arrays.asList(Card.NINE_OF_HEARTS, Card.NINE_OF_SPADES,
				Card.TWO_OF_CLUBS, Card.TWO_OF_DIAMONDS));
		input8.addAll(Arrays.asList(card8, 100, 10));
		List<Object> result8 = new ArrayList<Object>();
		result8.addAll(Arrays.asList(22, 100, 200, 10));

		ArrayList<Object> input9 = new ArrayList<Object>();
		List<Card> card9 = new ArrayList<Card>();
		card9.addAll(Arrays.asList(Card.FOUR_OF_CLUBS, Card.TEN_OF_DIAMONDS,
				Card.SIX_OF_HEARTS, Card.ACE_OF_SPADES));
		input9.addAll(Arrays.asList(card9, 100, 10));
		List<Object> result9 = new ArrayList<Object>();
		result9.addAll(Arrays.asList(21, 100, 200, 10));

		return Arrays.asList(new Object[][] { { input0, result0 },
				{ input1, result1 }, { input2, result2 }, { input3, result3 },
				{ input4, result4 }, { input5, result5 }, { input6, result6 },
				{ input7, result7 }, { input8, result8 }, { input9, result9 } });
	}

	@Test(expected = IllegalArgumentException.class)
	public final void setBetLowerThan0() {
		hand.setBet(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void setBetEqualTo0() {
		hand.setBet(0);
	}

	@Test(expected = IllegalStateException.class)
	public final void setBetTwice() {
		hand.setBet(1);
		hand.setBet(2);
	}

	@Test
	public final void getSumCardValues() {
		hand.setBet(bet);
		for (Card c : cards)
			hand.addCard(c);
		int actual = hand.getSumCardValue();
		int expected = (Integer) results.get(0);
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalStateException.class)
	public final void getSumCardValuesWithoutCards() {
		hand.getSumCardValue();
	}

	@Test
	public final void getBet() {
		hand.setBet(bet);
		assertEquals(false, hand.isDouble());
		double actual = hand.getBet();
		double expected = (Integer) results.get(1);
		assertEquals(expected, actual, 0.001);
	}

	@Test
	public final void getDoubleBet() {
		hand.setBet(bet);
		hand.doubleBet();
		assertEquals(true, hand.isDouble());
		double actual = hand.getBet();
		double expected = (Integer) results.get(2);
		assertEquals(expected, actual, 0.001);
	}

	@Test(expected = IllegalStateException.class)
	public final void doubleBetTwoTimes() {
		hand.setBet(bet);
		hand.doubleBet();
		hand.doubleBet();
	}

	@Test(expected = IllegalStateException.class)
	public final void insureWithWrongNoOfCard0() {
		hand.setInsurance(insurance);
	}

	@Test(expected = IllegalStateException.class)
	public final void insureWithWrongNoOfCard1() {
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.setInsurance(insurance);
	}

	@Test(expected = IllegalStateException.class)
	public final void insureWithWrongNoOfCard3() {
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.setInsurance(insurance);
	}

	@Test(expected = IllegalStateException.class)
	public final void getInsuranceWithoutBeingInsured() {
		hand.getInsurance();
	}

	@Test
	public final void getInsurance() {
		hand.setBet(bet);
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.setInsurance(insurance);
		assertEquals(true, hand.isInsured());
		double actual = hand.getInsurance();
		double expected = (Integer) results.get(3);
		assertEquals(expected, actual, 0.001);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void insureLowerThan0() {
		hand.setBet(bet);
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.setInsurance(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void insureEqualThan0() {
		hand.setBet(bet);
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.setInsurance(0);
	}

	@Test(expected = IllegalStateException.class)
	public final void insureTwoTimes() {
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.setInsurance(insurance);
		hand.setInsurance(insurance);
	}

	@Test(expected = IllegalStateException.class)
	public final void getSplitWhenNoSplit() {
		hand.getSplitHands();
	}

	@Test(expected = IllegalStateException.class)
	public final void splitWithWrongNoOfCard0() {
		hand.split();
	}

	@Test(expected = IllegalStateException.class)
	public final void splitWithWrongNoOfCard1() {
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.split();
	}

	@Test(expected = IllegalStateException.class)
	public final void splitWithWrongNoOfCard3() {
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.split();
	}

	@Test(expected = IllegalStateException.class)
	public final void splitWithDifferentCards() {
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.addCard(Card.ACE_OF_DIAMONDS);
		hand.split();
	}

	@Test
	public final void getSplitHands() {
		hand.setBet(bet);
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.split();
		assertTrue(hand.isSplit());
		Set<ComplexHand> actuals = hand.getSplitHands();
		assertTrue(actuals != null);
		assertEquals(2, actuals.size());
		for (ComplexHand actual : actuals) {
			assertTrue(actual.getCards() != null);
			assertTrue(actual.getCards().size() == 1);
			assertTrue(actual.getStatus().equals(Status.ACTIVE));
			assertEquals(Card.ACE_OF_CLUBS, actual.getCards().get(0));
			assertTrue(!actual.isRoot());
			assertTrue(!actual.isSplit());
			assertTrue(!actual.isDouble());
			assertTrue(!actual.isInsured());
			double actualBet = actual.getBet();
			double expectedBet = ((Integer) results.get(1)) / 2;
			assertEquals(expectedBet, actualBet, 0.001);
		}
		assertTrue(hand.getStatus().equals(Status.EVALUATED));
	}

	@Test(expected = IllegalStateException.class)
	public final void splitAndInsure() {
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.split();
		Set<ComplexHand> actuals = hand.getSplitHands();
		for (ComplexHand actual : actuals)
			actual.setInsurance(insurance);
	}

	@Test(expected = IllegalStateException.class)
	public final void splitAndGetSumCardValue() {
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.split();
		hand.getSumCardValue();
	}

	@Test(expected = IllegalStateException.class)
	public final void splitAndGetBet() {
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.split();
		hand.getBet();
	}

	@Test(expected = IllegalStateException.class)
	public final void splitAndDoubleBet() {
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.split();
		hand.doubleBet();
	}

	@Test(expected = IllegalStateException.class)
	public final void splitAndCheckIfDouble() {
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.addCard(Card.ACE_OF_CLUBS);
		hand.split();
		hand.isDouble();
	}
}