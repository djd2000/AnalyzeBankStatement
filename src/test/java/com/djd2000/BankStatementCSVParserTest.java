package com.djd2000;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class BankStatementCSVParserTest {

	private BankStatementCSVParser statementParser = new BankStatementCSVParser();

	@Test
	public void shouldParseOneCorrectline() {

		String line = "30-01-2017,-50,Tesco";

		BankTransaction result = statementParser.parseFrom(line);

		BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50d, "Tesco");

		assertEquals(expected.getDate(), result.getDate());
		assertEquals(expected.getAmount(), result.getAmount(), 0.0d);
		assertEquals(expected.getDescription(), result.getDescription());

	}

	@Test
	public void shouldParseTwoBankTransactions() {
		
		List<String> lines = Arrays.asList("30-01-2017,-50,Tesco","10-02-2017,-90,Deliveroo");
		
		List<BankTransaction> result = statementParser.parseLinesFrom(lines);
		
		assertEquals(2, result.size());
		
	}

}
