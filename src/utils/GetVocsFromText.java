package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.IOUtils;

public class GetVocsFromText implements Pathes {

    Set<String> set = new HashSet<String>();

    public void readFile(String p) throws IOException {
	File f = new File(p);
	String s = "";
	String encoding = "UTF-8";
	if (!f.exists())
	    return;
	try (FileInputStream inputStream = new FileInputStream(p)) {
	    s = IOUtils.toString(inputStream, encoding); 
	}
	for (int i = 0; i+1 < s.length(); i++) {
	    String k = s.substring(i, i + 1);
	    if (containsHanScript(k)) {
		set.add(k);
	    }
	}
	for (String entry : set) {
	    System.out.println(entry);
	}
    }

    public static boolean containsHanScript(String s) {
	return s.codePoints().anyMatch(codepoint -> Character.UnicodeScript
		.of(codepoint) == Character.UnicodeScript.HAN);
    }

    public static void main(String[] args) throws IOException {
	new GetVocsFromText().readFile(BASE + VOCS_TXT);
    }
}
