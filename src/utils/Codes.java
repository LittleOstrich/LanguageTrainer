package utils;

public enum Codes {
	EMPTY_PROBLEM_SET("The problem set is empty! Check the correspnding file!");

	private final String name;

	private Codes(String s) {
		name = s;
	}

	public boolean equalsName(String name2) {
		return name.equals(name2);
	}

	public String toString() {
		return this.name;
	}
}
