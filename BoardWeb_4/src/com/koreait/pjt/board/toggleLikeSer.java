package com.koreait.pjt.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.db.BoardLikeDAO;
import com.koreait.pjt.vo.BoardVO;

@WebServlet("/board/toggleLike")
public class toggleLikeSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board = request.getParameter("i_board");
		int i_board = MyUtils.parseStrToInt(strI_board);
		
		String strYn_like = request.getParameter("yn_like");
		int yn_like = MyUtils.parseStrToInt(strYn_like);
		
		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		param.setI_user(MyUtils.getLoginUser(request).getI_user());
		
		int result = 0;
		if(yn_like == 0) {
			result = BoardLikeDAO.insBoardLike(param);
		} else if(yn_like == 1) {
			result = BoardLikeDAO.delBoardLike(param);
		} 
		
		if(result != 1) {
			//에러 어쩌구	
			return;
		} 
		
		
		response.sendRedirect("/board/detail?i_board="+i_board);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
