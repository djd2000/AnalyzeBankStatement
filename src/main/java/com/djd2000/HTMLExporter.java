package com.djd2000;

public class HTMLExporter implements Exporter {

	@Override
	public String export(SummaryStatistics summaryStatistics) {

		String result = "<STRONG>The sum is</STRONG>: " + summaryStatistics.getSum();
		result += "<STRONG>The average is</STRONG>: " + summaryStatistics.getAverage();
		result += "<STRONG>The max is</STRONG>: " + summaryStatistics.getMax();
		result += "<STRONG>The min is</STRONG>: " + summaryStatistics.getMin();
		return result;
	}

}
