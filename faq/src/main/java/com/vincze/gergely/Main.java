package com.vincze.gergely;

public class Main {

	public static void main(String[] args) {
		Util.createResourcesFolderIfNotExists();
		QandAs qA = new QandAs(true);
		Util.inputQuestionAnswerHandler(qA);
		qA.saveQandAs(Consts.QANDAJSONFILE_PATH);
	}

}
