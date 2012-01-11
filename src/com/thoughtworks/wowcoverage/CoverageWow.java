package com.thoughtworks.wowcoverage;

import java.io.IOException;

public class CoverageWow {

	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.err.println("java -jar coveragewow.jar [coverage-file] [coverage-data]");
			System.exit(-1);
		}
		
		CoverageConfiguration configuration = new CoverageConfiguration(args[0]);
		CoverageDataReader cvsReader = new CoverageDataReader(args[1]);
		CoverageChecker checker = new CoverageChecker(cvsReader, configuration);
		checker.update();
		checker.report();
		
		if (checker.coverageDesreased()) System.exit(-1);
	}
}
