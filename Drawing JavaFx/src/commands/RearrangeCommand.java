package commands;

import drawing.Drawing;
import drawing.RearrangeStrategy;
import drawing.Shape;

public class RearrangeCommand extends Command {
	
	private RearrangeStrategy strat;

	public RearrangeCommand(Drawing drawing, RearrangeStrategy strategy) {
		super(drawing);
		strat = strategy.clone();
		this.history = drawing.getCommandHistory();
	}

	public RearrangeCommand(RearrangeCommand that) {
		super(that.drawing);
		strat = that.strat.clone();
		this.history = drawing.getCommandHistory();
	}

	@Override
	public void execute() throws Exception {
		super.execute();
		strat.rearrange(drawing);
		history.pushUndo(this.clone());
		strat.clear();
	}

	@Override
	public void undo() {
		for(Shape s : strat.getNews())
			drawing.remove(s);
		for(Shape s : strat.getOlds())
			drawing.addShape(s);
	}

	@Override
	public void redo() {
		for(Shape s : strat.getOlds())
			drawing.remove(s);
		for(Shape s : strat.getNews())
			drawing.addShape(s);
	}

	@Override
	public Command clone() {
		return new RearrangeCommand(this);
	}

}
