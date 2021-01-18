package ball;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public final class Ball implements Balls {

	public int c = 0;

	private static double speedX;
	private static double speedY;
	private static double posX;
	private static double posY;
	private static double radius;
	private static Circle ball;

	public Ball() {
		ball = new Circle();
		speedX = (((int) Math.random() * 10) + 8) * ((int) (Math.random() * 2) == 0 ? -1 : 1);
		speedY = (((int) Math.random() * 10) + 8) * ((int) (Math.random() * 2) == 0 ? -1 : 1);
		radius = 20;
		posX = 0;
		posY = 0;
	}

	public Ball(double Width, double Height) {
		ball = new Circle();
		speedX = (((int) Math.random() * 5) + 3) * ((int) (Math.random() * 2) == 0 ? -1 : 1);
		speedY = (((int) Math.random() * 5) + 3) * ((int) (Math.random() * 2) == 0 ? -1 : 1);
		radius = 20;
		posX = Width / 2;
		posY = Height / 2;
	}

	@Override
	public void setXSpeed(double xSpeed) {

		Ball.speedX = xSpeed;

	}

	@Override
	public double getXSpeed() {
		return Ball.speedX;
	}

	@Override
	public void setYSpeed(double ySpeed) {
		Ball.speedY = ySpeed;

	}

	@Override
	public double getYSpeed() {
		return Ball.speedY;
	}

	@Override
	public Circle getCircle() {
		return ball;
	}

	@Override
	public void setRadius(double radius) {
		ball.setRadius(radius);
		Ball.radius = radius;

	}

	@Override
	public double getRadius() {
		return Ball.radius;
	}

	@Override
	public void hitY() {
		Ball.speedX *= -1;

	}

	@Override
	public void hitX() {
		Ball.speedY *= -1;

	}

	public boolean willHit(Rectangle hitbox) {// Will improve by using some mathematics for now conditions
		boolean ret = false;
		double fPosX = Ball.posX + Ball.speedX;
		double fPosY = Ball.posY + Ball.speedY;
		double iPosX = Ball.posX;
		double iPosY = Ball.posY;
		double paddleX = hitbox.getX();
		double paddleY = hitbox.getY();

		boolean movingLeft = Ball.speedX < 0; // is it moving towards left?
		boolean movingUp = Ball.speedY < 0;// is it moving upwards?
		boolean paddleLeft = iPosX >= paddleX && ((Math.abs((iPosX - paddleX)) - Math.abs((fPosX - paddleX))) > 0);
		boolean paddleRight = iPosX <= paddleX && ((Math.abs((iPosX - paddleX)) - Math.abs((fPosX - paddleX))) > 0);
		boolean paddleUp = iPosY >= paddleY && (Math.abs(iPosY - paddleY) - Math.abs(fPosY - paddleY) > 0);
		boolean paddleDown = iPosY <= paddleY && (Math.abs(iPosY - paddleY) - Math.abs(fPosY - paddleY) > 0);

		if (hitbox.getHeight() > hitbox.getWidth()) {
			// Verticle Line Hits
			if (movingLeft) {

				if (paddleLeft) {
					if ((paddleX + hitbox.getWidth()) >= ((int) fPosX)
							&& (paddleY <= fPosY && (paddleY + hitbox.getHeight() >= fPosY))) {
						hitY();
						ret = true;
					}

				}
			} else if (!movingLeft) {
				if (paddleRight) {
					if (paddleX - hitbox.getWidth() <= (int) fPosX
							&& (paddleY <= fPosY && (paddleY + hitbox.getHeight() >= fPosY))) {
						hitY();
						ret = true;
					}
				}
			}
		} else {
			// Horizontal Line hits
			if (movingUp) {

				if (paddleUp) {
					if (((paddleY + hitbox.getHeight()) >= (int) fPosY)
							&& ((paddleX <= fPosX) && (paddleX + (hitbox.getWidth()) > fPosX))) {
						hitX();
						ret = true;

					}

				}

			} else if (!movingUp) {
				if (paddleDown) {
					if ((paddleY + hitbox.getHeight()) <= (int) fPosY
							&& ((paddleX <= fPosX) && (paddleX + (hitbox.getWidth()) > fPosX))) {
						hitX();
						ret = true;
					}
				}

			}
		}
		return ret;
	}

	@Override
	public void setXP(double x) {
		Ball.posX = x;
		ball.setCenterX(Ball.posX);

	}

	@Override
	public void setYP(double y) {
		Ball.posY = y;
		ball.setCenterY(Ball.posY);

	}

	@Override
	public double getXP() {
		return Ball.posX;
	}

	@Override
	public double getYP() {
		return Ball.posY;
	}

	@Override
	public void move() {
		setXP(getXP() + Ball.speedX);
		setYP(getYP() + Ball.speedY);

	}

}