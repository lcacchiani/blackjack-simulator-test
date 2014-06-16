package com.luca.blackjack.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class Card {

	com.luca.blackjack.card.Card card;
	List<Object> results;

	public Card(com.luca.blackjack.card.Card card, List<Object> results) {
		this.card = card;
		this.results = results;
	}

	@Before
	public final void setup() {
		assertTrue(com.luca.blackjack.card.Card.values().length == 52);
	}

	// Each card is handled as a single independent parameter in order to
	// facilitate future improvements
	@Parameters
	public static Collection<Object[]> data() {

		com.luca.blackjack.card.Card card1 = com.luca.blackjack.card.Card.ACE_OF_SPADES;
		List<Object> result1 = new ArrayList<Object>();
		result1.addAll(Arrays.asList(11, 1, 11));

		com.luca.blackjack.card.Card card2 = com.luca.blackjack.card.Card.ACE_OF_HEARTS;
		List<Object> result2 = new ArrayList<Object>();
		result2.addAll(Arrays.asList(11, 1, 11));

		com.luca.blackjack.card.Card card3 = com.luca.blackjack.card.Card.ACE_OF_DIAMONDS;
		List<Object> result3 = new ArrayList<Object>();
		result3.addAll(Arrays.asList(11, 1, 11));

		com.luca.blackjack.card.Card card4 = com.luca.blackjack.card.Card.ACE_OF_CLUBS;
		List<Object> result4 = new ArrayList<Object>();
		result4.addAll(Arrays.asList(11, 1, 11));

		com.luca.blackjack.card.Card card5 = com.luca.blackjack.card.Card.KING_OF_SPADES;
		List<Object> result5 = new ArrayList<Object>();
		result5.addAll(Arrays.asList(10, 10, 10));

		com.luca.blackjack.card.Card card6 = com.luca.blackjack.card.Card.KING_OF_HEARTS;
		List<Object> result6 = new ArrayList<Object>();
		result6.addAll(Arrays.asList(10, 10, 10));

		com.luca.blackjack.card.Card card7 = com.luca.blackjack.card.Card.KING_OF_DIAMONDS;
		List<Object> result7 = new ArrayList<Object>();
		result7.addAll(Arrays.asList(10, 10, 10));

		com.luca.blackjack.card.Card card8 = com.luca.blackjack.card.Card.KING_OF_CLUBS;
		List<Object> result8 = new ArrayList<Object>();
		result8.addAll(Arrays.asList(10, 10, 10));

		com.luca.blackjack.card.Card card9 = com.luca.blackjack.card.Card.QUEEN_OF_SPADES;
		List<Object> result9 = new ArrayList<Object>();
		result9.addAll(Arrays.asList(10, 10, 10));

		com.luca.blackjack.card.Card card10 = com.luca.blackjack.card.Card.QUEEN_OF_HEARTS;
		List<Object> result10 = new ArrayList<Object>();
		result10.addAll(Arrays.asList(10, 10, 10));

		com.luca.blackjack.card.Card card11 = com.luca.blackjack.card.Card.QUEEN_OF_DIAMONDS;
		List<Object> result11 = new ArrayList<Object>();
		result11.addAll(Arrays.asList(10, 10, 10));

		com.luca.blackjack.card.Card card12 = com.luca.blackjack.card.Card.QUEEN_OF_CLUBS;
		List<Object> result12 = new ArrayList<Object>();
		result12.addAll(Arrays.asList(10, 10, 10));

		com.luca.blackjack.card.Card card13 = com.luca.blackjack.card.Card.JACK_OF_SPADES;
		List<Object> result13 = new ArrayList<Object>();
		result13.addAll(Arrays.asList(10, 10, 10));

		com.luca.blackjack.card.Card card14 = com.luca.blackjack.card.Card.JACK_OF_HEARTS;
		List<Object> result14 = new ArrayList<Object>();
		result14.addAll(Arrays.asList(10, 10, 10));

		com.luca.blackjack.card.Card card15 = com.luca.blackjack.card.Card.JACK_OF_DIAMONDS;
		List<Object> result15 = new ArrayList<Object>();
		result15.addAll(Arrays.asList(10, 10, 10));

		com.luca.blackjack.card.Card card16 = com.luca.blackjack.card.Card.JACK_OF_CLUBS;
		List<Object> result16 = new ArrayList<Object>();
		result16.addAll(Arrays.asList(10, 10, 10));

		com.luca.blackjack.card.Card card17 = com.luca.blackjack.card.Card.TEN_OF_SPADES;
		List<Object> result17 = new ArrayList<Object>();
		result17.addAll(Arrays.asList(10, 10, 10));

		com.luca.blackjack.card.Card card18 = com.luca.blackjack.card.Card.TEN_OF_HEARTS;
		List<Object> result18 = new ArrayList<Object>();
		result18.addAll(Arrays.asList(10, 10, 10));

		com.luca.blackjack.card.Card card19 = com.luca.blackjack.card.Card.TEN_OF_DIAMONDS;
		List<Object> result19 = new ArrayList<Object>();
		result19.addAll(Arrays.asList(10, 10, 10));

		com.luca.blackjack.card.Card card20 = com.luca.blackjack.card.Card.TEN_OF_CLUBS;
		List<Object> result20 = new ArrayList<Object>();
		result20.addAll(Arrays.asList(10, 10, 10));

		com.luca.blackjack.card.Card card21 = com.luca.blackjack.card.Card.NINE_OF_SPADES;
		List<Object> result21 = new ArrayList<Object>();
		result21.addAll(Arrays.asList(9, 9, 9));

		com.luca.blackjack.card.Card card22 = com.luca.blackjack.card.Card.NINE_OF_HEARTS;
		List<Object> result22 = new ArrayList<Object>();
		result22.addAll(Arrays.asList(9, 9, 9));

		com.luca.blackjack.card.Card card23 = com.luca.blackjack.card.Card.NINE_OF_DIAMONDS;
		List<Object> result23 = new ArrayList<Object>();
		result23.addAll(Arrays.asList(9, 9, 9));

		com.luca.blackjack.card.Card card24 = com.luca.blackjack.card.Card.NINE_OF_CLUBS;
		List<Object> result24 = new ArrayList<Object>();
		result24.addAll(Arrays.asList(9, 9, 9));

		com.luca.blackjack.card.Card card25 = com.luca.blackjack.card.Card.EIGHT_OF_SPADES;
		List<Object> result25 = new ArrayList<Object>();
		result25.addAll(Arrays.asList(8, 8, 8));

		com.luca.blackjack.card.Card card26 = com.luca.blackjack.card.Card.EIGHT_OF_HEARTS;
		List<Object> result26 = new ArrayList<Object>();
		result26.addAll(Arrays.asList(8, 8, 8));

		com.luca.blackjack.card.Card card27 = com.luca.blackjack.card.Card.EIGHT_OF_DIAMONDS;
		List<Object> result27 = new ArrayList<Object>();
		result27.addAll(Arrays.asList(8, 8, 8));

		com.luca.blackjack.card.Card card28 = com.luca.blackjack.card.Card.EIGHT_OF_CLUBS;
		List<Object> result28 = new ArrayList<Object>();
		result28.addAll(Arrays.asList(8, 8, 8));

		com.luca.blackjack.card.Card card29 = com.luca.blackjack.card.Card.SEVEN_OF_SPADES;
		List<Object> result29 = new ArrayList<Object>();
		result29.addAll(Arrays.asList(7, 7, 7));

		com.luca.blackjack.card.Card card30 = com.luca.blackjack.card.Card.SEVEN_OF_HEARTS;
		List<Object> result30 = new ArrayList<Object>();
		result30.addAll(Arrays.asList(7, 7, 7));

		com.luca.blackjack.card.Card card31 = com.luca.blackjack.card.Card.SEVEN_OF_DIAMONDS;
		List<Object> result31 = new ArrayList<Object>();
		result31.addAll(Arrays.asList(7, 7, 7));

		com.luca.blackjack.card.Card card32 = com.luca.blackjack.card.Card.SEVEN_OF_CLUBS;
		List<Object> result32 = new ArrayList<Object>();
		result32.addAll(Arrays.asList(7, 7, 7));

		com.luca.blackjack.card.Card card33 = com.luca.blackjack.card.Card.SIX_OF_SPADES;
		List<Object> result33 = new ArrayList<Object>();
		result33.addAll(Arrays.asList(6, 6, 6));

		com.luca.blackjack.card.Card card34 = com.luca.blackjack.card.Card.SIX_OF_HEARTS;
		List<Object> result34 = new ArrayList<Object>();
		result34.addAll(Arrays.asList(6, 6, 6));

		com.luca.blackjack.card.Card card35 = com.luca.blackjack.card.Card.SIX_OF_DIAMONDS;
		List<Object> result35 = new ArrayList<Object>();
		result35.addAll(Arrays.asList(6, 6, 6));

		com.luca.blackjack.card.Card card36 = com.luca.blackjack.card.Card.SIX_OF_CLUBS;
		List<Object> result36 = new ArrayList<Object>();
		result36.addAll(Arrays.asList(6, 6, 6));

		com.luca.blackjack.card.Card card37 = com.luca.blackjack.card.Card.FIVE_OF_SPADES;
		List<Object> result37 = new ArrayList<Object>();
		result37.addAll(Arrays.asList(5, 5, 5));

		com.luca.blackjack.card.Card card38 = com.luca.blackjack.card.Card.FIVE_OF_HEARTS;
		List<Object> result38 = new ArrayList<Object>();
		result38.addAll(Arrays.asList(5, 5, 5));

		com.luca.blackjack.card.Card card39 = com.luca.blackjack.card.Card.FIVE_OF_DIAMONDS;
		List<Object> result39 = new ArrayList<Object>();
		result39.addAll(Arrays.asList(5, 5, 5));

		com.luca.blackjack.card.Card card40 = com.luca.blackjack.card.Card.FIVE_OF_CLUBS;
		List<Object> result40 = new ArrayList<Object>();
		result40.addAll(Arrays.asList(5, 5, 5));

		com.luca.blackjack.card.Card card41 = com.luca.blackjack.card.Card.FOUR_OF_SPADES;
		List<Object> result41 = new ArrayList<Object>();
		result41.addAll(Arrays.asList(4, 4, 4));

		com.luca.blackjack.card.Card card42 = com.luca.blackjack.card.Card.FOUR_OF_HEARTS;
		List<Object> result42 = new ArrayList<Object>();
		result42.addAll(Arrays.asList(4, 4, 4));

		com.luca.blackjack.card.Card card43 = com.luca.blackjack.card.Card.FOUR_OF_DIAMONDS;
		List<Object> result43 = new ArrayList<Object>();
		result43.addAll(Arrays.asList(4, 4, 4));

		com.luca.blackjack.card.Card card44 = com.luca.blackjack.card.Card.FOUR_OF_CLUBS;
		List<Object> result44 = new ArrayList<Object>();
		result44.addAll(Arrays.asList(4, 4, 4));

		com.luca.blackjack.card.Card card45 = com.luca.blackjack.card.Card.THREE_OF_SPADES;
		List<Object> result45 = new ArrayList<Object>();
		result45.addAll(Arrays.asList(3, 3, 3));

		com.luca.blackjack.card.Card card46 = com.luca.blackjack.card.Card.THREE_OF_HEARTS;
		List<Object> result46 = new ArrayList<Object>();
		result46.addAll(Arrays.asList(3, 3, 3));

		com.luca.blackjack.card.Card card47 = com.luca.blackjack.card.Card.THREE_OF_DIAMONDS;
		List<Object> result47 = new ArrayList<Object>();
		result47.addAll(Arrays.asList(3, 3, 3));

		com.luca.blackjack.card.Card card48 = com.luca.blackjack.card.Card.THREE_OF_CLUBS;
		List<Object> result48 = new ArrayList<Object>();
		result48.addAll(Arrays.asList(3, 3, 3));

		com.luca.blackjack.card.Card card49 = com.luca.blackjack.card.Card.TWO_OF_SPADES;
		List<Object> result49 = new ArrayList<Object>();
		result49.addAll(Arrays.asList(2, 2, 2));

		com.luca.blackjack.card.Card card50 = com.luca.blackjack.card.Card.TWO_OF_HEARTS;
		List<Object> result50 = new ArrayList<Object>();
		result50.addAll(Arrays.asList(2, 2, 2));

		com.luca.blackjack.card.Card card51 = com.luca.blackjack.card.Card.TWO_OF_DIAMONDS;
		List<Object> result51 = new ArrayList<Object>();
		result51.addAll(Arrays.asList(2, 2, 2));

		com.luca.blackjack.card.Card card52 = com.luca.blackjack.card.Card.TWO_OF_CLUBS;
		List<Object> result52 = new ArrayList<Object>();
		result52.addAll(Arrays.asList(2, 2, 2));

		return Arrays.asList(new Object[][] { { card1, result1 }, { card2, result2 }, { card3, result3 }, { card4, result4 },
				{ card5, result5 }, { card6, result6 }, { card7, result7 }, { card8, result8 }, { card9, result9 }, { card10, result10 },
				{ card11, result11 }, { card12, result12 }, { card13, result13 }, { card14, result14 }, { card15, result15 },
				{ card16, result16 }, { card17, result17 }, { card18, result18 }, { card19, result19 }, { card20, result20 },
				{ card21, result21 }, { card22, result22 }, { card23, result23 }, { card24, result24 }, { card25, result25 },
				{ card26, result26 }, { card27, result27 }, { card28, result28 }, { card29, result29 }, { card30, result30 },
				{ card31, result31 }, { card32, result32 }, { card33, result33 }, { card34, result34 }, { card35, result35 },
				{ card36, result36 }, { card37, result37 }, { card38, result38 }, { card39, result39 }, { card40, result40 },
				{ card41, result41 }, { card42, result42 }, { card43, result43 }, { card44, result44 }, { card45, result45 },
				{ card46, result46 }, { card47, result47 }, { card48, result48 }, { card49, result49 }, { card50, result50 },
				{ card51, result51 }, { card52, result52 } });
	}

	@Test
	public final void getValues() {
		Set<Integer> actual = card.getValues();
		int expected1 = (Integer) results.get(0);
		int expected2 = (Integer) results.get(1);
		assertTrue(actual.contains(expected1));
		assertTrue(actual.contains(expected2));
		if (expected1 == expected2)
			assertTrue(actual.size() == 1);
		else
			assertTrue(actual.size() == 2);
	}

	@Test
	public final void getHighestValue() {
		int actual = card.getHighestValue();
		int expected = (Integer) results.get(2);
		assertEquals(expected, actual);
	}
	
	@Test
	public final void isAce() {
		boolean actual = card.isAce();
		boolean expected = (Integer) results.get(2) == 11 ? true : false;
		assertEquals(expected, actual);
	}
}