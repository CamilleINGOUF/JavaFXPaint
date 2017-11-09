package drawing;

import com.google.common.collect.Iterators;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class StatusBoxObserver extends HBox implements StatusObserver
{
	private Text statusText;
	
	private Drawing drawing;
	
	public StatusBoxObserver(Drawing drawing)
	{
		this.setPadding(new Insets(15, 12, 15, 12));
        this.setSpacing(10);
        this.setStyle("-fx-background-color: #336699;");
        
        statusText = new Text("Shape(s) : 0");
        
        this.getChildren().add(statusText);
        this.drawing = drawing;
        this.drawing.registerObserver(this);
	}
	
	public HBox getStatusBox()
	{
		return this;
	}

	@Override
	public void updateStatus() 
	{
		int nb = Iterators.size(drawing.iterator());
		statusText.setText("Shape(s) : "+nb);
	}
}
