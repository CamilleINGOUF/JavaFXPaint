package drawing;

public class TranslateCommand extends Command {
	
	private Shape target;
	
	private double oldx;
	private double oldy;
	 
	private double tx;
	private double ty;

	public TranslateCommand(Drawing drawing, Shape target, double oldx, double oldy, double tx, double ty) {
		super(drawing);
		this.history = drawing.getCommandHistory();
		this.target = target;
		this.oldx = oldx;
		this.oldy = oldy;
		this.tx = tx;
		this.ty = ty;
	}

	public TranslateCommand(TranslateCommand that) 
	{
		super(that.drawing);
		this.history = that.history;
		this.target = that.target;
		this.oldx = that.oldx;
		this.oldy = that.oldy;
		this.tx = that.tx;
		this.ty = that.ty;
	}

	@Override
	public void execute() 
	{
        target.setOrigin(tx, ty);
        drawing.repaint();
        history.pushUndo(this.clone());
        
        history.clearRedos();
	}

	@Override
	public void undo() 
	{
		target.setOrigin(oldx, oldy);
        drawing.repaint();
	}

	@Override
	public void redo() 
	{
		target.setOrigin(tx, ty);
        drawing.repaint();
	}

	@Override
	public Command clone() {
		return new TranslateCommand(this);
	}

}
