package handlers;

import commands.Command;
import drawing.Drawing;
import drawing.Shape;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

/**
 * Created by lewandowski on 07/09/2017.
 */
public abstract class ShapeButtonHandler implements EventHandler<Event> {

    protected Drawing drawing;
    protected Point2D origin;
    protected Point2D destination;

    protected Shape shape;
    
    protected Command shapeCommand;

    public ShapeButtonHandler(Drawing drawing) {
        this.drawing = drawing;
    }

    @Override
    public void handle(Event event) {

        if (event instanceof ActionEvent) 
        {
            drawing.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        }

        if (event instanceof MouseEvent) {
            if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
                drawing.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
                origin = new Point2D( ((MouseEvent)event).getX(), ((MouseEvent)event).getY());
            }

            if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
                destination = new Point2D( ((MouseEvent)event).getX(), ((MouseEvent)event).getY());                
                createShape();

                drawing.removeEventHandler(MouseEvent.MOUSE_PRESSED, this);
                drawing.removeEventHandler(MouseEvent.MOUSE_RELEASED, this);
            }
        }
    }
    
    public abstract void createShape();
}
