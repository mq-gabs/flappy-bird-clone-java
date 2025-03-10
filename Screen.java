import java.awt.Color;

import javax.swing.JFrame;

public class Screen extends JFrame {
  int WIDTH = 1280;
  int HEIGTH = 720;
  
  Screen() {
    this.setTitle("Flappy Bird Clone");
		this.setSize(this.WIDTH, this.HEIGTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
    this.getContentPane().setBackground(Color.decode("#87CEEB"));
    this.setIconImage(ImageLoader.loadImageTransformed("assets/images/bird-up.png", 30, 30).getImage());
  }

  public void reset() {
    this.getContentPane().removeAll();
    this.repaint();
  }
}
