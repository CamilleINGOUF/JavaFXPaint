package drawing;

import java.util.ArrayList;

public class ClearCommand extends Command 
{
	private ArrayList<Shape> clearedShapes;
	
	public ClearCommand(Drawing drawing)
	{
		super(drawing);
		this.history = drawing.getCommandHistory();
		clearedShapes = new ArrayList<Shape>();
	}
	
	public ClearCommand(ClearCommand that) 
	{
		super(that.drawing);
		this.history = that.history;
		this.clearedShapes = new ArrayList<Shape>();
		for(Shape s : that.clearedShapes)
			this.clearedShapes.add(s);
	}

	@Override
	public void execute() throws Exception 
	{
		super.execute();
		saveShapes();
        
		history.pushUndo(this.clone());
		clearedShapes.clear();
        history.clearRedos();
	}

	@Override
	public void undo() 
	{
		for(Shape s : clearedShapes)
			drawing.addShape(s);
		clearedShapes.clear();
	}

	@Override
	public void redo() 
	{
		saveShapes();
	}

	private void saveShapes() {
		for(Shape s : drawing)
			clearedShapes.add(s);
		drawing.clear();
	}

	@Override
	public Command clone() {
		return new ClearCommand(this);
	}
	
}
