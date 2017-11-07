package drawing;

import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;

public class ContainerShapeHandler implements EventHandler<ActionEvent>
{

	private Drawing drawing;

    public ContainerShapeHandler(Drawing drawing) {
        this.drawing = drawing;
    }

	@Override
	public void handle(ActionEvent event) 
	{
		//Taking ALL the shape to make one container
		ContainerShape cs = new ContainerShape(new Point2D(0, 0));
		for(Iterator<Shape> iter = drawing.iterator(); iter.hasNext();)
			cs.add(iter.next());
		drawing.clear();
		drawing.addShape(cs);
	}
}
