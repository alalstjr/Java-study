package ch02;

import ch01.Student;

public class Scholar extends Student {
	private String category;
	private int money;
	public void input(String name, String num, String major, int year, String category, int money) 
	{
		
		setName(name);
		setNum(num); 
		setMajor(major);
		setYear(year);
		// 부모 클래스의 변수가  외부에서 접근할 수 없는 private 일경우 방법
		
		// super.name;
		// super.num;
		// super.major;
		// super.year;
		// 부모 클래스를 지정한것
		
		// 또 다른 방법으로는
		// super.input(name, num, major, year);
		
		this.category = category;
		this.money = money;
	}
	
	public void print() {
		System.out.println("이름\t학번\t\t전공\t학년\t구분\t장학금액");
		System.out.println(getName() +"\t"+ getNum() +"\t"+ getMajor() +"\t"+  getYear() +"\t"+ category +"\t"+ money);
	}
}
