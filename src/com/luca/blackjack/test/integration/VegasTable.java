package com.luca.blackjack.test.integration;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.luca.blackjack.Result;
import com.luca.blackjack.StandardBlackjackGame;
import com.luca.blackjack.card.Card;
import com.luca.blackjack.card.Deck;
import com.luca.blackjack.card.StandardDeck;
import com.luca.blackjack.game.Rules;
import com.luca.blackjack.game.StandardRules;
import com.luca.blackjack.game.Table;
import com.luca.blackjack.strategy.betting.DefaultBettingStrategy;
import com.luca.blackjack.strategy.game.DefaultDealerGameStrategy;
import com.luca.blackjack.strategy.game.DefaultPlayerGameStrategy;
import com.luca.blackjack.strategy.game.SimpleStrategy;
import com.luca.blackjack.strategy.insurance.AlwaysInsurance;
import com.luca.blackjack.strategy.insurance.NeverInsurance;
import com.luca.blackjack.user.Dealer;
import com.luca.blackjack.user.Player;
import com.luca.blackjack.user.SimpleDealer;
import com.luca.blackjack.user.SimplePlayer;

@RunWith(Parameterized.class)
public class VegasTable {

	com.luca.blackjack.game.VegasTable table;

	File xml;
	List<Object> results;

	public VegasTable(List<Object> inputs, List<Object> results) {
		this.xml = (File) inputs.get(0);
		this.results = results;
	}

	@Before
	public final void setup() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(
				StandardBlackjackGame.class,
				com.luca.blackjack.game.VegasTable.class, StandardDeck.class,
				SimpleDealer.class, StandardRules.class, SimplePlayer.class,
				DefaultBettingStrategy.class, DefaultDealerGameStrategy.class,
				DefaultPlayerGameStrategy.class, SimpleStrategy.class,
				NeverInsurance.class, AlwaysInsurance.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		StandardBlackjackGame standardBlackjackGame = (StandardBlackjackGame) unmarshaller
				.unmarshal(xml);
		List<Table> tables = standardBlackjackGame.getTables();
		table = (com.luca.blackjack.game.VegasTable) tables.get(0);
	}

	@After
	public final void tearDown() {
		table = null;
	}
	@SuppressWarnings("unchecked")
	@Parameters
	public static Collection<Object[]> data() {

		ArrayList<Object> input0 = new ArrayList<Object>();
		File file0 = new File("data/test-data-v1.0.xml");
		input0.addAll(Arrays.asList(file0));
		List<Object> result0 = new ArrayList<Object>();
		
		List<Object> run01 = new ArrayList<Object>();
		List<Object> player01a = new ArrayList<Object>();
		player01a.addAll(Arrays.asList(110d, Result.WON_HIGHER_SCORE));
		List<Object> player01b = new ArrayList<Object>();
		player01b.addAll(Arrays.asList(110d, Result.WON_HIGHER_SCORE));
		List<Object> player01c = new ArrayList<Object>();
		player01c.addAll(Arrays.asList(90d, Result.LOST_LOWER_SCORE));
		List<Object> player01d = new ArrayList<Object>();
		player01d.addAll(Arrays.asList(90d, Result.LOST_LOWER_SCORE));
		List<Object> player01e = new ArrayList<Object>();
		player01e.addAll(Arrays.asList(90d, Result.LOST_LOWER_SCORE));
		List<Object> player01f = new ArrayList<Object>();
		player01f.addAll(Arrays.asList(90d, Result.LOST_LOWER_SCORE));
		List<Object> player01g = new ArrayList<Object>();
		player01g.addAll(Arrays.asList(110d, Result.WON_HIGHER_SCORE));
		run01.addAll(Arrays.asList(Card.FIVE_OF_DIAMONDS, 19, 7, player01a,
				player01b, player01c, player01d, player01e, player01f,
				player01g));
		
		List<Object> run05 = new ArrayList<Object>();
		List<Object> player05a = new ArrayList<Object>();
		player05a.addAll(Arrays.asList(130d, Result.WON_DEALER_BUSTED_OUT));
		List<Object> player05b = new ArrayList<Object>();
		player05b.addAll(Arrays.asList(110d, Result.WON_DEALER_BUSTED_OUT));
		List<Object> player05c = new ArrayList<Object>();
		player05c.addAll(Arrays.asList(110d, Result.LOST_BUSTED_OUT));
		List<Object> player05d = new ArrayList<Object>();
		player05d.addAll(Arrays.asList(85d, Result.LOST_BUSTED_OUT));
		List<Object> player05e = new ArrayList<Object>();
		player05e.addAll(Arrays.asList(105d, Result.WON_DEALER_BUSTED_OUT));
		List<Object> player05f = new ArrayList<Object>();
		player05f.addAll(Arrays.asList(90d, Result.WON_DEALER_BUSTED_OUT));
		List<Object> player05g = new ArrayList<Object>();
		player05g.addAll(Arrays.asList(110d, Result.WON_DEALER_BUSTED_OUT));
		run05.addAll(Arrays.asList(Card.FOUR_OF_HEARTS, 24, 7, player05a,
				player05b, player05c, player05d, player05e, player05f,
				player05g));
		
		result0.addAll(Arrays.asList(true, true, true, true, run01, run05));

		return Arrays.asList(new Object[][] { { input0, result0 } });
	}

