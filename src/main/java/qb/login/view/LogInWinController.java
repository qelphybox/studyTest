package qb.login.view;

import org.controlsfx.dialog.Dialogs;

import qb.login.model.LogPass;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInWinController {

	private boolean isClosed = false;
	
	@FXML
	private TextField loginField;
	
	@FXML
	private PasswordField passwordField;

	private Stage dialogStage;
	private LogPass logpass = new LogPass();
	private boolean okClicked = false;

	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setLogPass(LogPass logPass){
		this.logpass = logPass;
	}

	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	private void handleOk() {

		if (isInputValid()) {
			logpass.setLogin(loginField.getText());
			logpass.setPassword(passwordField.getText());
			
			//TODO доработать куда будет передаваться пара(логин, пароль);
			
		}

		okClicked = true;
		dialogStage.close();
	}

	private boolean isInputValid() {

		String errorMessage = "";
		if (loginField.getText() == null || loginField.getText().length() == 0)
			errorMessage += "Введите логин \n";

		if (passwordField.getText() == null || passwordField.getText().length() == 0)
			errorMessage += "Введите пароль";
		
		if (errorMessage.length() == 0) {
			return true;
			
		} else {

			Dialogs.create().title("Ошибка").masthead("Ошибка ввода")
					.message(errorMessage).showError();
			return false;
			
		}

	}
}