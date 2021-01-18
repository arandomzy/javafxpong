package pong;

import ball.Ball;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import paddle.Paddle;

public final class Pong extends Application {

	private int p1 = 0, p2 = 0;
	private final int HEIGHT = 750;
	private final int WIDTH = 1000;
	private final int P_HEIGHT = 150;
	private final int P_WIDTH = 20;
	private Boolean isMovingUp = false;
	private Boolean isMovingDown = false;
	private Paddle paddle2 = new Paddle(WIDTH - P_WIDTH, (HEIGHT / 2) - (P_HEIGHT / 2), P_WIDTH, P_HEIGHT);
	private Paddle paddle1 = new Paddle(0, ((HEIGHT / 2) - (P_HEIGHT / 2)), P_WIDTH, P_HEIGHT);
	private Ball ball = new Ball();
	private boolean gameRunning = false;

	public void start(Stage primaryStage) {
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), (e) -> run(gc)));
		timeline.setCycleCount(Timeline.INDEFINITE);

		ball.setXP(WIDTH / 2);
		ball.setYP(HEIGHT / 2);

		Scene scene = new Scene(new Pane(canvas), WIDTH, HEIGHT);

		scene.setOnKeyPressed(e -> {

			if ((e.getCode().equals(KeyCode.W) || e.getCode().equals(KeyCode.UP))) {
				isMovingDown = false;
				isMovingUp = true;
			}
			if ((e.getCode().equals(KeyCode.S) || e.getCode().equals(KeyCode.DOWN))) {
				isMovingUp = false;
				isMovingDown = true;

			}
			if (e.getCode().equals(KeyCode.SPACE)) {
				gameRunning = true;
			}
		});
		scene.setOnKeyReleased(e -> {
			isMovingDown = false;
			isMovingUp = false;
		});

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("PONG");
		primaryStage.show();
		timeline.play();

	}

	public void run(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		gc.setFill(Color.LIMEGREEN);
		gc.fillRect(0, 0, WIDTH, 3);
		gc.fillRect(0, HEIGHT - 3, WIDTH, 3);
		if (gameRunning) {

			gc.setStroke(Color.WHITE);
			gc.setFill(Color.YELLOW);

			// Paddle - 1
			paddle1.moveUp(isMovingUp);
			paddle1.moveDown(isMovingDown);

			if (paddle1.getYP() < HEIGHT - P_HEIGHT && (paddle1.getYP() > (0)))
				gc.fillRect(paddle1.getXP(), paddle1.getYP(), P_WIDTH, P_HEIGHT);
			else if (paddle1.getYP() >= HEIGHT - P_HEIGHT) {
				gc.fillRect(paddle1.getXP(), HEIGHT - P_HEIGHT, P_WIDTH, P_HEIGHT);
				paddle1.setYP(HEIGHT - P_HEIGHT);
			} else if (paddle1.getYP() <= 0) {
				gc.fillRect(paddle1.getXP(), 0, P_WIDTH, P_HEIGHT);
				paddle1.setYP(0);
			}

			// Paddle 2
			paddle2.setXP(WIDTH - P_WIDTH);
			if (ball.getYP() <= HEIGHT - (P_HEIGHT / 2) && ball.getYP() >= (P_HEIGHT / 2)) {
				paddle2.setYP(ball.getYP() - (P_HEIGHT / 2));
			} else if (ball.getYP() <= P_HEIGHT / 2) {
				paddle2.setYP(0);
			} else if (ball.getYP() >= HEIGHT - (P_HEIGHT / 2)) {
				paddle2.setYP(HEIGHT - P_HEIGHT);
			}
			gc.fillRect(paddle2.getXP(), paddle2.getYP(), P_WIDTH, P_HEIGHT);

			// Ball
			gc.setFill(Color.RED);
			ball.willHit(paddle1.getRectangle());
			ball.willHit(new Rectangle(0, 0, WIDTH, 0));
			ball.willHit(new Rectangle(0, HEIGHT, WIDTH, 0));
			ball.willHit(paddle2.getRectangle());
			ball.move();
			gc.fillOval(ball.getXP(), ball.getYP(), ball.getRadius(), ball.getRadius());
			if (ball.getXP() <= 0 || ball.getXP() >= WIDTH || ball.getYP() <= 0 || ball.getYP() >= HEIGHT) {
				gameRunning = false;
				if (ball.getXSpeed() < 0) {
					p2 = p2 + 1;
				} else {
					p1 = p1 + 1;
				}

			}
		} else {
			gc.setFill(Color.WHITE);
			gc.fillText("Press space to serve", (WIDTH / 2) - 35, (HEIGHT / 2) - 10);
			gc.fillText("P1 : " + p1, (WIDTH / 2) - 100, (HEIGHT / 2) + 10);
			gc.fillText("P2 : " + p2, (WIDTH / 2) + 100, (HEIGHT / 2) + 10);
			ball = new Ball(WIDTH, HEIGHT);
		}
//		System.out.printf("Paddle X,Y : %f,%f%nBall X,Y : %f,%f%nBall Speed X,Y : %f,%f%n", paddle1.getXP(),paddle1.getYP(), ball.getXP(), ball.getYP(), ball.getXSpeed(), ball.getYSpeed());
	}

	public static void main(String args[]) {
		launch(args);
	}

}// Total Lines 500+