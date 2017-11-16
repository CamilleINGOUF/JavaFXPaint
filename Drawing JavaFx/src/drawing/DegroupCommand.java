package drawing;

import java.util.ArrayList;

public class DegroupCommand extends Command
{
	private ArrayList<Shape> targets;
	
	private CompositeShape group;
	
	public DegroupCommand(Drawing drawing) 
	{
		super(drawing);
		targets = new ArrayList<Shape>();
	}

	public DegroupCommand(DegroupCommand that) 
	{
		super(that.drawing);
		this.history = drawing.getCommandHistory();
		this.history = that.history;
		targets = new ArrayList<Shape>();
		for(Shape s : that.targets)
			targets.add(s);
	}

	@Override
	public void execute() 
	{
		ArrayList<Shape> shapesTemp = new ArrayList<Shape>();
		for(int  i = 0; i < drawing.getSelectedShapes().size(); i++)
		{
			Shape s = drawing.getSelectedShapes().get(i);
			if (s instanceof CompositeShape) 
			{
				CompositeShape cs = (CompositeShape) s;
				for(int j  = 0; j < cs.getShapes().size(); j++)
				{
					Shape shape = cs.getShapes().get(j);
					shapesTemp.add(shape);
					targets.add(shape);
				}
				group = cs;
				drawing.remove(cs);
			}
			else
			{
				shapesTemp.add(s);
			}
		}
		for(Shape s : shapesTemp)
			drawing.addShape(s);
     	history.pushUndo(this);
     	history.clearRedos();
	}

	@Override
	public void undo() 
	{
		for(Shape s : targets)
			drawing.remove(s);
		drawing.addShape(group);
		drawing.repaint();
	}

	@Override
	public void redo() 
	{
		drawing.remove(group);
		for(Shape s : targets)
			drawing.addShape(s);
		drawing.repaint();
	}

	@Override
	public Command clone() {
		return new DegroupCommand(this);
	}
	
}
