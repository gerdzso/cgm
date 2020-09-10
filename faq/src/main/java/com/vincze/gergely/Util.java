package com.vincze.gergely;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Util {
	
	/**
	 * Creates the resources folder, if it is not exists
	 * @throws IOException
	 */
	public static void createResourcesFolderIfNotExists() {
		File directory = new File("resources/");
	    if (! directory.exists()){
	        directory.mkdir();
	    }
	}
	
	/**
	 * This method gets the input from the user from the console
	 * @param firstRun This indicates that, the app asks the user for the first time
	 * @return String returns a question/answer setup/ exit command from the user 
	 */
	public static String getQuestionFromConsole(boolean firstRun) {
		if (firstRun) {
			System.out.println(Consts.FIRST_QUESTION);
		} else {
			System.out.println(Consts.MORE_QUESTION);
		}
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in); 
	    String s = in.nextLine(); 
	    return s;
	}
	
	/**
	 * This method a simple parser, parsing the answers provided by the user in the proper form
	 * @param s input string containing the answer part of the user's input
	 * @return List<String> a parsed form of the input string 
	 */
	public static List<String> evaluateAnswers(String s){
		String[] splitted = s.split(Consts.DELIMITTER_ANSWER_MARK);
		List<String> out = new ArrayList<String>();
		
		for (int i = 0; i < splitted.length; i++) {
			
			if (i == 0 && !splitted[i].startsWith(Consts.START_ANSWER_MARK)) {
				return sendNUllWithProblemMessage();
			}  else if (i == 0 && splitted[i].startsWith(Consts.START_ANSWER_MARK)) {
				splitted[i] = splitted[i].substring(Consts.START_ANSWER_MARK.length());
			}
			
			if (i == splitted.length - 1 && !splitted[i].endsWith(Consts.END_ANSWER_MARK)) {
				return sendNUllWithProblemMessage();
			} else if (i == splitted.length - 1 && splitted[i].endsWith(Consts.END_ANSWER_MARK)) {
				splitted[i] = splitted[i].substring(0, splitted[i].length() - Consts.END_ANSWER_MARK.length());
			}
			
			if (countCharOccurrence(splitted[i], Consts.QUOTE_MARK_CH) > 0) {
				return sendNUllWithProblemMessage();
			}
			out.add(splitted[i]);
		}
		return out;
	}
	
	
	/**
	 * This method splits the user's input into a question and an aswer, if there is only a question,
	 * the answer part of the return array is a space
	 * @param s input string from the user
	 * @return String[] input splitted into two parts: question, answers
	 */
	public static String[] checkQuestionMarks(String s) {
		s = ' ' + s + ' ';
		String[] splitted = s.split("\\?");
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
	 * This method returns a null and prints wrong answer format message to the console
	 * @return null
	 */
	private static List<String> sendNUllWithProblemMessage(){
		System.out.println(Consts.WRONG_ANSWER_FORMAT_MSG);
		return null;
	}
	
	/**
	 * This method counts character occurrence in a string
	 * @param s examined string
	 * @param c examined character
	 * @return long number of the occurrence
	 */
	private static long countCharOccurrence(String s, char c) {
		return s.chars().filter(ch -> ch == c).count();
	}

}
