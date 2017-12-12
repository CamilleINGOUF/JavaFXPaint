package drawing;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;
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
			String s = JOptionPane.showInputDialog("File name ?");
			if(s == null || s.equals(""))
				return;
		
			XmlUtil.saveDrawing(drawing,s+".xml");
		} catch (FileNotFoundException | XMLStreamException e ) {
			e.printStackTrace();
		} catch (NullPointerException e) {
		}
	}

}
