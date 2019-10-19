package com.djd2000;

import java.io.IOException;
import java.nio.file.Paths;

public class MainApplication {

	public static void main(String[] args) throws IOException {

		final String statementPath = Paths.get("statement.csv").toFile().getAbsolutePath();

		BankStatementParser bankStatementParser = new BankStatementCSVParser();

		BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();

		try {
			bankStatementAnalyzer.analyze(statementPath, bankStatementParser);
		} catch (Exception e) {
			System.out.println("unable to process transaction\n " + e);
		}

	}
}
