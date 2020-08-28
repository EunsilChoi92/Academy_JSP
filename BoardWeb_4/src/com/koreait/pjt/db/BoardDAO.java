package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.BoardVO;

public class BoardDAO {
	
	public static List<BoardVO> selBoardList(BoardDomain param) {
		final List<BoardVO> list = new ArrayList();
		
		String sql = " select * from "  
				+ " ( "
				+ "    select rownum as rnum, A.* from " 
				+ "		( "
				+ "			SELECT A.i_board, A.title, A.hits, B.nm, to_char(A.r_dt, 'yyyy/mm/dd hh24:mi:ss') as r_dt "
				+ " 		FROM t_board4 A "
				+ " 		JOIN t_user B "
				+ " 		ON A.i_user = B.i_user "
				+ "			WHERE A.title LIKE ? "
				+ " 		ORDER BY i_board DESC "
				+ "     ) A "
				+ "    where rownum <= ? " 
				+ " ) A " 
				+ " where A.rnum >= ? ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				
				ps.setNString(1, param.getSearchText());
				ps.setInt(2, param.geteIdx());
				ps.setInt(3, param.getsIdx());
			}

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
	
	// 디테일 i_num 뽑기
	public static int selI_board(int i_user) {
		String  sql = " SELECT MAX(i_board) FROM t_board4 WHERE i_user = ? ";
		
		return JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_user);
			}
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				int i_board = 0;
				if(rs.next()) {
					i_board = rs.getInt("MAX(i_board)");
				}
				return i_board;
			}
		});		
	}
	
	// 디테일 
	public static BoardDomain selBoard(BoardVO param) {
		BoardDomain result = new BoardDomain(); 	
		result.setI_board(param.getI_board());
		
		String sql = " SELECT A.title, A.ctnt, A.hits, A.i_user, "
				+ " A.r_dt, A.m_dt, B.nm, DECODE(C.i_user, null, 0, 1) as yn_like "
				+ " FROM t_board4 A "
				+ " INNER JOIN t_user B "
				+ " ON A.i_user = B.i_user "
				+ " LEFT JOIN t_board4_like C "
				+ " ON A.i_board = C.i_board "
				+ " AND C.i_user = ? "
				+ " WHERE A.i_board = ? ";
		
		int resultInt = JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_user());
				ps.setInt(2, param.getI_board());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					String title = rs.getNString("title");
					String ctnt = rs.getNString("ctnt");
					int hits = rs.getInt("hits");
					String nm = rs.getNString("nm");
					String r_dt = rs.getNString("r_dt");
					String m_dt = rs.getNString("m_dt");
					int i_user = rs.getInt("i_user");
					int yn_like = rs.getInt("yn_like");
					
					System.out.println("title : " + title);
					System.out.println("i_user : " + i_user);
					System.out.println("yn_like : " + yn_like);
					
					result.setTitle(title);
					result.setCtnt(ctnt);
					result.setHits(hits);
					result.setNm(nm);
					result.setR_dt(r_dt);
					result.setM_dt(m_dt);
					result.setI_user(i_user);
					result.setYn_like(yn_like);
				}
				return 1;
			}
		});
		return result;
	}
	
	public static int selPagingCnt(final BoardDomain param) {
		String sql = " SELECT CEIL(COUNT(i_board) / ?) FROM t_board4 "
				+ " WHERE title LIKE ? ";
		
		return JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getRecord_cnt());
				ps.setNString(2, param.getSearchText());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			}
			
		});
	}
	
	public static int delBoard(BoardVO param) {
		String sql = " DELETE t_board4 WHERE i_board = ? AND i_user = ? ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
				ps.setInt(2, param.getI_user());
			}
		});
	}
	
	public static int updBoard(BoardVO param) {
		String sql = " UPDATE t_board4 SET title = ?, ctnt = ?, m_dt = sysdate WHERE i_board = ? AND i_user = ? ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_board());
				ps.setInt(4, param.getI_user());
			}
		});
	}
	
	public static int addHits(BoardVO param) {
		String sql = " UPDATE t_board4 SET hits = ? WHERE i_board = ? ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				int newHits = param.getHits() + 1;
				ps.setInt(1, newHits);
				ps.setInt(2, param.getI_board());
				param.setHits(newHits);
			}
		});
	}
	
   public static void addHits1(final int i_board) {
	      String sql = " UPDATE t_board4 "
	            + " SET hits = hits + 1 "
	            + " WHERE i_board = ? ";
	      JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
	         @Override
	         public void update(PreparedStatement ps) throws SQLException {
	            ps.setInt(1, i_board);
	         }
	      });
	   }
   
   
	
}
