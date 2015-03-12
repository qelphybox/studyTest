package qb.login.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;
import org.sonatype.guice.bean.containers.Main;

import qb.MainApp;
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
		
		//TODO добавить подобные эвент фильтры на ввод имени и фамилии
		
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
					if (txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")) {
						e.consume();
					} else if (txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")) {
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
	private boolean regClicked = false;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setLogPass(UserData userData) {
		this.userData = userData;
	}

	public boolean isRegClicked() {
		return regClicked;
	}

	@SuppressWarnings({ "deprecation", "resource", "null" })
	@FXML
	private void handleOk() throws Exception {

		boolean canWrite = true;

		if (!passwordField1.getText().equals(passwordField2.getText()) && Validators.logPassValidator(passwordField1) && Validators.logPassValidator(passwordField2))
			Dialogs.create().title("Ошибка").masthead("Ошибка ввода").message("Пароли не совпадают").showError();
		else {

			if (Validators.logPassValidator(loginField) && Validators.nameValidator(firstNameField) && Validators.nameValidator(lastNameField)) {

				// FIXME написать компактней
				String firstname = firstNameField.getText();
				String lastName = lastNameField.getText();
				String login = loginField.getText();
				Integer password = passwordField1.getText().hashCode();
				Long groupnum = new Long(groupNumField.getText());

				userData.setFirstName(firstname);
				userData.setLastName(lastName);
				userData.setLogin(login);
				userData.setPassword(password);
				userData.setGroupNum(groupnum);

				Gson gson = new Gson();
				File userDataFile = new File("userData.txt");
				
				// проверка на существование
				if (userDataFile.exists()) {
					BufferedReader input = new BufferedReader(new FileReader(userDataFile));
					String inLine = new String();
					do {
						inLine = input.readLine();
						if (inLine != null && !inLine.isEmpty()){
							UserData checkUserData = gson.fromJson(inLine, UserData.class);
							if (checkUserData == null)
								checkUserData.setLogin("");
							if (checkUserData.getLogin().equals(userData.getLogin())) {
								Dialogs.create().title("Ошибка").masthead("Ошибка регистрации").message("Пользователь с логином \"" + login + "\" уже зарегистрирован").showError();
								canWrite = false;
								break;
							}
						}
					} while (inLine != null && !inLine.isEmpty());
				}

				if (canWrite) {
					// Записываем в файл. изи
					String gsonstring = gson.toJson(userData);
					BufferedWriter output = new BufferedWriter(userDataFile.exists() ? new FileWriter(userDataFile, true) : new FileWriter(userDataFile));
					output.write(gsonstring);
					output.newLine();
					output.close();
					dialogStage.close();
					regClicked = true;
				} else {
					loginField.clear();
					passwordField1.clear();
					passwordField2.clear();
				}
			}
		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
}
