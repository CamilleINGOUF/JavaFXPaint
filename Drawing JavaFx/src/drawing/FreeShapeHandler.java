package drawing;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class FreeShapeHandler  implements EventHandler<ActionEvent>
{
	private DegroupCommand degroupCommand;

    public FreeShapeHandler(Drawing drawing) 
    {
        degroupCommand = new DegroupCommand(drawing);
    }

	@Override
	public void handle(ActionEvent event) 
	{
		try {
			degroupCommand.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
