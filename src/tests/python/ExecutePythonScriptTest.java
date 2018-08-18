package tests.python;

import java.io.IOException;

import org.junit.Test;

import audio.ExecutePythonScript;

public class ExecutePythonScriptTest extends ExecutePythonScript{
    
    
    private static void playAudioFileFromPython() {
	Runtime rt = Runtime.getRuntime();
	String command = "python C:\\Users\\Benjamin\\Desktop\\LainProject\\JavaFX_Playground\\src\\tests\\python\\py_testscript.py";
	try {
	    rt.exec(command);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	System.out.println("Hello..");
	
    }
    
    @Test
    public void test1() {
	playAudioFileFromPython();
    }

    @Test
    public void test2() {
	String[] args = new String[1];
	args[0] = "你好吗";
	getAudioFileWithPython(args);
    }
}
