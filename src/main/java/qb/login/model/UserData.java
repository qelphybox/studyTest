package qb.login.model;

/**
 * Бин для пользователей
 * 
 * @author KBobykin
 *
 */
public class UserData {

	private String login;
	private Integer password;
	private String firstName;
	private String lastName;
	private Long groupNum;

	public UserData() {
		this(null, null);
	}

	public UserData(String login, Integer password) {
		this.login = login;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public int getPassword() {
		return password;
	}

	public void setPassword(Integer password) {
		this.password = password;
	}
}
