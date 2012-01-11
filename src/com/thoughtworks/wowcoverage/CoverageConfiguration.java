package com.thoughtworks.wowcoverage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CoverageConfiguration {

	private String fileContent;
	private final String filePath;

	public CoverageConfiguration(String filePath) {
		this.filePath = filePath;
		if (filePath == null) return;
		try {
			init(filePath);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load configuration.");
		}
	}
	
	private void init(String filePath) throws IOException {
		File configuration = new File(filePath);
		if (!configuration.exists()) {
			configuration.createNewFile();
		}
		loadConfigurationContent(filePath);
	}

	private void loadConfigurationContent(String filePath) throws IOException {
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
			throw new RuntimeException("Failed to write configuration.");
		}
	}

	public double lastInstructionCoverage() {
		return Double.parseDouble(fileContent.split(" ")[0]);
	}

	public double lastBranchCoverage() {
		return Double.parseDouble(fileContent.split(" ")[1]);
	}

}
