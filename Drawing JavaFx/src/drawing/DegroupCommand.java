package drawing;

import java.util.ArrayList;

public class DegroupCommand extends Command
{
	public DegroupCommand(Drawing drawing) 
	{
		super(drawing);
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
					shapesTemp.add(cs.getShapes().get(j));
				}
				drawing.remove(cs);
			}
			else
			{
				shapesTemp.add(s);
			}
		}
		for(Shape s : shapesTemp)
			drawing.addShape(s);
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
