package drawing;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.common.collect.Iterators;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by lewandowski on 05/09/2017.
 */
public class Paint extends Application implements StatusObserver{

    private Drawing drawing;
    private Button clearButton;
    private Button circleButton;
    private Button rectangleButton;
    
    private Text statusText;
    
    private Button groupButton;
    private Button ungroupButton;

    public static void main(String[] args) {
        launch(Paint.class, args);
    }

    public Drawing getDrawing() {
        return drawing;
    }

    @Override
    public void start(Stage primaryStage) throws Exception 
    {	
        primaryStage.setTitle("Première application JavaFx");
        BorderPane border = new BorderPane();

        VBox middleBox = new VBox();
        drawing = new Drawing();
        drawing.registerObserver(this);
        drawing.widthProperty().bind(middleBox.widthProperty());
        drawing.heightProperty().bind(middleBox.heightProperty());
        middleBox.getChildren().add(drawing);
        VBox.setVgrow(drawing, Priority.ALWAYS);
        border.setCenter(middleBox);

        border.setTop(createButtonsBox());
        border.setBottom(createStatusBox());

        Scene scene = new Scene(border, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createButtonsBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        clearButton = new Button("Clear");
        clearButton.setOnAction(new ClearButtonHandler(drawing));

        circleButton = new Button("Cercle");
        circleButton.addEventHandler(ActionEvent.ACTION, new CircleButtonHandler(drawing));

        rectangleButton = new Button("Rectangle");
        rectangleButton.addEventHandler(ActionEvent.ACTION, new RectangleButtonHandler(drawing));
        
        groupButton = new Button("Grouper (en test)");
        groupButton.addEventHandler(ActionEvent.ACTION, new ContainerShapeHandler(drawing));
        
        ungroupButton = new Button("Dégrouper");

        hbox.getChildren().addAll(clearButton, circleButton, rectangleButton, groupButton, ungroupButton);
        return hbox;
    }
    
    private HBox createStatusBox()
    {
    	HBox hbox = new HBox();
    	hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        
        statusText = new Text("Shape(s) : 0");
        
        hbox.getChildren().add(statusText);
        
    	return hbox;
    }

	@Override
	public void updateStatus() 
	{
		int nb = Iterators.size(drawing.iterator());
		statusText.setText("Shape(s) : "+nb);
	}
}
