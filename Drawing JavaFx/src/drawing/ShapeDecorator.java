package drawing;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class ShapeDecorator extends Shape {
	
	protected Shape shape;

	public ShapeDecorator(Point2D origin, Shape decoratedShape) {
		super(origin);
		shape = decoratedShape;
	}

	public ShapeDecorator(Shape that) {
		super(that);	
	}

	@Override
	public void paint(GraphicsContext gc) {
		shape.paint(gc);
	}
	
	@Override
	public void setOrigin(double x, double y)
	{
		origin = new Point2D(x, y);
		shape.setOrigin(x, y);
	}

	@Override
	public boolean isOn(Point2D p) {
		return shape.isOn(p);
	}

	@Override
	public abstract Shape clone();

}
