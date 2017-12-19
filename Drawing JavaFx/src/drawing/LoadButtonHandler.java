package drawing;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;
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
			String s = JOptionPane.showInputDialog("File name ?");
			if(s == null || s.equals(""))
				return;
		
			XmlUtilLoad.loadDrawing(drawing,s+".xml");
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}

	}

}
