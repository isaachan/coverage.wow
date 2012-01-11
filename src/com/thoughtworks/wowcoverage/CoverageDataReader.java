package com.thoughtworks.wowcoverage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CoverageDataReader {

	private double totalMissedInstructions = 0;
	private double totalCoveredInstructions = 0;
	private double totalCoveredBranches = 0;
	private double totalMissedBranches = 0;
	
	public CoverageDataReader(String cvsFilePath) throws IOException {
		this(new FileInputStream(cvsFilePath));
	}
	
	public CoverageDataReader(InputStream csvInput) throws IOException {
		parse(csvInput);
	}

	private void parse(InputStream csvInput) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(csvInput));
		String currentLine = input.readLine();
		currentLine = input.readLine();
		while (currentLine != null) {
			parseLine(currentLine);
			currentLine = input.readLine();
		}
	}

	private void parseLine(String currentLine) throws IOException {
		String[] lineOfCSV = currentLine.split(",");
		totalMissedInstructions += Integer.parseInt(lineOfCSV[3]);
		totalCoveredInstructions += Integer.parseInt(lineOfCSV[4]);
		totalMissedBranches += Integer.parseInt(lineOfCSV[5]);
		totalCoveredBranches += Integer.parseInt(lineOfCSV[6]);
	}

	public double instructionCoverage() {
		return totalCoveredInstructions / (totalCoveredInstructions + totalMissedInstructions);
	}

	public double branchCoverage() {
		return totalCoveredBranches / (totalMissedBranches + totalCoveredBranches);
	}

}
