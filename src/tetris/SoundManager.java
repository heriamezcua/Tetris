package tetris;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {
	private static Clip loopingClip; // Store the clip in the loop

	public static void play(String soundFile) {
		try {
			File file = new File("./resources/sounds/" + soundFile + ".wav");
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();

			clip.addLineListener(event -> {
				if (event.getType() == LineEvent.Type.STOP) {
					clip.close();
				}
			});

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public static void loop(String soundFile) {
		try {
			File file = new File("./resources/sounds/" + soundFile + ".wav");
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			loopingClip = AudioSystem.getClip();
			loopingClip.open(audioStream);
			loopingClip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public static void stop() {
		if (loopingClip != null && loopingClip.isRunning()) {
			loopingClip.stop();
			loopingClip.close();
		}
	}
}
