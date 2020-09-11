package com.vincze.gergely;

public class Main {

	public static void main(String[] args) {
		Util.createResourcesFolderIfNotExists();
		KnowledgeBase qA = new KnowledgeBase(Consts.QANDAJSONFILE_PATH);
		Util.inputQuestionAnswerHandler(qA);
		qA.saveQandAs(Consts.QANDAJSONFILE_PATH);
	}

}
