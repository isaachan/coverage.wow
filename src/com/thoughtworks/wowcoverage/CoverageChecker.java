package com.thoughtworks.wowcoverage;

public class CoverageChecker {

	private final CoverageDataReader coverageDataReader;
	private final CoverageConfigration configration;

	public CoverageChecker(CoverageDataReader coverageDataReader, CoverageConfigration coverageFileUpdator) {
		this.coverageDataReader = coverageDataReader;
		this.configration = coverageFileUpdator;
	}

	public void update() {
		double instructionCoverage = Math.max(configration.lastInstructionCoverage(), coverageDataReader.instructionCoverage());
		double branchCoverage = Math.max(configration.lastBranchCoverage(), coverageDataReader.branchCoverage());
		
		configration.update(instructionCoverage, branchCoverage);
	}

	public boolean coverageDesreased() {
		return configration.lastInstructionCoverage() > coverageDataReader.instructionCoverage() ||
			   configration.lastBranchCoverage() > coverageDataReader.branchCoverage();
	}

	public void report() {
		System.out.print("test coverage last    InstructionCoverage  is " + configration.lastInstructionCoverage() + "\n");
		System.out.print("test coverage current InstructionCoverage  is " + coverageDataReader.instructionCoverage() + "\n");
		System.out.print("test coverage last    BranchCoverage  is " + configration.lastBranchCoverage() + "\n");
		System.out.print("test coverage current BranchCoverage  is " + coverageDataReader.branchCoverage() + "\n");
	}

	
}
