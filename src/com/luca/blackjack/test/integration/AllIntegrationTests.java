package com.luca.blackjack.test.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ DealerHand.class, //
		BasicRecord.class, //
		DealerHand.class, //
		Importer.class, //
		PlayerHand.class, //
		StandardReport.class, //
		VegasTable.class, //
		VegasTableStandardRules.class,
		VegasTableDealerWinTies.class,
		VegasTableSoft17.class }) //
public class AllIntegrationTests {

}