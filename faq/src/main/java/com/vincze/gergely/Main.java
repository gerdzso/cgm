package com.vincze.gergely;

public class Main {
	
	public static void main(String[] args) {
		QandAs qA = new QandAs(true);
		String s;
		String[] splittedS;
		boolean firstRun = true;
		while (true) {
			s = Util.getQuestionFromConsole(firstRun);
			if (s.equals(Consts.EXIT_COMMAND)) {
				break;
			}
			firstRun = false;
			splittedS = Util.checkQuestionMarks(s);
			if (splittedS != null) {
				if (splittedS[1].equals(" ")) {
					qA.printAnswer(splittedS[0]);
				} else {
					qA.updateQandAs(splittedS[0], Util.evaluateAnswers(splittedS[1]));
				}
			}
			System.out.println(Consts.CONSOLE_DELIMITTER);
		}
		qA.saveQandAs(Consts.QANDAJSONFILE_PATH);
	}
	
	
}
