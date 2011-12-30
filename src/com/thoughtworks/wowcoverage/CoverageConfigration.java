package com.thoughtworks.wowcoverage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CoverageConfigration {

	private String fileContent;
	private final String filePath;

	public CoverageConfigration(String filePath) {
		this.filePath = filePath;
		if (filePath == null) return;
		try {
			init(filePath);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load configration.");
		}
	}
	
	private void init(String filePath) throws IOException {
		File configration = new File(filePath);
		if (!configration.exists()) {
			configration.createNewFile();
		}
		loadConfigrationContent(filePath);
	}

	private void loadConfigrationContent(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		fileContent = reader.readLine();
		reader.close();
		
		if (fileContent == null || fileContent.trim().length() == 0) {
			fileContent = "0 0";
		}
	}

	public void update(double instructionCoverage, double branchCoverage) {
		try {
			FileWriter writer = new FileWriter(filePath);
			writer.write(instructionCoverage + " " + branchCoverage);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException("Failed to write configration.");
		}
	}

	public double lastInstructionCoverage() {
		return Double.parseDouble(fileContent.split(" ")[0]);
	}

	public double lastBranchCoverage() {
		return Double.parseDouble(fileContent.split(" ")[1]);
	}

}
