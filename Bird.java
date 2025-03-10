import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bird extends JLabel {
  int vel = 0;
  int acc = 1;
  int SIZE = 40;
  int MAX_VEL = 16;

  Screen screen;

  ImageIcon birdUp;
  ImageIcon birdDown;

  Bird(Screen screen) {
    this.screen = screen;
    this.setBounds(0, 0, SIZE, SIZE);
    this.setLocation(this.screen.getWidth()/2 - this.SIZE, this.screen.getHeight()/2 - this.SIZE);

    this.loadAssets();
    
    this.screen.add(this);
  }

  private void loadAssets() {
    this.birdUp = ImageLoader.loadImageTransformed("assets/images/bird-up.png", this.SIZE, this.SIZE);
    this.birdDown = ImageLoader.loadImageTransformed("assets/images/bird-down.png", this.SIZE, this.SIZE);

    this.setBirdDown();
  } 

  private void setBirdUp() {
    this.setIcon(this.birdUp);
  }

  private void setBirdDown() {
    this.setIcon(this.birdDown);
  }

  public void update() {
    if (this.vel < this.MAX_VEL) {
      this.vel += this.acc;
    }
    this.setLocation(this.getX(), this.getY() + this.vel);

    if (this.getY() > this.screen.getHeight() - this.SIZE) {
      this.setLocation(this.getX(), this.screen.getHeight() - this.SIZE);
    }

    if (this.getY() < 0) {
      this.setLocation(this.getX(), 0);
    }

    if (this.vel >= 0) {
      this.setBirdDown();
    }

    if (this.vel < 0) {
      this.setBirdUp();
    }
  }

  public boolean hitOutside() {
    return this.getY() >= this.screen.getHeight() - this.SIZE || this.getY() <= 0;
  }

  public void fly() {
    this.vel = -this.MAX_VEL;
  }
}
