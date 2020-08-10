package com.koreait.board.common;

public class Utils {
	
	public static int parseStringToInt(String strI_board) {	
		return parseStringToInt(strI_board, 0);
	}
	
	public static int parseStringToInt(String strI_board, int returnVal) {	
		try {
			int i_board = Integer.parseInt(strI_board);
			return i_board;
		} catch(NumberFormatException e) {
			return returnVal;
		}
	}
	
}
