package com.luca.blackjack.test.integration;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.luca.blackjack.report.Record;
import com.luca.blackjack.report.RecordType;

public class BasicRecord {
	
	Record record;

	public BasicRecord() {
	}

	@Before
	public final void setup() {
		record = new com.luca.blackjack.report.BasicRecord();
	}

	@After
	public final void tearDown() {
		record = null;
	}
	
	@Test
	public final void setGetType() {
		RecordType type = RecordType.EVALUATION;
		record.setType(type);
		assertEquals(type, record.getType());
	}
}
