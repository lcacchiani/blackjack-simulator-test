package com.luca.blackjack.test.unit;

import static org.junit.Assert.assertEquals;

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
import com.luca.blackjack.strategy.game.Move;
import com.luca.blackjack.strategy.game.PlayerGameStrategy;

@RunWith(Parameterized.class)
public class DefaultPlayerGameStrategy {

	PlayerGameStrategy strategy;

	List<Card> cards;
	Move result;

	public DefaultPlayerGameStrategy(List<Card> cards, Move result) {
		this.cards = cards;
		this.result = result;
	}

	@Before
	public final void setup() {
		strategy = new com.luca.blackjack.strategy.game.DefaultPlayerGameStrategy();
	}

	@After
	public final void tearDown() {
		strategy = null;
	}

	@Parameters
	public static Collection<Object[]> data() {

		List<Card> cards0 = new ArrayList<Card>();
		cards0.addAll(Arrays.asList(Card.ACE_OF_HEARTS, Card.FOUR_OF_SPADES,
				Card.THREE_OF_CLUBS, Card.ACE_OF_DIAMONDS));
		Move result0 = Move.STAND;

		List<Card> cards1 = new ArrayList<Card>();
		cards1.addAll(Arrays.asList(Card.EIGHT_OF_CLUBS, Card.SIX_OF_DIAMONDS));
		Move result1 = Move.HIT;

		List<Card> cards2 = new ArrayList<Card>();
		cards2.addAll(Arrays.asList(Card.THREE_OF_HEARTS, Card.TWO_OF_SPADES,
				Card.EIGHT_OF_CLUBS, Card.FOUR_OF_DIAMONDS));
		Move result2 = Move.STAND;

		List<Card> cards3 = new ArrayList<Card>();
		cards3.addAll(Arrays.asList(Card.TEN_OF_CLUBS, Card.ACE_OF_DIAMONDS));
		Move result3 = Move.STAND;

		List<Card> cards4 = new ArrayList<Card>();
		cards4.addAll(Arrays.asList(Card.SEVEN_OF_HEARTS, Card.THREE_OF_SPADES,
				Card.SEVEN_OF_CLUBS, Card.SIX_OF_DIAMONDS));
		Move result4 = Move.STAND;

		List<Card> cards5 = new ArrayList<Card>();
		cards5.addAll(Arrays.asList(Card.SIX_OF_CLUBS, Card.EIGHT_OF_DIAMONDS,
				Card.TEN_OF_HEARTS, Card.TWO_OF_SPADES));
		Move result5 = Move.STAND;

		List<Card> cards6 = new ArrayList<Card>();
		cards6.addAll(Arrays.asList(Card.TWO_OF_HEARTS, Card.FIVE_OF_SPADES,
				Card.THREE_OF_CLUBS, Card.FOUR_OF_DIAMONDS,
				Card.FIVE_OF_HEARTS, Card.ACE_OF_CLUBS));
		Move result6 = Move.STAND;

		List<Card> cards7 = new ArrayList<Card>();
		cards7.addAll(Arrays.asList(Card.ACE_OF_CLUBS, Card.ACE_OF_DIAMONDS,
				Card.ACE_OF_HEARTS));
		Move result7 = Move.STAND;

		List<Card> cards8 = new ArrayList<Card>();
		cards8.addAll(Arrays.asList(Card.NINE_OF_HEARTS, Card.NINE_OF_SPADES,
				Card.TWO_OF_CLUBS, Card.TWO_OF_DIAMONDS));
		Move result8 = Move.STAND;

		List<Card> cards9 = new ArrayList<Card>();
		cards9.addAll(Arrays.asList(Card.FOUR_OF_CLUBS, Card.TEN_OF_DIAMONDS,
				Card.SIX_OF_HEARTS, Card.ACE_OF_SPADES));
		Move result9 = Move.STAND;

		return Arrays.asList(new Object[][] { { cards0, result0 },
				{ cards1, result1 }, { cards2, result2 }, { cards3, result3 },
				{ cards4, result4 }, { cards5, result5 }, { cards6, result6 },
				{ cards7, result7 }, { cards8, result8 }, { cards9, result9 } });
	}

	@Test(expected = IllegalArgumentException.class)
	public final void getMoveNoCards() {
		strategy.getMove(null, null, null, 0, 0);
	}

	@Test
	public final void getMove() {
		Move actual = strategy.getMove(cards, null, null, 0, 0);
		Move expected = result;
		assertEquals(expected, actual);
	}
}