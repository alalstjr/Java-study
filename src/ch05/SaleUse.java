package ch05;

public class SaleUse {
	public static void main(String[] args) {
		Sale s1 = new Sale();
		s1.input(1, "냉장고", "삼성", "2018-01-01", 500000, 10);
		s1.calc();
		s1.print();
	}
}
