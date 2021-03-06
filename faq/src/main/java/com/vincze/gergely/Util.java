package com.vincze.gergely;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Util {

	/**
	 * This method contains the main logic of the app
	 * 
	 * @param qA qA QandAs type object to get or update answers
	 */
	public static void inputQuestionAnswerHandler(KnowledgeBase qA) {
		String userInput;
		boolean firstRunFlag = true;
		while (true) {
			userInput = Util.getQuestionFromConsole(firstRunFlag);
			firstRunFlag = false;
			if (userInput.equals(Consts.EXIT_COMMAND)) {
				break;
			}
			if (userInput.length() <= Consts.MAX_CHARACTER_NUMBER) {
				Util.decideQuestionOrAnswer(userInput, qA);
			} else {
				System.out.println(Consts.TOO_LONG_INPUT_MSG);
			}
			System.out.println(Consts.CONSOLE_DELIMITTER);
		}
	}

	/**
	 * this method decides that the app should update the answers or provide an
	 * answer
	 * 
	 * @param userInput string typed by the user
	 * @param qA        QandAs type object to get or update answers
	 */
	public static void decideQuestionOrAnswer(String userInput, KnowledgeBase qA) {
		if (userInput == null || qA == null) {
			return;
		}
		String[] splitted = Util.checkQuestionMarks(userInput);
		if (splitted == null) {
			return;
		}
		if (splitted[1].equals("")) {
			qA.printAnswer(splitted[0]);
		} else {
			qA.updateQandAs(splitted[0], Util.evaluateAnswers(splitted[1]));
		}
	}

	/**
	 * Creates the resources folder, if it is not exists
	 * 
	 * @throws IOException
	 */
	public static void createResourcesFolderIfNotExists() {
		File directory = new File("resources/");
		if (!directory.exists()) {
			directory.mkdir();
		}
	}

	/**
	 * This method gets the input from the user from the console
	 * 
	 * @param firstRunFlag This indicates that, the app asks the user for the first
	 *                     time
	 * @return String returns a question/answer setup/ exit command from the user
	 */
	public static String getQuestionFromConsole(boolean firstRunFlag) {
		if (firstRunFlag) {
			System.out.println(Consts.FIRST_QUESTION);
		} else {
			System.out.println(Consts.MORE_QUESTION);
		}

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String inputString = in.nextLine();
		return inputString;
	}

	/**
	 * This method a simple parser, parsing the answers provided by the user in the
	 * proper form
	 * 
	 * @param answers input string containing the answer part of the user's input
	 * @return List<String> a parsed form of the input string
	 */

	public static List<String> evaluateAnswers(String answers) {
		List<String> out = new ArrayList<String>();
		if (answers == null) {
			return out;
		}
		String[] splitted = answers.split(Consts.DELIMITTER_ANSWER_MARK);
		if (countCharOccurrence(answers, Consts.QUOTE_MARK_CH) != 2 * splitted.length
				|| !splitted[0].startsWith(Consts.START_ANSWER_MARK)
				|| !splitted[splitted.length - 1].endsWith(Consts.END_ANSWER_MARK)) {
			return sendNUllWithProblemMessage();
		}
		splitted[0] = splitted[0].substring(Consts.START_ANSWER_MARK.length());
		splitted[splitted.length - 1] = splitted[splitted.length - 1].substring(0,
				splitted[splitted.length - 1].length() - Consts.END_ANSWER_MARK.length());
		return Arrays.asList(splitted);
	}

	/**
	 * This method splits the user's input into a question and an aswer, if there is
	 * only a question, the answer part of the return array is a space
	 * 
	 * @param userInput input string from the user
	 * @return String[] input splitted into two parts: question, answers
	 */
	public static String[] checkQuestionMarks(String userInput) {
		if (userInput == null) {
			userInput = "";
		}
		String[] splitted = userInput.split("\\?", -1);
		if (splitted.length == 1) {
			System.out.println(Consts.MISSING_QUESTIONMARK_MSG);
			return null;
		} else if (splitted.length > 2) {
			System.out.println(Consts.MULTIPLE_QUESTIONMARK_MSG);
			return null;
		}
		return splitted;
	}

	/**
	 * This method returns a null and prints wrong answer format message to the
	 * console
	 * 
	 * @return null
	 */
	private static List<String> sendNUllWithProblemMessage() {
		System.out.println(Consts.WRONG_ANSWER_FORMAT_MSG);
		return null;
	}

	/**
	 * This method counts character occurrence in a string
	 * 
	 * @param s examined string
	 * @param c examined character
	 * @return long number of the occurrence
	 */
	private static long countCharOccurrence(String s, char c) {
		if (s == null) {
			return 0;
		}
		return s.chars().filter(ch -> ch == c).count();
	}

}
