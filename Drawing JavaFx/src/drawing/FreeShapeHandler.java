package drawing;

import java.util.ArrayList;

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
		for(int  i = 0; i < drawing.getSelectedShapes().size(); i++)
		{
			Shape s = drawing.getSelectedShapes().get(i);
			if (s instanceof ContainerShape) 
			{
				ContainerShape cs = (ContainerShape) s;
				for(int j  = 0; j < cs.getShapes().size(); j++)
				{
					shapesTemp.add(cs.getShapes().get(j));
				}
			}
			else
			{
				shapesTemp.add(s);
			}
		}
		for(Shape s : shapesTemp)
			drawing.addShape(s);
	}

}
