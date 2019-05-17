package member;

public class MemberDTO {
	private String userid;
	private String password;
	private String name;
	private String address;
	private String tel;
	
	// 생성자
	public MemberDTO() {
		super();
	}
	public MemberDTO(String userid, String password, String name, String address, String tel) {
		super();
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.address = address;
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "MemberDTO [address=" + address + ", name=" + name + ", password=" + password + ", tel=" + tel
				+ ", userid=" + userid + "]";
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}
