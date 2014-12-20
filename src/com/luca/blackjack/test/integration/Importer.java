package com.luca.blackjack.test.integration;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.luca.blackjack.Blackjack;
import com.luca.blackjack.StandardBlackjackGame;
import com.luca.blackjack.card.Deck;
import com.luca.blackjack.card.StandardDeck;
import com.luca.blackjack.game.Rules;
import com.luca.blackjack.game.StandardRules;
import com.luca.blackjack.game.Table;
import com.luca.blackjack.game.VegasTable;
import com.luca.blackjack.strategy.betting.BettingStrategy;
import com.luca.blackjack.strategy.betting.DefaultBettingStrategy;
import com.luca.blackjack.strategy.game.DealerGameStrategy;
import com.luca.blackjack.strategy.game.DefaultDealerGameStrategy;
import com.luca.blackjack.strategy.game.DefaultPlayerGameStrategy;
import com.luca.blackjack.strategy.game.GenericDealerGameStrategy;
import com.luca.blackjack.strategy.game.PlayerGameStrategy;
import com.luca.blackjack.strategy.game.SimpleStrategy;
import com.luca.blackjack.strategy.insurance.AlwaysInsurance;
import com.luca.blackjack.strategy.insurance.InsuranceStrategy;
import com.luca.blackjack.strategy.insurance.NeverInsurance;
import com.luca.blackjack.user.Dealer;
import com.luca.blackjack.user.Player;
import com.luca.blackjack.user.SimpleDealer;
import com.luca.blackjack.user.SimplePlayer;

@RunWith(Parameterized.class)
public class Importer {

	Unmarshaller unmarshaller;
	Blackjack blackjack;

	File xml;
	List<Object> results;

	public Importer(List<Object> inputs, List<Object> results) {
		this.xml = (File) inputs.get(0);
		this.results = results;
	}

	@Before
	public final void setup() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(
				StandardBlackjackGame.class, VegasTable.class,
				StandardDeck.class, SimpleDealer.class, StandardRules.class,
				SimplePlayer.class, DefaultBettingStrategy.class,
				GenericDealerGameStrategy.class,
				DefaultDealerGameStrategy.class,
				DefaultPlayerGameStrategy.class, SimpleStrategy.class,
				NeverInsurance.class, AlwaysInsurance.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		blackjack = null;
	}

	@After
	public final void tearDown() {
		blackjack = null;
		unmarshaller = null;
	}

	@Parameters
	public static Collection<Object[]> data() {

		ArrayList<Object> input0 = new ArrayList<Object>();
		File file0 = new File("data/test-data-v1.0.xml");
		input0.addAll(Arrays.asList(file0));
		List<Object> result0 = new ArrayList<Object>();
		Map<String, List<Object>> tables0 = new HashMap<String, List<Object>>();
		String tableName00 = "Excess";
		List<Object> tableProperties00 = new ArrayList<Object>();
		List<Object> dealer00 = new ArrayList<Object>();
		dealer00.addAll(Arrays.asList("Bastard",
				new DefaultDealerGameStrategy()));
		List<Object> rules00 = new ArrayList<Object>();
		rules00.addAll(Arrays.asList(false, 1, false, -1, false, false, false,
				false, false, false, false, false, "3:2", false, "2:1", false, true));
		List<Object> deck00 = new ArrayList<Object>();
		deck00.addAll(Arrays.asList(1));
		Map<String, List<Object>> players00 = new HashMap<String, List<Object>>();
		String playerName000 = "Luca";
		List<Object> playerProperties000 = new ArrayList<Object>();
		playerProperties000.addAll(Arrays.asList("Luca",
				new DefaultPlayerGameStrategy(), new DefaultBettingStrategy(),
				new NeverInsurance(), 100d));
		players00.put(playerName000, playerProperties000);
		String playerName001 = "Satan";
		List<Object> playerProperties001 = new ArrayList<Object>();
		playerProperties001.addAll(Arrays.asList("Satan",
				new DefaultPlayerGameStrategy(), new DefaultBettingStrategy(),
				new NeverInsurance(), 100d));
		players00.put(playerName001, playerProperties001);
		String playerName002 = "Evil";
		List<Object> playerProperties002 = new ArrayList<Object>();
		playerProperties002.addAll(Arrays.asList("Evil",
				new DefaultPlayerGameStrategy(), new DefaultBettingStrategy(),
				new NeverInsurance(), 100d));
		players00.put(playerName002, playerProperties002);
		String playerName003 = "Belzebu";
		List<Object> playerProperties003 = new ArrayList<Object>();
		playerProperties003.addAll(Arrays.asList("Belzebu",
				new DefaultPlayerGameStrategy(), new DefaultBettingStrategy(),
				new NeverInsurance(), 100d));
		players00.put(playerName003, playerProperties003);
		String playerName004 = "Jezebel";
		List<Object> playerProperties004 = new ArrayList<Object>();
		playerProperties004.addAll(Arrays.asList("Jezebel",
				new DefaultPlayerGameStrategy(), new DefaultBettingStrategy(),
				new NeverInsurance(), 100d));
		players00.put(playerName004, playerProperties004);
		String playerName005 = "Lucifer";
		List<Object> playerProperties005 = new ArrayList<Object>();
		playerProperties005.addAll(Arrays.asList("Lucifer",
				new DefaultPlayerGameStrategy(), new DefaultBettingStrategy(),
				new NeverInsurance(), 100d));
		players00.put(playerName005, playerProperties005);
		String playerName006 = "Mammon";
		List<Object> playerProperties006 = new ArrayList<Object>();
		playerProperties006.addAll(Arrays.asList("Mammon",
				new DefaultPlayerGameStrategy(), new DefaultBettingStrategy(),
				new NeverInsurance(), 100d));
		players00.put(playerName006, playerProperties006);
		tableProperties00.addAll(Arrays.asList("Excess", 10d, 1000d, dealer00,
				7, players00, rules00, deck00));
		tables0.put(tableName00, tableProperties00);
		result0.addAll(Arrays.asList("1.0", 1, tables0));

		return Arrays.asList(new Object[][] { { input0, result0 } });
	}

