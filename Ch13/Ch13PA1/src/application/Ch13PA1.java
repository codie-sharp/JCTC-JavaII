package application;

import java.text.NumberFormat;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ch13PA1 extends Application 
{
	Label dorm, meal, cost;
	ComboBox<String> dormCombo, mealCombo;
	Button calcCost;
	NumberFormat cash = NumberFormat.getCurrencyInstance();

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		createLabels();
		createCombos();
		createButton();
		
		VBox dormVBox = new VBox(10, dorm, dormCombo);
		VBox mealVBox = new VBox(10, meal, mealCombo);
		HBox topBox = new HBox(10, dormVBox, mealVBox);
		VBox costBox = new VBox(10, cost, calcCost);
		costBox.setAlignment(Pos.CENTER);
		VBox masterBox = new VBox(10, topBox, costBox);
		masterBox.setPadding(new Insets(10));
		Scene scene = new Scene(masterBox);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void createLabels()
	{
		dorm = new Label("Select a Dorm");
		meal = new Label("Select a Meal Plan");
		cost = new Label("Cost:   " +cash.format(0));
	}
	
	public void createCombos()
	{
		dormCombo = new ComboBox<String>();
		dormCombo.getItems().addAll("Allen Hall", "Pike Hall", "Farthing Hall", "University Suites");
		mealCombo = new ComboBox<String>();
		mealCombo.getItems().addAll("7 meals per week", "14 meals per week", "Unlimited meals per week");
	}
	
	public void createButton()
	{
		calcCost = new Button("Calculate Cost");
		calcCost.setOnAction(e ->
		{
			int dormCost, mealCost;
			if(dormCombo.getValue() == "Allen Hall")
			{
				dormCost = 1800;
			}
			else if(dormCombo.getValue() == "Pike Hall")
			{
				dormCost = 2200;
			}
			else if(dormCombo.getValue() == "Farthing Hall")
			{
				dormCost = 2800;
			}
			else if(dormCombo.getValue() == "University Suites")
			{
				dormCost = 3000;
			}
			else
				dormCost = 0;
			if(mealCombo.getValue() == "7 meals per week")
			{
				mealCost = 600;
			}
			else if(mealCombo.getValue() == "14 meals per week")
			{
				mealCost = 1100;
			}
			else if(mealCombo.getValue() == "Unlimited meals per week")
			{
				mealCost = 1800;
			}
			else
				mealCost = 0;
			cost.setText("Cost:   " +cash.format(dormCost + mealCost));
		});
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}

}
