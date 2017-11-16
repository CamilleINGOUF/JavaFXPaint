package drawing;

/**
 * Created by lewandowski on 07/09/2017.
 */
public class RectangleButtonHandler extends ShapeButtonHandler 
{
    public RectangleButtonHandler(Drawing drawing) {
       super(drawing);
    }

	@Override
	public void createShape() 
	{
		shapeCommand = new RectangleCommand(drawing, origin, destination);	
		shapeCommand.execute();
	}
}
