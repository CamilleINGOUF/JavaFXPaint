package drawing;

public class UndoCommand extends Command 
{
	public UndoCommand(Drawing drawing) 
	{
		super(drawing);
	}

	@Override
	public void execute() 
	{
		Command cmd = history.popUndo();
		if(cmd != null)
		{
			history.pushRedo(cmd);
			cmd.undo();
		}
		
		System.out.println(history);
	}

	@Override
	public void undo() 
	{
		
	}

	@Override
	public void redo() 
	{
		
	}

}
