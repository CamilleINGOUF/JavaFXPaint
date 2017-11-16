package drawing;

import javafx.geometry.Point2D;

public class RectangleCommand extends Command
{
	private Point2D origin;
	private Point2D destination;
	
	private Rectangle rectangle;

	public RectangleCommand(Drawing drawing, Point2D origin, Point2D destination) 
	{
		super(drawing);
		this.origin = new Point2D(origin.getX(), origin.getY());
		this.destination = new Point2D(destination.getX(), destination.getY());
	}
	
	public void execute()
	{
		double x = Math.min(origin.getX(),destination.getX());
        double y = Math.min(origin.getY(),destination.getY());
        double width = Math.abs(destination.getX()-origin.getX());
        double height = Math.abs(destination.getY()-origin.getY());
        rectangle = new Rectangle(new Point2D(x, y), width, height);
        drawing.addShape(rectangle);
        drawing.getCommandHistory().pushUndo(this);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		
	}

}
