package com.thoughtworks.wowcoverage;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Test;

public class CoverageDataReaderTest {

	@Test
	public void calculate_coverage_of_code_and_brach_from_one_line() throws IOException {
		String csvTitle = "GROUP,PACKAGE,CLASS,INSTRUCTION_MISSED,INSTRUCTION_COVERED...\n";
		String csvContent = csvTitle + "ZTE HUB,org.jivesoftware.smackx.comands,AdHocCommand.new PacketListern() {...},90,10,1,3,0,0,0,0,0,0\n";
		ByteArrayInputStream csvInput = new ByteArrayInputStream(csvContent.getBytes("utf-8"));
		CoverageDataReader reader = new CoverageDataReader(csvInput);
		
		assertEquals(0.1, reader.instructionCoverage(), 0.01);
		assertEquals(0.75, reader.branchCoverage(), 0.01);
	}
	
	@Test
	public void calculate_coverage_of_code_and_brach_from_multiple_lines() throws IOException {
		String csvTitle = "GROUP,PACKAGE,CLASS,INSTRUCTION_MISSED,INSTRUCTION_COVERED...\n";
		String csvContent = csvTitle + "ZTE HUB,org.jivesoftware.smackx.comands,AdHocCommand.new PacketListern() {...},10,10,4,1,0,0,0,0,0,0\n" +
				                       "ZTE HUB,org.jivesoftware.smackx.comands,AdHocCommand.new PacketListern() {...},20,0,2,3,0,0,0,0,0,0\n";
		ByteArrayInputStream csvInput = new ByteArrayInputStream(csvContent.getBytes("utf-8"));
		CoverageDataReader reader = new CoverageDataReader(csvInput);
		
		assertEquals(0.25, reader.instructionCoverage(), 0.01);
		assertEquals(0.4, reader.branchCoverage(), 0.01);
	}
}
