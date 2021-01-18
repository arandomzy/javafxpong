package paddle;

import javafx.scene.shape.Rectangle;

public interface Paddles {

	public double getXP();

	public double getYP();

	public void setXP(double x);

	public void setYP(double y);

	public Rectangle getRectangle();

}