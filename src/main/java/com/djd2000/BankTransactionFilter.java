package com.djd2000;

@FunctionalInterface
public interface BankTransactionFilter {

	boolean test(BankTransaction t);

}
