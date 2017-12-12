package drawing;

import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SaveButtonHandler implements EventHandler<ActionEvent> {

	private Drawing drawing;
	
	public SaveButtonHandler(Drawing drawing) {
		this.drawing = drawing;
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			XmlUtil.saveDrawing(drawing);
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}
	}

}
