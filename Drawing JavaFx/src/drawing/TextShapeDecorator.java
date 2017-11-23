package drawing;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class TextShapeDecorator extends ShapeDecorator {
	
	private Text text;

	public TextShapeDecorator(Point2D origin, Shape decoratedShape) {
		super(origin, decoratedShape);
		text = new Text(origin);
	}

	public TextShapeDecorator(ShapeDecorator that) {
		super(that);
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

}
