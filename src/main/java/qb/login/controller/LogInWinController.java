package qb.login.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.controlsfx.dialog.Dialogs;

import qb.MainApp;
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

	public void setLogPass(UserData logPass) {
		this.logpass = logPass;
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleEnter() throws Exception {

		if (isInputValid()) {
			
			logpass.setLogin(loginField.getText());
			logpass.setPassword(passwordField.getText());

			// TODO доработать куда будет передаваться пара(логин, пароль);

		}

		okClicked = true;
		dialogStage.close();
	}

	@FXML
	private void handleRegister() {

		// TODO создать форму регистрации, дописать после

	}

	private boolean isInputValid() {

		// FIXME доработать валидацию, выбрасывает после нажатия на ОК в
		// errorMessage.

		Pattern validationPattern = Pattern.compile("^[a-zA-Z0-9_-]{5,}$");

		String errorMessage = "";

		if (loginField.getText() != null || loginField.getText().length() != 0) {
			Matcher m = validationPattern.matcher(loginField.getText());
			if (!m.matches())
				errorMessage += "Логин должен содержать не менее 5 символов. Может содежать латинские буквы, цифры, тире и нижнее подчеркивание\n";

		} else
			errorMessage += "Вы забыли ввести логин. \n";

		if (passwordField.getText() != null
				|| passwordField.getText().length() != 0) {
			Matcher m = validationPattern.matcher(passwordField.getText());
			if (!m.matches()) {
				if (errorMessage.length() > 30)
					errorMessage = "Логин и пароль должны"
							+ errorMessage.substring(12, errorMessage.length());
				else
					errorMessage += "Пароль должен содержать не менее 5 символов. Может содежать латинские буквы, цифры, тире и нижнее подчеркивание\n";
			}
		} else
			errorMessage += "Вы забыли ввести пароль.";

		if (errorMessage.length() == 0) {
			return true;

		} else {

			Dialogs.create().title("Ошибка").masthead("Ошибка ввода")
					.message(errorMessage).showError();
			return false;

		}

	}
}