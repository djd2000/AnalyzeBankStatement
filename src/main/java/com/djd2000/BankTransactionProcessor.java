package com.djd2000;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionProcessor {
	private final List<BankTransaction> bankTransactions;

	public BankTransactionProcessor(List<BankTransaction> bankTransactions) {
		this.bankTransactions = bankTransactions;
	}

	public double summarizeTransactions(BankTransactionSummarizer bankStatementSummarizer) {
		double result = 0d;
		for (BankTransaction bankTransaction : bankTransactions) {
			result = bankStatementSummarizer.summarize(result, bankTransaction);
		}
		return result;
	}

	public double calculateSumOfAllTransactions() {

		return summarizeTransactions((acc, bankTransaction) -> acc + bankTransaction.getAmount());
	}

	public double calculateTotalForCategory(String category) {
		double total = 0;
		for (BankTransaction bankTransaction : bankTransactions) {
			if (bankTransaction.getDescription().equals(category)) {
				total += bankTransaction.getAmount();
			}
		}
		return total;
	}

	public double calculateTotalForRange(LocalDate from, LocalDate to) {
		double total = 0;
		for (BankTransaction bankTransaction : bankTransactions) {
			if (bankTransaction.getDate().isAfter(from.minusDays(1))
					&& bankTransaction.getDate().isBefore(to.plusDays(1))) {
				total += bankTransaction.getAmount();
			}
		}
		return total;
	}

	public List<BankTransaction> findTransactions(BankTransactionFilter filter) {

		List<BankTransaction> result = new ArrayList<>();
		for (BankTransaction bankTransaction : bankTransactions) {
			if (filter.test(bankTransaction)) {
				result.add(bankTransaction);
			}

		}
		return result;
	}

	public List<BankTransaction> findTransactionsGreaterThan(int amount) {
		return findTransactions(bankTransaction -> bankTransaction.getAmount() > amount);
	}

}
