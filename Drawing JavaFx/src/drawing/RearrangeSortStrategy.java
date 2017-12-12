package drawing;

import java.util.Collections;

import com.google.common.collect.Iterators;

public class RearrangeSortStrategy extends RearrangeStrategy {

	public RearrangeSortStrategy() {
		
	}

	public RearrangeSortStrategy(RearrangeStrategy that) {
		super(that);
	}
	
	@Override
	public void rearrange(Drawing drawing) {
		super.rearrange(drawing);
		
		Collections.sort(newShapes);
		int numberShapes = Iterators.size(drawing.iterator());
		double widest = 0;
		double tallest = 0;
		int column = 0;
		int line = 0;
		boolean uneven = numberShapes % 2 == 1;

		if(numberShapes == 1) {
			column = 1;
			line = 1;
		} else {
			for(int i = 2; i <= numberShapes; i++) {
				int tempNumber = uneven ? numberShapes + 1 : numberShapes;
				if(((float)tempNumber / (float)i) == (tempNumber / i)) {
					column = i;
					line = (tempNumber / i);
					break;
				}
			}
		}
		
		for(int i = 0; i < oldShapes.size(); i++) {
			drawing.remove(oldShapes.get(i));
		}

		for(Shape s :  oldShapes) {
			if(s.getHeight() > tallest) 
				tallest = s.getHeight();
				
			if(s.getWidth() > widest)
				widest = s.getWidth();
		}

		int index = 0;
		for(int i = 0; i < line; i++) {
			for(int j = 0; j < column; j++) {
				if(index < numberShapes) {
					newShapes.get(index).setOrigin(i * widest + 100, j * tallest + 100);
					drawing.addShape(newShapes.get(index));
				}
				index++;
			}
		}
		drawing.repaint();
	}

	@Override
	public RearrangeStrategy clone() {
		return new RearrangeSortStrategy(this);
	}

}
