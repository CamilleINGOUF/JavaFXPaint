package drawing;

public class RearrangeCommand extends Command {
	
	private RearrangeStrategy strat;

	public RearrangeCommand(Drawing drawing) {
		super(drawing);
		strat = new RearrangeGridStrategy();
		this.history = drawing.getCommandHistory();
	}

	public RearrangeCommand(RearrangeCommand that) {
		super(that.drawing);
		strat = that.strat.clone();
		this.history = drawing.getCommandHistory();
	}

	@Override
	public void execute() throws Exception {
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
		for(Shape s : strat.getNews())
			drawing.addShape(s);
		for(Shape s : strat.getOlds())
			drawing.remove(s);
	}

	@Override
	public Command clone() {
		return new RearrangeCommand(this);
	}

}
