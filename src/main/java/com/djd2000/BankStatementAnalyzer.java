package com.djd2000;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class BankStatementAnalyzer {

	public void analyze(String fileName, BankStatementParser bankStatementParser) throws Exception {
		List<String> lines = Files.readAllLines(Paths.get(fileName));

		List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
		BankTransactionProcessor bankStatementProcessor = new BankTransactionProcessor(bankTransactions);

		collectSummary(bankStatementProcessor);

		SummaryStatistics summaryStatistics = generateStatistics(bankStatementProcessor);

		Exporter exporter = new HTMLExporter();
		System.out.println(exporter.export(summaryStatistics));

	}

	private SummaryStatistics generateStatistics(BankTransactionProcessor bankStatementProcessor) {

		List<BankTransaction> transactIonList = bankStatementProcessor
				.findTransactions(t -> t.getDate().getYear() == 2017);
		double max = transactIonList.stream().max(Comparator.comparing(BankTransaction::getAmount)).get().getAmount();
		double min = transactIonList.stream().min(Comparator.comparing(BankTransaction::getAmount)).get().getAmount();
		Optional<Double> sum = transactIonList.stream().map(transaction -> transaction.getAmount()).reduce(Double::sum);
		double average = sum.get() / transactIonList.stream().count();

		return new SummaryStatistics(sum.get(), max, min, Math.round(average));
	}

	public void collectSummary(BankTransactionProcessor bankStatementProcessor) {

		System.out.println("The total for all transactions is " + bankStatementProcessor
				.summarizeTransactions((acc, bankTransaction) -> acc + bankTransaction.getAmount()));

		System.out.println("Transactions in " + Month.JANUARY + " : ");
		for (BankTransaction transaction : bankStatementProcessor
				.findTransactions(t -> t.getDate().getMonth().equals(Month.JANUARY))) {
			System.out.println(transaction);
		}

		System.out.println("Transactions in " + Month.FEBRUARY + " : ");
		for (BankTransaction transaction : bankStatementProcessor
				.findTransactions(t -> t.getDate().getMonth().equals(Month.FEBRUARY))) {
			System.out.println(transaction);
		}

		System.out.println("The total salary received is " + bankStatementProcessor
				.summarizeTransactions((acc, bankTransaction) -> bankTransaction.getDescription().equals("Salary")
						? acc + bankTransaction.getAmount()
						: acc));
//		System.out.println("The total transactions in the range are " + bankStatementProcessor
//				.calculateTotalForRange(LocalDate.of(2017, 02, 03), LocalDate.of(2017, 02, 05)));

		System.out.println("Transactions greater than $1000 : ");
		for (BankTransaction transaction : bankStatementProcessor
				.findTransactions(bankTransaction -> bankTransaction.getAmount() > 1000d)) {
			System.out.println(transaction);
		}

	}

}
