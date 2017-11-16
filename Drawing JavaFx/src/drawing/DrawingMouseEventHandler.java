package drawing;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
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
    
    
    private TranslateCommand translateCommand;
    
    private double newTranslateX;
    private double newTranslateY;

    public DrawingMouseEventHandler(Drawing drawing) {
        this.drawing = drawing;
    }
    
    public void handle(InputEvent event)
    {    
        if(event.getEventType().equals(KeyEvent.KEY_PRESSED))
        {
        	KeyEvent ke = (KeyEvent) event;
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
                translateCommand = new TranslateCommand(drawing, currentShape, orgTranslateX, orgTranslateY,newTranslateX, newTranslateY);
                currentShape.setOrigin(newTranslateX, newTranslateY);
                drawing.repaint();
            }
        }

        if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) 
        {
        	if(currentShape != null)
        		translateCommand.execute();
            currentShape = null;
        }
        
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
    }
}
