package drawing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import javafx.geometry.Point2D;


public class XmlUtilLoad {
	private static void parse(Shape s, XMLStreamReader reader) throws XMLStreamException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(s instanceof Circle)
			parse((Circle)s,reader);
	}
	
	private static void parse(Point2D point, XMLStreamReader reader) throws XMLStreamException {
		Class<?> shapeClass;
		Method setter;
		while(reader.hasNext()) {
			int eventType = reader.next();
			switch (eventType) {
			case XMLStreamReader.ATTRIBUTE:
				shapeClass = point.getClass();
				
				break;
			case XMLStreamReader.END_ELEMENT:
				return;
			default:
				break;
			}
			
		}
	}
	
	private static void parse(Circle circle, XMLStreamReader reader) throws XMLStreamException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> shapeClass;
		Method setter;
		while(reader.hasNext()) {
			int eventType = reader.next();
			switch (eventType) {
			case XMLStreamReader.ATTRIBUTE:
				shapeClass = circle.getClass();
				setter = shapeClass.getMethod("setRadius",double.class);
				setter.invoke(circle, Double.parseDouble(reader.getLocalName()));
				break;
			case XMLStreamReader.START_ELEMENT:
				Point2D point = new Point2D(0, 0);
				parse(point, reader);
				circle.setOrigin(point.getX(), point.getY());
				break;
			case XMLStreamReader.END_ELEMENT:
				return;
			default:
				break;
			}
		}
	}
	
	public static void loadDrawing(Drawing drawing, String filename) throws FileNotFoundException, XMLStreamException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		drawing.clear();
		
		XMLInputFactory factory = XMLInputFactory.newFactory();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(filename));
		
		Class<?> shapeClass;
		Constructor<?> shapeConstructor;
		
		ArrayList<Shape> shapes = new ArrayList<>();
		
		while(reader.hasNext()) {
			int eventType = reader.next();
			switch (eventType) {
			case XMLStreamReader.START_ELEMENT:
				shapeClass = Class.forName(reader.getLocalName());
				shapeConstructor = shapeClass.getConstructor();
				Shape shape = (Shape) shapeConstructor.newInstance();
				parse(shape, reader);
				shapes.add(shape);
				break;
			default:
				break;
			}
		}
		
		for(Shape s : shapes)
			drawing.addShape(s);
		
		drawing.repaint();
	}
}
