package drawing;

import java.util.ArrayList;
import java.util.Stack;

public class CommandHistory
{
	private Stack<Command> undos;
	private Stack<Command> redos;
	
    private ArrayList<Observer> observers;
	
	public CommandHistory()
	{
		this.undos = new Stack<Command>();
		this.redos = new Stack<Command>();
        observers = new ArrayList<Observer>();
	}
	
	public Command popUndo()
	{
		if(undos.size() == 0)
			return null;
		notifyObservers();
		return undos.pop();
	}
	
	public void pushUndo(Command cmd)
	{
		undos.push(cmd);
		notifyObservers();
	}
	
	public Command popRedo()
	{
		if(redos.size() == 0)
			return null;
		notifyObservers();
		return redos.pop();
	}
	
	public void pushRedo(Command cmd)
	{
		redos.push(cmd);
		notifyObservers();
	}
	
	public void clearUndos()
	{
		undos.clear();
		notifyObservers();
	}
	
	public void clearRedos()
	{
		redos.clear();
		notifyObservers();
	}
	
	public boolean canRedo() {
		return !redos.isEmpty();
	}
	
	public boolean canUndo() {
		return !undos.isEmpty();
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
	
    public void registerObserver(Observer s)
    {
    	observers.add(s);
		notifyObservers();
    }
    
    private void notifyObservers()
    {
    	for(Observer s : observers)
    		s.updateStatus();
    }
}
