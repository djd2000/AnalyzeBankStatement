package com.djd2000;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BankStatementProcessorTest {

	List<BankTransaction> bankTransactions = new ArrayList<>(); 
	
	@Before
	public void setUp() throws Exception {
		
		bankTransactions.add(new BankTransaction(LocalDate.of(2017, 01, 01), -100d, "Tesco"));
		bankTransactions.add(new BankTransaction(LocalDate.of(2017, 03, 20), 6000d, "Salary"));
		bankTransactions.add(new BankTransaction(LocalDate.of(2017, 01, 10), -200d, "Currys"));
		
	}

	@Test
	public void testCalculateTotalAmount() {
		BankStatementProcessor processor = new BankStatementProcessor(bankTransactions);
		assertEquals(5700d, processor.calculateTotalAmount(),0);
		
	}

	@Test
	public void testSelectInMonth() {
		BankStatementProcessor processor = new BankStatementProcessor(bankTransactions);
		assertEquals(2, processor.selectInMonth(Month.JANUARY).size());
	}

	@Test
	public void testCalculateTotalForCategory() {
		BankStatementProcessor processor = new BankStatementProcessor(bankTransactions);
		assertEquals(6000d, processor.calculateTotalForCategory("Salary"),0);
	}

}
