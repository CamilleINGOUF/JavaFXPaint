package drawing;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class addTextButtonHandler implements EventHandler<ActionEvent> {

	private Drawing drawing;
	
	protected TextDecoratorCommand textCommand;
	
	public addTextButtonHandler(Drawing drawing) {
		this.drawing = drawing;
	}
	
	@Override
	public void handle(ActionEvent event) {
		textCommand = new TextDecoratorCommand(drawing);
		textCommand.execute();
	}

}
