package drawing;

import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
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
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter(
				"xml files (*.xml)", "xml");
		chooser.setDialogTitle("Open schedule file");
		chooser.setFileFilter(xmlfilter);
		chooser.showOpenDialog(null);
		try {
			XmlUtilLoad.loadDrawing(drawing,chooser.getSelectedFile().getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

}
