package com.djd2000;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {

	private static final int EXPECTED_COLUMN_COUNT = 3;
	private final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	@Override
	public BankTransaction parseFrom(String line) throws Exception {
		String[] columns = line.split(",");
		if (columns.length < EXPECTED_COLUMN_COUNT) {
			throw new CSVSyntaxException("CSV issue");
		}

		Notification notification;
		BankTransactionValidator validator = new BankTransactionValidator(columns[2], columns[0], columns[1]);
		if ((notification = validator.validate()).hasErrors()) {
			throw new IllegalArgumentException(notification.errorMessage());
		}

		LocalDate date;
		double amount;

		date = LocalDate.parse(columns[0], DATE_PATTERN);
		amount = Double.parseDouble(columns[1]);

		String description = columns[2];

		return new BankTransaction(date, amount, description);
	}

	@Override
	public List<BankTransaction> parseLinesFrom(List<String> lines) throws Exception {
		List<BankTransaction> bankTransactions = new ArrayList<>();
		for (String line : lines) {
			bankTransactions.add(parseFrom(line));
		}
		return bankTransactions;
	}

}
