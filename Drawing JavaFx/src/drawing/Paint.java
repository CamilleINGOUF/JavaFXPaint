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
    private ClearButton clearButton;
    private Button circleButton;
    private Button rectangleButton;
    
    private GroupButton groupButton;
    private UngroupButton ungroupButton;
    
    private Button duplicate;
    
    private UndoButton undo;
    private RedoButton redo;
    
    private TextButton addTextButton;
    
    private Button rearrange;
    private ComboBox<String> listAlgoRearrange;
    
    private Button save;
    
    StatusExceptionSingleton errorBox;

    public static void main(String[] args) {
        launch(Paint.class, args);
    }

    public Drawing getDrawing() {
        return drawing;
    }

    @Override
    public void start(Stage primaryStage) throws Exception 
    {	
    	errorBox = StatusExceptionSingleton.getInstance();
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
        
        VBox bottomBox = new VBox();
        bottomBox.getChildren().add(errorBox);
        bottomBox.getChildren().add(createStatusBox());
        border.setBottom(bottomBox);

        Scene scene = new Scene(border, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createButtonsBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        clearButton = new ClearButton(drawing);
        clearButton.setOnAction(new ClearButtonHandler(drawing));

        circleButton = new Button("Cercle");
        circleButton.addEventHandler(ActionEvent.ACTION, new CircleButtonHandler(drawing));

        rectangleButton = new Button("Rectangle");
        rectangleButton.addEventHandler(ActionEvent.ACTION, new RectangleButtonHandler(drawing));
        
        groupButton = new GroupButton(drawing);
        groupButton.addEventHandler(ActionEvent.ACTION, new CompositeShapeHandler(drawing));
        
        ungroupButton = new UngroupButton(drawing);
        ungroupButton.addEventHandler(ActionEvent.ACTION, new FreeShapeHandler(drawing));
        
        duplicate = new Button("Dupliquer");
        duplicate.addEventHandler(ActionEvent.ACTION, new DuplicateButtonHandler(drawing));
        
        undo = new UndoButton(drawing);
        undo.addEventHandler(ActionEvent.ACTION, new UndoButtonHandler(drawing));
        
        redo = new RedoButton(drawing);
        redo.addEventHandler(ActionEvent.ACTION, new RedoButtonHandler(drawing));
        
        addTextButton = new TextButton(drawing);
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
        Separator sep2 = new Separator(Orientation.VERTICAL);
        
        save = new Button("Save");
        save.addEventFilter(ActionEvent.ACTION, new SaveButtonHandler(drawing));
        
        hbox.getChildren().addAll(clearButton, circleButton, rectangleButton, addTextButton, groupButton, ungroupButton, duplicate,undo,redo);
        hbox.getChildren().addAll(sep,listAlgoRearrange,rearrange,sep2,save);
        
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
        
        SelectedShapesObserver selectedObs = new SelectedShapesObserver(drawing, drawing.getHandler());
        hbox.getChildren().add(selectedObs);
        
        return hbox;
    }
}
