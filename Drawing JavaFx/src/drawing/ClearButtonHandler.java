package drawing;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class ClearButtonHandler implements EventHandler<ActionEvent> {

    private Command clearCommand;

    public ClearButtonHandler(Drawing drawing) {
        clearCommand = new ClearCommand(drawing);
    }

    @Override
    public void handle(ActionEvent event) {
        try {
			clearCommand.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
