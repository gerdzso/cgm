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

public class QandAs {
	
	private Map<String, List<String>> QAMap;

	public QandAs(boolean loadQandAs) {
		if (loadQandAs) {
			loadQandAs(Consts.QANDAJSONFILE_PATH);
		} else {
			QAMap = new HashMap<String, List<String>>();
		}
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
			System.out.println(Consts.ANSWER_FOR_EVERITHING_MSG);
		}
	}
	
	public void loadQandAs(String resourcePath) {
		try {
            String content = new String(Files.readAllBytes( Paths.get(resourcePath)));
            Type type = new TypeToken<HashMap<String, List<String>>>(){}.getType();
            QAMap = new Gson().fromJson(content, type);
        }  catch (IOException e)  {
            //e.printStackTrace(); This should be logged
            QAMap = new HashMap<String, List<String>>();
        }
	}
	
	public void saveQandAs(String resourcePath) {
		Gson gson = new Gson();
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(resourcePath);
			outputStream.write(gson.toJson(QAMap).getBytes());
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
