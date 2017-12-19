package commands;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import drawing.Drawing;
import drawing.Shape;
import drawing.ShapeDecorator;
import drawing.TextShapeDecorator;
import exceptions.DrawingException;

public class TextDecoratorCommand extends Command {
	
	private ArrayList<TextShapeDecorator> decorators;
	private ArrayList<Shape> oldShapes;

	public TextDecoratorCommand(Drawing drawing) {
		super(drawing);
		decorators = new ArrayList<TextShapeDecorator>();
		oldShapes = new ArrayList<Shape>();
	}

	@Override
	public void execute() throws Exception {
		super.execute();
		for(Shape shape : drawing)
		{
			if(shape.isSelected()) {
				if(!shape.isSimple()) {
					throw new DrawingException("Tryed to add text on a group of shapes.");
				}
				decorators.add(new TextShapeDecorator(shape.getOrigin(), shape, ""));
				oldShapes.add(shape);
			}
		}
		
		if(decorators.isEmpty())
			return;
		
		String s = JOptionPane.showInputDialog("What do you want to write ?");
		
		if(s == null || s.equals(""))
			return;
		
		for(TextShapeDecorator tsd : decorators)
			tsd.setText(s);
		
		for(int i = 0; i < decorators.size(); i++)
		{
			drawing.addShape(decorators.get(i));
			drawing.remove(oldShapes.get(i));
		}
		drawing.repaint();
		
		history.pushUndo(this);
        history.clearRedos();
	}

	@Override
	public void undo() {
		for(ShapeDecorator tsd : decorators)
			drawing.remove(tsd);
		for(Shape s : oldShapes)
			drawing.addShape(s);
	}

	@Override
	public void redo() {
		for(ShapeDecorator tsd : decorators)
			drawing.addShape(tsd);
		for(Shape s : oldShapes)
			drawing.remove(s);
	}

	@Override
	public Command clone() {
		return null;
	}

}
