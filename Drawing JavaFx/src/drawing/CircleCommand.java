package drawing;

import javafx.geometry.Point2D;

public class CircleCommand extends Command 
{
	private Point2D origin;
	private Point2D destination;
	
	private Circle circle;

	public CircleCommand(Drawing drawing, Point2D origin, Point2D destination) 
	{
		super(drawing);
		this.history = drawing.getCommandHistory();
		this.origin = new Point2D(origin.getX(), origin.getY());
		this.destination = new Point2D(destination.getX(), destination.getY());
	}

	public CircleCommand(CircleCommand that) 
	{
		super(that.drawing);
		this.history = that.history;
		this.origin = new Point2D(that.origin.getX(), that.origin.getY());
		this.destination = new Point2D(that.destination.getX(), that.destination.getY());
	}

	@Override
	public void execute() throws Exception 
	{
		super.execute();
		circle = new Circle(origin, destination.distance(origin));
		drawing.addShape(circle);
       	history.pushUndo(this);
        history.clearRedos();
	}

	@Override
	public void undo() 
	{
		drawing.remove(circle);
		drawing.repaint();
	}

	@Override
	public void redo() 
	{
		drawing.addShape(circle);
		drawing.repaint();
	}

	@Override
	public Command clone() {
		return new CircleCommand(this);
	}

}
