package com.vincze.gergely;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class UtilTest {
	
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	
	@Test
	public void testEvaluateAnswersWithOkInputs() {
		List<String> methodOut1 = Util.evaluateAnswers(" \"kuki\" ");
		List<String> methodOut2 = Util.evaluateAnswers(" \"kuki\" \"kiki\" ");
		List<String> expected = new ArrayList<String>();
		expected.add("kuki");
		assertEquals(expected, methodOut1);
		expected.add("kiki");
		assertEquals(expected, methodOut2);
	}
	
	@Test
	public void testEvaluateAnswersWithWrongInputs() {
		List<String> methodOut1 = Util.evaluateAnswers(" \"kuki\"\"kiki\"\"kuku\" ");
		Assert.assertEquals(Consts.WRONG_ANSWER_FORMAT_MSG, systemOutRule.getLog().trim());
		assertNull(methodOut1);
		
		List<String> methodOut2= Util.evaluateAnswers(" \"kuki\"  \"kiki\" \"kuku\" ");
		assertNull(methodOut2);
		
		List<String> methodOut3 = Util.evaluateAnswers(" \"kuki \"kiki\" \"kuku\" ");
		assertNull(methodOut3);
		
		List<String> methodOut4 = Util.evaluateAnswers("kuki\" \"kiki\" \"kuku\" ");
		assertNull(methodOut4);
		
		List<String> methodOut5 = Util.evaluateAnswers(" \"kuki \"kiki\" \"kuku\" ");
		assertNull(methodOut5);
		
		List<String> methodOut6 = Util.evaluateAnswers(" \"kuki\"\"kiki\" \"kuku ");
		assertNull(methodOut6);
		
		
	}
	
	@Test
	public void testCheckQuestionMarksWithOkInputs() {
		String[] methodOut1 = Util.checkQuestionMarks("the question ? \"kuki\"");
		String[] methodOut4 = Util.checkQuestionMarks("Is it only a question?");
		String[] expected1 = {' ' + "the question ", " \"kuki\" "};
		String[] expected4 = {' ' + "Is it only a question", " "};
		assertArrayEquals( expected1, methodOut1);
		assertArrayEquals(expected4, methodOut4);
	}
	
	@Test
	public void testCheckQuestionMarkWithMultipleQuestionMarks() {
		String[] methodOut2 = Util.checkQuestionMarks("the question ? was? \"kuki\"");
		Assert.assertEquals(Consts.MULTIPLE_QUESTIONMARK_MSG, systemOutRule.getLog().trim());
		assertNull(methodOut2);
	}
	
	@Test
	public void testCheckQuestionMarkWithMissingQuestionMark() {
		String[] methodOut3 = Util.checkQuestionMarks("there is no question");
		Assert.assertEquals(Consts.MISSING_QUESTIONMARK_MSG, systemOutRule.getLog().trim());
		assertNull(methodOut3);
	}
	
}
