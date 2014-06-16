package com.luca.blackjack.test.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ BasicPlayerReport.class, //
		BasicRecord.class, //
		Card.class, //
		DealerHand.class, //
		DefaultPlayerGameStrategy.class, //
		DefaultPlayerGameStrategyV2.class, //
		PlayerHand.class, //
		SimpleDealer.class, //
		SimplePlayer.class, //
		SimpleStrategy.class, //
		StandardBlackjackGame.class, //
		StandardDeck.class, //
		StandardReport.class, //
		StandardRules.class, //
		VegasTable.class //
})
public class AllUnitTests {
}
