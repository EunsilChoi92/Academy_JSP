package com.koreait.pjt.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;


@WebServlet("/board/regmod")
public class BoardRegmodSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	// 화면 띄우기(등록/수정창)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		
		if(hs.getAttribute(Const.LOGIN_USER) == null) {
			System.out.println("실패실패!!");
			response.sendRedirect("/login");
			return;
		}
		
		ViewResolver.forward("board/regmod", request, response);
	}

	// 처리 용도(실제로 DB에 등록/수정 실시)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		
		HttpSession hs = request.getSession();
		UserVO sessionParam = (UserVO) hs.getAttribute(Const.LOGIN_USER);
		int i_user = sessionParam.getI_user();
		
		
		// 확인용
//		System.out.println(title);
//		System.out.println(ctnt);
//		System.out.println(i_user);
		
		BoardVO param = new BoardVO();
		
		param.setTitle(title);
		param.setCtnt(ctnt);
		param.setI_user(i_user);
		
		int result = BoardDAO.insBoard(param);	
		System.out.println("result : " + result);
		
		if(result != 1) {
			String msg = "에러가 발생했습니다.";
			request.setAttribute("msg", msg);
			doGet(request, response);
			return;
		}
		response.sendRedirect("detail");
		
	}

}
