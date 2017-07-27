package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mechanics.*;

/**
 * This class realizes control of elements declared in Arkham_Welcome.fxml
 * @author SanchoPansa
 *
 */

public class WelcomeWindowController
{	
	private Framework fw; 
	
	@FXML
	private TextField numField;
	
	@FXML
	private Button playerNumBtn;
	
	@FXML
	private void enterText(KeyEvent event) throws Exception
	{
		if(event.getCode().equals(KeyCode.ENTER))
			this.playerNumEntered();
	}
	
	@FXML
	private void playerNumEntered() throws Exception
	{
		if(this.numField.getText().isEmpty())
			return;
		else
		{
			this.fw = new Framework(new Integer(this.numField.getText()));
			new SetPlayersWindowController(this.fw, this.numField.getScene()).arrangeWindow();
		}
		System.out.println("Correct input");
	}
	
}
