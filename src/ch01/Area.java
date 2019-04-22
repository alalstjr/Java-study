package ch01;

public class Area {
	private int width;
	private int length;
	public Area() {} // 기본생성자
	public Area(int width, int length) {
		this.width = width;
		this.length = length;
	}
	public int getArea() {
		return width * length;
	}
}
