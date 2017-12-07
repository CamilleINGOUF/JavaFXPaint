package drawing;

import java.lang.reflect.Constructor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class RearrangeButtonHandler implements  EventHandler<ActionEvent>  {
	private RearrangeCommand command;
	private ComboBox<String> box;
	private Drawing drawing;
	
	public RearrangeButtonHandler(Drawing drawing) {
		this.drawing = drawing;
	}

	@Override
	public void handle(ActionEvent event) {
		
		String strategyName = new String("drawing.Rearrange"+box.getValue()+"Strategy");
		
		try {
			Class<?> stratClass = Class.forName(strategyName);
			Constructor<?> stratConstructor = stratClass.getConstructor();
			
			RearrangeStrategy strat = (RearrangeStrategy) stratConstructor.newInstance();
			command = new RearrangeCommand(drawing,strat);
			
			command.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setComboBox(ComboBox<String> box) {
		this.box = box;
	}

}
