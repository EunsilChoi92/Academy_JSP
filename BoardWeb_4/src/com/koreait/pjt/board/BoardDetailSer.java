package com.koreait.pjt.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
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
import com.koreait.pjt.db.BoardLikeDAO;
import com.koreait.pjt.db.CommentDAO;
import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.CommentVO;
import com.koreait.pjt.vo.UserVO;


@WebServlet("/board/detail")
public class BoardDetailSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 글 눌렀을 때 디테일 보이기
		String strI_board = request.getParameter("i_board");
		int i_board = Integer.parseInt(strI_board);
		System.out.println("i_board : " + i_board);
		
		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		
		
		
		// 조회수 올리기
		
		ServletContext application = getServletContext();
		Integer readI_user = (Integer)application.getAttribute("read_" + strI_board);
		UserVO loginUser = MyUtils.getLoginUser(request);
		if(readI_user == null || loginUser.getI_user() != readI_user) {
			BoardDAO.addHits1(i_board);
			application.setAttribute("read_" + strI_board, loginUser.getI_user());
		}
		
		// 좋아요 - 로그인한 유저의 i_user 정보 넣기
		param.setI_user(loginUser.getI_user());
		// 좋아요 여기까지
				
		
		BoardDomain data = BoardDAO.selBoard(param);
		System.out.println(data.getYn_like());
		request.setAttribute("data", data);
		
		// 좋아요 숫자 표시
		int like_count = BoardLikeDAO.selBoardLikeCnt(param);
		request.setAttribute("like_count", like_count);
		
		// comment
		List<CommentVO> commentList = CommentDAO.selCommentList();
		request.setAttribute("commentList", commentList);	
		
				
		
		ViewResolver.forwardLoginChk("board/detail", request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
