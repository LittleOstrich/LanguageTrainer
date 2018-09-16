package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import main.Problem2;

public class ExcelReader {

	// TODO: Use a map...
	public enum DEFAULT_HEADERS {
		pronounciation, answer, question, tag, correct, wrong, character, translation, ignore
	};

	public void readOutFile(String path) throws IOException {

		String excelFilePath = path;
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();

			while (cellIterator.hasNext()) {
				Cell c = cellIterator.next();

				String s = getCellVal(c);
				System.out.print(s);
			}
			System.out.print(" - ");
		}
		System.out.println();

		workbook.close();
		inputStream.close();

	}

	public List<ArrayList<String>> wBAsList(Workbook wb) {
		List<ArrayList<String>> l = new ArrayList<ArrayList<String>>();
		Sheet sheet = wb.getSheetAt(0);
		int lastR = sheet.getLastRowNum();
		int lastC = getLastCol(sheet);

		for (int i = 0; i < lastC; i++) {
			l.add(new ArrayList<String>());
		}

		for (int i = 0; i <= lastR; i++) {
			Row r = sheet.getRow(i);
			for (int j = 0; j < lastC; j++) {
				Cell c = r.getCell(j);
				String s = "";
				if (c != null)
					s = getCellVal(c);
				l.get(j).add(s);
			}
		}
		System.out.println("Num of rows: " + l.get(0).size());
		return l;
	}

	private int getLastCol(Sheet sheet) {
		int m = 0;
		int lR = sheet.getLastRowNum();
		for (int i = 0; i < lR; i++) {
			Row r = sheet.getRow(i);
			int lC = r.getLastCellNum();
			if (m < lC) {
				m = lC;
			}
		}
		return m;
	}

	private String getCellVal(Cell c) {
		if (c == null)
			return "";
		switch (c.getCellTypeEnum()) {
		case STRING:
			return c.getStringCellValue();
		case BOOLEAN:
			return c.getBooleanCellValue() + "";
		case NUMERIC:
			return c.getNumericCellValue() + "";
		case _NONE:
		case ERROR:
		case BLANK:
		default:
			return "";
		}
	}

	public void updateWorkbook(Workbook wB, int r, int c, int val) {
		Sheet s = wB.getSheetAt(0);
		Row row = s.getRow(r);
		if (row == null) {
			// Codes.notify(Codes.ERROR);
		}
		Cell cell = row.getCell(c);
		if (cell == null)
			cell = row.createCell(c);
		cell.setCellValue(val + "");
	}

	public void resetWorkbook(String path) throws FileNotFoundException, IOException {
		Workbook wB = new XSSFWorkbook(new FileInputStream(new File(path)));
		String[] headers = getHeaders(wB);

		int[] col_idx = { StringUtils.getPos(DEFAULT_HEADERS.wrong.toString(), headers),
				StringUtils.getPos(DEFAULT_HEADERS.correct.toString(), headers),
				StringUtils.getPos(DEFAULT_HEADERS.ignore.toString(), headers) };

		Sheet sheet = wB.getSheetAt(0);
		int lastR = sheet.getLastRowNum();

		for (int i = 1; i <= lastR; i++) {
			for (int j = 0; j < 3; j++) {
				updateWorkbook(wB, i, col_idx[j], 0);
			}
		}
		writeBack(wB, path);
	}

	public void writeBack(Workbook wB, String path) {
		try {
			File f = new File(path);
			FileOutputStream fout = new FileOutputStream(f);
			wB.write(fout);
			fout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public XSSFWorkbook getWorkbook(String s) throws IOException {
		return new XSSFWorkbook(new FileInputStream(new File(s)));
	}

	public String[] getHeaders(List<ArrayList<String>> l) {
		int cols = l.size();
		String[] headers = new String[cols];

		for (int i = 0; i < cols; i++) {
			headers[i] = l.get(i).get(0);
		}
		return headers;
	}

	public String[] getHeaders(Workbook wb) {
		Sheet sheet = wb.getSheetAt(0);
		Row r = sheet.getRow(0);
		int cols = r.getLastCellNum();
		String[] headers = new String[cols];

		for (int i = 0; i < cols; i++) {
			Cell c = r.getCell(i);
			headers[i] = getCellVal(c);
		}
		return headers;
	}

	public int getNumRows(List<ArrayList<String>> l) {
		return l.get(0).size();
	}

	public int getNumCols(List<ArrayList<String>> l) {
		return l.size();
	}

	public List<Problem2> createProblem2Set(String path) throws IOException {
		List<Problem2> ret = new ArrayList<Problem2>();
		Workbook wB = getWorkbook(path);
		String[] headers = getHeaders(wB);

		Sheet sheet = wB.getSheetAt(0);

		int lastR = sheet.getLastRowNum();
		int lastC = getLastCol(sheet);

		for (int i = 0; i < lastR; i++) {
			Row r = sheet.getRow(i);
			Problem2 p = new Problem2();
			for (int j = 0; j < lastC; j++) {
				Cell c = r.getCell(j);
				if (c == null)
					continue;
				p.entry.put(headers[j], getCellVal(c));
			}
			ret.add(p);
		}
		return ret;
	}
}
