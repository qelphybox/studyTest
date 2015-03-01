package qb.login.model;

/**
 * Бин для пользователей
 * 
 * @author KBobykin
 *
 */
public class UserData {

	private String login;
	private String password;
	private String firstName;
	private String secondName;
	private Long groupNum;

	public UserData() {
		this(null, null);
	}

	public UserData(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public Long getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(Long groupNum) {
		this.groupNum = groupNum;
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
