package com.luca.blackjack.test.unit;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.luca.blackjack.report.Record;

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

	@Test(expected = IllegalArgumentException.class)
	public final void setDateNoDate() {
		record.setDate(null);
	}
	
	@Test
	public final void setGetDate() {
		Calendar date = Calendar.getInstance();
		record.setDate(date);
		assertEquals(date, record.getDate());
	}

	@Test(expected = IllegalArgumentException.class)
	public final void setTypeNoType() {
		record.setType(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void setNameNoName() {
		record.setName(null);
	}
	
	@Test
	public final void setGetName() {
		String name = "record";
		record.setName(name);
		assertEquals(name, record.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public final void setDescriptionNoDescription() {
		record.setDescription(null);
	}
	
	@Test
	public final void setGetDescription() {
		String description = "description";
		record.setDescription(description);
		assertEquals(description, record.getDescription());
	}
}
