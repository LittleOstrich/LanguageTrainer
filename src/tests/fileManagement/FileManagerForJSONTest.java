package tests.fileManagement;

import org.json.simple.JSONObject;
import org.junit.Test;

import main.LearnSet;
import utils.FileManagerForJSON;

public class FileManagerForJSONTest extends FileManagerForJSON {

    @Test
    public void writeTest1() {
	JSONObject o = new JSONObject();
	o.put("String 1", "String 2");
	write(o);
    }

    @Test
    public void writeTest2() {
	JSONObject o = new JSONObject();
	LearnSet.loadLearnSet();
	int s = LearnSet.ls.get(0).size();
	for (int i = 0; i < s; i++) {
	    String k = LearnSet.ls.get(0).get(i);
	    String v = LearnSet.ls.get(1).get(i);
	    o.put(k, v);
	}
	write(o);
    }
    
    @Test
    public void writeTest3() {
	readXLSX_writeJSON(TEST_FILE_1);
    }
}
