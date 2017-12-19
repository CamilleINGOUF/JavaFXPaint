package exceptions;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class StatusExceptionSingleton extends HBox {
	
	private static StatusExceptionSingleton instance = null;
	
	private Text error;
	
	private StatusExceptionSingleton() {
		error = new Text("");
		this.setVisible(false);
		this.setStyle("-fx-background-color: #990000;");
		this.setSpacing(10);
		this.setPadding(new Insets(8, 12, 8, 12));
		this.getChildren().add(error);
	}
	
	public static StatusExceptionSingleton getInstance() {
		if(instance == null)
			instance = new StatusExceptionSingleton();
		return instance;
	}
	
	public void sendError(String txt) {
		hide();
		setVisible(true);
		error.setText(txt);
		error.setFill(Color.WHITE);
	}
	
	public void hide() {
		setVisible(false);
		error.setText("");
	}
}
