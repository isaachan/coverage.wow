package com.thoughtworks.wowcoverage;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Test;

public class CoverageCheckerTest {

	private FakeCoverageConfigration updater = new FakeCoverageConfigration(0.5, 0.5);

	@Test
	public void should_update_configration_file_if_coverage_increased() throws IOException {
		CoverageChecker checker = new CoverageChecker(new FakeCoverageDataReader(0.6, 0.7), updater);
		checker.update();
		
		assertFalse(checker.coverageDesreased());
		assertEquals("0.6 0.7", updater.message);
	}
	
	@Test
	public void should_update_instrunction_coverage_if_it_increased() throws IOException {
		CoverageChecker checker = new CoverageChecker(new FakeCoverageDataReader(0.6, 0.4), updater);
		checker.update();
		
		assertTrue(checker.coverageDesreased());
		assertEquals("0.6 0.5", updater.message);
	}

	@Test
	public void should_update_branch_coverage_if_it_increased() throws IOException {
		CoverageChecker checker = new CoverageChecker(new FakeCoverageDataReader(0.4, 0.7), updater);
		checker.update();
		
		assertTrue(checker.coverageDesreased());
		assertEquals("0.5 0.7", updater.message);
	}
	
	@Test
	public void should_not_update_if_notnhing_increased() throws IOException {
		CoverageChecker checker = new CoverageChecker(new FakeCoverageDataReader(0.4, 0.4), updater);
		checker.update();
		
		assertTrue(checker.coverageDesreased());
		assertEquals("0.5 0.5", updater.message);
	}
}

class FakeCoverageConfigration extends CoverageConfigration {

	String message = "";
	private double instrucntionCoverage;
	private final double branchCoverage;

	public FakeCoverageConfigration(double instrucntionCoverage, double branchCoverage) {
		super(null);
		this.instrucntionCoverage = instrucntionCoverage;
		this.branchCoverage = branchCoverage;
	}


	@Override
	public double lastInstructionCoverage() {
		return instrucntionCoverage;
	}

	@Override
	public double lastBranchCoverage() {
		return branchCoverage;
	}
	
	@Override
	public void update(double instructionCoverage, double branchConverage) {
		message = instructionCoverage + " " + branchConverage;
	}
}

class FakeCoverageDataReader extends CoverageDataReader {

	private final double instrunctionCoverage;
	private final double brancheCoverage;

	public FakeCoverageDataReader(double instrunctionCoverage, double brancheCoverage) throws IOException {
		super(new ByteArrayInputStream("".getBytes()));
		this.instrunctionCoverage = instrunctionCoverage;
		this.brancheCoverage = brancheCoverage;
	}

	@Override
	public double instructionCoverage() {
		return instrunctionCoverage;
	}
	
	@Override
	public double branchCoverage() {
		return brancheCoverage;
	}
}