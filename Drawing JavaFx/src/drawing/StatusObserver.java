package drawing;

import com.google.common.collect.Iterators;

import javafx.scene.text.Text;

public class StatusObserver extends Text implements Observer
{	
	private Drawing drawing;
	
	public StatusObserver(Drawing drawing)
	{
        this.setStyle("-fx-background-color: #336699;");
        
        setText("Shape(s) : 0");
        
        this.drawing = drawing;
        this.drawing.registerObserver(this);
	}

	@Override
	public void updateStatus()
	{
		int nb = Iterators.size(drawing.iterator());
		setText("Shape(s) : "+nb);
	}
}
