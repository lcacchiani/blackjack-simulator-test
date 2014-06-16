package com.luca.blackjack.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.*;

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
import com.luca.blackjack.card.Deck;

@RunWith(Parameterized.class)
public class StandardDeck {

	Deck deck;

	int deckNo;
	int seed;
	List<Object> results;

	public StandardDeck(List<Object> inputs, List<Object> results) {
		this.deckNo = (Integer) inputs.get(0);
		this.seed = (Integer) inputs.get(1);
		this.results = results;
	}

	@Before
	public final void setup() {
		deck = new com.luca.blackjack.card.StandardDeck();
		deck.setDeckNo(deckNo);
		deck.setSeed(seed);
		deck.initialise();
	}

	@After
	public final void tearDown() {
		deck = null;
	}

	@Parameters
	public static Collection<Object[]> data() {

		ArrayList<Object> input0 = new ArrayList<Object>();
		input0.addAll(Arrays.asList(8, 1982));
		List<Object> result0 = new ArrayList<Object>();
		result0.addAll(Arrays.asList(416));

		ArrayList<Object> input1 = new ArrayList<Object>();
		input1.addAll(Arrays.asList(7, -30));
		List<Object> result1 = new ArrayList<Object>();
		result1.addAll(Arrays.asList(364));

		ArrayList<Object> input2 = new ArrayList<Object>();
		input2.addAll(Arrays.asList(6, 32));
		List<Object> result2 = new ArrayList<Object>();
		result2.addAll(Arrays.asList(312));

		ArrayList<Object> input3 = new ArrayList<Object>();
		input3.addAll(Arrays.asList(5, -3));
		List<Object> result3 = new ArrayList<Object>();
		result3.addAll(Arrays.asList(260));

		ArrayList<Object> input4 = new ArrayList<Object>();
		input4.addAll(Arrays.asList(4, 6546546));
		List<Object> result4 = new ArrayList<Object>();
		result4.addAll(Arrays.asList(208));

		ArrayList<Object> input5 = new ArrayList<Object>();
		input5.addAll(Arrays.asList(3, -1221));
		List<Object> result5 = new ArrayList<Object>();
		result5.addAll(Arrays.asList(156));

		ArrayList<Object> input6 = new ArrayList<Object>();
		input6.addAll(Arrays.asList(2, 1));
		List<Object> result6 = new ArrayList<Object>();
		result6.addAll(Arrays.asList(104));

		ArrayList<Object> input7 = new ArrayList<Object>();
		input7.addAll(Arrays.asList(1, 97));
		List<Object> result7 = new ArrayList<Object>();
		result7.addAll(Arrays.asList(52));

		ArrayList<Object> input8 = new ArrayList<Object>();
		input8.addAll(Arrays.asList(9, 1942182));
		List<Object> result8 = new ArrayList<Object>();
		result8.addAll(Arrays.asList(468));

		ArrayList<Object> input9 = new ArrayList<Object>();
		input9.addAll(Arrays.asList(14, -33));
		List<Object> result9 = new ArrayList<Object>();
		result9.addAll(Arrays.asList(728));

		return Arrays
				.asList(new Object[][] { { input0, result0 },
						{ input1, result1 }, { input2, result2 },
						{ input3, result3 }, { input4, result4 },
						{ input5, result5 }, { input6, result6 },
						{ input7, result7 }, { input8, result8 },
						{ input9, result9 } });
	}

	@Test(expected = IllegalStateException.class)
	public final void initialiseNoDeckNo() {
		com.luca.blackjack.card.StandardDeck deck = new com.luca.blackjack.card.StandardDeck();
		deck.setSeed(1234);
		deck.initialise();
	}

	@Test(expected = IllegalStateException.class)
	public final void initialiseNoSeed() {
		com.luca.blackjack.card.StandardDeck deck = new com.luca.blackjack.card.StandardDeck();
		deck.setDeckNo(1);
		deck.initialise();
	}

	@Test(expected = IllegalStateException.class)
	public final void regenerateNoSeed() {
		com.luca.blackjack.card.StandardDeck deck = new com.luca.blackjack.card.StandardDeck();
		deck.setDeckNo(1);
		deck.initialise();
	}

	@Test(expected = IllegalArgumentException.class)
	public final void setSeedEqual0() {
		com.luca.blackjack.card.StandardDeck deck = new com.luca.blackjack.card.StandardDeck();
		deck.setSeed(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void setDeckNoLowerThan0() {
		com.luca.blackjack.card.StandardDeck deck = new com.luca.blackjack.card.StandardDeck();
		deck.setDeckNo(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void setDeckNoEqualTo0() {
		com.luca.blackjack.card.StandardDeck deck = new com.luca.blackjack.card.StandardDeck();
		deck.setDeckNo(0);
	}

	@Test(expected = IllegalStateException.class, timeout = 1000)
	public final void deckEventuallyGetsEmpty() {
		while (true)
			deck.getCard();
	}

	@Test(timeout = 1000)
	public final void burnCardEventuallyFound() {
		assertTrue(!deck.hasBurnCardFound());
		try {
			while (true)
				deck.getCard();
		} catch (Exception e) {
			assertTrue(deck.hasBurnCardFound());
		}
	}

	@Test
	public final void numberOfCards() {
		int cardNo = 0;
		try {
			while (true) {
				deck.getCard();
				cardNo++;
			}
		} catch (IllegalStateException e) {
			assertEquals(results.get(0), cardNo);
		}
	}

	@Test
	public final void burnCardBeforeLast13thAnd26th() {
		while (!deck.hasBurnCardFound())
			deck.getCard();
		int cardLeft = 0;
		try {
			while (true) {
				deck.getCard();
				cardLeft++;
			}
		} catch (IllegalStateException e) {
			assertTrue(cardLeft >= 25);
			assertTrue(cardLeft <= 38);
		}
	}

	@Test
	public final void regenerateResetsBurnCard() {
		while (!deck.hasBurnCardFound())
			deck.getCard();
		deck.regenerate();
		assertTrue(!deck.hasBurnCardFound());
		assertThat(deck.getSeed(), not(equalTo(seed)));
	}

	@Test
	public final void initialise() {
		List<Card> current = new ArrayList<Card>();
		try {
			while (true)
				current.add(deck.getCard());
		} catch (Exception e) {
		}
		deck.regenerate();
		List<Card> regenerated = new ArrayList<Card>();
		try {
			while (true)
				regenerated.add(deck.getCard());
		} catch (Exception e) {
		}
		assertEquals(regenerated.size(), current.size());
		boolean differentCard = false;
		try {
			for (int i = 0; !differentCard; i++)
				differentCard = current.get(i) == regenerated.get(i) ? true
						: false;
		} catch (Exception e) {
		}
		assertTrue(differentCard);
	}

	@Test
	public final void isInitialised() {
		assertTrue(deck.isInitialised());
		assertNotNull(deck.getDeckNo());
		assertNotNull(deck.getCard());
		assertNotNull(deck.getSeed());
		assertNotNull(deck.hasBurnCardFound());
	}

	@Test
	public final void setGetSeed() {
		int expected = seed;
		int actual = deck.getSeed();
		assertEquals(expected, actual);
	}

	@Test
	public final void setGetDeckNo() {
		int expected = deckNo;
		int actual = deck.getDeckNo();
		assertEquals(expected, actual);
	}
}
