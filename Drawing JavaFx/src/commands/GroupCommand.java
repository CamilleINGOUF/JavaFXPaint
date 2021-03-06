package commands;

import java.util.ArrayList;

import drawing.CompositeShape;
import drawing.Drawing;
import drawing.Shape;
import javafx.geometry.Point2D;

public class GroupCommand extends Command
{
	private ArrayList<Shape> groupedShapes;
	private CompositeShape createdGroup;
	
	public GroupCommand(Drawing drawing) 
	{
		super(drawing);
		groupedShapes = new ArrayList<Shape>();
	}

	public GroupCommand(GroupCommand that) 
	{
		super(that.drawing);
		this.history = that.history;
		groupedShapes = new ArrayList<Shape>();
		for(Shape s : that.groupedShapes)
			groupedShapes.add(s);
		this.createdGroup = (CompositeShape) that.createdGroup;
	}

	@Override
	public void execute() throws Exception 
	{	
		super.execute();
		if(drawing.getSelectedShapes().size() <= 1)
			throw new Exception("You need at least 2 shapes selected");
		
		double x = drawing.getSelectedShapes().get(0).getOrigin().getX();
		double y = drawing.getSelectedShapes().get(0).getOrigin().getY();
		
		for(int  i = 0; i < drawing.getSelectedShapes().size(); i++)
		{
			Shape s = drawing.getSelectedShapes().get(i);
			x = Math.min(x, s.getOrigin().getX());
			y = Math.min(y, s.getOrigin().getY());
		}
		
		CompositeShape cs = new CompositeShape(new Point2D(x, y));
		for(int  i = 0; i < drawing.getSelectedShapes().size(); i++)
		{
			Shape shape = drawing.getSelectedShapes().get(i);
			cs.add(shape);
			groupedShapes.add(shape);
		}
		
		while(drawing.getSelectedShapes().size() != 0) {
			Shape tmp = drawing.getSelectedShapes().get(0);
			tmp.setSelected(false);
			drawing.remove(tmp);
		}
		
		cs.setSelected(true);
		
		drawing.addShape(cs);
		createdGroup = cs;
        history.pushUndo(this.clone());
        createdGroup = null;
        groupedShapes.clear();
        history.clearRedos();
	}

	@Override
	public void undo() 
	{
		drawing.remove(createdGroup);
		for(Shape s : groupedShapes)
			drawing.addShape(s);
		drawing.repaint();
	}

	@Override
	public void redo() 
	{
		drawing.addShape(createdGroup);
		for(Shape s : groupedShapes)
			drawing.remove(s);
		drawing.repaint();
	}

	@Override
	public Command clone() {
		return new GroupCommand(this);
	}
}
