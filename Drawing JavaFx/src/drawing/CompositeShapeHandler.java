package drawing;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;

public class CompositeShapeHandler implements EventHandler<ActionEvent>
{

	private Drawing drawing;

    public CompositeShapeHandler(Drawing drawing) 
    {
        this.drawing = drawing;
    }

	@Override
	public void handle(ActionEvent event) 
	{	
		if(drawing.getSelectedShapes().size() == 0)
			return;
		
		double x = drawing.getSelectedShapes().get(0).getOrigin().getX();
		double y = drawing.getSelectedShapes().get(0).getOrigin().getY();
		
		for(int  i = 0; i < drawing.getSelectedShapes().size(); i++)
		{
			Shape s = drawing.getSelectedShapes().get(i);
			x = Math.min(x, s.getOrigin().getX());
			y = Math.min(y, s.getOrigin().getY());
		}
		
		CompositeShape cs = new CompositeShape(new Point2D(x, y));
		for(int  i = 0; i < drawing.getSelectedShapes().size(); i++)
		{
			cs.add(drawing.getSelectedShapes().get(i));
		}
		
		while(drawing.getSelectedShapes().size() != 0)
			drawing.remove(drawing.getSelectedShapes().get(0));
		
		drawing.addShape(cs);
	}
}
