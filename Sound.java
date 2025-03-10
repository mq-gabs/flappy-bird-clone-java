import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
  static public void playScore() {
    try {
      File f = new File("assets/sounds/score.wav");
      AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
      Clip clip = AudioSystem.getClip();
      clip.open(audioIn);
      clip.start();
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }

  static public void playCrash() {
    try {
      File f = new File("assets/sounds/crash.wav");
      AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
      Clip clip = AudioSystem.getClip();
      clip.open(audioIn);
      clip.start();
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }
}
