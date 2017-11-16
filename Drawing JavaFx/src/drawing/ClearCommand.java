package drawing;

import java.util.ArrayList;

public class ClearCommand extends Command 
{
	private ArrayList<Shape> clearedShapes;
	
	public ClearCommand(Drawing drawing)
	{
		super(drawing);
		clearedShapes = new ArrayList<Shape>();
	}
	
	@Override
	public void execute() 
	{
		saveShapes();
        
		history.pushUndo(this);
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
	
}
