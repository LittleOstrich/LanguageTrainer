package utils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FileManagerForJSON implements Pathes {

    private static final Charset UTF_8 = Charset.forName("UTF-8");
    String dfault = Pathes.BASE;

    public void write(String s) {
	
	System.out.println(s);
	
	String rs = new Random().nextInt(Integer.MAX_VALUE) + "";
	String path = rs + ".json";

	Writer out = null;

	try {
	    out = new BufferedWriter(
		    new OutputStreamWriter(new FileOutputStream(path), UTF_8));
	    out.write(s);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		out.close();
	    } catch (IOException e) {
		System.err.println("Seriously, something went wrong...");
		e.printStackTrace();
	    }
	}
    }

    public void write(JSONObject obj) {
	String s = obj.toJSONString();
	write(s);
    }

    public void readFile(String s) {

    }

    public void readXLSX_writeJSON(String s) {
	if (!s.endsWith(".xlsx"))
	    return;

	ExcelReader e = new ExcelReader();
	List<ArrayList<String>> l = null;
	try {
	    l = e.wBAsList(e.getWorkbook(s));
	} catch (IOException e1) {
	    e1.printStackTrace();
	}

	String[] headers = e.getHeaders(l);

	int nR = e.getNumRows(l);
	int nC = e.getNumCols(l);

	JSONArray vocs = new JSONArray();


	for (int j = 1; j < nR; j++) {
	    JSONObject voc = new JSONObject();
	    for (int i = 0; i < nC; i++) {
		voc.put(headers[i], l.get(i).get(j));
	    }
	    vocs.add(voc);
	}
	write(vocs.toJSONString());
    }
}
