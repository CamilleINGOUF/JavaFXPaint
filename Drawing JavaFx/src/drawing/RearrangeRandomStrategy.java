package drawing;

public class RearrangeRandomStrategy extends RearrangeStrategy {

	public RearrangeRandomStrategy() {
		super();
	}

	public RearrangeRandomStrategy(RearrangeRandomStrategy that) {
		super(that);
	}

	@Override
	public RearrangeRandomStrategy clone() {
		return new RearrangeRandomStrategy(this);
	}

	@Override
	public void rearrange(Drawing drawing) {
		for(Shape s : drawing) {
			oldShapes.add(s);
			newShapes.add(s.clone());
		}
		
		for(int i = 0; i < newShapes.size(); i++) {
			
			double x = Math.random() * drawing.getWidth();
			double y = Math.random() * drawing.getHeight();
			
			newShapes.get(i).setOrigin(x, y);
			drawing.remove(oldShapes.get(i));
			drawing.addShape(newShapes.get(i));
		}
		
		drawing.repaint();
	}

}
