package utils;

public interface Pathes {

	public static final String BASE = System.getProperty("user.dir");

	public static final String FXML_MAIN_CONTROLLER_PATH = BASE + "\\src\\main\\TestGui.fxml";
	public static final String AUDIO_PY_PATH = "\\src\\audio\\audio.py";
	public static final String AUDIO_PATH = "\\src\\audio\\temp.mp3";

	public static final String VOCS_TXT = "\\src\\tests\\utils\\vocs.txt";

	public static final String RIGHT_ANSWER_IMAGE_PATH = BASE + "\\src\\main\\rightAnswer.jpg";
	public static final String WRONG_ANSWER_IMAGE_PATH = BASE + "\\src\\main\\wrongAnswer.jpg";

	public static final String[] IMAGES = { RIGHT_ANSWER_IMAGE_PATH, WRONG_ANSWER_IMAGE_PATH };

	public static final String TEST_FILE_1 = "C:\\Users\\Benjamin\\FAUbox\\Me\\Languages\\Chinese\\Study Material\\Voc2.xlsx";

	public static final String TEST_PATH1 = "C:\\Users\\Benjamin\\FAUbox\\Me\\Languages\\Chinese\\Study Material\\";

	public static final String TEST_FOLDER_1 = "C:\\Users\\Benjamin\\FAUbox\\Me\\Languages\\Chinese\\Study Material";

}
