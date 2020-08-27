package com.koreait.pjt.board;

import java.io.IOException;
import java.util.List;

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
import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.BoardVO;


@WebServlet("/board/list")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDomain param = new BoardDomain();
		param.setRecord_cnt(Const.RECORD_CNT);
		request.setAttribute("pagingCnt", BoardDAO.selPagingCnt(param));
		
		int page = MyUtils.getIntParameter(request, "page");
		page = (page == 0) ? 1 : page;
		
		int eIdx = page * Const.RECORD_CNT;
		int sIdx = eIdx - Const.RECORD_CNT;
		
		param.seteIdx(eIdx);
		param.setsIdx(sIdx);
		
		param.setRecord_cnt(Const.RECORD_CNT);
		
		List<BoardVO> list = BoardDAO.selBoardList(param);
		request.setAttribute("list", list);
		//request.setAttribute("page", page);
		
		ViewResolver.forwardLoginChk("board/list", request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
