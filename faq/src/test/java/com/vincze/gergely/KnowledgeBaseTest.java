package com.vincze.gergely;

import static org.junit.Assert.assertTrue;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class KnowledgeBaseTest {

	@BeforeClass
	public static void createTestFile() {
		Util.createResourcesFolderIfNotExists();
		KnowledgeBase qa = getDummyQandAObject();
		qa.saveQandAs("resources/testQA.json");
	}

	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	@Test
	public void testPrintAnswerForValidQuestion() {
		KnowledgeBase qa = getDummyQandAObject();
		// this test might not work under linux machine cause of \r\n
		// qa.printAnswer("Could you give me 2 colors");
		// Assert.assertEquals("blue\r\ngreen", systemOutRule.getLog().trim());
		qa.printAnswer("What is 6 * 7");
		Assert.assertEquals("42", systemOutRule.getLog().trim());
	}

	@Test
	public void testPrintAnswerForInvalidQuestion() {
		KnowledgeBase qa = getDummyQandAObject();
		qa.printAnswer("What about life");
		Assert.assertEquals(Consts.ANSWER_FOR_EVERITHING_MSG, systemOutRule.getLog().trim());
	}

	@Test
	public void testSaveQandAs() {

		File file = new File("resources/testQA.json");
		assertTrue(file.exists());
		assertTrue(file.length() != 0);

	}

	@Test
	public void testLoadQandAs() {
		KnowledgeBase qa = getDummyQandAObject();
		KnowledgeBase loadedQA = new KnowledgeBase();
		loadedQA.loadQandAs("resources/testQA.json");
		Assert.assertEquals(loadedQA.getQAMap(), qa.getQAMap());
	}

	@AfterClass
	public static void clean() {
		File file = new File("resources/testQA.json");
		file.delete();
	}

	private static KnowledgeBase getDummyQandAObject() {
		KnowledgeBase qa = new KnowledgeBase();
		List<String> dummyAnswers1 = new ArrayList<String>();
		dummyAnswers1.add("42");
		qa.updateQandAs("What is 6 * 7", dummyAnswers1);

		List<String> dummyAnswers2 = new ArrayList<String>();
		dummyAnswers2.add("blue");
		dummyAnswers2.add("green");
		qa.updateQandAs("Could you give me 2 colors", dummyAnswers2);
		return qa;
	}

}
