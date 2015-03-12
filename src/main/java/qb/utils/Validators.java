package qb.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import org.controlsfx.dialog.Dialogs;

/**
 * @author KBobykin
 * 
 *         Валидаторы
 *
 */
@SuppressWarnings("deprecation")
public class Validators {

	/**
	 * Валидатор для полей ввода имени и фамилии
	 * 
	 * @param nameField
	 */
	public static boolean nameValidator(TextField nameField) {

		String errMessage = rusInputValidator(nameField);

		if (errMessage.length() == 0) {
			return true;

		} else {
			if (nameField.getPromptText().contains("имя"))
				errMessage = "Имя " + errMessage;
				if (errMessage.length() > 15)
					errMessage += "о";
			if (nameField.getPromptText().contains("фамил"))
				errMessage = "Фамилия " + errMessage; 
				if (errMessage.length() > 15)
					errMessage += "a";
			Dialogs.create().title("Ошибка").masthead("Ошибка ввода")
					.message(errMessage).showError();
		}
		return false;
	}

	/**
	 * Валидатор для полей ввода логина и пароля
	 * 
	 * @param loginField
	 * @return
	 */
	public static boolean logPassValidator(TextField loginField) {

		String errMessage = engInputValidator(loginField);

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

	/**
	 * Валидатор ввода на кириллице
	 * 
	 * @param field
	 * @return
	 */
	private static String rusInputValidator(TextField field) {

		Pattern validationPattern = Pattern.compile("^[а-яА-Я]+$");

		String errorMessage = "";

		if (field.getText() != null && !field.getText().isEmpty()) {
			Matcher m = validationPattern.matcher(field.getText());
			if (!m.matches())
				errorMessage += "может содежать только русские буквы\n";
		} else {
			errorMessage += "не введен";
		}
		return errorMessage;
	}

	/**
	 * Валидатор ввода на латинице
	 * 
	 * @param field
	 * @return
	 */
	private static String engInputValidator(TextField field) {

		Pattern validationPattern = Pattern.compile("^[a-zA-Z0-9_-]{5,}$");

		String errorMessage = "";

		if (field.getText() != null && !field.getText().isEmpty()) {
			Matcher m = validationPattern.matcher(field.getText());
			if (!m.matches())
				errorMessage += "должен содержать не менее 5 символов. Может содежать латинские буквы, цифры, тире и нижнее подчеркивание\n";
		} else {
			errorMessage += "не введен";
		}

		return errorMessage;
	}
	
	private static String numInputValidator(TextField field) {

		Pattern validationPattern = Pattern.compile("^[0-9]+$");

		String errorMessage = "";

		if (field.getText() != null && !field.getText().isEmpty()) {
			Matcher m = validationPattern.matcher(field.getText());
			if (!m.matches())
				errorMessage += "должен содержать не менее 5 символов. Может содежать латинские буквы, цифры, тире и нижнее подчеркивание\n";
		} else {
			errorMessage += "не введен";
		}

		return errorMessage;
	}
}
