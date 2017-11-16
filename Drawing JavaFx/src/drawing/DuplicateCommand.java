package drawing;

import java.util.ArrayList;

public class DuplicateCommand extends Command
{

	public DuplicateCommand(Drawing drawing) 
	{
		super(drawing);
	}

	@Override
	public void execute() 
	{
		ArrayList<Shape> sh = new ArrayList<Shape>();
		for(Shape s : drawing)
		{
			if(s.isSelected())
				sh.add(s.clone());
		}
		for(Shape s : sh)
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
