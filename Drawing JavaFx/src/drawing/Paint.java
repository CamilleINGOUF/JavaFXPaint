package drawing;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
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
    
    private Button rearrange;
    private ComboBox<String> listAlgoRearrange;

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
        border.setBottom(createStatusBox());

        Scene scene = new Scene(border, 1000, 600);
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
        addTextButton.addEventHandler(ActionEvent.ACTION, new TextButtonHandler(drawing));
        
        String[] elements = new String[]{"Grid", "Random", "Vertical","Sort"};
        listAlgoRearrange = new ComboBox<>();
        listAlgoRearrange.getItems().addAll(elements);
        listAlgoRearrange.getSelectionModel().selectFirst();

        rearrange = new Button("Réarranger");
        RearrangeButtonHandler rearrangeHandler = new RearrangeButtonHandler(drawing);
        rearrangeHandler.setComboBox(listAlgoRearrange);
        rearrange.addEventHandler(ActionEvent.ACTION, rearrangeHandler);
        
        Separator sep = new Separator(Orientation.VERTICAL);
        
        hbox.getChildren().addAll(clearButton, circleButton, rectangleButton, addTextButton, groupButton, ungroupButton, duplicate,undo,redo);
        hbox.getChildren().addAll(sep,listAlgoRearrange,rearrange);
        
        return hbox;
    }
    
    private HBox createStatusBox()
    {
    	HBox hbox = new HBox();
    	hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        
        StatusObserver status = new StatusObserver(drawing);
        hbox.getChildren().add(status);
        
        SelectedShapesObserver selectedObs = new SelectedShapesObserver(drawing);
        hbox.getChildren().add(selectedObs);
        
        return hbox;
    }
}
