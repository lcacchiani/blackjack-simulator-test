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

import com.luca.blackjack.game.Rules;

@RunWith(Parameterized.class)
public class StandardRules {

	Rules rules;

	boolean soft17;
	boolean earlySurrender;
	boolean lateSurrender;
	boolean noSurrenderAllowed;
	String surrender;
	boolean splitSameValue;
	boolean splitSameRank;
	boolean splitAllowed;
	String split;
	int resplit;
	boolean resplitSplitAces;
	boolean hitSplitAces;
	boolean doubleSplitAces;
	boolean noDoubleAfterSplit;
	boolean renoRule;
	boolean renoRuleEuropean;
	boolean noHoleCard;
	boolean obo;
	String blackjackPayout;
	boolean dealerWinTies;
	String winPayout;
	List<Object> results;

	public StandardRules(List<Object> inputs, List<Object> results) {
		this.soft17 = (Boolean) inputs.get(0);
		this.surrender = (String) inputs.get(1);
		this.split = (String) inputs.get(14);
		this.resplit = (Integer) inputs.get(2);
		this.resplitSplitAces = (Boolean) inputs.get(3);
		this.hitSplitAces = (Boolean) inputs.get(4);
		this.doubleSplitAces = (Boolean) inputs.get(5);
		this.noDoubleAfterSplit = (Boolean) inputs.get(6);
		this.renoRule = (Boolean) inputs.get(7);
		this.renoRuleEuropean = (Boolean) inputs.get(8);
		this.noHoleCard = (Boolean) inputs.get(9);
		this.obo = (Boolean) inputs.get(10);
		this.blackjackPayout = (String) inputs.get(11);
		this.dealerWinTies = (Boolean) inputs.get(12);
		this.winPayout = (String) inputs.get(13);
		this.results = results;
	}

	@Before
	public final void setup() {
		rules = new com.luca.blackjack.game.StandardRules();
		rules.setSoft17(soft17);
		rules.setSurrender(surrender);
		rules.setSplit(split);
		rules.setResplit(resplit);
		rules.setResplitSplitAces(resplitSplitAces);
		rules.setHitSplitAces(hitSplitAces);
		rules.setDoubleSplitAces(doubleSplitAces);
		rules.setNoDoubleAfterSplit(noDoubleAfterSplit);
		rules.setRenoRule(renoRule);
		rules.setRenoRuleEuropean(renoRuleEuropean);
		rules.setNoHoleCard(noHoleCard);
		rules.setObo(obo);
		rules.setBlackjackPayout(blackjackPayout);
		rules.setDealerWinTies(dealerWinTies);
		rules.setWinPayout(winPayout);
	}

	@After
	public final void tearDown() {
		rules = null;
	}

	@Parameters
	public static Collection<Object[]> data() {

		ArrayList<Object> inputs0 = new ArrayList<Object>();
		inputs0.addAll(Arrays.asList(false, "early-surrender", 2, false, false,
				false, false, false, false, false, false, "3:2", false, "2:1",
				"no-split"));
		List<Object> results0 = new ArrayList<Object>();
		results0.addAll(Arrays.asList(1.5, 2.0, true, false, true, false,
				false, false));

		ArrayList<Object> inputs1 = new ArrayList<Object>();
		inputs1.addAll(Arrays.asList(false, "no-surrender", 2, false, false,
				false, false, false, true, false, false, "15:9", false, "8:7",
				"same-value"));
		List<Object> results1 = new ArrayList<Object>();
		results1.addAll(Arrays.asList(1.666, 1.142, false, false, false, true,
				false, true));

		ArrayList<Object> inputs2 = new ArrayList<Object>();
		inputs2.addAll(Arrays.asList(false, "late-surrender", 2, false, false,
				false, false, false, false, false, false, "3:2", false, "2:1",
				"same-rank"));
		List<Object> results2 = new ArrayList<Object>();
		results2.addAll(Arrays.asList(1.5, 2.0, false, true, true, false, true,
				true));

		return Arrays.asList(new Object[][] { { inputs0, results0 },
				{ inputs1, results1 }, { inputs2, results2 } });
	}

	@Test(expected = IllegalArgumentException.class)
	public final void constructorWrongNoReSplit() {
		com.luca.blackjack.game.StandardRules rules = new com.luca.blackjack.game.StandardRules();
		rules.setResplit(-2);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void constructorRenoRuleAndEuropeanRenoRule() {
		com.luca.blackjack.game.StandardRules rules = new com.luca.blackjack.game.StandardRules();
		rules.setRenoRule(true);
		rules.setRenoRuleEuropean(true);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void constructorWrongBlackJackPayout1() {
		com.luca.blackjack.game.StandardRules rules = new com.luca.blackjack.game.StandardRules();
		rules.setBlackjackPayout("32");
	}

	@Test(expected = IllegalArgumentException.class)
	public final void constructorWrongBlackJackPayout2() {
		com.luca.blackjack.game.StandardRules rules = new com.luca.blackjack.game.StandardRules();
		rules.setBlackjackPayout("32:");
	}

	@Test(expected = IllegalArgumentException.class)
	public final void constructorWrongBlackJackPayout3() {
		com.luca.blackjack.game.StandardRules rules = new com.luca.blackjack.game.StandardRules();
		rules.setBlackjackPayout(":32");
	}

	@Test(expected = IllegalArgumentException.class)
	public final void constructorWrongWinPayout() {
		com.luca.blackjack.game.StandardRules rules = new com.luca.blackjack.game.StandardRules();
		rules.setWinPayout("21");
	}

	@Test(expected = IllegalArgumentException.class)
	public final void constructorNoSurrender() {
		com.luca.blackjack.game.StandardRules rules = new com.luca.blackjack.game.StandardRules();
		rules.setSurrender(null);
	}

	@Test
	public final void isObjectInitialised() {
		assertEquals(true, rules.isInitialised());
	}

	@Test
	public final void getBlackjackPayout() {
		double actual = rules.getBlackjackPayoutValue();
		double expected = (Double) results.get(0);
		assertEquals(expected, actual, 0.001);
	}

	@Test
	public final void getWinPayout() {
		double actual = rules.getWinPayoutValue();
		double expected = (Double) results.get(1);
		assertEquals(expected, actual, 0.001);
	}

	@Test
	public final void getSurrender() {
		boolean actualEarlySurrender = rules.isEarlySurrender();
		boolean expectedEarlySurrender = (Boolean) results.get(2);
		assertEquals(expectedEarlySurrender, actualEarlySurrender);
		boolean actualLateSurrender = rules.isLateSurrender();
		boolean expectedLateSurrender = (Boolean) results.get(3);
		assertEquals(expectedLateSurrender, actualLateSurrender);
		boolean actualSurrenderAllowed = rules.isSurrenderAllowed();
		boolean expectedSurrenderAllowed = (Boolean) results.get(4);
		assertEquals(expectedSurrenderAllowed, actualSurrenderAllowed);
	}

	@Test
	public final void getSplit() {
		boolean actualSplitSameValue = rules.isSplitSameValue();
		boolean expectedSplitSameValue = (Boolean) results.get(5);
		assertEquals(expectedSplitSameValue, actualSplitSameValue);
		boolean actualSplitSameRank = rules.isSplitSameRank();
		boolean expectedSplitSameRank = (Boolean) results.get(6);
		assertEquals(expectedSplitSameRank, actualSplitSameRank);
		boolean actualSplitAllowed = rules.isSplitAllowed();
		boolean expectedSplitAllowed = (Boolean) results.get(7);
		assertEquals(expectedSplitAllowed, actualSplitAllowed);
	}
}
