package handlers;

import commands.RedoCommand;
import drawing.Drawing;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RedoButtonHandler implements EventHandler<ActionEvent> 
{
	private RedoCommand redoCommand;
	
	public RedoButtonHandler(Drawing drawing) 
	{
		redoCommand = new RedoCommand(drawing);
	}

	@Override
	public void handle(ActionEvent event) 
	{
		try {
			redoCommand.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
