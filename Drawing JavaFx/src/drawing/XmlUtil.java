package drawing;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import javafx.geometry.Point2D;

public class XmlUtil {
	
	private static void parse(Shape shape, XMLStreamWriter writer) throws XMLStreamException {
		if(shape instanceof Circle)
			parse((Circle)shape,writer);
		else if(shape instanceof Rectangle)
			parse((Rectangle)shape,writer);
		else if(shape instanceof CompositeShape)
			parse((CompositeShape)shape,writer);
		else if(shape instanceof ShapeDecorator)
			parse((ShapeDecorator)shape,writer);
	}
	
	private static void parse(Circle circle, XMLStreamWriter writer) throws XMLStreamException {
		writer.writeStartElement("Circle");
		writer.writeAttribute("radius", circle.getWidth()/2+" ");
		parse(circle.getOrigin(),writer);
		writer.writeEndElement();
	}
	
	private static void parse(Rectangle rectangle, XMLStreamWriter writer) throws XMLStreamException {
		writer.writeStartElement("Rectangle");
		writer.writeAttribute("width", rectangle.getWidth()+" ");
		writer.writeAttribute("height", rectangle.getHeight()+" ");
		parse(rectangle.getOrigin(),writer);
		writer.writeEndElement();
	}
	
	private static void parse(CompositeShape group, XMLStreamWriter writer) throws XMLStreamException {
		writer.writeStartElement("CompositeShape");
		parse(group.getOrigin(),writer);
		writer.writeStartElement("Shapes");
		for(Shape s : group.getShapes())
			parse(s,writer);
		writer.writeEndElement();
		writer.writeEndElement();
	}
	
	private static void parse(ShapeDecorator shapeDec, XMLStreamWriter writer) throws XMLStreamException {
		if(shapeDec instanceof TextShapeDecorator)
			parse((TextShapeDecorator)shapeDec,writer);
	}
	
	private static void parse(TextShapeDecorator shapeDec, XMLStreamWriter writer) throws XMLStreamException {
		writer.writeStartElement("TextShapeDecorator");
		parse(shapeDec.getText(),writer);
		parse(shapeDec.getOrigin(),writer);
		parse(shapeDec.getShape(),writer);
		writer.writeEndElement();
	}
	
	private static void parse(Point2D point, XMLStreamWriter writer) throws XMLStreamException {
		writer.writeStartElement("Point");
		writer.writeAttribute("x", point.getX()+" ");
		writer.writeAttribute("y", point.getY()+" ");
		writer.writeEndElement();
	}
	
	private static void parse(Text text, XMLStreamWriter writer) throws XMLStreamException {
		writer.writeStartElement("Text");
		writer.writeAttribute("String",	text.getString());
		parse(text.getOrigin(),writer);
		writer.writeEndElement();
	}
	
	public static void saveDrawing(Drawing drawing, String filename) throws FileNotFoundException, XMLStreamException {
		XMLOutputFactory output = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = output.createXMLStreamWriter(new FileOutputStream(filename));
		for(Shape s : drawing) {
			parse(s, writer);
		}
		writer.flush();
		writer.close();
	}

}
