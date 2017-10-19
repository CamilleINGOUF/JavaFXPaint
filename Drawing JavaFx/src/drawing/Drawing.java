package drawing;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by lewandowski on 05/09/2017.
 */
public class Drawing extends Canvas implements Iterable<Shape> {

    DrawingMouseEventHandler handler;
    GraphicsContext gc;

    ArrayList<Shape> shapes;

    public Drawing() {
        super();
        shapes = new ArrayList<>();
        gc = getGraphicsContext2D();
        handler = new DrawingMouseEventHandler(this);
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, handler);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, handler);
    }

    @Override
    public Iterator<Shape> iterator() {
        return shapes.iterator();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
        repaint();
    }

    public void clear() {
        shapes.clear();
        repaint();
    }

    public void repaint() {
        gc.clearRect(0,0,getWidth(), getHeight());
        for (Shape s: shapes) {
            s.paint(gc);
        }
    }

    @Override
    public boolean isResizable() {
        return true;
    }
}
