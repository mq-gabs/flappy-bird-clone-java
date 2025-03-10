import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {
	boolean running = true;
	
	Screen screen = new Screen();
	Bird bird = new Bird(screen);
	Text text = new Text(screen);
	PipeGroupGenerator pipeGroupGenerator = new PipeGroupGenerator(screen);

	GameStateEnum state = GameStateEnum.MENU;
	int FPS = 60;
	int score = 0;

	Game() {
		screen.addKeyListener(this);
		screen.setVisible(true);
	}

	private void reset() {
		this.screen.reset();
		this.bird = new Bird(screen);
		this.text = new Text(screen);
		this.pipeGroupGenerator = new PipeGroupGenerator(screen);

		this.state = GameStateEnum.PLAYING;
		this.score = 0;
		this.text.updateScore(this.score);
	}

	public void start() {
		this.run();
	}

	public void run() {
		while(this.running) {
			switch (state) {
				case MENU:
					this.runMenu();
					break;
				case PLAYING:
					this.runPlaying();
					break;
				case LOSE:
					this.runLose();
					break;
				default:
					break;
			}

			try {
				Thread.sleep(1000/FPS);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}

	public void runMenu() {}

	public void runPlaying() {
		this.pipeGroupGenerator.update();
		this.bird.update();

		int dist = this.bird.SIZE/2;
		int x = this.bird.getX() + dist;
		int y = this.bird.getY() + dist;

		if (this.bird.hitOutside() || this.pipeGroupGenerator.hitNextPipe(x, y, dist)) {
			this.state = GameStateEnum.LOSE;
			this.text.setLose(this.score);
			Sound.playCrash();
			return;
		}

		if (this.pipeGroupGenerator.passNextPipe(x, y, dist)) {
			this.score += 1;
			Sound.playScore();
			this.text.updateScore(score);
		}
	}

	public void runLose() {}

	public void controllMenu(KeyEvent e) {
		if (e.getKeyChar() == ' ') {
			this.text.setText("Score: 0");
			this.state = GameStateEnum.PLAYING;
		}
	}

	public void controllPlaying(KeyEvent e) {
		if (e.getKeyChar() == ' ') {
			this.bird.fly();
		}
	}

	public void controllLose(KeyEvent e) {
		if (e.getKeyChar() == ' ') {
			this.reset();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (this.state) {
			case MENU:
				this.controllMenu(e);
				break;
			case PLAYING:
				this.controllPlaying(e);
				break;
			case LOSE:
				this.controllLose(e);
				break;
			default:
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
}
