package com.djd2000;

import java.util.ArrayList;
import java.util.List;

public class Notification {
	private List<String> errors = new ArrayList<>();

	public void addError(String message) {
		errors.add(message);
	}

	public boolean hasErrors() {
		return !errors.isEmpty();
	}

	public String errorMessage() {
		return errors.toString();
	}

	public List<String> getErrors() {
		return this.errors;
	}
}
