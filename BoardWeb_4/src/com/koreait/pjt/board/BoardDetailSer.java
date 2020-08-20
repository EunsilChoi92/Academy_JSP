package com.koreait.pjt.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;


@WebServlet("/board/detail")
public class BoardDetailSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// i_board 뽑기
//		int i_board = BoardDAO.selI_board(i_user);
//		System.out.println("detail i_board : " + i_board);
		
		// 글 눌렀을 때 디테일 보이기
		String strI_board = request.getParameter("i_board");
		int i_board = Integer.parseInt(strI_board);
		System.out.println("i_board : " + i_board);
		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		
		BoardVO data = BoardDAO.selBoard(param);
		
		request.setAttribute("data", data);
		
		ViewResolver.forwardLoginChk("board/detail", request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
