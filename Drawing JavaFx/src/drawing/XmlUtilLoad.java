package drawing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import javafx.geometry.Point2D;


public class XmlUtilLoad {
	private static Shape parse(XMLStreamReader reader, String shape) throws XMLStreamException{
		if(shape.equals("Circle")) {
			return parseCircle(reader);
		} else if(shape.equals("Rectangle")) {
			return parseRectangle(reader);
		}
		return null;
	}

	private static Point2D parsePoint(XMLStreamReader reader) throws XMLStreamException {
		Point2D point =  new Point2D(Double.parseDouble(reader.getAttributeValue(0)), Double.parseDouble(reader.getAttributeValue(1)));
		reader.next();
		return point;
	}

	private static Rectangle parseRectangle(XMLStreamReader reader) throws XMLStreamException {
		Rectangle rectangle = new Rectangle();

		rectangle.setWidth(Double.parseDouble(reader.getAttributeValue(0)));
		rectangle.setHeight(Double.parseDouble(reader.getAttributeValue(1)));
		reader.next();
		Point2D point = parsePoint(reader);
		rectangle.setOrigin(point.getX(), point.getY());

		return rectangle;
	}

	private static Circle parseCircle(XMLStreamReader reader) throws XMLStreamException {
		Circle circle = new Circle();

		circle.setRadius(Double.parseDouble(reader.getAttributeValue(0)));
		reader.next();
		Point2D point = parsePoint(reader);
		circle.setOrigin(point.getX(), point.getY());

		return circle;
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
