package com.luca.blackjack.test.performance;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ Importer.class, //
		VegasTable.class })
public class AllPerformanceTests {

}
