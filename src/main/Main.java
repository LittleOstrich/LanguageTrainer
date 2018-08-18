package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Pathes;

public class Main extends Application implements Pathes {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		File f = new File(FXML_MAIN_CONTROLLER_PATH);
		System.out.println(f.exists());

		Parent root = loader.load(new FileInputStream(f));

		Scene scene = new Scene(root);

		stage.setTitle("FXML Welcome");
		stage.setScene(scene);
		stage.show();
	}
}