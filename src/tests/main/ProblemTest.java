package tests.main;

import org.junit.Test;

import main.Problem1;

public class ProblemTest extends Problem1 {

    @Test
    public void test1() {
	Problem1 p = new Problem1("Q", "word1, word2, word3");
	System.out.println(p.question);
	for (String s : p.answer) {
	    System.out.println(s);
	}
    }
}
