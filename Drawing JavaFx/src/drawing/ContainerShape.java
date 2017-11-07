package drawing;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class ContainerShape extends Shape
{
	private ArrayList<Shape> shapes = new ArrayList<Shape>(0);

	public ContainerShape(Point2D origin) 
	{
		super(origin);
	}

	@Override
	public void paint(GraphicsContext gc) 
	{
		for(Shape s : shapes)
			s.paint(gc);
	}

	@Override
	public boolean isOn(Point2D p) 
	{
		for(Shape s : shapes)
			if(s.isOn(p)) return true;
		return false;
	}
	
	public void setOrigin(double x, double y)
    {
        this.origin = new Point2D(x, y);
        if(shapes == null)
        	return;
        for(Shape s : shapes)
        {
        	double xt =  x - s.getOrigin().getX();
        	double yt = y - s.getOrigin().getY();
        	s.setOrigin(x + xt, y + yt);
        }
    }
	
	/* Composite */
    public void add(Shape e) 
    {
        shapes.add(e);
    }
	
    public void remove(Shape e) 
    {
    	shapes.remove(e);
    }

    public ArrayList<Shape> getShapes()
    {
    	return shapes;
    }
    /* ********* */
}