	@Test
	public final void initialise() {
		table.initialise();

		boolean actualTableIsInitialised = table.isInitialised();
		boolean expectedTableIsInitialised = (Boolean) results.get(0);
		assertEquals(expectedTableIsInitialised, actualTableIsInitialised);

		Deck deck = table.getDeck();
		boolean actualDeckIsInitialised = deck.isInitialised();
		boolean expectedDeckIsInitialised = (Boolean) results.get(1);
		assertEquals(expectedDeckIsInitialised, actualDeckIsInitialised);

		Rules rules = table.getRules();
		boolean actualRulesIsInitialised = rules.isInitialised();
		boolean expectedRulesIsInitialised = (Boolean) results.get(2);
		assertEquals(expectedRulesIsInitialised, actualRulesIsInitialised);
	}

	@Test
	public final void isInitialised() {
		table.initialise();

		boolean actualIsInitialised = table.isInitialised();
		boolean expectedIsInitialised = (Boolean) results.get(3);
		assertEquals(expectedIsInitialised, actualIsInitialised);
	}

	@Test
	public final void playGame1Run() {
		table.initialise();
		table.playGame();

		playGame(1);
	}

	@Test
	public final void playGame5Run() {
		table.initialise();
		for (int i = 0; i < 5; i++)
			table.playGame();

		playGame(5);
	}

	@SuppressWarnings("unchecked")
	private void playGame(int run) {

		List<Object> result;

		switch (run) {
		case 1:
			result = (List<Object>) results.get(4);
			break;
		case 5:
			result = (List<Object>) results.get(5);
			break;
		default:
			throw new IllegalArgumentException(
					"run not mapped in any result object");
		}

		Dealer dealer = table.getDealer();

		Card actualDealerFirstCard = dealer.getFirstCard();
		Card expectedDealerFirstCard = (Card) result.get(0);
		assertEquals(expectedDealerFirstCard, actualDealerFirstCard);

		int actualDealerHandValue = dealer.getHandValue();
		int expectedDealerHandValue = (Integer) result.get(1);
		assertEquals(expectedDealerHandValue, actualDealerHandValue);

		int actualNumberPlayers = table.getPlayers().size();
		int expectedNumberPlayers = (Integer) result.get(2);
		assertEquals(expectedNumberPlayers, actualNumberPlayers);

		for (int i = 0; i < table.getPlayers().size(); i++) {

			Player player = table.getPlayers().get(i);
			List<Object> resultPlayers = (List<Object>) result.get(3 + i);

			double actualPlayerBalance = player.getBalance();
			double expectedPlayerBalance = (Double) resultPlayers.get(0);
			assertEquals(expectedPlayerBalance, actualPlayerBalance, 0.001);

			Result actualResult = player.getTopResult();
			Result expectedResult = (Result) resultPlayers.get(1);
			assertEquals(expectedResult, actualResult);
		}
	}
}
