package drawing;

import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LoadButtonHandler implements EventHandler<ActionEvent> {

	private Drawing drawing;

	public LoadButtonHandler(Drawing drawing) {
		this.drawing = drawing;
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			XmlUtilLoad.loadDrawing(drawing, "rectangle.xml");
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}

	}

}
