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
import com.luca.blackjack.card.StandardDeck;
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
public class VegasTableDealerWinTies {

	com.luca.blackjack.game.VegasTable table;

	File xml;
	List<Object> results;

	public VegasTableDealerWinTies(List<Object> inputs, List<Object> results) {
		this.xml = (File) inputs.get(0);
		this.results = results;
	}

	@Before
	public final void setup() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(StandardBlackjackGame.class,
				com.luca.blackjack.game.VegasTable.class, StandardDeck.class, SimpleDealer.class, StandardRules.class,
				SimplePlayer.class, DefaultBettingStrategy.class, DefaultDealerGameStrategy.class,
				DefaultPlayerGameStrategy.class, SimpleStrategy.class, NeverInsurance.class, AlwaysInsurance.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		StandardBlackjackGame standardBlackjackGame = (StandardBlackjackGame) unmarshaller.unmarshal(xml);
		List<Table> tables = standardBlackjackGame.getTables();
		table = (com.luca.blackjack.game.VegasTable) tables.get(0);
	}

	@After
	public final void tearDown() {
		table = null;
	}

	@Parameters
	public static Collection<Object[]> data() {

		ArrayList<Object> input0 = new ArrayList<Object>();
		File file0 = new File("data/test-data-v1.0-001.xml");
		input0.addAll(Arrays.asList(file0));
		List<Object> result0 = new ArrayList<Object>();

		List<Object> run = new ArrayList<Object>();
		List<Object> player1 = new ArrayList<Object>();
		player1.addAll(Arrays.asList(65d, Result.WON_DEALER_BUSTED_OUT));
		List<Object> player2 = new ArrayList<Object>();
		player2.addAll(Arrays.asList(100d, Result.WON_DEALER_BUSTED_OUT));
		List<Object> player3 = new ArrayList<Object>();
		player3.addAll(Arrays.asList(160d, Result.WON_DEALER_BUSTED_OUT));
		run.addAll(Arrays.asList(Card.ACE_OF_SPADES, 23, player1, player2, player3));

		result0.addAll(Arrays.asList(run));

		return Arrays.asList(new Object[][] { { input0, result0 } });
	}

	@Test
	@SuppressWarnings("unchecked")
	public final void playGame50Run() {
		table.initialise();
		for (int i = 0; i < 50; i++)
			table.playGame();

		List<Object> result = (List<Object>) results.get(0);

		Dealer dealer = table.getDealer();

		Card actualDealerFirstCard = dealer.getFirstCard();
		Card expectedDealerFirstCard = (Card) result.get(0);
		assertEquals(expectedDealerFirstCard, actualDealerFirstCard);

		int actualDealerHandValue = dealer.getHandValue();
		int expectedDealerHandValue = (Integer) result.get(1);
		assertEquals(expectedDealerHandValue, actualDealerHandValue);

		for (int i = 0; i < table.getPlayers().size(); i++) {

			Player player = table.getPlayers().get(i);
			List<Object> resultPlayers = (List<Object>) result.get(2 + i);

			double actualPlayerBalance = player.getBalance();
			double expectedPlayerBalance = (Double) resultPlayers.get(0);
			assertEquals(expectedPlayerBalance, actualPlayerBalance, 0.001);

			Result actualResult = player.getTopResult();
			Result expectedResult = (Result) resultPlayers.get(1);
			assertEquals(expectedResult, actualResult);
		}
	}
}
