package drawing;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by lewandowski on 05/09/2017.
 */
public class Paint extends Application{

    private Drawing drawing;
    private Button clearButton;
    private Button circleButton;
    private Button rectangleButton;
    
    private Button groupButton;
    private Button ungroupButton;
    
    private Button duplicate;
    
    private Button undo;
    private Button redo;
    
    private Button addTextButton;

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
        drawing.widthProperty().bind(middleBox.widthProperty());
        drawing.heightProperty().bind(middleBox.heightProperty());
        middleBox.getChildren().add(drawing);
        VBox.setVgrow(drawing, Priority.ALWAYS);
        border.setCenter(middleBox);

        border.setTop(createButtonsBox());
        border.setBottom(new StatusBoxObserver(drawing).getStatusBox());

        Scene scene = new Scene(border, 800, 400);
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
        
        groupButton = new Button("Grouper");
        groupButton.addEventHandler(ActionEvent.ACTION, new CompositeShapeHandler(drawing));
        
        ungroupButton = new Button("Dégrouper");
        ungroupButton.addEventHandler(ActionEvent.ACTION, new FreeShapeHandler(drawing));
        
        duplicate = new Button("Dupliquer");
        duplicate.addEventHandler(ActionEvent.ACTION, new DuplicateButtonHandler(drawing));
        
        undo = new Button("Undo");
        undo.addEventHandler(ActionEvent.ACTION, new UndoButtonHandler(drawing));
        
        redo = new Button("Redo");
        redo.addEventHandler(ActionEvent.ACTION, new RedoButtonHandler(drawing));
        
        addTextButton = new Button("Ajouter texte");
        addTextButton.addEventHandler(ActionEvent.ACTION, new addTextButtonHandler(drawing));

        hbox.getChildren().addAll(clearButton, circleButton, rectangleButton, addTextButton, groupButton, ungroupButton, duplicate,undo,redo);
        return hbox;
    }
}
