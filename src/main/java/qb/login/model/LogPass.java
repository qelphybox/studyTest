package qb.login.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Класс менеджер для логин/пароль пар
 * 
 * @author KBobykin
 *
 */
public class LogPass {
	
	private final StringProperty login;
	private final StringProperty password;
	
	public LogPass() {
		this(null, null);
	}

	public LogPass(String login, String password) {
		this.login = new SimpleStringProperty(login);
		this.password = new SimpleStringProperty(password);
	}
	
	public String getLogin() {
        return login.get();
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public StringProperty loginProperty() {
        return login;
    }
    
    public String getPassword() {
    	return password.get();
    }
    
    public void setPassword(String password) {
        this.password.set(password);
    }

    public StringProperty passwordProperty() {
        return password;
    }
}

