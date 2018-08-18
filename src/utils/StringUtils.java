package utils;

public class StringUtils {

	public static final char[] CHARS_TO_REMOVE = { ' ', ',', '?', '.', '\t', '\n', };

	public static boolean equalIgnoreCase(String a, String b) {
		if (a == null || b == null)
			return false;
		return a.toLowerCase().equals(b.toLowerCase());
	}

	public static String removeMultipleCharcters(String s, char[] charsToRemove) {

		for (char c : charsToRemove) {
			s = s.replace(c + "", "");
		}

		return s;
	}

	public static int getPos(String s, String[] sa) {
		int l = sa.length;
		for (int i = 0; i < l; i++) {
			if (StringUtils.equalIgnoreCase(s, sa[i]))
				return i;
		}
		return -1;
	}
}
