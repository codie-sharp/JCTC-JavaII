package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;

public class Ch13Lab4 extends Application {
	// Create a labels
	private Label headingLabel;
	private Label redLabel;
	private Label greenLabel;
	private Label blueLabel;
	//create horizontal and vertical boxes
	private HBox headingHBox;
	private VBox colorVBox;
	private VBox sliderVBox;
	private HBox sliderHBox;
	private VBox masterVBox;
	// Declare color component values
	private int redValue, greenValue, blueValue;
	private Slider jscbRed, jscbGreen, jscbBlue;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		createLabels();
		createSliders();

		masterVBox = new VBox(50, headingHBox, sliderHBox);
		Scene scene = new Scene(masterVBox);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void createLabels()
	{
		redLabel = new Label("Red");
		greenLabel = new Label("Green");
		blueLabel = new Label("Blue");
		colorVBox = new VBox(30,redLabel, greenLabel, blueLabel);
		headingLabel = new Label("Show Colors");
		headingLabel.setStyle("-fx-font-size:24pt");
		headingHBox = new HBox(10,headingLabel);
		headingHBox.setAlignment(Pos.CENTER);
	}

	public void createSliders() {
			final int MIN = 0, MAX = 255, INITIAL = 0;
			String cssLayout = "-fx-border-color: red;\n" +
			 "-fx-border-insets: 5;\n" +
			 "-fx-border-width: 3;\n" +
			 "-fx-padding: 10 20 10 20;\n" +
			 "-fx-border-style: solid;\n";
			jscbRed= new Slider(MIN, MAX, INITIAL);
			jscbRed.setShowTickMarks(true);
			jscbRed.setPrefWidth(500);
			jscbGreen= new Slider(MIN, MAX, INITIAL);
			jscbGreen.setShowTickMarks(true);
			jscbGreen.setPrefWidth(500);
			jscbBlue= new Slider(MIN, MAX, INITIAL);
			jscbBlue.setShowTickMarks(true);
			jscbBlue.setPrefWidth(500);
			ChangeListener<Number> changeListener = new
			ChangeListener<Number>() 
			{
				 @Override
				 public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number value) 
				 {
					 redValue = (int)jscbRed.getValue();
					 greenValue = (int)jscbGreen.getValue();
					 blueValue = (int)jscbBlue.getValue();
					 Color c = Color.rgb(redValue, greenValue, blueValue);
					 headingLabel.setTextFill(c);
				 }
			 };
			jscbRed.valueProperty().addListener(changeListener);
			jscbGreen.valueProperty().addListener(changeListener);
			jscbBlue.valueProperty().addListener(changeListener);
			sliderVBox = new VBox(30, jscbRed, jscbGreen, jscbBlue );
			sliderHBox = new HBox(50,colorVBox, sliderVBox);
			sliderHBox.setStyle(cssLayout);
		}


	public static void main(String[] args) {
		launch(args);
	}
}
