package drawing;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class CompositeShape extends Shape
{
	private ArrayList<Shape> shapes;

	public CompositeShape(Point2D origin) 
	{
		super(origin);
		shapes = new ArrayList<Shape>(0);
	}
	
	public CompositeShape(CompositeShape that) 
	{
		super(that);
		this.shapes = new ArrayList<Shape>();
		for(Shape s : that.shapes)
			this.shapes.add(s.clone());
		refreshBounds();
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
		double oldX = origin.getX();
		double oldY = origin.getY();
		double translateX = x- oldX ;
		double translateY =  y - oldY;
        this.origin = new Point2D(x, y);
        for(int i = 0; i < shapes.size(); i++)
        {
        	double xt =  translateX + shapes.get(i).getOrigin().getX();
        	double yt = translateY + shapes.get(i).getOrigin().getY();
        	shapes.get(i).setOrigin(xt, yt);
        }
        refreshBounds();
    }
	
	private void refreshBounds() {
		double minX = 0;
		double maxX = 0;
		double minY = 0;
		double maxY = 0;
		
		for(Shape s : shapes) {
			double currentX = origin.getX() - s.getOrigin().getX();
			double currentY = origin.getY() - s.getOrigin().getY();
			if(minX > currentX)
				minX = currentX;
			else if(maxX < currentX)
				maxX = currentX;
			
			if(minY > currentY)
				minY = currentY;
			else if(maxY < currentY)
				maxY = currentY;
		}
		
		height = maxY - minY;
		width = maxX - minX;
	}
	
	public void setSelected(boolean flag) {
		selected = flag;
		for(Shape s : shapes)
			s.setSelected(flag);
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	/* Composite */
    public void add(Shape e) 
    {
        shapes.add(e);
        refreshBounds();
    }
	
    public void remove(Shape e) 
    {
    	shapes.remove(e);
    	refreshBounds();
    }

    public ArrayList<Shape> getShapes()
    {
    	return shapes;
    }
    /* ********* */

	@Override
	public Shape clone() 
	{
		return new CompositeShape(this);
	}
	
	@Override
	public boolean isSimple()
	{
		return false;
	}

	@Override
	public String toString() {
		return new String("Group[size="+shapes.size()+"]");
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public double getWidth() {
		return width;
	}
}
