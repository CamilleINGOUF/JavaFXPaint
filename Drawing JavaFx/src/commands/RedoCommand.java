package commands;

import drawing.Drawing;

public class RedoCommand extends Command 
{

	public RedoCommand(Drawing drawing) 
	{
		super(drawing);
		this.history = drawing.getCommandHistory();
	}

	public RedoCommand(RedoCommand that)
	{
		super(that.drawing);
		this.history = that.history;
	}

	@Override
	public void execute() throws Exception 
	{
		super.execute();
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

	@Override
	public Command clone() {
		return new RedoCommand(this);
	}

}
