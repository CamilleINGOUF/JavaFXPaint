package drawing;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class FreeShapeHandler  implements EventHandler<ActionEvent>
{

	private Drawing drawing;

    public FreeShapeHandler(Drawing drawing) {
        this.drawing = drawing;
    }

	@Override
	public void handle(ActionEvent event) 
	{
		ArrayList<Shape> shapesTemp = new ArrayList<Shape>();
		//taking the only container to free all shapes
		for(Iterator<Shape> iter = drawing.iterator(); iter.hasNext();)
		{
			Shape s = iter.next();
			if (s instanceof ContainerShape) 
			{
				ContainerShape cs = (ContainerShape) s;
				for(int i  = 0; i < cs.getShapes().size(); i++)
				{
					shapesTemp.add(cs.getShapes().get(i));
				}
			}
			else
			{
				shapesTemp.add(s);
			}
		}
		drawing.clear();
		for(Shape s : shapesTemp)
			drawing.addShape(s);
	}

}
