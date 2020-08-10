package com.koreait.first;

import java.util.Scanner;

public class Practice {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		seasons: while(true) {
			try {
				System.out.print("몇 월입니까? > ");
				String month = scanner.next();
				int result = Integer.parseInt(month);
				String season = "";
				
				switch(result) {
					case 12: case 1: case 2: season = "겨울"; break;
					case 3: case 4: case 5: season = "봄"; break;
					case 6: case 7: case 8: season = "여름"; break;
					case 9: case 10: case 11: season = "가을"; break;
					case 13: System.out.println("종료합니다."); break seasons; // return;도
					default : season = "다시 입력해주세요.";
				}
				System.out.println(season);
			} catch(Exception e) {
				System.out.println("숫자만 입력해주세요.");
			}
		}
	
	
	}
}
