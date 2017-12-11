package drawing;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;

/**
 * Created by lewandowski on 05/09/2017.
 */
public class DrawingMouseEventHandler implements EventHandler<InputEvent>{

    private Drawing drawing;

    private double orgSceneX;
    private double orgSceneY;
    private double orgTranslateX;
    private double orgTranslateY;

    private Shape currentShape;
    
    private ArrayList<Observer> observers;
    
    private TranslateCommand translateCommand;
    
    private double newTranslateX;
    private double newTranslateY;

    public DrawingMouseEventHandler(Drawing drawing) {
        this.drawing = drawing;
        observers = new ArrayList<Observer>();
    }
    
    public void handle(InputEvent event)
    {
        if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED))
        {
        	MouseEvent me = (MouseEvent) event;
        	for (Shape s : drawing) 
        	{
                 if (s.isOn(new Point2D(me.getX(), me.getY())))
                 {
                     currentShape = s;
                 }
        	}
        	
        	if(currentShape == null)
        		return;
        	
        	if(!me.isShiftDown())
        	{
        		for(Shape s : drawing)
        			s.setSelected(false);
        	}
        	currentShape.setSelected(true);
        }

        if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) 
        {
        	MouseEvent me = (MouseEvent) event;
            orgSceneX = me.getSceneX();
            orgSceneY = me.getSceneY();

            for (Shape s : drawing) {
                if (s.isOn(new Point2D(me.getX(), me.getY())))
                {
                	currentShape = s;
                }
            }
            if (currentShape != null) {
                orgTranslateX = currentShape.getOrigin().getX();
                orgTranslateY = currentShape.getOrigin().getY();
            }
        }

        if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED))
        {
        	MouseEvent me = (MouseEvent) event;
            double offsetX = me.getSceneX() - orgSceneX;
            double offsetY = me.getSceneY() - orgSceneY;
            newTranslateX = orgTranslateX + offsetX;
            newTranslateY = orgTranslateY + offsetY;

            if (currentShape != null) {
                
                currentShape.setOrigin(newTranslateX, newTranslateY);
                drawing.repaint();
                
            }
        }

        if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) 
        {
        	MouseEvent me = (MouseEvent) event;
        	double offsetX = me.getSceneX() - orgSceneX;
            double offsetY = me.getSceneY() - orgSceneY;
            newTranslateX = orgTranslateX + offsetX;
            newTranslateY = orgTranslateY + offsetY;
        	if(currentShape != null && offsetX != 0 && offsetY != 0) {
        		translateCommand = new TranslateCommand(drawing, currentShape, orgTranslateX, orgTranslateY,newTranslateX, newTranslateY);
        		try {
					translateCommand.execute();
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
            currentShape = null;
        }
    	notifyObservers();
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
