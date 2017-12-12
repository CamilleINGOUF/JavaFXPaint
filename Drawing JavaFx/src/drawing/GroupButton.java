package drawing;

import javafx.scene.control.Button;

public class GroupButton extends Button implements Observer {

	private Drawing drawing;
	
	public GroupButton(Drawing drawing ) {
		super("Group");
		this.drawing = drawing;
        this.drawing.registerObserver(this);
		this.setDisable(true);
	}

	@Override
	public void updateStatus() {
		int size = drawing.getShapes().size();
		this.setDisable(size <= 1);
	}

}
