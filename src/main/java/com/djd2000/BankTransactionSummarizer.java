package com.djd2000;

@FunctionalInterface
public interface BankTransactionSummarizer {

	double summarize(double accumulator, BankTransaction bankTransaction);

}
