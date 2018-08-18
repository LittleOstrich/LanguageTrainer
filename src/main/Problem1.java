package main;

import org.json.simple.JSONObject;

import utils.StringDistanceMetrics;
import utils.StringUtils;

public class Problem1 extends Problem {

	private static final String SPLITTER_REGEX = ",|;";

	public Problem1(String question, String pronounciation, String answer, int c, int w) {
		this.question = question;
		this.a = answer;
		this.pronounciation = pronounciation;
		this.correct = c;
		this.wrong = w;

		this.answer = answer.split(SPLITTER_REGEX);

		for (int i = 0; i < this.answer.length; i++) {
			this.answer[i] = this.answer[i].trim();
		}
	}

	public String question;

	public String pronounciation;

	public String[] answer;

	public String a;

	public String[] tags;

	public int correct;

	public int wrong;

	public boolean isValid;

	public Problem1(String question, String answer) {
		this.question = question;
		this.answer = answer.split(SPLITTER_REGEX);
		setAnswer(answer);
	}

	public Problem1(String question, String answer, String pronounciation) {
		this.question = question;
		this.pronounciation = pronounciation;
		setAnswer(answer);
	}

	public Problem1() {
	}

	public Problem1(JSONObject obj) {
		this.question = (String) obj.get("question");
		this.pronounciation = (String) obj.get("pronounciation");
		setAnswer((String) obj.get(answer));
	}

	private void setAnswer(String answer) {
		this.answer = answer.split(SPLITTER_REGEX);
		for (int i = 0; i < this.answer.length; i++) {
			this.answer[i] = this.answer[i].trim();
		}
	}

	public boolean check1(String submitted) {
		if (submitted == null)
			return false;
		for (String s : answer) {
			if (StringUtils.equalIgnoreCase(submitted, s)) {
				return true;
			}
		}
		return false;
	}

	public boolean check2(String submitted) {
		if (submitted == null)
			return false;
		submitted = StringUtils.removeMultipleCharcters(submitted, StringUtils.CHARS_TO_REMOVE);
		for (String s : answer) {
			String cleaned_answer = StringUtils.removeMultipleCharcters(s, StringUtils.CHARS_TO_REMOVE);
			if (StringDistanceMetrics.damerauLevenshteinDistance(cleaned_answer.toLowerCase(),
					submitted.toLowerCase()) <= 1)
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Question=" + question + " " + "Answer=" + a + " " + "Correct=" + correct + " " + "Wrong=" + wrong;
	}
}
