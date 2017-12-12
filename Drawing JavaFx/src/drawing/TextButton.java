package drawing;

import javafx.scene.control.Button;

public class TextButton extends Button implements Observer {

	private Drawing drawing;
	
	public TextButton(Drawing drawing) {
		super("Text");
		this.drawing = drawing;
        this.drawing.registerObserver(this);
		this.setDisable(true);
	}

	@Override
	public void updateStatus() {
		int numberSimpleShapes = 0;
		for(Shape s : drawing) {
			if(s.isSimple())
				numberSimpleShapes++;
		}
		
		this.setDisable(numberSimpleShapes <= 0);
	}

}
