package drawing;

import java.util.ArrayList;

public class TextDecoratorCommand extends Command {
	
	private ArrayList<TextShapeDecorator> decorators;
	ArrayList<Shape> oldShapes;

	public TextDecoratorCommand(Drawing drawing) {
		super(drawing);
		decorators = new ArrayList<TextShapeDecorator>();
		oldShapes = new ArrayList<Shape>();
	}

	@Override
	public void execute() {
		for(Shape shape : drawing)
		{
			if(shape.isSimple())
			{
				if(shape.isSelected()) {
					decorators.add(new TextShapeDecorator(shape.origin, shape));
					oldShapes.add(shape);
				}
			}
		}
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
		//TODO
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}

	@Override
	public Command clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
