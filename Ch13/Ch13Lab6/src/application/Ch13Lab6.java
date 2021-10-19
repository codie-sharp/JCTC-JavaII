package application;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.scene.control.TextArea;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser.ExtensionFilter;

public class Ch13Lab6 extends Application {
	private Button jbtBrowse;
	private TextField jtfFile;
	private TextArea jtaFileContent;
	//Create Menu
	private MenuBar menuBar;
	private Menu fileMenu;
	private Menu helpMenu;
	private Menu editMenu;
	private Menu colorMenu;
	private MenuItem openMenu;
	private MenuItem exitMenu;
	 private MenuItem selectAllMenu;
	 private Menu chooseColor;
	 private MenuItem setBackgroundMenu;
	 private MenuItem setForegroundMenu;
	 private MenuItem aboutMenu;
	 private ColorPicker colorPickerBackground = new ColorPicker();
	 private ColorPicker colorPickerText = new ColorPicker();
	 private BorderPane borderPane;
	 private GridPane gridPane = new GridPane();
	 private ScrollPane jsp;
	 private String finalColor = "";
	 private String backgroundColor = "";
	 private String textColor = "";

	@Override
	public void start(Stage primaryStage) throws Exception {
		createMenu(primaryStage);
		createOtherComponents(primaryStage);
		// Add the gridPane to a scene.
		Scene scene = new Scene(gridPane);
		// Set the scene to the stage aand display it.
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void createMenu(Stage primaryStage)
	 {
		 menuBar = new MenuBar();
		 fileMenu = new Menu("File");
		 editMenu = new Menu("Edit");
		 colorMenu = new Menu("Set Color");
		 helpMenu = new Menu("Help");
		 menuBar.getMenus().add(fileMenu);
		 menuBar.getMenus().add(editMenu);
		 menuBar.getMenus().add(colorMenu);
		 menuBar.getMenus().add(helpMenu);
		 openMenu = new MenuItem("Open File");
		 openMenu.setOnAction(e ->
		 {
			 browse(primaryStage);
		 });
		 exitMenu = new MenuItem("Exit Program");
		 exitMenu.setOnAction(e ->
		 {
			 System.exit(0);
		 });
		 selectAllMenu = new MenuItem("Select All");
		 selectAllMenu.setOnAction(e ->
		 {
			 jtaFileContent.selectAll();
		 });
		 chooseColor = new Menu("Change Color");
		 setBackgroundMenu = new MenuItem("Set Background Color",colorPickerBackground);
		 setBackgroundMenu.setOnAction(e ->
		 {
			 backgroundColor = "";
			 Color selectedColor = colorPickerBackground.getValue();
			 backgroundColor = "-fx-control-inner-background: " +
			selectedColor.toString().replace("0x", "#");
			 finalColor = backgroundColor + ";\n" + textColor ;
			 jtaFileContent.setStyle(finalColor);
		 });
		 setForegroundMenu = new MenuItem("Set Text Color",colorPickerText);
		 setForegroundMenu.setOnAction(e ->
		 {
			 Color selectedColor = colorPickerText.getValue();
			 textColor = "-fx-text-inner-color: " +
			selectedColor.toString().replace("0x", "#");
			 finalColor = backgroundColor + ";\n" + textColor ;
			 jtaFileContent.setStyle(finalColor);
		 });
		 aboutMenu = new MenuItem("About Program");
		 aboutMenu.setOnAction(e ->
		 {
			 jtaFileContent.setText("Click Browse and Choose a File to Display");
		 });
		 fileMenu.getItems().add(openMenu);
		 fileMenu.getItems().add(exitMenu);
		 editMenu.getItems().add(selectAllMenu);
		 colorMenu.getItems().add(chooseColor);
		 chooseColor.getItems().add(setBackgroundMenu);
		 chooseColor.getItems().add(setForegroundMenu);
		 helpMenu.getItems().add(aboutMenu);
		 borderPane = new BorderPane();
		 borderPane.setTop(menuBar);
		 gridPane.add(borderPane, 0,0);
	}

	public void createOtherComponents(Stage primaryStage)
	{
		jtfFile = new TextField();
		jtfFile.setText("Browse to File Name");
		gridPane.add(jtfFile, 0, 2);
		jtfFile.textProperty().addListener((observable, oldValue, newValue) -> 
		{
			showFile(new File(jtfFile.getText().trim()));
		});
		jbtBrowse = new Button("Browse");
		jbtBrowse.setOnAction(e ->
		{
			browse(primaryStage);
		});
		 // Create a scrollabel text area
		 jtaFileContent = new TextArea();
		 jsp = new ScrollPane(jtaFileContent);
		 gridPane.add(jtaFileContent,0,1);
		 gridPane.add(jbtBrowse,1,2);
	}
	
	private void showFile(File file)
	 {
		 BufferedReader infile = null; //declare buffered stream
		 //get file name from the text field
		 String inLine;
		 jtfFile.setText(file.getName());
		 try
		 {
			 //create a buffered stream
			 infile = new BufferedReader(new FileReader(file));
			 //read a line
			 inLine = infile.readLine();
			 boolean firstLine = true;
			 //append the line to the text area
			 while (inLine != null)
			 {
				 if (firstLine)
				 {
					 firstLine = false;
					 jtaFileContent.appendText(inLine);
				 }
				 else
				 {
					 jtaFileContent.appendText("\n" + inLine);
				 }
				 inLine = infile.readLine();
			 }
		 }
		 catch (IOException ex)
		 {
		 }
		 finally
		 {
			 try
			 {
				 if (infile != null)
				 infile.close();
			 }
			 catch (IOException ex)
			 {
			 }
		 }
	}
	
	private void browse(Stage primaryStage)
	 {
	FileChooser fileChooser = new FileChooser();
	 fileChooser.setTitle("Open Text File");
	 fileChooser.setInitialDirectory(new File("."));
	 fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files",
	"*.txt", "*.csv","*.java"));
	 File selectedFile = fileChooser.showOpenDialog(null);
	 showFile(selectedFile);
	}
	public static void main(String[] args) {
		launch(args);
	}

}
