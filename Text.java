import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JLabel;

public class Text extends JLabel {
  Screen screen;

  Text(Screen screen) {
    this.screen = screen;
    this.loadAssets();
    this.setText("Press 'Space' to start");
		this.setLocation(0, 0);
		this.setBackground(Color.white);
		this.setBounds(0, 0, 1000, 50);
    
    this.screen.add(this);
  }
  
  private void loadAssets() {
    try {
      this.setFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("assets/fonts/FiraCodeNerdFont-Bold.ttf").openStream()).deriveFont(Font.PLAIN, 24));
    } catch (IOException e) {
      System.out.println(e.toString());
    } catch (FontFormatException e) {
      System.out.println(e.toString());
    }
  }

  public void updateScore(int score) {
    this.setText(String.format("Score: %d", score));
  }

  public void setLose(int score) {
    this.setText(String.format("You lose... Score: %d. Press 'space' to start again", score));
  }
}
