package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public enum Codes {
	EMPTY_PROBLEM_SET("The problem set is empty! Check the corresponding file!");

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

	public static void notify(Codes code) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(code.toString());
		alert.showAndWait();
	}

}
