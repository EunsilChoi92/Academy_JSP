package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.koreait.pjt.vo.BoardVO;

public class BoardDAO {
	
	public static List<BoardVO> selBoardList() {
		final List<BoardVO> list = new ArrayList();
		
		String sql = " SELECT A.i_board, A.title, A.hits, B.nm, A.r_dt "
				+ " FROM t_board4 A "
				+ " JOIN t_user B "
				+ " ON A.i_user = B.i_user "
				+ " ORDER BY i_board DESC ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					int i_board = rs.getInt("i_board");
					String title = rs.getNString("title");
					int hits = rs.getInt("hits");
					String nm = rs.getNString("nm");
					String r_dt = rs.getNString("r_dt");
					
					BoardVO vo = new BoardVO(); 
					vo.setI_board(i_board);
					vo.setTitle(title);
					vo.setHits(hits);
					vo.setNm(nm);
					vo.setR_dt(r_dt);
					
					list.add(vo);
				}
				return 1;
			}
		});
		
		return list;	
	}
	
	public static int insBoard(BoardVO param) {

		String sql = " INSERT INTO t_board4 (i_board, title, ctnt, i_user) " + 
				" VALUES (seq_board4.nextval, ?, ?, ?) " ;
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_user());
			}
		});
	}
	
	// 디테일 아직 미완성
	public static BoardVO selBoard(BoardVO param) {
		
		String sql = " SELECT A.i_board, A.title, A.ctnt, A.hits, B.nm, A.r_dt "
				+ " FROM t_board4 A "
				+ " JOIN t_user B "
				+ " ON A.i_user = B.i_user "
				+ " ORDER BY i_board DESC ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					int i_board = rs.getInt("i_board");
					String title = rs.getNString("title");
					String ctnt = rs.getNString("ctnt");
					int hits = rs.getInt("hits");
					String nm = rs.getNString("nm");
					String r_dt = rs.getNString("r_dt");
					
					BoardVO vo = new BoardVO(); 
					vo.setI_board(i_board);
					vo.setTitle(title);
					vo.setCtnt(ctnt);
					vo.setHits(hits);
					vo.setNm(nm);
					vo.setR_dt(r_dt);
				}
				return 1;
			}
		});
		
		return ;
	}
	
}
