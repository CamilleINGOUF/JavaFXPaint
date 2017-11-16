package drawing;

public class RedoCommand extends Command 
{

	public RedoCommand(Drawing drawing) 
	{
		super(drawing);
	}

	@Override
	public void execute() 
	{
		Command cmd = history.popRedo();
		if(cmd != null)
		{
			history.pushUndo(cmd);
			cmd.redo();
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
