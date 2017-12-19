package buttons;

import drawing.Drawing;
import drawing.Observer;
import drawing.Shape;
import javafx.scene.control.Button;

public class UngroupButton extends Button implements Observer {

	private Drawing drawing;
	
	public UngroupButton(Drawing drawing) {
		super("Ungroup");
		this.drawing = drawing;
        this.drawing.registerObserver(this);
		this.setDisable(true);
	}
	
	@Override
	public void updateStatus() {
		int numberGroup = 0;
		for(Shape s : drawing) {
			if(!s.isSimple())
				numberGroup++;
		}
		
		this.setDisable(numberGroup <= 0);
	}

}
