package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Toggle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Ch13Lab1 extends Application {
	private Button buttons[];
	private Label topLabel, hiddenLabel;
	private TextField topField;
	private RadioButton[] RBs;
	private HBox labelHBox;
	private HBox topHBox;
	private HBox radioHBox;
	private HBox bottomHBox;
	private ToggleGroup group;
	private VBox masterVBox;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		createLabels();
		createTextField();
		createRadioButtons();
		createButtons(primaryStage);
		
		masterVBox = new VBox(10, labelHBox,topHBox, radioHBox, bottomHBox);
		 masterVBox.setPadding(new Insets(50));
		 // Add the master VBox to a scene.
		 Scene scene = new Scene(masterVBox);

		 // Set the scene to the stage aand display it.
		 primaryStage.setScene(scene);
		 primaryStage.show();

	}

	public void createLabels()
	{
		topLabel = new Label("Testing Label");
		hiddenLabel = new Label("Now something different displays");
		labelHBox = new HBox(10,hiddenLabel);
		labelHBox.setAlignment(Pos.CENTER);
		labelHBox.setVisible(false);
	}
	
	public void createTextField()
	{
		topField = new TextField();
		topHBox = new HBox(30, topLabel, topField);
		topHBox.setAlignment(Pos.CENTER);
		topHBox.setPadding(new Insets(20));
	}
	
	public void createRadioButtons()
	{
		RBs = new RadioButton[2];
		RBs[0] = new RadioButton("Show Labels/Fields");
		RBs[0].setSelected(true);
		RBs[1] = new RadioButton("Hide Labels/Fields");
		radioHBox = new HBox(30);
		radioHBox.setAlignment(Pos.CENTER);
		radioHBox.setPadding(new Insets(20));
		for(int i = 0; i < RBs.length; i++)
		{
			radioHBox.getChildren().add(RBs[i]);
		}
		group = new ToggleGroup();
		for(int i = 0; i < RBs.length; i++)
		{
			RBs[i].setToggleGroup(group);
		}
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() 
		{
			public void changed(ObservableValue<? extends Toggle> ov,Toggle old_toggle, Toggle new_toggle)
			{
				if (RBs[1].isSelected()) 
				{
					topHBox.setVisible(false);
					labelHBox.setVisible(true);
				}
				else
				{
					topHBox.setVisible(true);
					labelHBox.setVisible(false);
				}
			}
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
