package com.koreait.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board.common.Utils;
import com.koreait.board.db.BoardDAO;
import com.koreait.board.vo.BoardVO;

@WebServlet("/boardWrite")
public class BoardWriteSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsp = "/WEB-INF/view/boardRegmod.jsp";
		request.getRequestDispatcher(jsp).forward(request, response);
				
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

//		
//		BoardVO param = new BoardVO();
//		param.setTitle(title);
//		param.setCtnt(ctnt);
//		param.setI_student(i_student);
//		
//		
//		BoardVO data = BoardDAO.selBoard(param); //DB로 값 받기
//		request.setAttribute("data", data);
//		
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		String strI_student = request.getParameter("i_student");
		int i_student = Utils.parseStringToInt(strI_student, 0);
		
//		값이 잘 넘어왔는지 항상 확인할 것!!
//		System.out.println("title: " + title);
//		System.out.println("ctnt: " + ctnt);
//		System.out.println("i_student: " + strI_student); 
		
		BoardVO param = new BoardVO();
		param.setTitle(title);
		param.setCtnt(ctnt);
		param.setI_student(i_student);
		
		int result = BoardDAO.insBoard(param);
		
//		1이 넘어와야 잘 실행된 것!
		System.out.println("result : " + result);
		
		//response.sendRedirect("/boardList"); // 특정 처리 후 또는 특정 조건일 때에 지정한 페이지로 이동하고 싶은 경우 사용
		
		
		
		
	}

}
