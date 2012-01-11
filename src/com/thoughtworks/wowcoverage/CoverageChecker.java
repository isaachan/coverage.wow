package com.thoughtworks.wowcoverage;

public class CoverageChecker {

	private final CoverageDataReader coverageDataReader;
	private final CoverageConfiguration configuration;

	public CoverageChecker(CoverageDataReader coverageDataReader, CoverageConfiguration coverageFileUpdator) {
		this.coverageDataReader = coverageDataReader;
		this.configuration = coverageFileUpdator;
	}

	public void update() {
		double instructionCoverage = Math.max(configuration.lastInstructionCoverage(), coverageDataReader.instructionCoverage());
		double branchCoverage = Math.max(configuration.lastBranchCoverage(), coverageDataReader.branchCoverage());
		
		configuration.update(instructionCoverage, branchCoverage);
	}

	public boolean coverageDesreased() {
		return configuration.lastInstructionCoverage() > coverageDataReader.instructionCoverage() ||
			   configuration.lastBranchCoverage() > coverageDataReader.branchCoverage();
	}

	public void report() {
		System.out.print("test coverage last    InstructionCoverage  is " + configuration.lastInstructionCoverage() + "\n");
		System.out.print("test coverage current InstructionCoverage  is " + coverageDataReader.instructionCoverage() + "\n");
		System.out.print("test coverage last    BranchCoverage  is " + configuration.lastBranchCoverage() + "\n");
		System.out.print("test coverage current BranchCoverage  is " + coverageDataReader.branchCoverage() + "\n");
	}

	
}
