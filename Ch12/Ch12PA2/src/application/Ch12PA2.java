package application;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.text.NumberFormat;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Ch12PA2 extends Application 
{
	Label daysLabel, airLabel, carLabel, milesLabel, parkingLabel, taxiLabel, regLabel, lodgeLabel, 
	totalLabel, allowedLabel, excessLabel, savedLabel, totalField, allowedField, excessField, savedField;
	TextField daysField, airField, carField, milesField, parkingField, taxiField, regField, lodgeField;
	NumberFormat cash = NumberFormat.getCurrencyInstance();
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
	//GridPane only supports an empty constructor from what I understand
		GridPane grid = new GridPane();
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(10));
	//Labels (left)
		grid.add(daysLabel = new Label("Days on the trip: "), 0, 0);
		grid.add(airLabel = new Label("Airfare: "), 0, 1);
		grid.add(carLabel = new Label("Car rental: "), 0, 2);
		grid.add(milesLabel = new Label("Miles driven: "), 0, 3);
		grid.add(parkingLabel = new Label("Parking fees: "), 0, 4);
		grid.add(taxiLabel = new Label("Taxi fees: "), 0, 5);
		grid.add(regLabel = new Label("Registration fees: "), 0, 6);
		grid.add(lodgeLabel = new Label("Nightly lodging fees: "), 0, 7);
		grid.add(totalLabel = new Label("Total expenses: "), 0, 8);
		grid.add(allowedLabel = new Label("Allowable expenses: "), 0, 9);
		grid.add(excessLabel = new Label("Excess expenses: "), 0, 10);
		grid.add(savedLabel = new Label("Saved expenses: "), 0, 11);
	//Text fields (right)
		grid.add(daysField = new TextField(), 1, 0);
		grid.add(airField = new TextField(), 1, 1);
		grid.add(carField = new TextField(), 1, 2);
		grid.add(milesField = new TextField(), 1, 3);
		grid.add(parkingField = new TextField(), 1, 4);
		grid.add(taxiField = new TextField(), 1, 5);
		grid.add(regField = new TextField(), 1, 6);
		grid.add(lodgeField = new TextField(), 1, 7);
		grid.add(totalField = new Label(), 1, 8);
		grid.add(allowedField = new Label(), 1, 9);
		grid.add(excessField = new Label(), 1, 10);
		grid.add(savedField = new Label(), 1, 11);
	//Calc button - take input for days and miles and multiply them by allowances
		Button calc = new Button("Calculate Expenses");
		calc.setOnAction(e->
		{
		//Instructions confused me - rolling expenses all into 1 sum
			int days = Integer.parseInt(daysField.getText());
			double airfare = Double.parseDouble(airField.getText());
			double carRental = Double.parseDouble(carField.getText());
			double miles = Double.parseDouble(milesField.getText());
			double parkingFees = Double.parseDouble(parkingField.getText());
			double taxiFees = Double.parseDouble(taxiField.getText());
			double regFees = Double.parseDouble(regField.getText());
			double lodgeFees = Double.parseDouble(lodgeField.getText());
			double total = (airfare + carRental + parkingFees + taxiFees + regFees + (lodgeFees * days));
			totalField.setText(cash.format(total));
		//Same as above, rolling budget all into one
			double allowedExpense = (302 * days) + (.42 * miles);
			allowedField.setText(cash.format(allowedExpense));
		//Do the math
			if(total > allowedExpense)
			{
				excessField.setText(cash.format(total - allowedExpense));
				savedField.setText(cash.format(0));
			}
			else if(total < allowedExpense)
			{
				excessField.setText(cash.format(0));
				savedField.setText(cash.format(allowedExpense - total));
			}
			else
			{
				excessField.setText(cash.format(0));
				savedField.setText(cash.format(0));
			}
		});
		HBox bottomBox = new HBox(calc);
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.setPadding(new Insets(10));
	//Master container and set scene
		BorderPane majorPane = new BorderPane();
		majorPane.setCenter(grid);
		majorPane.setBottom(bottomBox);
		majorPane.setPadding(new Insets(10));
		Scene scene = new Scene(majorPane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) 
	{
		launch(args);
	}

}
