package drawing;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by lewandowski on 07/09/2017.
 */
public abstract class Shape {

    protected Point2D origin;

    public Shape(Point2D origin) {
        setOrigin(origin.getX(), origin.getY());
    }

    public Point2D getOrigin() {
        return origin;
    }

    public abstract void paint(GraphicsContext gc) ;

    public abstract boolean isOn(Point2D p);

    public void setOrigin(double x, double y) {
        this.origin = new Point2D(x, y);
    }
}
