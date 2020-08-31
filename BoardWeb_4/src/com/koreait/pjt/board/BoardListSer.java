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
		
		String selSearch = request.getParameter("selSearch");
		System.out.println("selSearch : " + selSearch);
	
		String searchText = request.getParameter("searchText");
		searchText = (searchText == null ? "" : searchText);
		
		int page = MyUtils.getIntParameter(request, "page");
		page = (page == 0) ? 1 : page;
		
		int recordCnt = MyUtils.getIntParameter(request, "record_cnt");
		recordCnt = (recordCnt == 0 ? 10 : recordCnt);
		
		int eIdx = page * recordCnt;
		int sIdx = eIdx - recordCnt;
		
		BoardDomain param = new BoardDomain();
		param.seteIdx(eIdx);
		param.setsIdx(sIdx);
		param.setRecord_cnt(recordCnt);
		param.setSearchText("%" + searchText + "%");
		// selSearch 추가
		param.setSelSearch(selSearch);
		
		int pagingCnt = BoardDAO.selPagingCnt(param);
	
		HttpSession hs = (HttpSession)request.getSession();
		Integer beforeRecordCnt = (Integer)hs.getAttribute("recordCnt");
		
		//이전 레코드수 값이 있고, 이전 레코드수보다 변경한 레코드 수가 더 크다면 마지막 페이지수로 변경
		if(beforeRecordCnt != null && beforeRecordCnt < recordCnt) {  
			page = pagingCnt;
		}
		request.setAttribute("page", page);
		System.out.println("page : " + page);
		
		request.setAttribute("pagingCnt", pagingCnt);
		
		List<BoardVO> list = BoardDAO.selBoardList(param);
		request.setAttribute("list", list);
		
		
		hs.setAttribute("recordCnt", recordCnt);
		ViewResolver.forwardLoginChk("board/list", request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
