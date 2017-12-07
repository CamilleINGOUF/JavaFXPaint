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
	
	public void execute() throws Exception {
		StatusExceptionSingleton.getInstance().hide();
	}
	
	public abstract void undo();
	
	public abstract void redo();
	
	public abstract Command clone();
}
