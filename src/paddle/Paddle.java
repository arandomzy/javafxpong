package paddle;

import javafx.scene.shape.Rectangle;

public final class Paddle implements Paddles {

	private double x;
	private double y;
	private double width;
	private double height;
	private Rectangle paddle;
	private final double ACCELRATION = 1.65;
	private double speed;
	private final double nonZeroMinSpeed = 10;
	private final double maxSpeed = 50;

	public Paddle(int x, int y, double width, double height) {

		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.speed = 0.00;
		paddle = new Rectangle(this.width, this.height);
		paddle.setX(x);
		paddle.setY(y);

	}

	public Paddle() {

		paddle = new Rectangle();
		paddle.setArcHeight(100);
		paddle.setArcWidth(20);

	}

	public Rectangle getRectangle() {
		return this.paddle;
	}

	public double getXP() {
		return this.x;
	}

	public double getYP() {
		return this.y;
	}

	public void setXP(double x) {
		this.x = x;
		paddle.setX(this.x);
	}

	public void setYP(double y) {
		this.y = y;
		paddle.setY(this.y);
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeed() {
		return speed;
	}

	public boolean moveUp(boolean moving) {
		try {
			if (moving) {// pressedAgain &&
				speed = nonZeroMinSpeed;
			}
			if (moving && speed < maxSpeed) {
				setYP(getYP() - speed);
				speed = speed * ACCELRATION;
//				pressedAgain = false;
			}
			if (moving && speed >= maxSpeed) {
				speed = maxSpeed;
				setYP(getYP() - speed);
			}
//			if (!moving && speed > 5) {
//				setYP(getYP() - speed);
//				speed = speed / ACCELRATION;
//				pressedAgain = true;
//			}
			if (!moving) {// && speed <= 5
				speed = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean moveDown(boolean moving) {
		try {
			if (moving) {// pressedAgain &&
				speed = nonZeroMinSpeed;
			}
			if (moving && speed < maxSpeed) {
				setYP(getYP() + speed);
				speed = speed * ACCELRATION;
//				pressedAgain = false;
			}
			if (moving && speed >= maxSpeed) {
				speed = maxSpeed;
				setYP(getYP() + speed);
			}
//			if (!moving && speed > 5) {
//				setYP(getYP() + speed);
//				speed = speed / ACCELRATION;
//				pressedAgain = true;
//			}
			if (!moving) {// && speed <= 5
				speed = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}