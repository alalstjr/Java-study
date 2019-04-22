package ch05;

public class Sale extends Product {
	private int price;
	private int amount;
	private int money;
	
	public void input(int code, String name, String company, String date, int price, int amount) 
	{
		setCode(code);
		setName(name);
		setCompany(company);
		setDate(date);
		this.price = price;
		this.amount = amount;
	}
	
	public void calc() {
		this.money = price * amount;
	}
	
	public void print() { 
		System.out.println("코드 \t 품명 \t 제조사 \t 제조일자 \t 단가 \t 수량 \t 금액");
		System.out.println(getCode() + "\t" + getName() + "\t" + getCompany() + "\t" + getDate() + "\t" + price + "\t" + amount + "\t" + money);
	}
}
