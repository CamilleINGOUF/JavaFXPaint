package drawing;

import java.util.ArrayList;

import com.google.common.collect.Iterators;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RearrangeButtonHandler implements  EventHandler<ActionEvent>  {

	//TODO RearrangeCommand
	private Drawing drawing;
	
	private ArrayList<Shape> oldShapes;
	
	public RearrangeButtonHandler(Drawing drawing) {
		this.drawing = drawing;
		
		oldShapes = new ArrayList<>();
	}

	@Override
	public void handle(ActionEvent event) {
		int numberShapes = Iterators.size(drawing.iterator());
		int column = 0;
		int line = 0;
		for(Shape s :  drawing) {
			oldShapes.add(s.clone());
			//TODO
		}
	}

}
