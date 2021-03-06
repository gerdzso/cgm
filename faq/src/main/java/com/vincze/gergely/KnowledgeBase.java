package com.vincze.gergely;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class KnowledgeBase {

	private Map<String, List<String>> QAMap;

	/**
	 * getter for QandAs dataholder HashMap
	 * 
	 * @returndataholder HashMap
	 */
	public Map<String, List<String>> getQAMap() {
		return this.QAMap;
	}

	/**
	 * Constructor of QandA object
	 * 
	 */
	public KnowledgeBase() {
		QAMap = new HashMap<String, List<String>>();
	}

	/**
	 * Constructor of QandA object
	 * 
	 * @param qandaFilePath file path of a qanda json file
	 */
	public KnowledgeBase(String qandaFilePath) {
		if (qandaFilePath != null) {
			loadQandAs(qandaFilePath);
		} else {
			new KnowledgeBase();
		}
	}

	/**
	 * Add or update answers for the question
	 * 
	 * @param question String of a question
	 * @param answers  List of strings of answers for the question
	 */
	public void updateQandAs(String question, List<String> answers) {
		if (answers != null && question != null) {
			QAMap.put(question, answers);
		}
	}

	/**
	 * This method gets the answer for a question, if it is not available it
	 * provides the ultimate answer
	 * 
	 * @param question the question what the user asked
	 */
	public void printAnswer(String question) {
		if (question == null) {
			return;
		}
		try {
			for (String answer : QAMap.get(question)) {
				System.out.println(answer);
			}
		} catch (NullPointerException e) {
			System.out.println(Consts.ANSWER_FOR_EVERITHING_MSG);
		}
	}

	/**
	 * Loads a json file content into the QandAs object, if the json format is
	 * invelid or not exists it create an empty QandAs object
	 * 
	 * @param resourcePath path of the target json file
	 */
	public void loadQandAs(String resourcePath) {
		if (resourcePath == null) {
			return;
		}
		try {
			String content = new String(Files.readAllBytes(Paths.get(resourcePath)));
			Type type = new TypeToken<HashMap<String, List<String>>>() {
			}.getType();
			QAMap = new Gson().fromJson(content, type);
		} catch (IOException e) {
			// e.printStackTrace(); This should be logged
			QAMap = new HashMap<String, List<String>>();
		}
	}

	/**
	 * This method create/overwrite a json file with the content of the QandAs
	 * object
	 * 
	 * @param resourcePath path of the json file
	 */
	public void saveQandAs(String resourcePath) {
		if (resourcePath == null) {
			return;
		}
		Gson gson = new Gson();
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(resourcePath);
			outputStream.write(gson.toJson(QAMap).getBytes());
			outputStream.close();
		} catch (IOException e) {
			// e.printStackTrace(); This should be logged
		}
	}

}
