package ch03;

import ch01.Area;

// 상속제외 : private, 상속자

public class Volume extends Area {
	private int height;

	public Volume(int height, int width, int length) {
		super(width, length);
		this.height = height;
	}
	
	public int getVolume() {
		return getArea() * height;
	}
}
