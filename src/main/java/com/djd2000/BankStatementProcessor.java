package com.djd2000;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor {
	private List<BankTransaction> bankTransactions;

	public BankStatementProcessor(List<BankTransaction> bankTransactions) {
		this.bankTransactions = bankTransactions;
	}

	public double calculateTotalAmount() {
		double total = 0d;
		for (BankTransaction transaction : bankTransactions) {
			total += transaction.getAmount();

		}
		return total;
	}

	public List<BankTransaction> selectInMonth(Month month) {

		List<BankTransaction> bankTransactionsInMonth = new ArrayList<>();
		for (BankTransaction bankTransaction : bankTransactions) {
			if (bankTransaction.getDate().getMonth() == month) {
				bankTransactionsInMonth.add(bankTransaction);
			}

		}
		return bankTransactionsInMonth;
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
			if (bankTransaction.getDate().isAfter(from.minusDays(1)) && bankTransaction.getDate().isBefore(to.plusDays(1))) {
				total += bankTransaction.getAmount();
			}
		}
		return total;
	}
	
	public List<BankTransaction> selectOverAmount(Double minAmount) {

		List<BankTransaction> bankTransactionsOverMinimum = new ArrayList<>();
		for (BankTransaction bankTransaction : bankTransactions) {
			if (bankTransaction.getAmount() > minAmount) {
				bankTransactionsOverMinimum.add(bankTransaction);
			}

		}
		return bankTransactionsOverMinimum;
	}
}
