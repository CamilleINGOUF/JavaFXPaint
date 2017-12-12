package drawing;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Text extends Shape {

	private String text;
	
	public Text(Point2D origin, String text) {
		super(origin);
		text = new String(text);
	}

	public Text(Text that) {
		super(that);
		text = new String(that.text);
	}
	
	public void setText(String text)
	{
		this.text = new String(text);
	}
	
	public String getString() {
		return text;
	}

	@Override
	public void paint(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillText(text, origin.getX(), origin.getY());
	}

	@Override
	public boolean isOn(Point2D p) {
		return false;
	}

	@Override
	public Shape clone() {
		return new Text(this);
	}

	@Override
	public String toString() {
		return new String("Text[]");
	}

	@Override
	public double getHeight() {
		return 0;
	}

	@Override
	public double getWidth() {
		return 0;
	}

}
