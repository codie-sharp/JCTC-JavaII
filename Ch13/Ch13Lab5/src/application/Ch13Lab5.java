package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Ch13Lab5 extends Application {
	private Button colorButtons[];
	private TextField colorField;
	private Label colorLabel;
	private MenuBar menuBar;
	private Menu fileMenu, editMenu, aboutMenu,changeColors;
	private MenuItem displayMenu,clearMenu,closeMenu,aboutColorMenu,
	changeTextFieldColor;
	private HBox rowOne, rowTwo, rowThree;
	private VBox buttonVBox;
	private BorderPane borderPane;
	private ColorPicker colorPicker = new ColorPicker();
	private GridPane gridPane = new GridPane();
	private String textColor = "";
	 private String finalColor = "";
	 private String backgroundColor = "";
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		createLabelandField();
		createButtons();
		createMenu(primaryStage);
		borderPane.setTop(menuBar);
		gridPane.add(borderPane, 0, 0);
		gridPane.add(colorField, 0, 1);
		gridPane.add(colorLabel, 0, 3);
		// Add the gridPane to a scene.
		 Scene scene = new Scene(gridPane);
		 // Set the scene to the stage aand display it.
		 primaryStage.setScene(scene);
		 primaryStage.show();
	}

	public void createLabelandField()
	{
		colorLabel = new Label("Welcome to Your Favorite Colors!");
		colorField = new TextField("Welcome to Your Favorite Colors!");
	}

	public void createButtons()
	{
		colorButtons = new Button[6];
		colorButtons[0]=new Button("Blue");
		colorButtons[1]=new Button("Black");
		colorButtons[2]=new Button("Dark Gray");
		colorButtons[3]=new Button("Gray");
		colorButtons[4]=new Button("Red");
		colorButtons[5]=new Button("Magenta");
		colorButtons[0].setStyle("-fx-background-color:blue;-fx-text-fill:white;");
		colorButtons[1].setStyle("-fx-background-color:black;-fx-text-fill:white;");
		colorButtons[2].setStyle("-fx-background-color:darkgray;-fx-text-fill:white;");
		colorButtons[3].setStyle("-fx-background-color:gray;-fx-text-fill:white;");
		colorButtons[4].setStyle("-fx-background-color:red;-fx-text-fill:white;");
		colorButtons[5].setStyle("-fx-background-color:magenta;-fx-text-fill:white;");
		for(int i=0;i<colorButtons.length;i++)
		{
			colorButtons[i].setPrefSize(300, 100);
		}
		rowOne = new HBox(colorButtons[0],colorButtons[1]);
		rowTwo = new HBox(colorButtons[2],colorButtons[3]);
		rowThree = new HBox(colorButtons[4],colorButtons[5]);
		buttonVBox = new VBox(rowOne, rowTwo, rowThree);
		gridPane.add(buttonVBox, 0, 2);
		for(int i=0;i<colorButtons.length;i++)
		{
			colorButtons[i].setOnAction(e ->
			{
				for(int j=0;j<colorButtons.length;j++)
				{
					if(e.getSource() == colorButtons[j])
					{
						colorField.setBackground(colorButtons[j].getBackground());
						colorField.setText(colorButtons[j].getText()+" is such a lovely color");
						colorField.setStyle("-fx-text-fill:white;");
					}
				}//closes for loop
			});
		}
	}
	
	public void createMenu(Stage primaryStage)
	{
		String menuPadding = "-fx-padding: 5 30 0 0;";
		menuBar = new MenuBar();
		fileMenu = new Menu("_File");
		editMenu = new Menu("_Edit");
		aboutMenu = new Menu("_About");
		changeColors = new Menu("C_hange Text Field Background Color");
		//changeColors.setStyle("-fx-padding: 5 0 0 30;");
		displayMenu = new MenuItem("_Display");
		clearMenu = new MenuItem("_Clear");
		closeMenu = new MenuItem("E_xit");
		aboutColorMenu = new MenuItem("A_bout Color Buttons");
		changeTextFieldColor = new MenuItem(null,colorPicker);
		menuBar.getMenus().add(fileMenu);
		menuBar.getMenus().add(editMenu);
		menuBar.getMenus().add(aboutMenu);
		menuBar.getMenus().add(changeColors);
		menuBar.getMenus().forEach(menu -> menu.setStyle(menuPadding));
		fileMenu.getItems().add(displayMenu);
		fileMenu.getItems().add(closeMenu);
		editMenu.getItems().add(clearMenu);
		aboutMenu.getItems().add(aboutColorMenu);
		changeColors.getItems().add(changeTextFieldColor);
		displayMenu.setOnAction(e ->
		 {
			 colorField.setText("Press the key for your favorite color");
			 colorField.setStyle("-fx-background-color: white;-fx-text-inner-color: black;");
		 });
		clearMenu.setOnAction(e ->
		 {
			 colorField.setText("");
			 colorField.setStyle("-fx-background-color: white;");
		 });
		closeMenu.setOnAction(e ->
		 {
			 primaryStage.close();
		 });
		aboutColorMenu.setOnAction(e ->
		 {
			 String message = "Drop Down Menus and Layout Managers";
			colorField.setText(message);
		 });
		changeTextFieldColor.setOnAction(e ->
		 {
			 Color c = colorPicker.getValue();
			 textColor = "-fx-text-inner-color: " + c.toString().replace("0x", "#");
			finalColor = backgroundColor + ";\n" + textColor ;
			 colorField.setStyle(finalColor);
		 });
		borderPane = new BorderPane();
		borderPane.setTop(menuBar);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
