package drawing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import drawing.ShapeFactory.ShapeType;
import javafx.geometry.Point2D;


public class XmlUtilLoad {
	private static Shape parse(XMLStreamReader reader, String shape) throws XMLStreamException{
		if(shape.equals("Circle")) {
			return parseCircle(reader);
		} else if(shape.equals("Rectangle")) {
			return parseRectangle(reader);
		} else if (shape.equals("CompositeShape")) {
			return parseGroup(reader);
		} else if(shape.equals("TextShapeDecorator")) {
			return parseTextDecorator(reader);
		}
		return null;
	}

	private static Point2D parsePoint(XMLStreamReader reader) throws XMLStreamException {
		Point2D point =  new Point2D(Double.parseDouble(reader.getAttributeValue(0)), Double.parseDouble(reader.getAttributeValue(1)));
		reader.next();
		return point;
	}

	private static Rectangle parseRectangle(XMLStreamReader reader) throws XMLStreamException {
		Rectangle rectangle = (Rectangle) ShapeFactory.getShape(ShapeType.RECTANGLE);

		rectangle.setWidth(Double.parseDouble(reader.getAttributeValue(0)));
		rectangle.setHeight(Double.parseDouble(reader.getAttributeValue(1)));
		reader.next();
		Point2D point = parsePoint(reader);
		rectangle.setOrigin(point.getX(), point.getY());

		return rectangle;
	}

	private static Circle parseCircle(XMLStreamReader reader) throws XMLStreamException {
		Circle circle = (Circle) ShapeFactory.getShape(ShapeType.CIRCLE);
		
		circle.setRadius(Double.parseDouble(reader.getAttributeValue(0)));
		reader.next();
		Point2D point = parsePoint(reader);
		circle.setOrigin(point.getX(), point.getY());

		return circle;
	}
	
	private static CompositeShape parseGroup(XMLStreamReader reader) throws XMLStreamException {
		reader.next();
		CompositeShape group = new CompositeShape(parsePoint(reader));
		reader.next();
		
		ArrayList<Shape> shapes = new ArrayList<>();
		boolean flag = true;
		
		while(flag) {
			reader.next();//start element
			int eventType = reader.getEventType();
			
			if(reader.getLocalName().equals("Shapes") && eventType == XMLStreamReader.END_ELEMENT) {
				flag = false;
			} else {
				shapes.add(parse(reader,reader.getLocalName()));
			}
			reader.next();//end element
		}
		for(Shape s : shapes)
			group.add(s);
		return group;
	}

	private static TextShapeDecorator parseTextDecorator(XMLStreamReader reader) throws XMLStreamException {
		TextShapeDecorator deco = new TextShapeDecorator();
		
		reader.next();// start Text
		Text text = parseText(reader);
		reader.next(); // end Text - start Point
		
		Point2D point = parsePoint(reader);
		reader.next();//end Point - start Shape
		Shape shape = parse(reader, reader.getLocalName());
		
		deco.setText(text);
		deco.setShape(shape);
		deco.setOrigin(point.getX(), point.getY());
		reader.next();
		
		return deco;
	}
	
	private static Text parseText(XMLStreamReader reader) throws XMLStreamException {
		Text text = new Text();
		text.setText(reader.getAttributeValue(0));
		reader.next();// Point 
		Point2D point = parsePoint(reader);
		text.setOrigin(point.getX(), point.getY());
		reader.next();//end Point
		return text;
	}

	public static void loadDrawing(Drawing drawing, String filename) throws FileNotFoundException, XMLStreamException {
		drawing.clear();

		XMLInputFactory factory = XMLInputFactory.newFactory();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(filename));

		ArrayList<Shape> shapes = new ArrayList<>();

		while(reader.hasNext()) {
			int eventType = reader.next();
			switch (eventType) {
			case XMLStreamReader.START_ELEMENT:
				if(! reader.getLocalName().equals("Drawing")) {
					Shape shape = parse(reader,reader.getLocalName());
					shapes.add(shape);
				}
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
