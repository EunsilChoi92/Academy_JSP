package com.koreait.pjt.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.UserVO;


@WebServlet("/changePw")
public class changePwSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser = MyUtils.getLoginUser(request);
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
		ViewResolver.forwardLoginChk("user/changePw", request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser = MyUtils.getLoginUser(request);
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;
		}

		String type = request.getParameter("type");
		String pw = request.getParameter("pw");
		String encrypt_pw = MyUtils.encryptString(pw);
		UserVO param = new UserVO();
		
		switch(type) {
		case "1": // 현재 비밀번호 확인
			
			param.setUser_id(loginUser.getUser_id());
			param.setUser_pw(encrypt_pw);
			
			int result = UserDAO.login(param);
			
			if(result == 1) {
				request.setAttribute("isAuth", true);
			} else {
				request.setAttribute("msg", "비밀번호를 확인해주세요.");
			}
			
			doGet(request, response);
			break;
		case "2": // 비밀번호 변경		
			param.setI_user(loginUser.getI_user());
			param.setUser_pw(encrypt_pw);
			UserDAO.updUser(param);
			response.sendRedirect("/profile?proc=1");				
			break;
		}
	}

}
