package com.koreait.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board.common.Utils;
import com.koreait.board.db.BoardDAO;
import com.koreait.board.vo.BoardVO;


@WebServlet("/boardMod")
public class BoardMod extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board = request.getParameter("i_board");
		
		int i_board = Utils.parseStringToInt(strI_board, 0);
		if(i_board == 0) {
			response.sendRedirect("/boardList");
			return;
		}
		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		
		
		BoardVO data = BoardDAO.selBoard(param); //DB로 값 받기
		request.setAttribute("data", data);
		request.setAttribute("err", Utils.parseStringToInt(request.getParameter("err"),0) == 1 ? " 삭제실패했습니다." : null);
		
		String jsp = "WEB-INF/view/boardRegmod.jsp";
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		//String strI_student = request.getParameter("i_student");
		//int i_student = Utils.parseStringToInt(strI_student, 0);
		
		String strI_board = request.getParameter("i_board");
		int i_board = Utils.parseStringToInt(strI_board, 0);
		
		if(i_board == 0) {
			response.sendRedirect("/boardList");
			return;
		}
		
		
		BoardVO param = new BoardVO();
		
		param.setCtnt(ctnt);
		param.setI_board(i_board);
		//param.setI_student(i_student);
		param.setTitle(title);
		
		int result = BoardDAO.modBoard(param);
		
//		System.out.println("result : " + result);
		
		if(result == 1) {
			response.sendRedirect("/boardDetail?i_board="+i_board);// 특정 처리 후 또는 특정 조건일 때에 지정한 페이지로 이동하고 싶은 경우 사용
		} else {
			String jsp = "WEB-INF/view/boardDetail.jsp";
			request.getRequestDispatcher(jsp).forward(request, response);
		}
		
		
		
//		System.out.println("title: " + title);
//		System.out.println("ctnt: " + ctnt);
//		System.out.println("i_student: " + strI_student); 
//		System.out.println("i_student: " + strI_board); 
		
		
		
		
	}

}
