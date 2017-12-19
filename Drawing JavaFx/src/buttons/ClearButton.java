package buttons;

import drawing.Drawing;
import drawing.Observer;
import javafx.scene.control.Button;

public class ClearButton extends Button implements Observer {

	private Drawing drawing;
	
	public ClearButton(Drawing drawing) {
		super("Clear");
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
