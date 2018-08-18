package audio;

import java.io.IOException;

import utils.Pathes;

public class ExecutePythonScript implements Pathes {

	private static final String PYTHON = "C:\\Users\\Benjamin\\Anaconda3\\python.exe ";

	private static String addArgs(String... args) {
		String s = "";
		for (String arg : args) {
			s = s + arg + " ";
		}
		s = s + SupportedLanguages.chineseMandarin;
		return s;
	}

	public static void getAudioFileWithPython(String... args) {
		Runtime rt = Runtime.getRuntime();
		String command = PYTHON + BASE + AUDIO_PY_PATH + " " + addArgs(args);
		try {
			// System.out.println("Python Command: " + command);
			rt.exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
