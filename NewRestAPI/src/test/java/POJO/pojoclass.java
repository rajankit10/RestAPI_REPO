package POJO;

public class pojoclass {
	private String name;
	private String weapon;
	private String IdName;
	private String password;
	
	
	public String SetName(String name) {
		return this.name=name;
	}
	public String SetWeapon(String weapon) {
		return this.weapon=weapon;
	}
	public String SetIdName(String IdName) {
		return this.IdName=IdName;
	}
	public String SetPassword(String password) {
		return this.password=password;
	}
	
	public String getName() {
		return name;
	}
	public String getWeapon() {
		return weapon;
	}
	public String getIdName() {
		return IdName;
	}
	public String getPassword() {
		return password;
	}
}
