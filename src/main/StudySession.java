package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utils.ExcelReader;

public class StudySession {

	public List<Problem1> problemSet;

	public LearnSet learnSet;

	public int correctAnswers;

	public int wrongAnswers;

	public int levhensteinProgress;

	public int numOfProblems;

	public int current;

	boolean problemAnswered = true;

	String path;

	public boolean isFinished() {
		boolean isFinished = correctAnswers + wrongAnswers == numOfProblems;
		if (isFinished) {
			updateProblems();
		}
		return isFinished;
	}

	private void updateProblems() {

	}

	public Problem1 getNextProblem() {
		return problemSet.get(correctAnswers + wrongAnswers);
	}

	public StudySession(LearnSet ls) {
		this.learnSet = ls;
		this.learnSet.createNewProblemSet();
		this.problemSet = learnSet.pS;

		path = ls.source;

		numOfProblems = ls.pS.size();
		correctAnswers = 0;
		wrongAnswers = 0;
		current = 0;
	}

	public Workbook updateArchiv() {
		String excelFilePath = this.path;
		Workbook wB = null;
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(new File(excelFilePath));
			wB = new XSSFWorkbook(inputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ExcelReader er = new ExcelReader();
		for (int i = 0; i < learnSet.position.size(); i++) {
			int k = learnSet.position.get(i);
			Problem1 p = problemSet.get(i);
			er.updateWorkbook(wB, k, 3, p.wrong);
			er.updateWorkbook(wB, k, 4, p.correct);
		}
		return wB;
	}

}
