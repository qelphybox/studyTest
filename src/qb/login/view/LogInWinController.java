package qb.login.view;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInWinController {
	
	@FXML
	private TextField loginField;
	@FXML
	private PasswordField passwordField;
	
	private Stage dialogStage;
	
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}

}
