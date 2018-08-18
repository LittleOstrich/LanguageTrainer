package main;

import java.util.Comparator;

public class ComparatorUtils {

	public static Comparator<Problem2> COMPARE_BY_WRONG = new Comparator<Problem2>() {
		public int compare(Problem2 p1, Problem2 p2) {
			int i = (int) p1.entry.get(Header.wrong.name());
			int j = (int) p2.entry.get(Header.wrong.name());
			if (i < j)
				return -1;
			else if (i > j)
				return 1;
			else
				return 0;
		}
	};

	public static Comparator<Problem2> COMPARE_BY_ABS = new Comparator<Problem2>() {
		public int compare(Problem2 p1, Problem2 p2) {
			int w1 = (int) p1.entry.get(Header.wrong.name());
			int c1 = (int) p1.entry.get(Header.correct.name());
			int w2 = (int) p1.entry.get(Header.wrong.name());
			int c2 = (int) p1.entry.get(Header.correct.name());

			int d1 = c1 - w1;
			int d2 = c2 - w2;
			if (d1 < d2)
				return -1;
			else if (d1 > d2)
				return 1;
			else
				return 0;
		}
	};

}
