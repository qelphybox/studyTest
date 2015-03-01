package qb.login.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import qb.login.model.UserData;

/**
 * @author KBobykin
 *
 */
public class LogInWinController {

	private boolean isClosed = false;
	
	@FXML
	private TextField loginField;
	
	@FXML
	private PasswordField passwordField;

	private Stage dialogStage;
	private UserData logpass = new UserData();
	private boolean okClicked = false;

	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setLogPass(UserData logPass){
		this.logpass = logPass;
	}

	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	private void handleEnter() {

		if (isInputValid()) {
			logpass.setLogin(loginField.getText());
			logpass.setPassword(passwordField.getText());
			
			//TODO доработать куда будет передаваться пара(логин, пароль);
			
		}

		okClicked = true;
		dialogStage.close();
	}
	
	@FXML
	private void handleRegister() {
		
		//TODO создать форму регистрации, дописать после
		
	}
	
	private boolean isInputValid() {
		
		//FIXME доработать валидацию, выбрасывает после нажатия на ОК в errorMessage.
		
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