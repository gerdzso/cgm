package com.vincze.gergely;

public class Main {
	
	public static void main(String[] args) {
		QandAs qA = new QandAs(true);
		String s;
		boolean firstRun = true;
		while (true) {
			s = Util.getQuestionFromConsole(firstRun);
			if (s.equals(Consts.EXIT_COMMAND)) {
				break;
			}
			firstRun = false;
			if (s.length() <= Consts.MAX_CHARACTER_NUMBER) {
				separateQorA(s, qA);
			} else {
				System.out.println(Consts.TOO_LONG_INPUT_MSG);
			}
			
			System.out.println(Consts.CONSOLE_DELIMITTER);
		}
		qA.saveQandAs(Consts.QANDAJSONFILE_PATH);
	}
	
	private static void separateQorA(String s, QandAs qA) {
		String[] splittedS = Util.checkQuestionMarks(s);
		if (splittedS != null) {
			if (splittedS[1].equals(" ")) {
				qA.printAnswer(splittedS[0]);
			} else {
				qA.updateQandAs(splittedS[0], Util.evaluateAnswers(splittedS[1]));
			}
		}
	}
	
}
