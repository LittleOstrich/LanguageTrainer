package tests.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.Problem2;
import utils.ExcelReader;
import utils.Pathes;

public class Problem2Test extends Problem2 {

	
	@Test
	public void test1() throws IOException {
		List<Problem2> l = new ArrayList<Problem2>();
		ExcelReader r = new ExcelReader();
		l = r.createProblem2Set(Pathes.TEST_FILE_1);
		for(Problem2 p : l) {
			if(p.entry.containsKey("ignore")) {
				String i = (String) p.entry.get("ignore");
					if(i.matches("1.0")) {
						continue;
					}
				}
			System.out.println(p.entry.toString());
		}
		
	}	
}
