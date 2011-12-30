package com.thoughtworks.wowcoverage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CoverageDataReader {

	private double totalMissedInstructions = 0;
	private double totalCoveredINstructions = 0;
	private double totalCoveredBranches = 0;
	private double totalMissedBraches = 0;
	
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
			parseLine(input, currentLine);
			currentLine = input.readLine();
		}
	}

	private void parseLine(BufferedReader input, String currentLine)
			throws IOException {
		String[] lineOfCSV = currentLine.split(",");
		totalMissedInstructions += Integer.parseInt(lineOfCSV[3]);
		totalCoveredINstructions += Integer.parseInt(lineOfCSV[4]);
		totalMissedBraches += Integer.parseInt(lineOfCSV[5]);
		totalCoveredBranches += Integer.parseInt(lineOfCSV[6]);
	}

	public double instructionCoverage() {
		return totalCoveredINstructions / (totalCoveredINstructions + totalMissedInstructions);
	}

	public double branchCoverage() {
		return totalCoveredBranches / (totalMissedBraches + totalCoveredBranches);
	}

}
