package com.koreait.pjt.comment;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.CommentDAO;
import com.koreait.pjt.vo.CommentVO;


@WebServlet("/comment/regmod")
public class CommentRegmodSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board = request.getParameter("i_board");
		int i_board = MyUtils.parseStrToInt(strI_board);
		
		int i_user = MyUtils.getLoginUser(request).getI_user();
		
		String commentCtnt = request.getParameter("commentCtnt");
		
//		System.out.println("코멘트 i_board : " + i_board);
//		System.out.println("코멘트 i_user : " + i_user);
//		System.out.println("코멘트 ctnt : " + commentCtnt);
		
		CommentVO param = new CommentVO();
		
		param.setI_board(i_board);
		param.setI_user(i_user);
		param.setCommentCtnt(commentCtnt);
		
		CommentDAO.insComment(param);
		
		response.sendRedirect("/board/detail?i_board="+i_board);
				
	}

}
