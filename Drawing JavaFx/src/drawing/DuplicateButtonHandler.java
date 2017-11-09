package drawing;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DuplicateButtonHandler implements EventHandler<ActionEvent> 
{
	private Drawing drawing;

    public DuplicateButtonHandler(Drawing drawing) 
    {
        this.drawing = drawing;
    }
	
	@Override
	public void handle(ActionEvent event) 
	{
		ArrayList<Shape> sh = new ArrayList<Shape>();
		for(Shape s : drawing)
		{
			if(s.isSelected())
				sh.add(s.clone());
		}
		for(Shape s : sh)
			drawing.addShape(s);
	}
}
