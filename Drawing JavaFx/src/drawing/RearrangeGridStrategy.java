package drawing;

import com.google.common.collect.Iterators;

public class RearrangeGridStrategy extends RearrangeStrategy {

	public RearrangeGridStrategy() {
		super();
	}

	public RearrangeGridStrategy(RearrangeGridStrategy that) {
		super(that);
	}

	@Override
	public RearrangeGridStrategy clone() {
		return new RearrangeGridStrategy(this);
	}

	@Override
	public void rearrange(Drawing drawing) {
		int numberShapes = Iterators.size(drawing.iterator());
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

		for(Shape s :  drawing) {
			oldShapes.add(s);
			newShapes.add(s.clone());
		}

		for(int i = 0; i < oldShapes.size(); i++) {
			drawing.remove(oldShapes.get(i));
		}

		int index = 0;
		for(int i = 0; i < line; i++) {
			for(int j = 0; j < column; j++) {
				if(index < numberShapes) {
					newShapes.get(index).setOrigin(i * 150 + 100, j * 150 + 100);
					drawing.addShape(newShapes.get(index));
				}
				index++;
			}
		}
		drawing.repaint();
	}

}
