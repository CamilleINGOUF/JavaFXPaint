package drawing;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 * Created by lewandowski on 05/09/2017.
 */
public class Drawing extends Canvas implements Iterable<Shape> {

    DrawingMouseEventHandler handler;
    GraphicsContext gc;

    ArrayList<Shape> shapes;
    
    private ArrayList<StatusObserver> observers;

    public Drawing() {
        super();
        shapes = new ArrayList<>();
        gc = getGraphicsContext2D();
        handler = new DrawingMouseEventHandler(this);
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, handler);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, handler);
        observers = new ArrayList<StatusObserver>();
    }

    @Override
    public Iterator<Shape> iterator() {
        return shapes.iterator();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
        repaint();
        notifyObservers();
    }

    public void clear() {
        shapes.clear();
        repaint();
        notifyObservers();
    }
    
    public ArrayList<Shape> getShapes()
    {
    	return shapes;
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
    
    public void registerObserver(StatusObserver s)
    {
    	observers.add(s);
    }
    
    public void notifyObservers()
    {
    	for(StatusObserver s : observers)
    		s.updateStatus();
    }
}
