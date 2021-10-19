package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.SelectionMode;
import javafx.collections.ObservableList;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Formatter;
import java.io.FileOutputStream;

public class Ch13Lab3 extends Application {
	private String[] courseNames = {"CIT105", "CIT111", "CIT120", "CIT125", "CIT130","CIT140", "CIT141", "CIT142", 
	"CIT143", "CIT144", "CIT145", "CIT147", "CIT148","CIT149", "CIT150", "CIT151", "CIT152", "CIT155", "CIT157", 
	"CIT160", "CIT161","CIT167", "CIT170", "CIT171", "CIT180", "CIT182", "CIT184"};
	private Label headingLabel;
	private ListView<String> courseList;
	private HBox headingBox;
	private ComboBox<String> coursesCombo;
	private TextArea displayArea;
	private HBox middleBox;
	private Button addButton, removeButton, addFirst, removeLast;
	private HBox button1Box, button2Box;
	private VBox buttonVBox;
	private VBox masterVBox;
	private MenuBar menuBar;
	private Menu fileMenu;
	private MenuItem downloadMenu, exitMenu;
	private BorderPane borderPane;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		createHeading();
		createComboBox();
		createTextArea();
		createListView();
		createButtons();
		
		middleBox = new HBox(20,coursesCombo, displayArea, courseList);
		masterVBox = new VBox(10,headingBox, middleBox, buttonVBox);
		masterVBox.setPadding(new Insets(50));
		createMenu(primaryStage);
		// Add the borderPane to a scene.
		 Scene scene = new Scene(borderPane);


		 // Set the scene to the stage aand display it.
		 primaryStage.setScene(scene);
		 primaryStage.show();
	}
	
	public void createHeading()
	{
		headingLabel = new Label("Example of the ListView Class");
		headingLabel.setStyle("-fx-font-size:18pt");
		Image image = new Image(getClass().getResourceAsStream("courses.jpg"));
		headingLabel.setGraphic(new ImageView(image));
		headingBox = new HBox(10,headingLabel);
		headingBox.setAlignment(Pos.CENTER);
	}
	
	public void createComboBox()
	{
		coursesCombo = new ComboBox();
		coursesCombo.setMinWidth(100.0);
		coursesCombo.setOnAction(e ->
		 {
			 setDisplay();
		 });
	}
	
	public void setDisplay()
	 {
		if(coursesCombo.getItems().size() != 0)
		{
			displayArea.setText(getDescription());
		}
		else
		displayArea.setText("To add a course to the combo box, select a course from the list on the "
		+ "right and click either 'Add Item' or 'Add Item To Start'. To display a description of a course"
		+ " in the combo box, selected the course name. ");
	}
	
	public String getDescription()
	{
		StringBuilder result = new StringBuilder();
		try
		{
			Scanner input = new Scanner(new File("text/" + coursesCombo.getValue() +".txt"));
			while(input.hasNext())
			{
				result.append(input.nextLine() + '\n');
			}
			input.close();
		}
		catch(IOException ex)
		{
			displayArea.setText("Error loading file! Check name and location of file.");
		}
		return result.toString();
	}

	public void createTextArea()
	{
		displayArea = new TextArea("To add a course to the combo box, select a course from the list on the right"
		+ " and click either 'Add Item' or 'Add Item To Start'. To display a description of a course in the combo"
		+ " box select the course name.");
		displayArea.setWrapText(true);
		displayArea.setPrefColumnCount(20);
		displayArea.setPrefRowCount(10);
	}
	
	public void createListView()
	{
		courseList = new ListView<>();
		courseList.setMaxWidth(100);
		courseList.setMaxHeight(350);
		courseList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		for(int i = 0; i < courseNames.length; i++)
		{
			courseList.getItems().addAll(courseNames[i]);
		}
	}

	public void createButtons()
	{
		ObservableList<String> selections = courseList.getSelectionModel().getSelectedItems();
		addButton = new Button("Add Item");
		removeButton = new Button("Remove First Item");
		addFirst = new Button("Add Item To Start");
		removeLast = new Button("Remove Last Item");
		button1Box = new HBox(10, addButton, removeButton);
		button1Box.setAlignment(Pos.CENTER);
		button2Box = new HBox(10,addFirst, removeLast);
		button2Box.setAlignment(Pos.CENTER);
		buttonVBox = new VBox(10,button1Box,button2Box);
		addButton.setOnAction(e ->
		 {
			coursesCombo.getItems().addAll(selections);
		 });
		removeButton.setOnAction(e ->
		 {
			 coursesCombo.getItems().remove(0);
		 });
		addFirst.setOnAction(e ->
		 {
			 coursesCombo.getItems().addAll(0,selections);
		 });
		removeLast.setOnAction(e ->
		 {
			 int size = coursesCombo.getItems().size() - 1;
			 coursesCombo.getItems().remove(size);
		 });
	}
	
	public void createMenu(Stage primaryStage)
	{
		menuBar = new MenuBar();
		fileMenu = new Menu("_File");
		downloadMenu = new MenuItem("_Download Description");
		exitMenu = new MenuItem("_Exit Program");
		downloadMenu.setOnAction(e ->
		{
			try
			{
				Formatter output = new Formatter(new FileOutputStream("Ch13Lab3File.txt",
				true));
				output.format("%n%s%n", displayArea.getText());
				output.close();
			}
			catch(IOException ex)
			{
				displayArea.setText("Error creating file");
			}
		});
		exitMenu.setOnAction(e ->
		 {
			 primaryStage.close();
		 });
		fileMenu.getItems().add(downloadMenu);
		fileMenu.getItems().add(exitMenu);
		menuBar.getMenus().add(fileMenu);
		borderPane = new BorderPane();
		borderPane.setTop(menuBar);
		borderPane.setCenter(masterVBox);
	}
	public static void main(String[] args) {
		launch(args);
	}

}
