package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utils.ArrayUtils;
import utils.Codes;
import utils.ExcelReader;
import utils.ExcelReader.DEFAULT_HEADERS;
import utils.Pathes;
import utils.StringUtils;

public class LearnSet implements Pathes {

	public static List<ArrayList<String>> ls = new ArrayList<ArrayList<String>>();

	public int n = 50;

	public int m;

	public List<Problem1> pS;

	public List<Integer> position;

	public String[] headers;

	public StudyMode studyMode;

	public boolean valid_learnSet = false;

	public String source;

	// TODO: Replace loadLearnSet() with some meaningful code...
	public static void loadLearnSet() {
		try {
			ls = new ExcelReader().wBAsList(new XSSFWorkbook(new FileInputStream(new File(TEST_FILE_1))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public LearnSet(String source) {
		clearLearnSet();
		this.source = source;
		if (ls == null || ls.isEmpty()) {
			try {
				ExcelReader r = new ExcelReader();
				System.out.println("Loading source... " + source);
				FileInputStream fileInputStream = new FileInputStream(new File(source));
				ls = r.wBAsList(new XSSFWorkbook(fileInputStream));
				fileInputStream.close();
				m = LearnSet.ls.get(0).size();
				System.out.println("Num of Problems in the source: " + (--m));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void clearLearnSet() {
		ls = null;
		pS = null;
		headers = null;
	}

	public void createNewProblemSet() {
		pS = new ArrayList<Problem1>();
		position = new ArrayList<Integer>();

		int[] pph = ArrayUtils.create_pph(m);
		pph = ArrayUtils.shuffle(pph);

		this.headers = new ExcelReader().getHeaders(ls);

		int t = n < m ? n : m;

		int qint = StringUtils.getPos(DEFAULT_HEADERS.character.toString(), headers);
		int pint = StringUtils.getPos(DEFAULT_HEADERS.pronounciation.toString(), headers);
		int aint = StringUtils.getPos(DEFAULT_HEADERS.translation.toString(), headers);
		int cint = StringUtils.getPos(DEFAULT_HEADERS.correct.toString(), headers);
		int wint = StringUtils.getPos(DEFAULT_HEADERS.wrong.toString(), headers);
		int iint = StringUtils.getPos(DEFAULT_HEADERS.ignore.toString(), headers);

		try {

			for (int i = 0; i < t; i++) {
				if (i >= m)
					break;
				int rInt = pph[i];

				String is = LearnSet.ls.get(iint).get(rInt);
				// System.out.println(is);

				boolean dis = StringUtils.equalIgnoreCase(is, "1.0");
				int c = (int) Float.parseFloat(LearnSet.ls.get(cint).get(rInt));
				int w = (int) Float.parseFloat(LearnSet.ls.get(wint).get(rInt));
				if (dis || c - w > 3) {
					t++;
					continue;
				}

				position.add(rInt);

				String q = LearnSet.ls.get(qint).get(rInt);
				String p = LearnSet.ls.get(pint).get(rInt);
				String a = LearnSet.ls.get(aint).get(rInt);

				if (q.isEmpty() || p.isEmpty() || a.isEmpty())
					continue;
				pS.add(new Problem1(q, p, a, c, w));
			}
		} catch (Exception e) {
			Codes.notify(Codes.EMPTY_PROBLEM_SET);
		}
		valid_learnSet = true;
	}

	public void createNewProblemSet2(int n) {
		assert (studyMode.equals(StudyMode.WORST));
		pS = new ArrayList<Problem1>();
		position = new ArrayList<Integer>();

		int r = LearnSet.ls.get(0).size() - 1;
		int[] pph = ArrayUtils.create_pph(r);
		pph = ArrayUtils.shuffle(pph);

		this.headers = new ExcelReader().getHeaders(ls);

		int qint = StringUtils.getPos(DEFAULT_HEADERS.character.toString(), headers);
		int pint = StringUtils.getPos(DEFAULT_HEADERS.pronounciation.toString(), headers);
		int aint = StringUtils.getPos(DEFAULT_HEADERS.translation.toString(), headers);
		int cint = StringUtils.getPos(DEFAULT_HEADERS.correct.toString(), headers);
		int wint = StringUtils.getPos(DEFAULT_HEADERS.wrong.toString(), headers);
		int iint = StringUtils.getPos(DEFAULT_HEADERS.ignore.toString(), headers);

		ArrayUtils.print(pph);

		for (int i = 0; i < n; i++) {

			int rInt = pph[i];
			position.add(rInt);

			String q = LearnSet.ls.get(qint).get(rInt);
			String p = LearnSet.ls.get(pint).get(rInt);
			String a = LearnSet.ls.get(aint).get(rInt);
			int c = (int) Float.parseFloat(LearnSet.ls.get(cint).get(rInt));
			int w = (int) Float.parseFloat(LearnSet.ls.get(wint).get(rInt));
			pS.add(new Problem1(q, p, a, c, w));
		}
	}
}
