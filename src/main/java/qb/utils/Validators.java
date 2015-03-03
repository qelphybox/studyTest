package qb.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.InputVerifier;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import org.controlsfx.dialog.Dialogs;

/**
 * @author KBobykin
 * 
 *         Валидаторы
 *
 */
public class Validators {

	// TODO создать валидатор полей формы регистрации

	// FIXME продумать, написать нормальный вариант валидации

	public static boolean logPassValidator(TextField loginField) {

		String errMessage = inputValidator(loginField);

		if (errMessage.length() == 0) {
			return true;

		} else {
			if (loginField instanceof PasswordField) {
				errMessage = "Пароль " + errMessage;
				Dialogs.create().title("Ошибка").masthead("Ошибка ввода")
						.message(errMessage).showError();
			} else {
				errMessage = "Логин " + errMessage;
				Dialogs.create().title("Ошибка").masthead("Ошибка ввода")
						.message(errMessage).showError();
			}
			return false;
		}
	}

	private static String inputValidator(TextField field) {

		Pattern validationPattern = Pattern.compile("^[a-zA-Z0-9_-]{5,}$");

		String errorMessage = "";

		if (field.getText() != null || !field.getText().isEmpty()) {
			Matcher m = validationPattern.matcher(field.getText());
			if (!m.matches())
				errorMessage += "должен содержать не менее 5 символов. Может содежать латинские буквы, цифры, тире и нижнее подчеркивание\n";
		} else {
			errorMessage += "не введен";
		}

		return errorMessage;
	};
}
