package drawing;

public abstract class Command 
{
	protected Drawing drawing;
	protected CommandHistory history;
	
	public Command(Drawing drawing)
	{
		this.drawing = drawing;
		this.history = drawing.getCommandHistory();
	}
	
	public abstract void execute() throws Exception;
	
	public abstract void undo();
	
	public abstract void redo();
	
	public abstract Command clone();
}
