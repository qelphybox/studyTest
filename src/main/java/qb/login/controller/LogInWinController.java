package qb.login.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import qb.MainApp;
import qb.login.model.UserData;
import qb.utils.Validators;

/**
 * @author KBobykin
 *
 */
public class LogInWinController {

	@FXML
	private TextField loginField;

	@FXML
	private PasswordField passwordField;

	private Stage dialogStage;
	private UserData logpass = new UserData();
	private boolean enterClicked = false;
	private boolean regClicked = false;

	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setLogPass(UserData logPass) {
		this.logpass = logPass;
	}

	public boolean isEnterClicked() {
		return enterClicked;
	}
	
	public boolean isRegisterClicked() {
		
		return regClicked;
	}
	
	@FXML
	private void handleRegister() {

		MainApp.showRegisterDialog();
		
		regClicked = true;
		dialogStage.show();
	}
	

	@FXML
	private void handleEnter() throws Exception {
		
		String login = loginField.getText();
		int password = passwordField.getText().hashCode();
		if (Validators.logPassValidator(loginField) && Validators.logPassValidator(passwordField)) {

			logpass.setLogin(login);
			logpass.setPassword(password);
			
		} else { 
			MainApp.showLoginDialog();
		}
		 
		if(Validators.authenticate(login, password))
			dialogStage.close();
		
		// TODO Сделать плюшки типа: зачистки полей при ошибке и передачу данных пользователя в систему
		
		enterClicked = true;
	}
	
	
	
}