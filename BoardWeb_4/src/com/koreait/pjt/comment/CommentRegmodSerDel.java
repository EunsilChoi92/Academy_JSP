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


@WebServlet("/comment/regmoddel")
public class CommentRegmodSerDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board = request.getParameter("i_board");
		int i_board = MyUtils.parseStrToInt(strI_board);
		
		String strI_comment = request.getParameter("i_comment");
		int i_comment = MyUtils.parseStrToInt(strI_comment);
		
		int i_user = MyUtils.getLoginUser(request).getI_user();
		
		CommentVO param = new CommentVO();
		
		param.setI_board(i_board);
		param.setI_comment(i_comment);
		param.setI_user(i_user);
		
//		System.out.println("코멘트 i_board : " + param.getI_board());
//		System.out.println("코멘트 i_comment : " + param.getI_comment());
//		System.out.println("코멘트 i_user : " + param.getI_user());
		
		int result = CommentDAO.delComment(param);
		response.sendRedirect("/board/detail?i_board="+i_board);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i_board;
		int i_user = MyUtils.getLoginUser(request).getI_user();
		
		CommentVO param = new CommentVO();
		param.setI_user(i_user);
		
		
		int result;
		String strI_comment = request.getParameter("modI_comment");
		
		if(strI_comment == null) {
			String commentCtnt = request.getParameter("commentCtnt");
			String strI_board = request.getParameter("i_board");
			i_board = MyUtils.parseStrToInt(strI_board);
			
			param.setI_board(i_board);
			param.setCommentCtnt(commentCtnt);
			
			result = CommentDAO.insComment(param);
		} else {
			int i_comment = MyUtils.parseStrToInt(strI_comment);
			String modCommentCtnt = request.getParameter("modCommentCtnt");
			
			String strModI_board = request.getParameter("modI_board");
			i_board = MyUtils.parseStrToInt(strModI_board);
			
			param.setI_board(i_board);
			param.setI_comment(i_comment);
			param.setCommentCtnt(modCommentCtnt);
			
			result = CommentDAO.updComment(param);
			
		}
		
		
		
//		System.out.println("코멘트 i_board : " + param.getI_board());
//		System.out.println("코멘트 i_user : " + param.getI_user());
//		System.out.println("코멘트 i_comment : " + param.getI_comment());
//		System.out.println("코멘트 ctnt : " + param.getCommentCtnt());
		
		
		response.sendRedirect("/board/detail?i_board="+i_board);
				
	}

}
