package com.djd2000;

import java.time.LocalDate;

public class BankTransaction {

	private LocalDate date;
	private double amount;
	private String description;

	public BankTransaction(LocalDate transactionDate, double transactionAmount, String transactionDecsription) {
		super();
		this.date = transactionDate;
		this.amount = transactionAmount;
		this.description = transactionDecsription;
	}

	public LocalDate getDate() {
		return date;
	}

	public double getAmount() {
		return amount;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "[Date=" + date + ", Amount=" + amount
				+ ", Description=" + description + "]";
	}

}
