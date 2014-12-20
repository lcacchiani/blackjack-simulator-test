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

import com.luca.blackjack.card.Card;
import com.luca.blackjack.game.Rules;
import com.luca.blackjack.strategy.game.DealerGameStrategy;
import com.luca.blackjack.strategy.game.Move;

@RunWith(Parameterized.class)
public class DefaultDealerGameStrategy {

	private Mockery context = new JUnit4Mockery();
	
	DealerGameStrategy strategy;

	List<Card> cards;
	List<Move> results;
	Rules rules;

	public DefaultDealerGameStrategy(List<Card> cards, List<Move> results) {
		this.cards = cards;
		this.results = results;
		this.rules = context.mock(Rules.class);
	}

	@Before
	public final void setup() {
		strategy = new com.luca.blackjack.strategy.game.DefaultDealerGameStrategy();
	}

	@After
	public final void tearDown() {
		strategy = null;
	}

	@Parameters
	public static Collection<Object[]> data() {

		List<Card> cards0 = new ArrayList<Card>();
		cards0.addAll(Arrays.asList(Card.ACE_OF_HEARTS, Card.SIX_OF_CLUBS));
		List<Move> results0 = new ArrayList<Move> ();
		results0.addAll(Arrays.asList(Move.HIT, Move.STAND));

		List<Card> cards1 = new ArrayList<Card>();
		cards1.addAll(Arrays.asList(Card.EIGHT_OF_CLUBS, Card.SIX_OF_DIAMONDS));
		List<Move> results1 = new ArrayList<Move> ();
		results1.addAll(Arrays.asList(Move.HIT, Move.HIT));

		List<Card> cards2 = new ArrayList<Card>();
		cards2.addAll(Arrays.asList(Card.THREE_OF_HEARTS, Card.TWO_OF_SPADES,
				Card.EIGHT_OF_CLUBS, Card.FOUR_OF_DIAMONDS));
		List<Move> results2 = new ArrayList<Move> ();
		results2.addAll(Arrays.asList(Move.HIT, Move.HIT));

		List<Card> cards3 = new ArrayList<Card>();
		cards3.addAll(Arrays.asList(Card.TEN_OF_CLUBS, Card.ACE_OF_DIAMONDS));
		List<Move> results3 = new ArrayList<Move> ();
		results3.addAll(Arrays.asList(Move.STAND, Move.STAND));

		List<Card> cards4 = new ArrayList<Card>();
		cards4.addAll(Arrays.asList(Card.SEVEN_OF_HEARTS, Card.THREE_OF_SPADES,
				Card.SEVEN_OF_CLUBS, Card.SIX_OF_DIAMONDS));
		List<Move> results4 = new ArrayList<Move> ();
		results4.addAll(Arrays.asList(Move.STAND, Move.STAND));

		List<Card> cards5 = new ArrayList<Card>();
		cards5.addAll(Arrays.asList(Card.TEN_OF_HEARTS, Card.TWO_OF_SPADES));
		List<Move> results5 = new ArrayList<Move> ();
		results5.addAll(Arrays.asList(Move.HIT, Move.HIT));

		List<Card> cards6 = new ArrayList<Card>();
		cards6.addAll(Arrays.asList(Card.TWO_OF_HEARTS, Card.FIVE_OF_SPADES,
				Card.THREE_OF_CLUBS, Card.FOUR_OF_DIAMONDS,
				Card.FIVE_OF_HEARTS, Card.ACE_OF_CLUBS));
		List<Move> results6 = new ArrayList<Move> ();
		results6.addAll(Arrays.asList(Move.STAND, Move.STAND));

		List<Card> cards7 = new ArrayList<Card>();
		cards7.addAll(Arrays.asList(Card.ACE_OF_CLUBS, Card.ACE_OF_DIAMONDS,
				Card.ACE_OF_HEARTS));
		List<Move> results7 = new ArrayList<Move> ();
		results7.addAll(Arrays.asList(Move.STAND, Move.STAND));

		List<Card> cards8 = new ArrayList<Card>();
		cards8.addAll(Arrays.asList(Card.NINE_OF_HEARTS, Card.NINE_OF_SPADES,
				Card.TWO_OF_CLUBS, Card.TWO_OF_DIAMONDS));
		List<Move> results8 = new ArrayList<Move> ();
		results8.addAll(Arrays.asList(Move.STAND, Move.STAND));

		List<Card> cards9 = new ArrayList<Card>();
		cards9.addAll(Arrays.asList(Card.FOUR_OF_CLUBS, Card.TEN_OF_DIAMONDS,
				Card.SIX_OF_HEARTS, Card.ACE_OF_SPADES));
		List<Move> results9 = new ArrayList<Move> ();
		results9.addAll(Arrays.asList(Move.STAND, Move.STAND));

		return Arrays.asList(new Object[][] { { cards0, results0 },
				{ cards1, results1 }, { cards2, results2 }, { cards3, results3 },
				{ cards4, results4 }, { cards5, results5 }, { cards6, results6 },
				{ cards7, results7 }, { cards8, results8 }, { cards9, results9 } });
	}

	@Test(expected = IllegalArgumentException.class)
	public final void getMoveNoCards() {
		strategy.getNextMove(null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void getMoveNoRules() {
		strategy.getNextMove(cards, null);
	}

	@Test
	public final void getMoveHard17() {
		context.checking(new Expectations() {
			{
				allowing(rules).isSoft17();
				will(returnValue(false));
			}
		});
		
		Move actual = strategy.getNextMove(cards, rules);
		Move expected = results.get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	public final void getMoveSoft17() {
		context.checking(new Expectations() {
			{
				allowing(rules).isSoft17();
				will(returnValue(true));
			}
		});
		
		Move actual = strategy.getNextMove(cards, rules);
		Move expected = results.get(1);
		assertEquals(expected, actual);
	}
}