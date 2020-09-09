package com.vincze.gergely;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QandAs {
	
	private Map<String, List<String>> QAMap;

	public QandAs() {
		QAMap = new HashMap<String, List<String>>();
	}
	
	public void updateQandAs(String question, List<String> answers) {
		if (answers != null) {
			QAMap.put(question, answers);
		}
	}
	
	public void printAnswer(String question) {
		try {
			for (String answer : QAMap.get(question)) {
				System.out.println(answer);
			}
		} catch (NullPointerException e) {
			System.out.println("the answer to life, universe and everything is 42");
		}
	}
	
	

}
