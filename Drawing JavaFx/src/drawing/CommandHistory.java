package drawing;

import java.util.Stack;

public class CommandHistory 
{
	private Stack<Command> undos;
	private Stack<Command> redos;
	
	public CommandHistory()
	{
		this.undos = new Stack<Command>();
		this.redos = new Stack<Command>();
	}
	
	public Command popUndo()
	{
		if(undos.size() == 0)
			return null;
		return undos.pop();
	}
	
	public void pushUndo(Command cmd)
	{
		undos.push(cmd);
	}
	
	public Command popRedo()
	{
		if(redos.size() == 0)
			return null;
		return redos.pop();
	}
	
	public void pushRedo(Command cmd)
	{
		redos.push(cmd);
	}
	
	public void clearUndos()
	{
		undos.clear();
	}
	
	public void clearRedos()
	{
		redos.clear();
	}
	
	@Override
	public String toString()
	{
		String str = new String();
		
		str+= "[Undos] ";
		for(Command c : undos)
			str+= c+" ";
		str+= "[/Undos]\n[Redos] ";
		for(Command c : redos)
			str+= c+" ";
		str+= "[/Redos]\n========================";
		
		return str;
	}
}
