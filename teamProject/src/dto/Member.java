package dto;

public class Member {

	private int userKey;
	private String userId;
	private String pwd;
	private String name;
	
	public Member() {
	}

	public Member(int userKey, String userId, String pwd, String name) {
		super();
		this.userKey = userKey;
		this.userId = userId;
		this.pwd = pwd;
		this.name = name;
	}

	public int getUserKey() {
		return userKey;
	}

	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Member [userKey=" + userKey + ", userId=" + userId + ", pwd=" + pwd + ", name=" + name + "]";
	}
	
}
