package handlers;

import commands.TextDecoratorCommand;
import drawing.Drawing;
import exceptions.StatusExceptionSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TextButtonHandler implements EventHandler<ActionEvent> {

	private Drawing drawing;
	
	protected TextDecoratorCommand textCommand;
	
	public TextButtonHandler(Drawing drawing) {
		this.drawing = drawing;
	}
	
	@Override
	public void handle(ActionEvent event) {
		textCommand = new TextDecoratorCommand(drawing);
		try {
			textCommand.execute();
		} catch (Exception e) {
			StatusExceptionSingleton.getInstance().sendError("Can't add text on a group of shapes !");
		}
	}

}
