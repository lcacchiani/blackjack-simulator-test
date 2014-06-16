package com.luca.blackjack.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.luca.blackjack.test.integration.AllIntegrationTests;
import com.luca.blackjack.test.performance.AllPerformanceTests;
import com.luca.blackjack.test.unit.AllUnitTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AllIntegrationTests.class, //
		AllPerformanceTests.class, //
		AllUnitTests.class })
public class AllTests {
}
