package drawing;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class ContainerShape extends Shape
{
	private ArrayList<Shape> shapes;

	public ContainerShape(Point2D origin) 
	{
		super(origin);
		shapes = new ArrayList<Shape>(0);
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
        for(int i = 0; i < shapes.size(); i++)
        {
        	double xt =  x - shapes.get(i).getOrigin().getX();
        	double yt = y - shapes.get(i).getOrigin().getY();
        	System.out.println("x : "+x+", y : "+y);
        	System.out.println("xo : "+shapes.get(i).getOrigin().getX()+", yo : "+shapes.get(i).getOrigin().getY());
        	System.out.println("xt : "+xt+", yt : "+yt+"\n==============");
        	shapes.get(i).setOrigin(x + xt, y + yt);
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
