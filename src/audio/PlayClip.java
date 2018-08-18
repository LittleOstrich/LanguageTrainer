package audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineEvent.Type;

public class PlayClip {

    public static void playClip(File f)
	    throws IOException,
	    UnsupportedAudioFileException,
	    LineUnavailableException,
	    InterruptedException {
	class AudioListener implements LineListener {
	    private boolean done = false;

	    @Override
	    public synchronized void update(LineEvent event) {
		Type eventType = event.getType();
		if (eventType == Type.STOP || eventType == Type.CLOSE) {
		    done = true;
		    notifyAll();
		}
	    }

	    public synchronized void waitUntilDone()
		    throws InterruptedException {
		while (!done) {
		    wait();
		}
	    }
	}
	AudioListener listener = new AudioListener();
	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f);
	try {
	    Clip clip = AudioSystem.getClip();
	    clip.addLineListener(listener);
	    clip.open(audioInputStream);
	    try {
		clip.start();
		listener.waitUntilDone();
	    } finally {
		clip.close();
	    }
	} finally {
	    audioInputStream.close();
	}
    }

    public static void playClip2(File f) {
	  new Thread(new Runnable() {
	      // The wrapper thread is unnecessary, unless it blocks on the
	      // Clip finishing; see comments.
	        public void run() {
	          try {
	            Clip clip = AudioSystem.getClip();
	            AudioInputStream inputStream = AudioSystem.getAudioInputStream(f);
	            clip.open(inputStream);
	            clip.start(); 
	          } catch (Exception e) {
	            System.err.println(e.getMessage());
	          }
	        }
	      }).start();
	    
	
    }
}