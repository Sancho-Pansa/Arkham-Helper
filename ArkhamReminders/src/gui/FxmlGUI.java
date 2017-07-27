package gui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 * This application is a useful tool to players of Arkham Horror.
 * I have played this game several times and noticed, that in order to watch out for every rule
 * players must be very attentive and have significant memory. If they don't play this game every day,
 * it will be difficult to keep all the rules in mind.
 * This application reminds players about different mechanics of the game: 
 * opening of Gates, terror level, actions, when one is blessed, or cursed, or has loan, etc.
 * 
 * This class is the entry point of application. It creates the first window, which allows user
 * to enter the number of players and address of database with data about Investigators and Ancient Ones.
 * @author SanchoPansa
 * @version 1.0
 */

public class FxmlGUI extends Application
{
	private static String arg = "/D:/Java & Git/Sandbox/ArkhamReminders/src/ArkhamDB.accdb";
	
	/**
	 * This overriden method loads the first FXML UI (Arkham_Welcome.fxml)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		// In order to correctly load .fxml file, it must be in the same folder (or subfolder), where class resides
		Parent root = FXMLLoader.load(getClass().getResource("Arkham_Welcome.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Arkham Horror Reminders");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args)
	{
		if(args.length > 0)
			arg = args[0];
		Application.launch(args);
	}
	
	public static String getArg()
	{
		return arg;
	}
}
