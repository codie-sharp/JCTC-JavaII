package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import java.util.Random;

public class Ch12Lab2 extends Application 
{

	@Override
	public void start(Stage primaryStage) throws Exception
	{
	// Load the coin images.
		Image headsImage = new Image("Heads1.png");
		Image tailsImage = new Image("Tails1.png");
	// Create the ImageView control.
		ImageView coinImage = new ImageView();
		coinImage.setFitWidth(200);
		coinImage.setFitHeight(200);
	// Create the tossButton control.
		Button tossButton = new Button("Toss");
	// Register the event handler.
		tossButton.setOnAction(e ->
		{
		 // Create a Random object.
			 Random rand = new Random();
		 // Get a random value, 0 or 1.
			 int value = rand.nextInt(2);
		 // Set the coin image.
		 // 0 = "heads" or 1 = "tails"
			 if (value == 0)
			 coinImage.setImage(headsImage);
			 else
			 coinImage.setImage(tailsImage);
		});
	// Put everything into a VBox
		 VBox mainVBox = new VBox(10, coinImage, tossButton);
		 mainVBox.setAlignment(Pos.CENTER);
		 mainVBox.setPadding(new Insets(10));
	// Add the main VBox to a scene.
		 Scene scene = new Scene(mainVBox);
	// Set the scene to the stage and display it.
		 primaryStage.setScene(scene);
		 primaryStage.show();
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}

}
