package drawing;

import java.util.ArrayList;

public abstract class RearrangeStrategy {

	protected ArrayList<Shape> oldShapes;
	protected ArrayList<Shape> newShapes;
	
	public RearrangeStrategy() {
		oldShapes = new ArrayList<>();
		newShapes = new ArrayList<>();
	}
	
	public RearrangeStrategy(RearrangeStrategy that) {
		oldShapes = new ArrayList<>();
		newShapes = new ArrayList<>();
		for(Shape s : that.newShapes)
			newShapes.add(s);
		for(Shape s : that.oldShapes)
			oldShapes.add(s);
	}
	
	public ArrayList<Shape> getNews() {
		return newShapes;
	}
	
	public ArrayList<Shape> getOlds() {
		return oldShapes;
	}

	public void clear() {
		oldShapes.clear();
		newShapes.clear();
	}

	public abstract RearrangeStrategy clone();
	
	public void rearrange(Drawing drawing) {
		for(Shape s : drawing) {
			oldShapes.add(s);
			newShapes.add(s.clone());
		}
		
		if(oldShapes.size() <= 0 ) {
			try {
				throw new DrawingException("No drawn shape");
			} catch (DrawingException e) {
				StatusExceptionSingleton.getInstance().sendError(e.getMessage());
			}
		}
	}
}
