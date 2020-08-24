package com.koreait.pjt.user;

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
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.UserLoginHistoryVO;
import com.koreait.pjt.vo.UserVO;


@WebServlet("/login")
public class LoginSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.getLoginUser(request) != null) {
			response.sendRedirect("/board/list");
			return;
		}
		ViewResolver.forward("user/login", request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String encrypt_pw = MyUtils.encryptString(user_pw);
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(encrypt_pw);
		
		int result = UserDAO.selUser(param);
		// 아이디값 지우고 비번 남겨놓고 // 비번 틀렸으면 아이디 
		if(result != 1) {//에러처리
			String msg = "";
			
			switch(result) {
				case 2:
					msg = "비밀번호가 틀렸습니다.";
					break;
				case 3:
					msg = "존재하지 않는 아이디입니다.";			
					break;
				default:
					msg = "에러가 발생하였습니다.";
			}
			request.setAttribute("user_id", user_id);
			request.setAttribute("msg", msg);
			doGet(request, response);
			
			System.out.println(result);
			return;
		}
		
		// 로그인 히스토리 기록 [start]
		String agent = request.getHeader("User-Agent");
		System.out.println("agent : " + agent);
		
		String os = getOs(agent);
		String browser = getBrowser(agent);
		String ip_addr = request.getRemoteAddr();
			
		UserLoginHistoryVO ulhVO = new UserLoginHistoryVO();
		ulhVO.setI_user(param.getI_user());
		ulhVO.setOs(os);
		ulhVO.setIp_addr(ip_addr);
		ulhVO.setBrowser(browser);
		UserDAO.insUserLoginHistory(ulhVO);
		
		// 로그인 히스토리 기록 [end]
		
		
		HttpSession hs = request.getSession();
		hs.setAttribute(Const.LOGIN_USER, param);
		
	
		System.out.println("로그인 성공!");
		response.sendRedirect("/board/list");
		
	}
	
	private String getBrowser(String agent) {
		if(agent.toLowerCase().contains("msie")) {
			return "ie";
		} else if(agent.toLowerCase().contains("safari")) {
			return "safari";
		} else if(agent.toLowerCase().contains("chrome")) {
			return "chrome";
		}
		return "";
	}
	
	private String getOs(String agent) {
		String result = null;
		if(agent.toLowerCase().contains("mac")) {
			return "mac";
		} else if(agent.toLowerCase().contains("windows")) {
			return "win";
		} else if(agent.toLowerCase().contains("x11")) {
			return "linux";
		} else if(agent.toLowerCase().contains("android")) {
			return "android";
		} else if(agent.toLowerCase().contains("iphone")) {
			return "iOS";
		}
		return "";
	}

}
