package drawing;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

/**
 * Created by lewandowski on 05/09/2017.
 */
public class DrawingMouseEventHandler implements EventHandler<MouseEvent> {

    private Drawing drawing;

    private double orgSceneX;
    private double orgSceneY;
    private double orgTranslateX;
    private double orgTranslateY;

    private Shape currentShape;

    public DrawingMouseEventHandler(Drawing drawing) {
        this.drawing = drawing;
    }

    @Override
    public void handle(MouseEvent event) {

        if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
            orgSceneX = event.getSceneX();
            orgSceneY = event.getSceneY();

            for (Shape s : drawing) {
                if (s.isOn(new Point2D(event.getX(), event.getY()))) {
                    currentShape = s;
                }
            }
            if (currentShape != null) {
                orgTranslateX = currentShape.getOrigin().getX();
                orgTranslateY = currentShape.getOrigin().getY();
            }
        }

        if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
            double offsetX = event.getSceneX() - orgSceneX;
            double offsetY = event.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            if (currentShape != null) {
                currentShape.setOrigin(newTranslateX, newTranslateY);
                drawing.repaint();
            }
        }

        if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
            currentShape = null;
        }
    }
}
