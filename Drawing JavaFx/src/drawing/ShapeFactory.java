package drawing;

import java.util.HashMap;

public class ShapeFactory {
	
	private static final HashMap<ShapeType, Shape> shapes = new HashMap<>();
	
	public static Shape getShape(ShapeType type) {
		
		Shape shape = shapes.get(type);
		
		if(shape == null) {
			if(type.equals(ShapeType.CIRCLE)) {
				shape = new Circle();
			} else if(type.equals(ShapeType.RECTANGLE)) {
				shape = new Rectangle();
			}
			shapes.put(type, shape);
		}
		
		return shape;
	}
	
	public static enum ShapeType {
		CIRCLE, RECTANGLE;
	}
}
