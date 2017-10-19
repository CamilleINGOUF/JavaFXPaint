package drawing;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class ClearButtonHandler implements EventHandler<ActionEvent> {

    private Drawing drawing;

    public ClearButtonHandler(Drawing drawing) {
        this.drawing = drawing;
    }

    @Override
    public void handle(ActionEvent event) {
        drawing.clear();
    }
}
