package drawing;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by lewandowski on 07/09/2017.
 */
public class Rectangle extends Shape {

    private double width;
    private double height;

    public Rectangle(Point2D origin, double width, double height) {
        super(origin);
        this.width = width;
        this.height = height;
    }
    
    public Rectangle(Rectangle that) 
    {
    	super(that);
    	this.width = that.width;
    	this.height = that.height;
	}

    public Rectangle() {
    	super();
	}

	@Override
    public void paint(GraphicsContext gc) {
    	Color border = selected ? Color.LIGHTGREEN : Color.GREEN;
        gc.setFill(Color.GREENYELLOW);
        gc.setStroke(border);
        gc.setLineWidth(3);
        gc.fillRect(origin.getX() - width/2, origin.getY() - height/2, width, height);
        gc.strokeRect(origin.getX() - width/2, origin.getY() - height/2, width, height);
    }

    @Override
    public boolean isOn(Point2D p) {
        return (p.getX() > origin.getX()-width/2 && p.getX() < origin.getX()+width/2 &&
                p.getY() > origin.getY()-height/2 && p.getY() < origin.getY()+height/2);
    }

	@Override
	public Shape clone() 
	{
		return new Rectangle(this);
	}

	@Override
	public String toString() {
		return new String("Rectangle[width="+width+",height="+height+"]");
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public double getWidth() {
		return width;
	}
	
	public void setWidth(double w) {
		this.width = w;
	}
	
	public void setHeight(double h) {
		this.height = h;
	}
	
}
