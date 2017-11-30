package drawing;

import java.util.ArrayList;

public class DuplicateCommand extends Command
{
	private ArrayList<Shape> createdShape;

	public DuplicateCommand(Drawing drawing) 
	{
		super(drawing);
		this.history = drawing.getCommandHistory();
		createdShape = new ArrayList<Shape>();
	}

	public DuplicateCommand(DuplicateCommand that) 
	{
		super(that.drawing);
		this.history = that.history;
		createdShape = new ArrayList<Shape>();
		for(Shape s : that.createdShape)
			createdShape.add(s);
	}

	@Override
	public void execute() throws Exception 
	{
		ArrayList<Shape> sh = new ArrayList<Shape>();
		for(Shape s : drawing)
		{
			if(s.isSelected())
			{
				Shape clone = s.clone();
				sh.add(clone);
				createdShape.add(clone);
			}
		}
		for(Shape s : sh)
			drawing.addShape(s);
        drawing.getCommandHistory().pushUndo(this.clone());
        createdShape.clear();
	}

	@Override
	public void undo() 
	{
		for(Shape s : createdShape)
			drawing.remove(s);
		drawing.repaint();
	}

	@Override
	public void redo() 
	{
		for(Shape s : createdShape)
			drawing.addShape(s);
		drawing.repaint();
	}

	@Override
	public Command clone() {
		return new DuplicateCommand(this);
	}
}
