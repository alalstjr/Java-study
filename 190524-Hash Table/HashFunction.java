package study;

import java.util.Arrays;

public class HashFunction {

	String[] theArray, theArray2;
	int arraySize;
	int itemsInArray = 0;
	
	// HashFunction 생성자
	HashFunction(int size) {
		// size 해시 배열의 사이즈 
		arraySize = size;
		
		// theArray 라는 배열을 생성합니다. 
		theArray = new String[size];
		theArray2 = new String[size];
		
		// Arrays.fill 배열값을 초기화 해주는 클래스
		// theArray 배열을 -1 로 전체 초기화 합니다.
		Arrays.fill(theArray, "-1");
		Arrays.fill(theArray2, "-1");
	}
	
	public static void main(String[] args) {
		// {크기가 30인 해시배열}을 인스턴스화 하여 메모리공간에 올립니다.
		HashFunction theFunc = new HashFunction(30);
		
		// --- 해시 함수 첫번째 -- //
		
		// 해시배열에 추가할 배열 요소
		String[] elementsToAdd = {"1", "5", "17", "21", "26"};
		
		// hashFunction1 함수에 {추가할 요소} 와 {요소를 저장할 공간}을 파라미터로 전달합니다.
		theFunc.hashFunction1(elementsToAdd, theFunc.theArray);
		
		theFunc.viewTheStack(theFunc.theArray);
		
		// -- 해시 함수 두번째 -- //
		
		String[] elementsToAdd2 = {
				"100", "510", "170", "214", "268", 
				"624", "660", "70", "50", "450",
				"415", "300", "400", "320", "1",
				"820", "990", "988", "890", "999",
				"2", "40", "58", "987", "723",
				"590", "476", "369", "286", "45"
				};
		
		theFunc.hashFunction2(elementsToAdd2, theFunc.theArray2);
		
		// 해시 테이블에서 해당 값을 찾아 key값을 반환하는 함수
		theFunc.findKey("369");
		
		theFunc.viewTheStack(theFunc.theArray2);
		
	}
	
	// 아주 간단한 해시테이블 함수 1
	public void hashFunction1(String[] stringsForArray, String[] theArray) {
		for(int i = 0; i < stringsForArray.length; i++) {
			String newElementVal = stringsForArray[i];
			theArray[Integer.parseInt(newElementVal)] = newElementVal;
			
			/* ex) 반목문을 도는 경우 
			 * i = 0 
			 * newElementVal = "1"
			 * theArray[1] = "1"
			 * 
			 * i = 1
			 * newElementVal = "5"
			 * theArray[5] = "5"
			 * 
			 * i = 2
			 * newElementVal = "17"
			 * theArray[5] = "17"
			 * 
			 * ...
			 * 
			 * theArray[30] 크기의 배열을 풀어보면
			 * theArray[30] = {"-1", "1", "-1", ... "5", "-1", "-1", ... "17" ...};
			 * 
			 * 이런식으로 저장됩니다.
			 * */
		}
	}
	
	// 해시 충돌을 막기위한 해시 함수2
	public void hashFunction2(String[] stringsForArray, String[] theArray) {
		for(int i = 0; i < stringsForArray.length; i++) {
			String newElementVal = stringsForArray[i];
			
			// 테이블의 크기는 소수(Prime Number)로 설정하는 것이 효율성이 높기때문에 소수인 29로 나눕니다.
			int arrayIndex = Integer.parseInt(newElementVal) % 29;
			
			// 값이 저장되는 모습을 시각화합니다.
			System.out.println("Modulus Index" + arrayIndex + "for value" + newElementVal);
			
			// 빈 공간을 찾을 때까지 배열을 순회합니다.
			while(theArray[arrayIndex] != "-1") {
				// 해당 배열에 값이 존재하면 충돌 표시를 합니다.
				// 그와 동시에 해당 배열의 순서값을 1 증가시킵니다.
				// 예시를 들면 theArray[1] 의 자리에 값이 존재한다면 
				// theArray[2] 의 자리를 확인하고 이 자리도 값이 존재하면
				// theArray[3] 의 자리를 선형적으로 확인하며 계속해서 확인합니다.
				
				++arrayIndex;
				
				// Collision try!
				System.out.println("충돌!" + arrayIndex);
				
				arrayIndex %= arraySize;
			}
			
			// 빈공간을 찾았다면 해당 배열에 값을 추가합니다.
			theArray[arrayIndex] = newElementVal;
		}
	}
	
	public String findKey(String key) {
		int arrayIndexHash = Integer.parseInt(key) % 29;
		
		// 해당 배열의 값이 비어있지 않고
		while(theArray2[arrayIndexHash] != "-1") {
			// 해당 배열의 값이 key값과 일치한다면 key를 반환
			if(theArray2[arrayIndexHash] == key) {
				System.out.println(key + "index에서 찾았다." + arrayIndexHash);
				
				return theArray2[arrayIndexHash];
			}
			// 원하는 값이 아니라면 다음 index로 넘어간다.
			++arrayIndexHash;
			
			arrayIndexHash %= arraySize;
		}
		
		// 원하는 값을 찾지 못한경우 null 반환
		System.out.println("해당 값을 찾지 못했어요.");
		return null;
	}
	
	public void viewTheStack(String[] theArray) {
		for(int i = 0; i < theArray.length; i++) {
			System.out.print(i + " ");
			
			if(theArray[i] != "-1") {
				System.out.print("\""+theArray[i] + "\" ");
			} else {
				System.out.print(" " + "\t");
			}
			
			if(i != 0 &&(i % 10) == 9) {
				System.out.print("\n");
			}
		}
	}
	
}
