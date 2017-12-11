package drawing;

public class RearrangeVerticalStrategy extends RearrangeStrategy {

	public RearrangeVerticalStrategy() {
		super();
	}
	
	public RearrangeVerticalStrategy(RearrangeVerticalStrategy that) {
		super(that);
	}

	@Override
	public RearrangeVerticalStrategy clone() {
		return new RearrangeVerticalStrategy(this);
	}

	@Override
	public void rearrange(Drawing drawing) {
		super.rearrange(drawing);
		
		double tallest = 50;
		
		for(Shape s : oldShapes) {
			if(s.getHeight() > tallest)
				tallest = s.getHeight();
		}
		
		for(int  i = 0; i < newShapes.size(); i++)  {
			newShapes.get(i).setOrigin(50, i*tallest+10);
			
			drawing.remove(oldShapes.get(i));
			drawing.addShape(newShapes.get(i));
		}
		
		drawing.repaint();
	}

}
