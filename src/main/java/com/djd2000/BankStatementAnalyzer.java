package com.djd2000;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {

	public void analyze(String fileName, BankStatementParser bankStatementParser) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(fileName));

		List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
		BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

		collectSummary(bankStatementProcessor);
	}

	public void collectSummary(BankStatementProcessor bankStatementProcessor) {
		System.out.println("The total for all transactions is " + bankStatementProcessor.calculateTotalAmount());

		System.out.println("Transactions in " + Month.JANUARY + " : ");
		for (BankTransaction transaction : bankStatementProcessor.selectInMonth(Month.JANUARY)) {
			System.out.println(transaction);
		}

		System.out.println("Transactions in " + Month.FEBRUARY + " : ");
		for (BankTransaction transaction : bankStatementProcessor.selectInMonth(Month.FEBRUARY)) {
			System.out.println(transaction);
		}
		System.out
				.println("The total salary received is " + bankStatementProcessor.calculateTotalForCategory("Salary"));
		System.out
		.println("The total transactions in the range are " + bankStatementProcessor.calculateTotalForRange(LocalDate.of(2017, 02, 03), LocalDate.of(2017, 02, 05)));
	}

}
