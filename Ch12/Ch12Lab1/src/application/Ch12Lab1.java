package application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.stage.Stage;

public class Ch12Lab1 extends Application 
{

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
	// Create the controls for the first name.
		Label fnprompt = new Label("First name:");
		TextField fnTextField = new TextField();
		HBox fnhbox = new HBox(10, fnprompt, fnTextField);
		fnhbox.setAlignment(Pos.CENTER);
	// Create the controls for the middle name.
		Label mnprompt = new Label("Middle name:");
		TextField mnTextField = new TextField();
		HBox mnhbox = new HBox(10, mnprompt, mnTextField);
		mnhbox.setAlignment(Pos.CENTER);
	// Create the controls for the last name.
		Label lnprompt = new Label("Last name:");
		TextField lnTextField = new TextField();
		HBox lnhbox = new HBox(10, lnprompt, lnTextField);
		lnhbox.setAlignment(Pos.CENTER);
	// Create the controls for the title.
		Label tprompt = new Label("Title:");
		TextField tTextField = new TextField();
		HBox thbox = new HBox(10, tprompt, tTextField);
		thbox.setAlignment(Pos.CENTER);
	// Create the Button Controls.
		Button button1 = new Button("Format 1");
		Button button2 = new Button("Format 2");
		Button button3 = new Button("Format 3");
		Button button4 = new Button("Format 4");
		Button button5 = new Button("Format 5");
		Button button6 = new Button("Format 6");
		HBox bhbox = new HBox(10, button1, button2, button3, button4, button5, button6);
		bhbox.setAlignment(Pos.CENTER);
	// Create the output label.
		Label outputLabel = new Label();
		HBox outputHBox = new HBox(outputLabel);
	// Register event handlers.
		button1.setOnAction(e ->
		{
			outputLabel.setText(tTextField.getText() + " " +
			fnTextField.getText() + " " +
			mnTextField.getText() + " " +
			lnTextField.getText());
		}); 
		button2.setOnAction(e->
		{
			outputLabel.setText(fnTextField.getText() + " " +
			mnTextField.getText() + " " +
			lnTextField.getText());
		});
		button3.setOnAction(e->
		{
			outputLabel.setText(fnTextField.getText() + " " +
			lnTextField.getText());
		});
		button4.setOnAction(e ->
		{
			 outputLabel.setText(lnTextField.getText() + ", " +
			 fnTextField.getText() + " " +
			 mnTextField.getText() + ", " +
			 tTextField.getText());
		});
		button5.setOnAction(e ->
		{
			 outputLabel.setText(lnTextField.getText() + ", " +
			 fnTextField.getText() + " " +
			 mnTextField.getText());
		});
		button6.setOnAction(e ->
		{
			 outputLabel.setText(lnTextField.getText() + ", " +
			 mnTextField.getText());
		});
	//Create a master VBox.
		VBox masterVBox = new VBox(10, fnhbox, mnhbox, lnhbox, thbox, bhbox, outputHBox);
		masterVBox.setPadding(new Insets(10));
	//Add the master VBox to a scene.
		Scene scene = new Scene(masterVBox);
	//Set the scene to the stage and display it.
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) 
	{
		launch(args);
	}

}
