package drawing;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CompositeShapeHandler implements EventHandler<ActionEvent>
{
	GroupCommand groupCommand;

    public CompositeShapeHandler(Drawing drawing) 
    {
    	groupCommand = new GroupCommand(drawing);
    }

	@Override
	public void handle(ActionEvent event) 
	{	
		try {
			groupCommand.execute();
		} catch (Exception e) {
			StatusExceptionSingleton.getInstance().sendError("No Shape selected");
		}
	}
}
