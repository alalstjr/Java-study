package ch01;

public class Student {
	private String name;
	private String num;
	private String major;
	private int year;
	
	public Student() {}
	// 기본생성자

	// 단축키 alt + shift + s = Fields 클릭
	// 매개변수 생성자
	public Student(String name, String num, String major, int year) {
		super();
		// Object 클래스 기본으로 상속
		this.name = name;
		this.num = num;
		this.major = major;
		this.year = year;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	// 입력
	public void input (String name, String num, String major, int year) {
		this.name = name;
		this.num = num;
		this.major = major;
		this.year = year;
	}

	public void print() {
		System.out.println("이름\t학번\t전공\t학년");
		System.out.println(name + "\t" + num + "\t" + major + "\t" + year);
	}
	
}
