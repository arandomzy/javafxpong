package ball;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public interface Balls {

	public void setXP(double x);

	public void setYP(double y);

	public double getXP();

	public double getYP();

	public void setXSpeed(double xSpeed);

	public double getXSpeed();

	public void setYSpeed(double ySpeed);

	public double getYSpeed();

	public Circle getCircle();

	public void setRadius(double radius);

	public double getRadius();

	public void hitY();

	public void hitX();

	public void move();

	public boolean willHit(Rectangle hotbox);// if line from (X,Y) to (X+speed,Y+speed)cuts
												// (x+width,y) to (x+width,y+height) or (x,y) to
												// (x,y+height)
}