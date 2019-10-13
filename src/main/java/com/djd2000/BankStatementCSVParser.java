package com.djd2000;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {

	private final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	@Override
	public BankTransaction parseFrom(String line) {
		String[] columns = line.split(",");

		LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
		double amount = Double.parseDouble(columns[1]);
		String description = columns[2];

		return new BankTransaction(date, amount, description);
	}

	@Override
	public List<BankTransaction> parseLinesFrom(List<String> lines) {
		List<BankTransaction> bankTransactions = new ArrayList<>();
		for (String line : lines) {
			bankTransactions.add(parseFrom(line));
		}
		return bankTransactions;
	}

}
