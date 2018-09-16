package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.Workbook;

import audio.ExecutePythonScript;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import utils.ExcelReader;
import utils.Pathes;

public class MainController implements Pathes, Initializable {

	// @FXML
	// Button randomButton;
	//
	// @FXML
	// Button newWordButton;

	@FXML
	TextField randomText;

	@FXML
	TextField submittedAnswerTextField;

	@FXML
	ImageView feedback;

	@FXML
	TextField translation;

	@FXML
	TextField old_question;

	@FXML
	TextField pronounciation;

	@FXML
	TextField question;

	@FXML
	Button createNewStudySession;

	@FXML
	Button repeatAudio;

	@FXML
	Button resetFile;

	@FXML
	AnchorPane mainAnchorpane;

	@FXML
	TextField vocsLeft;

	@FXML
	TextField wrongAnswers;

	@FXML
	TextField correctAnswers;

	@FXML
	ChoiceBox<String> choicebox;

	StudySession studysession;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		File folder = new File(Pathes.TEST_FOLDER_1);
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles == null)
			return;
		List<String> files = new ArrayList<String>();
		files.add("");
		for (File file : listOfFiles) {
			if (file.isFile()) {
				// System.out.println(file.getName());
				String name = file.getName();
				if (name.endsWith(".xlsx")) {
					files.add(name);
				}
			}
		}
		choicebox.setItems(FXCollections.observableArrayList(files));
	}

	public void getKey() {
		submittedAnswerTextField.setOnKeyReleased(event -> {
			checkAnswer(event);
		});
	}

	// public void getNextWord() {
	// feedback.setVisible(false);
	// question.setText("");
	// System.out.println(submittedAnswerTextField.getText());
	// submittedAnswerTextField.setText("");
	// System.out.println(submittedAnswerTextField.getText());
	//
	// String[] qa = new String[2];
	// int n = new Random().nextInt(LearnSet.ls.get(0).size());
	// qa[0] = LearnSet.ls.get(0).get(n);
	// qa[1] = LearnSet.ls.get(2).get(n);
	// question.setText(qa[0]);
	// }

	public void resetFile() throws FileNotFoundException, IOException {
		String selectedFile = choicebox.getValue();
		if (selectedFile == null || selectedFile.isEmpty())
			return;
		new ExcelReader().resetWorkbook(TEST_PATH1 + selectedFile);
	}

	public void repeatAudio() throws URISyntaxException, InterruptedException {
		File f = new File(BASE + AUDIO_PATH);
		if (f.exists() && studysession != null && !submittedAnswerTextField.isFocused()) {
			pronounce(f);
		}
	}

	public void createNewStudySession() {
		String selectedFile = choicebox.getValue();
		if (selectedFile.isEmpty())
			return;
		clearScreen();

		studysession = new StudySession(new LearnSet(TEST_PATH1 + selectedFile));
		if (studysession.learnSet.valid_learnSet == false)
			return;
		startStudySession();

		vocsLeft.setText("Vocs left: " + (studysession.numOfProblems));
		correctAnswers.setText("Correct answers: " + studysession.correctAnswers);
		wrongAnswers.setText("Wrong answers: " + studysession.wrongAnswers);
	}

	public void repeatKeyWasReleased() {
		mainAnchorpane.setOnKeyReleased(event -> {
			if (event.getCode().equals(KeyCode.R))
				try {
					repeatAudio();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		});
	}

	public void startStudySession() {
		if (studysession == null) {
			return;
		} else if (studysession.isFinished()) {
			endStudySession();
			return;
		} else {
			continueStudySession();
		}

		// System.out.println(studysession.correctAnswers);
		// System.out.println(studysession.wrongAnswers);
		// System.out.println(studysession.numOfProblems);
		// System.out.println(studysession.current);
		// System.out.println(studysession.learnConfig == null);
		// System.out.println(studysession.learnSet == null);

	}

	public void write() {

	}

	private void endStudySession() {
		System.out.println("Updating Workbook...");
		Workbook wB = studysession.updateArchiv();
		new ExcelReader().writeBack(wB, studysession.path);
	}

	private void checkAnswer(KeyEvent event) {
		if (event.getCode() != KeyCode.ENTER || studysession == null) {
			return;
		} else if (studysession.isFinished()) {
			System.out.println("StudySession is finished!");
			clearScreen();
			studysession = null;
			return;
		}

		int c = studysession.correctAnswers + studysession.wrongAnswers;
		Problem1 p = studysession.problemSet.get(c);

		String submitted = submittedAnswerTextField.getText();
		boolean answerWasCorrect = p.check2(submitted);

		int imageNum = answerWasCorrect ? 0 : 1;

		if (answerWasCorrect) {
			imageNum = 0;
			studysession.correctAnswers++;
			p.correct++;
		} else {
			imageNum = 1;
			studysession.wrongAnswers++;
			p.wrong++;
			System.out.println("Wrong answered submitted: " + submitted);
		}
		studysession.current++;

		updateProgress(studysession);

		String ca = "";
		for (String s : p.answer) {
			ca = ca + s + ", ";
		}
		ca = ca.substring(0, ca.length() - 2);

		old_question.setText(p.question);
		pronounciation.setText(p.pronounciation);
		translation.setText(ca);

		try {
			feedback.setImage(new Image(new FileInputStream(IMAGES[imageNum])));
			feedback.setVisible(true);
			studysession.problemAnswered = false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// pronounce(new File(BASE+AUDIO_PATH));
		System.out.println(p.toString());

		if (studysession.isFinished()) {
			endStudySession();
		} else {
			continueStudySession();
		}
	}

	private void continueStudySession() {
		Problem1 p = studysession.getNextProblem();
		playAudio(p.question);
		submittedAnswerTextField.clear();
		question.setText(p.question);
	}

	private void playAudio(String question) {
		File f = new File(BASE + AUDIO_PATH);
		ExecutePythonScript.getAudioFileWithPython(new String[] { question });
		pronounce(f);
	}

	private void sleep(int t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void updateProgress(StudySession studysession) {
		vocsLeft.setText("Vocs left: " + (studysession.numOfProblems - studysession.current));
		correctAnswers.setText("Correct answers: " + studysession.correctAnswers);
		wrongAnswers.setText("Wrong answers: " + studysession.wrongAnswers);
	}

	private void pronounce(File f) {
		f = new File(BASE + AUDIO_PATH);
		// PlayClip.playClip2(f);
		String s = f.toURI().toString();
		MediaPlayer mediaPlayer = new MediaPlayer(new Media(s));
		sleep(100);
		mediaPlayer.play();
	}

	private void clearScreen() {
		submittedAnswerTextField.clear();
		translation.clear();
		old_question.clear();
		pronounciation.clear();
		question.clear();
		vocsLeft.clear();
		correctAnswers.clear();
		wrongAnswers.clear();
		feedback.setImage(null);
	}
}
