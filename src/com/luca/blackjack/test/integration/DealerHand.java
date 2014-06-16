package com.luca.blackjack.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.luca.blackjack.card.Card;
import com.luca.blackjack.card.HandUtils.Status;
import com.luca.blackjack.card.Hand;

@RunWith(Parameterized.class)
public class DealerHand {

	Hand hand;

	List<Card> cards;
	List<Object> results;

	public DealerHand(List<Card> inputs, List<Object> results) {
		this.cards = inputs;
		this.results = results;
	}

	@Before
	public final void setup() {
		hand = new com.luca.blackjack.card.DealerHand();
		hand.initialise();
		assertTrue(hand.getCards() != null);
		assertTrue(hand.getCards().isEmpty());
		assertTrue(hand.getStatus().equals(Status.ACTIVE));
		int noCards = 0;
		for (Card c : cards) {
			hand.addCard(c);
			assertEquals(++noCards, hand.getCards().size());
		}
	}

	@After
	public final void tearDown() {
		hand = null;
	}

	@SuppressWarnings("unchecked")
	@Parameters
	public static Collection<Object[]> data() {

		List<Card> input0 = new ArrayList<Card>();
		input0.addAll(Arrays.asList(Card.ACE_OF_HEARTS, Card.FOUR_OF_SPADES, Card.THREE_OF_CLUBS));
		List<Object> result0 = new ArrayList<Object>();
		result0.addAll(Arrays.asList(11, 4, 18, false, false));

		List<Card> input1 = new ArrayList<Card>();
		input1.addAll(Arrays.asList(Card.EIGHT_OF_CLUBS, Card.SIX_OF_DIAMONDS));
		List<Object> result1 = new ArrayList<Object>();
		result1.addAll(Arrays.asList(8, 6, 14, false, false));

		List<Card> input2 = new ArrayList<Card>();
		input2.addAll(Arrays.asList(Card.THREE_OF_HEARTS, Card.TWO_OF_SPADES, Card.EIGHT_OF_CLUBS, Card.FOUR_OF_DIAMONDS));
		List<Object> result2 = new ArrayList<Object>();
		result2.addAll(Arrays.asList(3, 2, 17, false, false));

		List<Card> input3 = new ArrayList<Card>();
		input3.addAll(Arrays.asList(Card.TEN_OF_CLUBS, Card.ACE_OF_DIAMONDS));
		List<Object> result3 = new ArrayList<Object>();
		result3.addAll(Arrays.asList(10, 11, 21, true, false));

		List<Card> input4 = new ArrayList<Card>();
		input4.addAll(Arrays.asList(Card.SEVEN_OF_HEARTS, Card.THREE_OF_SPADES, Card.SEVEN_OF_CLUBS, Card.SIX_OF_DIAMONDS));
		List<Object> result4 = new ArrayList<Object>();
		result4.addAll(Arrays.asList(7, 3, 23, false, true));

		List<Card> input5 = new ArrayList<Card>();
		input5.addAll(Arrays.asList(Card.SIX_OF_CLUBS, Card.EIGHT_OF_DIAMONDS, Card.TEN_OF_HEARTS, Card.TWO_OF_SPADES));
		List<Object> result5 = new ArrayList<Object>();
		result5.addAll(Arrays.asList(6, 8, 26, false, true));

		List<Card> input6 = new ArrayList<Card>();
		input6.addAll(Arrays.asList(Card.TWO_OF_HEARTS, Card.FIVE_OF_SPADES, Card.THREE_OF_CLUBS, //
				Card.FOUR_OF_DIAMONDS, Card.FIVE_OF_HEARTS));
		List<Object> result6 = new ArrayList<Object>();
		result6.addAll(Arrays.asList(2, 5, 19, false, false));

		List<Card> input7 = new ArrayList<Card>();
		input7.addAll(Arrays.asList(Card.FOUR_OF_CLUBS, Card.SEVEN_OF_DIAMONDS, Card.NINE_OF_HEARTS));
		List<Object> result7 = new ArrayList<Object>();
		result7.addAll(Arrays.asList(4, 7, 20, false, false));

		List<Card> input8 = new ArrayList<Card>();
		input8.addAll(Arrays.asList(Card.NINE_OF_HEARTS, Card.NINE_OF_SPADES, Card.TWO_OF_CLUBS, Card.TWO_OF_DIAMONDS));
		List<Object> result8 = new ArrayList<Object>();
		result8.addAll(Arrays.asList(9, 9, 22, false, true));

		List<Card> input9 = new ArrayList<Card>();
		input9.addAll(Arrays.asList(Card.FIVE_OF_CLUBS, Card.TEN_OF_DIAMONDS, Card.SIX_OF_HEARTS));
		List<Object> result9 = new ArrayList<Object>();
		result9.addAll(Arrays.asList(5, 10, 21, true, false));

		return Arrays.asList(new Object[][] { { input0, result0 }, { input1, result1 }, { input2, result2 }, { input3, result3 },
				{ input4, result4 }, { input5, result5 }, { input6, result6 }, { input7, result7 }, { input8, result8 }, { input9, result9 } });
	}

	@Test
	public final void getFirstCardValue() {
		int actual = hand.getFirstCard().getHighestValue();
		int expected = (Integer) results.get(0);
		assertEquals(expected, actual);
	}

	@Test
	public final void getSecondCardValue() {
		int actual = hand.getSecondCard().getHighestValue();
		int expected = (Integer) results.get(1);
		assertEquals(expected, actual);
	}

	@Test
	public final void getSumCardValues() {
		int actual = hand.getSumCardValue();
		int expected = (Integer) results.get(2);
		assertEquals(expected, actual);
	}

	@Test
	public final void isBlackJack() {
		boolean actual = hand.isBlackJack();
		boolean expected = (Boolean) results.get(3);
		assertEquals(expected, actual);
	}

	@Test
	public final void isBusted() {
		boolean actual = hand.isBusted();
		boolean expected = (Boolean) results.get(4);
		assertEquals(expected, actual);
	}
}