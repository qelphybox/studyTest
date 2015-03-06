package qb.login.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import qb.login.model.UserData;
import qb.utils.Validators;

import com.google.gson.Gson;

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

	public void initialize() {
		// добавил эвент фильтр на поле группы
		groupNumField.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(5));
	}

	// собственно эвент фильтр
	public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
		return new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				TextField txt_TextField = (TextField) e.getSource();
				if (txt_TextField.getText().length() >= max_Lengh) {
					e.consume();
				}
				if (e.getCharacter().matches("[0-9.]")) {
					if (txt_TextField.getText().contains(".")
							&& e.getCharacter().matches("[.]")) {
						e.consume();
					} else if (txt_TextField.getText().length() == 0
							&& e.getCharacter().matches("[.]")) {
						e.consume();
					}
				} else {
					e.consume();
				}
			}
		};
	}

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
	private void handleOk() throws IOException {

		if (!passwordField1.getText().equals(passwordField2.getText()) && Validators.logPassValidator(passwordField1) && Validators.logPassValidator(passwordField2))
			Dialogs.create().title("Ошибка").masthead("Ошибка ввода")
					.message("Пароли не совпадают").showError();
		else {

			if (Validators.logPassValidator(loginField) && Validators.nameValidator(firstNameField) && Validators.nameValidator(lastNameField)) {
				
				userData.setFirstName(firstNameField.getText());
				userData.setLastName(lastNameField.getText());
				userData.setLogin(loginField.getText());
				userData.setPassword(passwordField1.getText().hashCode());
				userData.setGroupNum(new Long(groupNumField.getText()));
				
				//TODO сделать проверку на существование пользователя.
				
				//Записываем в файл. изи
				Gson gson = new Gson();
				String gsonstring = gson.toJson(userData);
				File userDataFile = new File("userData.txt");
				BufferedWriter output = new BufferedWriter(new FileWriter(userDataFile));
				output.write(gsonstring);
				output.close();
			} 

			// TODO сделать валидацию ввода полей, проверку на существование
			// пользователя, запись в файл

		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
}
