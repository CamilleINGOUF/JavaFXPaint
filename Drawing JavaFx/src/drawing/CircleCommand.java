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
		this.origin = new Point2D(origin.getX(), origin.getY());
		this.destination = new Point2D(destination.getX(), destination.getY());
	}

	public CircleCommand(CircleCommand circleCommand) {
		//TODO faire ce contructeur
	}

	@Override
	public void execute() 
	{
		circle = new Circle(origin, destination.distance(origin));
		drawing.addShape(circle);
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

	@Override
	public Command clone() {
		return new CircleCommand(this);
	}

}
