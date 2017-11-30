package drawing;

import javafx.scene.text.Text;

public class SelectedShapesObserver extends Text implements Observer {

	private Drawing drawing;
	
	public SelectedShapesObserver(Drawing drawing) {
		
		this.drawing = drawing;
		drawing.registerObserver(this);
		setText("");
	}
	
	@Override
	public void updateStatus() {
		String str = new String();
		for(Shape s : drawing) {
			if(s.isSelected()) {
				str += s.toString() + " ";
			}
		}
		setText(str);
		drawing.repaint();
	}

}
