package tests.main;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.BeforeClass;
import org.junit.Test;

import configs.ConfigManager;

public class ConfigManagerTests extends ConfigManager {

    @BeforeClass
    public static void propIsNull() {
	assertTrue(prop == null);
    }

    @Test
    public void readConfigTest() {
	readConfigs();
	prop.setProperty("Key", "Value");

	// Create a stream to hold the output
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	// IMPORTANT: Save the old System.out!
	PrintStream old = System.out;
	// Tell Java to use your special stream
	System.setOut(ps);
	// Print some output: goes to your special stream
	readConfigs();
	// Put things back
	System.out.flush();
	System.setOut(old);
	// Show what happened
	String received = baos.toString().trim();
	String expected = "Key=Value";
	assertTrue(expected + " " + received, expected.matches(received));

    }

}
