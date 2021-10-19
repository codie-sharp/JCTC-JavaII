package application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Ch12PA1 extends Application 
{
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		Label text = new Label();
		Button sinButton = new Button("Sinister");
		sinButton.setOnAction(e ->
		{
			text.setText("Left");
		});
		Button dexButton = new Button("Dexter");
		dexButton.setOnAction(e ->
		{
			text.setText("Right");
		});
		Button medButton = new Button("Medium");
		medButton.setOnAction(e ->
		{
			text.setText("Center");
		});
		VBox box = new VBox(10, sinButton, dexButton, medButton, text);
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(10));
		Scene scene = new Scene(box);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) 
	{
		launch(args);
	}

}
