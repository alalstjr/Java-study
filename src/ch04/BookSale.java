package ch04;

public class BookSale extends Book {
	private int amount;
	private int rank;
	private int money;
	
	// getter, setter
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney() {
		this.money = price * amount;
		if(money > 100000) money = (int)(money * 0.9);
	}
	public void print() { 
		System.out.println("도서명 \t 출판사 \t 저자 \t" + "출판연도 \t 단가 \t 판매수량 \t 판매금액 \t 판매순위");
		System.out.println(bookName + "\t" + press + "\t" + author + "\t" + year + "\t" + price + "\t" + amount + "\t" + money + "\t" + rank);
	}
	
}
