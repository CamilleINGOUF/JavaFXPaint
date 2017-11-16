package drawing;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UndoButtonHandler implements EventHandler<ActionEvent> 
{
	private UndoCommand undoCommand;

	public UndoButtonHandler(Drawing drawing) 
	{
		undoCommand = new UndoCommand(drawing);
	}

	@Override
	public void handle(ActionEvent event) 
	{
		undoCommand.execute();
	}

}
