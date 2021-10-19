package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Ch13Lab2 extends Application {
	private Button buttons[];
	private Label topLabel, hiddenLabel;
	private TextField topField;
	private CheckBox[] RBs;
	private HBox labelHBox;
	private HBox topHBox;
	private HBox checkHBox;
	private HBox bottomHBox;
	private VBox masterVBox;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		createLabels();
		createTextField();
		createCheckBoxes();
		createButtons(primaryStage);
		
		masterVBox = new VBox(10, labelHBox,topHBox, checkHBox, bottomHBox);
		masterVBox.setPadding(new Insets(50));
		// Add the master VBox to a scene.
		Scene scene = new Scene(masterVBox);
		// Set the scene to the stage and display it.
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void createLabels(){
		topLabel = new Label("Testing Label");
		hiddenLabel = new Label("Now something different displays");
		labelHBox = new HBox(10,hiddenLabel);
		labelHBox.setAlignment(Pos.CENTER);
		labelHBox.setVisible(false);
	}

	public void createTextField(){
		 topField = new TextField();
		 topHBox = new HBox(30, topLabel, topField);
		 topHBox.setAlignment(Pos.CENTER);
		 topHBox.setPadding(new Insets(20));
	}

	public void createCheckBoxes()
	{
		RBs = new CheckBox[2];
		RBs[0] = new CheckBox("Show Labels/Fields");
		RBs[1] = new CheckBox("Hide Labels/Fields");
		checkHBox = new HBox(30);
		checkHBox.setAlignment(Pos.CENTER);
		checkHBox.setPadding(new Insets(20));
		for(int i = 0; i < RBs.length; i++)
		{
			checkHBox.getChildren().add(RBs[i]);
		}
		RBs[0].setOnAction(e ->
		{
			RBs[1].setSelected(false);
			topHBox.setVisible(true);
			labelHBox.setVisible(false);
		});
		RBs[1].setOnAction(e ->
		{
			RBs[0].setSelected(false);
			topHBox.setVisible(false);
			labelHBox.setVisible(true);
		});
	}
	
	public void createButtons(Stage primaryStage)
	{
		buttons = new Button[2];
		buttons[0] = new Button("Clear");
		buttons[1] = new Button("Exit");
		buttons[0].setOnAction(e ->
		{
			topHBox.setVisible(true);
			labelHBox.setVisible(false);
			for(int i = 0; i < RBs.length; i++)
			{
				RBs[i].setSelected(false);
			}
		});
		buttons[1].setOnAction(e ->
		{
			primaryStage.close();
		});
		bottomHBox = new HBox(30);
		bottomHBox.setAlignment(Pos.CENTER);
		bottomHBox.setPadding(new Insets(20));
		for(int i = 0; i < buttons.length; i++)
		{
			bottomHBox.getChildren().add(buttons[i]);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
