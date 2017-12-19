package buttons;

import drawing.Drawing;
import drawing.Observer;
import javafx.scene.control.Button;

public class DuplicateButton extends Button implements Observer {

	private Drawing drawing;

	public DuplicateButton(Drawing drawing) {
		super("Duplicate");
		this.drawing = drawing;
		this.drawing.registerObserver(this);
		this.setDisable(true); 
	}

	@Override
	public void updateStatus() {
		int numberOfShapes = drawing.getShapes().size();
		this.setDisable(numberOfShapes <= 0);
	}

}
