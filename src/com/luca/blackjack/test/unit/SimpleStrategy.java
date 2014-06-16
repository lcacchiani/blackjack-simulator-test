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
import com.luca.blackjack.strategy.game.Move;
import com.luca.blackjack.strategy.game.PlayerGameStrategy;

@RunWith(Parameterized.class)
public class SimpleStrategy {
	
	private Mockery context = new JUnit4Mockery();

	PlayerGameStrategy strategy;

	List<Card> cards;
	Card dealerCard;
	int resplitNo;
	Rules rules;
	Move result;

	@SuppressWarnings("unchecked")
	public SimpleStrategy(List<Object> inputs, Move result) {
		this.cards = (List<Card>) inputs.get(0);
		this.dealerCard = (Card) inputs.get(1);
		this.resplitNo = (Integer) inputs.get(2);
		this.rules = context.mock(Rules.class);
		this.result = result;
	}

	@Before
	public final void setup() {
		strategy = new com.luca.blackjack.strategy.game.SimpleStrategy();
	}

	@After
	public final void tearDown() {
		strategy = null;
	}

	@Parameters
	public static Collection<Object[]> data() {

		ArrayList<Object> input0 = new ArrayList<Object>();
		List<Card> card0 = new ArrayList<Card>();
		card0.addAll(Arrays.asList(Card.ACE_OF_HEARTS, Card.FOUR_OF_SPADES,
				Card.THREE_OF_CLUBS, Card.ACE_OF_DIAMONDS));
		input0.addAll(Arrays.asList(card0, Card.ACE_OF_HEARTS, 0));
		Move result0 = Move.STAND;

		ArrayList<Object> input1 = new ArrayList<Object>();
		List<Card> card1 = new ArrayList<Card>();
		card1.addAll(Arrays.asList(Card.EIGHT_OF_CLUBS, Card.SIX_OF_DIAMONDS));
		input1.addAll(Arrays.asList(card1, Card.EIGHT_OF_CLUBS, 0));
		Move result1 = Move.HIT;

		ArrayList<Object> input2 = new ArrayList<Object>();
		List<Card> card2 = new ArrayList<Card>();
		card2.addAll(Arrays.asList(Card.THREE_OF_HEARTS, Card.TWO_OF_SPADES,
				Card.EIGHT_OF_CLUBS, Card.FOUR_OF_DIAMONDS));
		input2.addAll(Arrays.asList(card2, Card.THREE_OF_HEARTS, 0));
		Move result2 = Move.STAND;

		ArrayList<Object> input3 = new ArrayList<Object>();
		List<Card> card3 = new ArrayList<Card>();
		card3.addAll(Arrays.asList(Card.TEN_OF_CLUBS, Card.ACE_OF_DIAMONDS));
		input3.addAll(Arrays.asList(card3, Card.TEN_OF_CLUBS, 0));
		Move result3 = Move.STAND;

		ArrayList<Object> input4 = new ArrayList<Object>();
		List<Card> card4 = new ArrayList<Card>();
		card4.addAll(Arrays.asList(Card.SEVEN_OF_HEARTS, Card.THREE_OF_SPADES,
				Card.SEVEN_OF_CLUBS, Card.SIX_OF_DIAMONDS));
		input4.addAll(Arrays.asList(card4, Card.SEVEN_OF_HEARTS, 0));
		Move result4 = Move.STAND;

		ArrayList<Object> input5 = new ArrayList<Object>();
		List<Card> card5 = new ArrayList<Card>();
		card5.addAll(Arrays.asList(Card.SIX_OF_CLUBS, Card.EIGHT_OF_DIAMONDS,
				Card.TEN_OF_HEARTS, Card.TWO_OF_SPADES));
		input5.addAll(Arrays.asList(card5, Card.SIX_OF_CLUBS, 0));
		Move result5 = Move.STAND;

		ArrayList<Object> input6 = new ArrayList<Object>();
		List<Card> card6 = new ArrayList<Card>();
		card6.addAll(Arrays.asList(Card.TWO_OF_HEARTS, Card.FIVE_OF_SPADES,
				Card.THREE_OF_CLUBS, Card.FOUR_OF_DIAMONDS,
				Card.FIVE_OF_HEARTS, Card.ACE_OF_CLUBS));
		input6.addAll(Arrays.asList(card6, Card.TWO_OF_HEARTS, 0));
		Move result6 = Move.STAND;

		ArrayList<Object> input7 = new ArrayList<Object>();
		List<Card> card7 = new ArrayList<Card>();
		card7.addAll(Arrays.asList(Card.ACE_OF_CLUBS, Card.ACE_OF_DIAMONDS,
				Card.ACE_OF_HEARTS));
		input7.addAll(Arrays.asList(card7, Card.ACE_OF_CLUBS, 0));
		Move result7 = Move.HIT;

		ArrayList<Object> input8 = new ArrayList<Object>();
		List<Card> card8 = new ArrayList<Card>();
		card8.addAll(Arrays.asList(Card.NINE_OF_HEARTS, Card.NINE_OF_SPADES,
				Card.TWO_OF_CLUBS, Card.TWO_OF_DIAMONDS));
		input8.addAll(Arrays.asList(card8, Card.NINE_OF_HEARTS, 0));
		Move result8 = Move.STAND;

		ArrayList<Object> input9 = new ArrayList<Object>();
		List<Card> card9 = new ArrayList<Card>();
		card9.addAll(Arrays.asList(Card.FOUR_OF_CLUBS, Card.TEN_OF_DIAMONDS,
				Card.SIX_OF_HEARTS, Card.ACE_OF_SPADES));
		input9.addAll(Arrays.asList(card9, Card.FOUR_OF_CLUBS, 0));
		Move result9 = Move.STAND;

		return Arrays
				.asList(new Object[][] { { input0, result0 },
						{ input1, result1 }, { input2, result2 },
						{ input3, result3 }, { input4, result4 },
						{ input5, result5 }, { input6, result6 },
						{ input7, result7 }, { input8, result8 },
						{ input9, result9 } });
	}

	@Test(expected = IllegalArgumentException.class)
	public final void getMoveNoCards() {
		strategy.getMove(null, null, null, 0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void getMoveNoDealerCard() {
		strategy.getMove(cards, null, null, 0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void getMoveNoRules() {
		strategy.getMove(cards, dealerCard, null, 0, 0);
	}

	@Test
	public final void getMoveResplitValid() {
		context.checking(new Expectations() {
			{
				oneOf(rules).getResplit();
				will(returnValue(1));
			}
		});
		Move actual = strategy.getMove(cards, dealerCard, rules, 0, resplitNo);
		Move expected = result;
		assertEquals(expected, actual);
	}
}