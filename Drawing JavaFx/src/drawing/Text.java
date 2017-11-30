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

}
