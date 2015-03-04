package qb.login.controller;

import org.controlsfx.dialog.Dialogs;

import qb.login.model.UserData;
import qb.utils.Validators;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author KBobykin
 * 
 *         Контроллер окна регистрации пользователя
 *
 */
public class RegistryWinController {

	@FXML
	private TextField loginField;

	@FXML
	private PasswordField passwordField1;

	@FXML
	private PasswordField passwordField2;

	@FXML
	private TextField firstNameField;

	@FXML
	private TextField lastNameField;

	@FXML
	private TextField groupNumField;

	private Stage dialogStage;
	private UserData userData = new UserData();
	private boolean okClicked = false;
	private boolean regClicked = false;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setLogPass(UserData userData) {
		this.userData = userData;
	}

	@FXML
	private void handleOk() {
		
		if (!passwordField1.getText().equals(passwordField2.getText()) && Validators.logPassValidator(passwordField1) && Validators.logPassValidator(passwordField2))
			Dialogs.create().title("Ошибка").masthead("Ошибка ввода").message("Пароли не совпадают").showError();
		else {
			
			if (Validators.logPassValidator(loginField)){
				
			}
				
				
			//TODO сделать валидацию ввода полей, проверку на существование пользователя, запись в файл
			
		}
	}
	 
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
}
