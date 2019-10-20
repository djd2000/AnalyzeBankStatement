package com.djd2000;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BankTransactionValidator {

	private String description;
	private String date;
	private String amount;
	private final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public BankTransactionValidator(String description, String date, String amount) {
		super();
		this.description = description;
		this.date = date;
		this.amount = amount;
	}

	public Notification validate() {
		
		
		Notification notification = new Notification();
		if (this.description.length() > 100) {
			notification.addError("The description is too long");
		}

		LocalDate parsedDate;
		try {
			parsedDate = LocalDate.parse(this.date,DATE_PATTERN);
			if (parsedDate.isAfter(LocalDate.now())) {
				notification.addError("date cannot be in the future");
			}
		} catch (DateTimeParseException e) {
			notification.addError("Invalid format for date");
		}

		try {
		} catch (NumberFormatException e) {
			notification.addError("Invalid format for amount");
		}

		double parsedAmount;
		try {
			parsedAmount = Double.parseDouble(this.amount);
		} catch (NumberFormatException e) {
			notification.addError("Invalid format for amount");
		}
		return notification;
	}
}
