package application;

import java.text.NumberFormat;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ch13PA2 extends Application 
{
	int cost;
	Label costLabel, listLabel;
	RadioButton[] reg;
	ToggleGroup group;
	CheckBox speech;
	ListView<String> workshops;
	Button calcCost;
	NumberFormat cash = NumberFormat.getCurrencyInstance();

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		createLabels();
		createRadios();
		createCheckBox();
		createList();
		createButton();
		
		VBox radioBox = new VBox(10, reg[0], reg[1]);
		radioBox.setAlignment(Pos.CENTER_LEFT);
		VBox speechBox = new VBox(10, speech);
		speechBox.setAlignment(Pos.CENTER);
		VBox listBox = new VBox(10, listLabel);
		listBox.setAlignment(Pos.CENTER_LEFT);
		VBox masterBox = new VBox(10, radioBox, speechBox, listBox, workshops, costLabel, calcCost);
		masterBox.setAlignment(Pos.CENTER);
		masterBox.setPadding(new Insets(10));
		Scene scene = new Scene(masterBox);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void createLabels()
	{
		costLabel = new Label("Cost:   " +cash.format(0));
		listLabel = new Label("Select an Option Workshop");
	}
	
	public void createRadios()
	{
		reg = new RadioButton[2];
		reg[0] = new RadioButton("General Registration");
		reg[0].setSelected(true);
		reg[1] = new RadioButton("Student Registration");
		group = new ToggleGroup();
		reg[0].setToggleGroup(group);
		reg[1].setToggleGroup(group);
	}
	
	public void createCheckBox()
	{
		speech = new CheckBox("Opening Night Dinner");
	}
	
	public void createList()
	{	
		workshops = new ListView<>();
		workshops.getItems().addAll("Introduction to E-commerce", "The Future of the Web", "Advanced Java Programming", "Network Security");
		workshops.setPrefSize(200,100);
		workshops.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	public void createButton()
	{
		calcCost = new Button("Calculate Cost");
		calcCost.setOnAction(e ->
		{
			cost = 0;
			ObservableList<Integer> selections = workshops.getSelectionModel().getSelectedIndices();
			Integer[] index = selections.toArray(new Integer[selections.size()]);
			if(reg[0].isSelected())
			{
				cost += 895;
			}
			else if(reg[1].isSelected())
			{
				cost += 495;
			}
			if(speech.isSelected())
			{
				cost += 30;
			}
			for(int i = 0; i < index.length; i++)
			{
				if(index[i] == 0 || index[i] == 1)
				{
					cost += 295;
				}
				else if(index[i] == 2 || index[i] == 3)
				{
					cost += 395;
				}
			}
			costLabel.setText("Cost:   " +cash.format(cost));
		});
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}

}
