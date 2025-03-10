import java.util.Random;

public class PipeGroup {
  int GAP = 200;
  int LIMIT = 300;
  int WIDTH = 150;
  int VEL = 5;

  Pipe topPipe = new Pipe();
  Pipe bottomPipe = new Pipe();
  Screen screen;

  Random rand = new Random();
  int topPipeHeight;

  PipeGroup(Screen screen) {
    this.screen = screen;

    this.topPipeHeight = this.rand.nextInt(this.screen.getHeight() - this.GAP*3/2);

    this.topPipe.setBounds(this.screen.getWidth(), this.topPipeHeight - this.screen.getHeight(), this.WIDTH, this.screen.getHeight());
    this.bottomPipe.setBounds(this.screen.getWidth(), this.topPipeHeight + this.GAP, this.WIDTH, this.screen.getHeight());

    this.loadAssets();
  }

  private void loadAssets() {
    this.topPipe.setIcon(ImageLoader.loadImageTransformed("assets/images/pipe-top.png", this.WIDTH, this.screen.getHeight()));
    this.bottomPipe.setIcon(ImageLoader.loadImageTransformed("assets/images/pipe-bottom.png", this.WIDTH, this.screen.getHeight()));
  }

  public void update() {
    this.topPipe.setLocation(this.topPipe.getX() - this.VEL, this.topPipe.getY());
    this.bottomPipe.setLocation(this.bottomPipe.getX() - this.VEL, this.bottomPipe.getY());
  }

  public boolean hitOutside() {
    return this.topPipe.getX() < -this.WIDTH;
  }

  public void insertIntoScreen() {
    this.screen.add(this.topPipe);
    this.screen.add(this.bottomPipe);
  }

  public void removeFromScreen() {
    this.screen.remove(this.topPipe);
    this.screen.remove(this.bottomPipe);
  }

  public boolean hitMe(int x, int y, int dist) {
    return 
      x > this.topPipe.getX() - dist &&
      x < this.topPipe.getX() + this.WIDTH + dist &&
      (
        y < this.topPipeHeight + dist ||
        y > this.topPipeHeight + this.GAP - dist
      );
  }

  public boolean passMe(int x, int y, int dist) {
    return x > this.topPipe.getX() + this.WIDTH + dist;
  }
}