	@Test
	@SuppressWarnings("unchecked")
	public final void unmarshaller() throws JAXBException {
		blackjack = (Blackjack) unmarshaller.unmarshal(xml);

		String actualVersion = blackjack.getVersion();
		String expectedVersion = (String) results.get(0);
		assertEquals(expectedVersion, actualVersion);

		List<Table> tables = blackjack.getTables();
		int actualTableNo = tables.size();
		int expectedTableNo = (Integer) results.get(1);
		assertEquals(expectedTableNo, actualTableNo);

		Map<String, List<Object>> resultTables = (Map<String, List<Object>>) results
				.get(2);

		for (Table t : tables) {

			String actualTableName = t.getName();
			List<Object> resultTable = resultTables.get(actualTableName);

			String expectedTableName = (String) resultTable.get(0);
			assertEquals(expectedTableName, actualTableName);

			double actualMinimumBet = t.getMinimumBet();
			double expectedMinimumBet = (Double) resultTable.get(1);
			assertEquals(expectedMinimumBet, actualMinimumBet, 0.001);

			double actualMaximumBet = t.getMaximumBet();
			double expectedMaximumBet = (Double) resultTable.get(2);
			assertEquals(expectedMaximumBet, actualMaximumBet, 0.001);

			Dealer actualDealer = t.getDealer();
			List<Object> resultDealer = (List<Object>) resultTable.get(3);

			String actualDealerName = actualDealer.getName();
			String expectedDealerName = (String) resultDealer.get(0);
			assertEquals(expectedDealerName, actualDealerName);

			DealerGameStrategy actualDealerGameStrategy = actualDealer
					.getGameStrategy();
			DealerGameStrategy expectedDealerGameStrategy = (DealerGameStrategy) resultDealer
					.get(1);
			assertEquals(expectedDealerGameStrategy.getClass(),
					actualDealerGameStrategy.getClass());

			List<Player> players = t.getPlayers();
			int actualPlayerNo = players.size();
			int expectedPlayerNo = (Integer) resultTable.get(4);
			assertEquals(expectedPlayerNo, actualPlayerNo);

			Map<String, List<Object>> resultPlayers = (Map<String, List<Object>>) resultTable
					.get(5);

			for (Player p : players) {

				String actualPlayerName = p.getName();
				List<Object> resultPlayer = resultPlayers.get(actualPlayerName);

				String expectedPlayerName = (String) resultPlayer.get(0);
				assertEquals(expectedPlayerName, actualPlayerName);

				PlayerGameStrategy actualPlayerGameStrategy = p
						.getGameStrategy();
				PlayerGameStrategy expectedPlayerGameStrategy = (PlayerGameStrategy) resultPlayer
						.get(1);
				assertEquals(expectedPlayerGameStrategy.getClass(),
						actualPlayerGameStrategy.getClass());

				BettingStrategy actualBettingStrategy = p.getBettingStrategy();
				BettingStrategy expectedBettingStrategy = (BettingStrategy) resultPlayer
						.get(2);
				assertEquals(expectedBettingStrategy.getClass(),
						actualBettingStrategy.getClass());

				InsuranceStrategy actualInsuranceStrategy = p
						.getInsuranceStrategy();
				InsuranceStrategy expectedInsuranceStrategy = (InsuranceStrategy) resultPlayer
						.get(3);
				assertEquals(expectedInsuranceStrategy.getClass(),
						actualInsuranceStrategy.getClass());

				double actualBalance = p.getBalance();
				double expectedBalance = (Double) resultPlayer.get(4);
				assertEquals(expectedBalance, actualBalance, 0.001);
			}

			Rules actualRules = t.getRules();
			List<Object> resultRules = (List<Object>) resultTable.get(6);

			Deck actualDeck = t.getDeck();
			List<Object> resultDeck = (List<Object>) resultTable.get(7);

			int actualDeckNo = actualDeck.getDeckNo();
			int expectedDeckNo = (Integer) resultDeck.get(0);
			assertEquals(expectedDeckNo, actualDeckNo);

			boolean actualSoft17 = actualRules.isSoft17();
			boolean expectedSoft17 = (Boolean) resultRules.get(0);
			assertEquals(expectedSoft17, actualSoft17);

			boolean actualEarlySurrender = actualRules.isEarlySurrender();
			boolean expectedEarlySurrender = (Boolean) resultRules.get(2);
			assertEquals(expectedEarlySurrender, actualEarlySurrender);
			
			boolean actualLateSurrender = actualRules.isLateSurrender();
			boolean expectedLateSurrender = (Boolean) resultRules.get(15);
			assertEquals(expectedLateSurrender, actualLateSurrender);
			
			boolean actualNoSurrenderAllowed = actualRules.isNoSurrenderAllowed();
			boolean expectedNoSurrenderAllowed = (Boolean) resultRules.get(16);
			assertEquals(expectedNoSurrenderAllowed, actualNoSurrenderAllowed);

			int actualResplit = actualRules.getResplit();
			int expectedResplit = (Integer) resultRules.get(3);
			assertEquals(expectedResplit, actualResplit);

			boolean actualResplitSplitAces = actualRules.isResplitSplitAces();
			boolean expectedResplitSplitAces = (Boolean) resultRules.get(4);
			assertEquals(expectedResplitSplitAces, actualResplitSplitAces);

			boolean actualHitSplitAces = actualRules.isHitSplitAces();
			boolean expectedHitSplitAces = (Boolean) resultRules.get(5);
			assertEquals(expectedHitSplitAces, actualHitSplitAces);

			boolean actualDoubleSplitAces = actualRules.isDoubleSplitAces();
			boolean expectedDoubleSplitAces = (Boolean) resultRules.get(6);
			assertEquals(expectedDoubleSplitAces, actualDoubleSplitAces);

			boolean actualNoDoubleAfterSplit = actualRules
					.isNoDoubleAfterSplit();
			boolean expectedNoDoubleAfterSplit = (Boolean) resultRules.get(7);
			assertEquals(expectedNoDoubleAfterSplit, actualNoDoubleAfterSplit);

			boolean actualRenoRule = actualRules.isRenoRule();
			boolean expectedRenoRule = (Boolean) resultRules.get(8);
			assertEquals(expectedRenoRule, actualRenoRule);

			boolean actualRenoRuleEuropean = actualRules.isRenoRuleEuropean();
			boolean expectedRenoRuleEuropean = (Boolean) resultRules.get(9);
			assertEquals(expectedRenoRuleEuropean, actualRenoRuleEuropean);

			boolean actualNoHoleCard = actualRules.isNoHoleCard();
			boolean expectedNoHoleCard = (Boolean) resultRules.get(10);
			assertEquals(expectedNoHoleCard, actualNoHoleCard);

			boolean actualObo = actualRules.isObo();
			boolean expectedObo = (Boolean) resultRules.get(11);
			assertEquals(expectedObo, actualObo);

			String actualBlackjackPayout = actualRules.getBlackjackPayout();
			String expectedBlackjackPayout = (String) resultRules.get(12);
			assertEquals(expectedBlackjackPayout, actualBlackjackPayout);

			boolean actualDealerWinTies = actualRules.isDealerWinTies();
			boolean expectedDealerWinTies = (Boolean) resultRules.get(13);
			assertEquals(expectedDealerWinTies, actualDealerWinTies);

			String actualWinPayout = actualRules.getWinPayout();
			String expectedWinPayout = (String) resultRules.get(14);
			assertEquals(expectedWinPayout, actualWinPayout);
		}
	}
}
