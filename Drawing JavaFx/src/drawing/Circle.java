package drawing;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by lewandowski on 07/09/2017.
 */
public class Circle extends Shape 
{

    private double radius;

    public Circle(Point2D origin, double radius) {
        super(origin);
        this.radius = radius;
    }
    
    public Circle(Circle that) 
    {
    	super(that);
    	this.radius = that.radius;
	}

    @Override
    public void paint(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.setStroke(Color.ORANGE);
        gc.setLineWidth(3);
        gc.fillOval(origin.getX()-radius, origin.getY()-radius, 2*radius, 2*radius);
        gc.strokeOval(origin.getX()-radius, origin.getY()-radius, 2*radius, 2*radius);
    }

    @Override
    public boolean isOn(Point2D p) {
        return distanceToCenter(p)<(radius);
    }

    private double distanceToCenter(Point2D p) {
        return Math.abs(this.origin.distance(p));
    }

	@Override
	public Shape clone() 
	{
		return new Circle(this);
	}

	@Override
	public String toString() {
		return new String("Circle[radius="+radius+"]");
	}
}
