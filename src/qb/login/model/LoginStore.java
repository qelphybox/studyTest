package qb.login.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Класс менеджер для логин/пароль пар
 * 
 * @author KBobykin
 *
 */
public class LoginStore {
	
	private final StringProperty login;
	private final StringProperty password;
	
	public LoginStore() {
		this(null, null);
	}

	public LoginStore(String login, String password) {
		this.login = new SimpleStringProperty(login);
		this.password = new SimpleStringProperty(password);
	}

}
