package drawing;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RearrangeButtonHandler implements  EventHandler<ActionEvent>  {
	private RearrangeCommand command;
	
	public RearrangeButtonHandler(Drawing drawing) {
		
		command = new RearrangeCommand(drawing);
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			command.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
