package gui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import mechanics.*;

/**
 * This class realizes window, where user enters names of Investigators and Ancient Ones.
 * This window does not have .fxml base file, because of large number of dynamically adjusted elements
 * (e.g. number of text areas, which directly depends on entered previously number). 
 * @author SanchoPansa
 */

public final class SetPlayersWindowController 
{
	private Framework fw;
	private Scene scene;
	
	private BorderPane bPane;
	private GridPane gPane;
	
	private Label label;
	private TextField ancientTField;
	
	private ArrayList<Label> ARLabel;
	private ArrayList<TextField> ARField;
	
	private Button okButton;
	private Button backButton;
	
	/**
	 * Constructs class. It needs Framework class, which will be filled with entered data, and instance
	 * of Scene class, on which previous window was painted.
	 * @param fw
	 * @param scene
	 */
	public SetPlayersWindowController(Framework fw, Scene scene)
	{
		this.fw = fw;
		this.scene = scene;
	}
	
	/**
	 * This function paints the window and arranges all elements on it.
	 */
	public final void arrangeWindow()
	{
		bPane = new BorderPane();
		gPane = new GridPane();
		
		label = new Label("Введите имена сыщиков и Древнего");
		label.setFont(new Font(24));
		label.setPadding(new Insets(20, 20, 40, 300));
		
		ARLabel = new ArrayList<>();
		ARField = new ArrayList<>();

		ancientTField = new TextField("Азатот");
		ancientTField.setPrefWidth(250);
		 
		for(int i = 0; i < fw.getPlayers(); i++)
		{
			ARLabel.add(new Label("Игрок " + (i + 1) + ": "));
			gPane.add(ARLabel.get(i), 0, i);
			//TODO Delete string in constructor after tests!
			TextField dummy = new TextField("Аманда");
			dummy.setPrefWidth(250);
			ARField.add(dummy);
			gPane.add(ARField.get(i), 1, i);
		}		

		gPane.add(new Label("Древний: "), 0, fw.getPlayers() + 10);
		gPane.add(ancientTField, 1, fw.getPlayers() + 10);
		
		okButton = new Button("OK");
		okButton.setMinWidth(250);
		backButton = new Button("Назад");
		okButton.setOnAction(this::playerNamesEntered);
		backButton.setOnAction(this::backPressed);
		gPane.add(backButton, 0, fw.getPlayers() + 15);
		gPane.add(okButton, 1, fw.getPlayers() + 15);
		
		gPane.setVgap(10);
		gPane.setHgap(20);
		gPane.setAlignment(Pos.TOP_CENTER);
		
		bPane.setTop(label);
		bPane.setCenter(gPane);
		bPane.setBorder(new Border((new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1, 0, 0, 0)))));
		Image backgr = new Image(this.getClass().getResource("pictures/Arkham Horror-01.jpg").toString(), 1050, 800, false, false);
		BackgroundImage bi = new BackgroundImage(backgr, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		bPane.setBackground(new Background(bi));
		
		scene.setRoot(bPane);
	}
	
	private void playerNamesEntered(ActionEvent event)
	{
		ArrayList<String> dummy = new ArrayList<>();
		for(TextField x : this.ARField)
		{
			if(x.getText().isEmpty() || this.ancientTField.getText().isEmpty())
				return;
			dummy.add(x.getText());
		}
		
		fw.setInvestigators(dummy);
		fw.setAncientOne(this.ancientTField.getText()); 
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Arkham_Main.fxml"));
			Parent root = loader.load();
			MainWindowController stub = loader.getController();
			stub.setFramework(this.fw);
			stub.upkeepPhase();
			this.scene.setRoot(root);
		} catch (IOException e) 
		{
			System.out.println(".fxml not found");
		}
	}
	
	private void backPressed(ActionEvent event)
	{
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("fxml/Arkham_Welcome.fxml"));
		} catch (IOException e) 
		{
			System.out.println(".fxml was not found");
		}
		this.scene.setRoot(root);
	}
}
