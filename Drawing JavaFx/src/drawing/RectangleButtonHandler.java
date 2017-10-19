package drawing;

import javafx.geometry.Point2D;

/**
 * Created by lewandowski on 07/09/2017.
 */
public class RectangleButtonHandler extends ShapeButtonHandler {

    public RectangleButtonHandler(Drawing drawing) {
        super(drawing);
    }

    @Override
    protected Shape createShape() {
        double x = Math.min(origin.getX(),destination.getX());
        double y = Math.min(origin.getY(),destination.getY());
        double width = Math.abs(destination.getX()-origin.getX());
        double height = Math.abs(destination.getY()-origin.getY());
        return new Rectangle(new Point2D(x, y), width, height);
    }
}
