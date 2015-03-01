package qb.login.model;

/**
 * Класс менеджер для логин/пароль пар
 * 
 * @author KBobykin
 *
 */
public class LogPass {

	private String login;
	private String password;

	public LogPass() {
		this(null, null);
	}

	public LogPass(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String loginProperty() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String passwordProperty() {
		return password;
	}
}
