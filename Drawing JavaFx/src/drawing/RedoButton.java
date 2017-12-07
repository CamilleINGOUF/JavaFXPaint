package drawing;

import javafx.scene.control.Button;

public class RedoButton extends Button implements Observer {
	
	private CommandHistory history;
	
	public RedoButton(Drawing drawing) {
		super("Redo");
		this.history = drawing.getCommandHistory();
        this.history.registerObserver(this);
		this.setDisable(true);
	}

	@Override
	public void updateStatus() {
		if(history.canRedo())
			this.setDisable(false);
		else
			this.setDisable(true);
	}

}
