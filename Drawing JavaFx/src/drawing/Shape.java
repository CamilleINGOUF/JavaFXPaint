package drawing;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by lewandowski on 07/09/2017.
 */
public abstract class Shape implements Comparable<Shape>
{
	protected Point2D origin;

	protected boolean selected;

	public Shape(Point2D origin)
	{
		this.origin = new Point2D(origin.getX(), origin.getY());
		selected = false;
	}

	public Shape(Shape that)
	{
		this.origin = new Point2D(that.origin.getX(), that.getOrigin().getY());
		selected = false;
	}

	public Shape() {
		this.origin = new Point2D(0, 0);
		selected = false;
	}

	public Point2D getOrigin() 
	{
		return origin;
	}

	public boolean isSelected()
	{
		return selected;
	}

	public void setSelected(boolean flag)
	{
		selected = flag;
	}

	public abstract void paint(GraphicsContext gc, double[] values) ;

	public abstract boolean isOn(Point2D p);

	public void setOrigin(double x, double y) 
	{
		this.origin = new Point2D(x, y);
	}

	public abstract Shape clone();

	public boolean isSimple()
	{
		return true;
	}

	public abstract double getHeight();

	public abstract double getWidth();

	@Override
	public abstract String toString();

	public int compareTo(Shape compareShape) {
		return Integer.compare((int)(this.getWidth() + this.getHeight()), (int) (compareShape.getWidth() + compareShape.getHeight()));
	}
}
