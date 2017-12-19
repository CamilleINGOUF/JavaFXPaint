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
    
    ArrayList<double[]> savedShapes;
    
    private ArrayList<Observer> observers;
    
    private CommandHistory commandHistory;

    public Drawing() {
        super();
        shapes = new ArrayList<>();
        gc = getGraphicsContext2D();
        handler = new DrawingMouseEventHandler(this);
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, handler);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, handler);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        observers = new ArrayList<Observer>();
        commandHistory = new CommandHistory();
        savedShapes = new ArrayList<>();
    }
    
    public DrawingMouseEventHandler getHandler() {
    	return handler;
    }

    @Override
    public Iterator<Shape> iterator() 
    {
        return shapes.iterator();
    }
    
    

    public void addShape(Shape shape) {
    	savedShapes.add(new double[]{shape.getOrigin().getX(),shape.getOrigin().getY(),shape.getWidth(),shape.getHeight()});
    	
        shapes.add(shape);
        repaint();
        notifyObservers();
    }

    public void clear() {
        shapes.clear();
        repaint();
        notifyObservers();
    }
    
    public void remove(Shape s)
    {
    	savedShapes.remove(shapes.indexOf(s));
    	shapes.remove(s);
    	notifyObservers();
    }
    
    public ArrayList<Shape> getShapes()
    {
    	return shapes;
    }
    
    public ArrayList<Shape> getSelectedShapes()
    {
    	ArrayList<Shape> sh = new ArrayList<Shape>();
    	
    	for(Shape s : shapes)
    	{
    		if(s.isSelected())
    			sh.add(s);
    	}
    	
    	return sh;
    }
    
    public CommandHistory getCommandHistory()
    {
    	return commandHistory;
    }

    public void repaint() 
    {
        gc.clearRect(0,0,getWidth(), getHeight());
        for (Shape s: shapes) 
        {
            s.paint(gc,savedShapes.get(shapes.indexOf(s)));
        }
    }

    @Override
    public boolean isResizable() {
        return true;
    }
    
    public void registerObserver(Observer s)
    {
    	observers.add(s);
    }
    
    private void notifyObservers()
    {
    	for(Observer s : observers)
    		s.updateStatus();
    }
}
