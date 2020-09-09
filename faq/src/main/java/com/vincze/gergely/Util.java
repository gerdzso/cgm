package com.vincze.gergely;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Util {
	
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
			
			if (countCharOccurance(splitted[i], Consts.QUOTE_MARK_CH) > 0) {
				return sendNUllWithProblemMessage();
			}
			out.add(splitted[i]);
		}
		return out;
	}
	
	private static List<String> sendNUllWithProblemMessage(){
		System.out.println(Consts.WRONG_ANSWER_FORMAT_MSG);
		return null;
	}
	
	private static long countCharOccurance(String s, char c) {
		return s.chars().filter(ch -> ch == c).count();
	}
	
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
	

}
