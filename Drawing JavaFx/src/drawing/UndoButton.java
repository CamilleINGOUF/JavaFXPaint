package drawing;

import javafx.scene.control.Button;

public class UndoButton extends Button implements Observer {
	
	private CommandHistory history;

	public UndoButton(Drawing drawing) {
		super("Undo");
		this.history = drawing.getCommandHistory();
        this.history.registerObserver(this);
		this.setDisable(true);
	}

	@Override
	public void updateStatus() {
		if(history.canUndo())
			this.setDisable(false);
		else
			this.setDisable(true);
	}

}
