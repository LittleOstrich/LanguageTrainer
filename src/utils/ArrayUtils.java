package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import main.ComparatorUtils;
import main.Header;
import main.Problem2;

public class ArrayUtils {

	private static final String IGNORED = "ignored";

	/**
	 * @param upperBound
	 *            for the random variable
	 * @return
	 */

	public static int[] create_pph(int upperBound) {
		int[] ret = new int[upperBound];
		for (int i = 0; i < upperBound; i++) {
			ret[i] = 1 + i;
		}
		return ret;
	}

	public static int[] shuffle(int[] pph) {
		int len = pph.length;
		Random r = new Random();
		for (int i = 0; i < len; i++) {
			int j = r.nextInt(len);
			swap(pph, i, j);
		}
		return pph;
	}

	private static void swap(int[] t, int i, int j) {
		int temp = t[i];
		t[i] = t[j];
		t[j] = temp;
	}

	public static void print(int[] pph) {
		for (Integer o : pph) {
			System.out.print(o + " ");
		}
		System.out.println();
	}

	public static int compare(Problem2 p1, Problem2 p2) {
		int i = (int) p1.entry.get(Header.wrong);
		int j = (int) p2.entry.get(Header.wrong);
		if (i < j)
			return -1;
		else if (i > j)
			return 1;
		else
			return 0;

	}

	public static void sort(ArrayList<Problem2> l) {
		Collections.sort(l, ComparatorUtils.COMPARE_BY_WRONG);
	}

	public static List<Problem2> filterTag(ArrayList<Problem2> l, String header, String tag) {
		List<Problem2> ret = new ArrayList<Problem2>();

		for (Problem2 p : l) {
			Object o = p.entry.get(tag);
			if (o == null || o.toString().isEmpty())
				continue;
			String s = o.toString();
			s.replaceAll("[|]", "");
			String[] tags = s.split(",|;");
			if (containsTag(tags, tag))
				l.add(p);
		}
		return ret;
	}

	public static List<Problem2> filterTag(List<Problem2> l) {
		List<Problem2> temp = new ArrayList<Problem2>();

		for (Problem2 p : l) {
			Object o = p.entry.get(IGNORED);
			if (o == null || o.toString().isEmpty())
				continue;
			if (isIgnored(o) == false)
				l.add(p);
		}
		l = temp;
		return l;
	}

	private static boolean isIgnored(Object o) {
		String s = o.toString().toLowerCase();
		if (s.matches("ignored|0|true"))
			return true;
		else
			return false;
	}

	private static boolean containsTag(String[] tags, String tag) {
		for (String t : tags) {
			if (t.matches(tag)) {
				return true;
			}
		}
		return false;
	}

	public static void swap(int i, int j, List<Object> l) {
		Object t = l.get(i);
		l.set(i, l.get(j));
		l.set(j, t);
	}

	public static void swap(int i, int j, Object[] l) {
		Object t = l[i];
		l[i] = l[j];
		l[j] = t;
	}

	public static List<ArrayList<String>> filterMarkedAsIgnore(ArrayList<ArrayList<String>> l) {
		List<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		return ret;
	}
}
