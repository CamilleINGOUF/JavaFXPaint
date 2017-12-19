package handlers;

import commands.DuplicateCommand;
import drawing.Drawing;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DuplicateButtonHandler implements EventHandler<ActionEvent> 
{
	private DuplicateCommand duplicateCommand;

    public DuplicateButtonHandler(Drawing drawing) 
    {
        duplicateCommand = new DuplicateCommand(drawing);
    }
	
	@Override
	public void handle(ActionEvent event) 
	{
		try {
			duplicateCommand.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
