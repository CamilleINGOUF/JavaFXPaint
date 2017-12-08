package drawing;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class TextShapeDecorator extends ShapeDecorator {
	
	private Text text;

	public TextShapeDecorator(Point2D origin, Shape decoratedShape, String text) {
		super(origin, decoratedShape);
		this.text = new Text(origin, text);
	}

	public TextShapeDecorator(TextShapeDecorator that) {
		super(that);
		this.text = that.text;
	}
	
	public void setText(String txt)
	{
		text.setText(txt);
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		super.paint(gc);
		text.paint(gc);
	}
	
	@Override
	public boolean isOn(Point2D p) {
		return shape.isOn(p);
	}
	
	@Override
	public void setOrigin(double x, double y) {
		super.setOrigin(x, y);
		text.setOrigin(x, y);
	}

	@Override
	public Shape clone() {
		return new TextShapeDecorator(this);
	}

	@Override
	public String toString() {
		return new String("TextShapeDecorator[]");
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
