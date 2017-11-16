package drawing;

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
		duplicateCommand.execute();
	}
}
