package com.zfsoft.boot.metrics.property;

import java.io.PrintStream;

import com.zfsoft.boot.metrics.MetricsReportProperties;

public class ConsoleReporterProperties extends ReporterProperties {

	public static final String PREFIX = MetricsReportProperties.PREFIX + ".console";
	
	public enum ConsoleStream {
		STDOUT(System.out), STDERR(System.err);

		private final PrintStream printStream;

		ConsoleStream(PrintStream printStream) {
			this.printStream = printStream;
		}

		public PrintStream get() {
			return printStream;
		}
	}
	
	private String timeZone = "UTC";

	private ConsoleStream output = ConsoleStream.STDOUT;

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public ConsoleStream getOutput() {
		return output;
	}

	public void setOutput(ConsoleStream stream) {
		this.output = stream;
	}

}
