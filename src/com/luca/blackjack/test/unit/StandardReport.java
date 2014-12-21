package com.luca.blackjack.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.luca.blackjack.report.PlayerReport;
import com.luca.blackjack.report.Record;
import com.luca.blackjack.report.Report;
import com.luca.blackjack.user.Player;

@RunWith(JMock.class)
public class StandardReport {
	
	private Mockery context = new JUnit4Mockery();

	Report report;
	Record record;
	Player player;

	public StandardReport() {
		record = context.mock(Record.class);
		player = context.mock(Player.class);
	}

	@Before
	public final void setup() {
		report = new com.luca.blackjack.report.StandardReport();
	}

	@After
	public final void tearDown() {
		report = null;
	}

	@Test(expected = IllegalArgumentException.class)
	public final void setDateNoDate() {
		report.setDate(null);
	}
	
	@Test
	public final void setGetDate() {
		Calendar date = Calendar.getInstance();
		report.setDate(date);
		assertEquals(date, report.getDate());
	}

	@Test(expected = IllegalArgumentException.class)
	public final void setNameNoName() {
		report.setName(null);
	}
	
	@Test
	public final void setGetName() {
		String name = "record";
		report.setName(name);
		assertEquals(name, report.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public final void setRecordsNoRecords() {
		report.setRecords(null);
	}
	
	@Test
	public final void setGetRecords() {
		List<Record> records = new ArrayList<Record>(); 
		report.setRecords(records);
		assertEquals(records, report.getRecords());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void addRecordNoRecord() {
		report.addRecord(null);
	}
	
	@Test
	public final void addRecord() {
		List<Record> records = new ArrayList<Record>(); 
		report.setRecords(records);
		report.addRecord(record);
		Record actualRecord = report.getRecords().get(0);
		assertEquals(record, actualRecord);		
	}
	
	@Test
	public final void addRecordNoRecords() {
		report.addRecord(record);
		Record actualRecord = report.getRecords().get(0);
		assertEquals(record, actualRecord);		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void initialisePlayerReportNoPlayer() {
		report.initialisePlayerReport(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void initialisePlayerReportPlayerAlreadyInit() {
		context.checking(new Expectations() {
			{
				exactly(3).of(player).getName();
				will(returnValue("player"));
				oneOf(player).getBalance();
				will(returnValue(1d));
			}
		});
		report.initialisePlayerReport(player);
		report.initialisePlayerReport(player);
	}
	
	@Test
	public final void initialiseFinalisePlayerReport() {
		context.checking(new Expectations() {
			{
				exactly(4).of(player).getName();
				will(returnValue("player"));
				exactly(2).of(player).getBalance();
				will(returnValue(1d));
				oneOf(player).getTopUpNo();
				will(returnValue(1));
			}
		});
		report.initialisePlayerReport(player);
		List<PlayerReport> reportsInit = report.getPlayerReports();
		assertFalse(reportsInit.isEmpty());
		assertEquals(1, reportsInit.size());
		report.finalisePlayerReport(player);
		List<PlayerReport> reportsFinal = report.getPlayerReports();
		assertFalse(reportsFinal.isEmpty());
		assertEquals(1, reportsFinal.size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void finalisePlayerReportNoPlayer() {
		report.finalisePlayerReport(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void finalisePlayerReportNoPlayerReport() {
		report.finalisePlayerReport(player);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void finalisePlayerReportPlayerAlreadyInit() {
		context.checking(new Expectations() {
			{
				exactly(2).of(player).getName();
				will(returnValue("player"));
				oneOf(player).getBalance();
				will(returnValue(1d));
				oneOf(player).getName();
				will(returnValue("player1"));
			}
		});
		report.initialisePlayerReport(player);
		report.finalisePlayerReport(player);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void setPlayerReportsNoReports() {
		report.setPlayerReports(null);
	}
	
	@Test
	public final void setGetPlayerReports() {
		List<PlayerReport> playerReports = new ArrayList<PlayerReport>();
		report.setPlayerReports(playerReports);
		assertEquals(playerReports, report.getPlayerReports());
	}
}
