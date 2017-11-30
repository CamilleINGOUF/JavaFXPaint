package drawing;

/**
 * Created by lewandowski on 07/09/2017.
 */
public class CircleButtonHandler extends ShapeButtonHandler 
{
    public CircleButtonHandler(Drawing drawing) {
        super(drawing);
    }

	@Override
	public void createShape() 
	{
        shapeCommand = new CircleCommand(drawing,origin,destination);
        try {
			shapeCommand.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
