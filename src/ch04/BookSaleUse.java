package ch04;

public class BookSaleUse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BookSale b1 = new BookSale();
		
		// book class method
		b1.setBookName("JAVA");
		b1.setAuthor("쭌프로");
		b1.setPress("길벗");
		b1.setYear(2019);
		b1.setPrice(30000);
		
		// book sale method
		b1.setAmount(5);
		b1.setRank(1);
		b1.setMoney();
		b1.print();
	}

}
