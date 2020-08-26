package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.CommentVO;

public class CommentDAO {
	public static List<CommentVO> selCommentList() {
		final List<CommentVO> list = new ArrayList();
		
		String sql = " SELECT A.i_comment, A.i_board, "
				+ " A.i_user, A.commentCtnt, B.nm, "
				+ " to_char(A.r_dt, 'yyyy/mm/dd hh24:mi:ss') as r_dt, "
				+ " to_char(A.m_dt, 'yyyy/mm/dd hh24:mi:ss') as m_dt "
				+ " FROM t_comment A "
				+ " JOIN t_user B "
				+ " ON A.i_user = B.i_user "
				+ " ORDER BY i_comment DESC ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					int i_comment = rs.getInt("i_comment");
					int i_board = rs.getInt("i_board");
					int i_user = rs.getInt("i_user");
					String commentCtnt = rs.getNString("commentCtnt");
					String nm = rs.getNString("nm");
					String r_dt = rs.getNString("r_dt");
					String m_dt = rs.getNString("m_dt");
					
					CommentVO vo = new CommentVO();
					
					vo.setI_comment(i_comment);
					vo.setI_board(i_board);
					vo.setI_user(i_user);
					vo.setCommentCtnt(commentCtnt);
					vo.setNm(nm);
					vo.setR_dt(r_dt);
					vo.setM_dt(m_dt);
					
					list.add(vo);
				}

				return 1;
			}
			
		});
		
		return list;
	}
	
	
	public static int insComment(CommentVO param) {

		String sql = " INSERT INTO t_comment " + 
				"	(i_board, i_user, i_comment, commentCtnt) " + 
				"	VALUES " + 
				"	(?, ?, seq_t_comment.nextval, ?) " ;
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
				ps.setInt(2, param.getI_user());
				ps.setNString(3, param.getCommentCtnt());
			}
		});
	}
	
	public static int updComment(CommentVO param) {
		
		String sql = " UPDATE t_comment"
				+ " SET "
				+ " commentCtnt = ?, "
				+ " m_dt = sysdate " 
				+ " WHERE i_board = ? "
				+ " AND i_comment = ? "
				+ " AND i_user = ? ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getCommentCtnt());
				ps.setInt(2, param.getI_board());
				ps.setInt(3, param.getI_comment());
				ps.setInt(4, param.getI_user());
			}
		});
	}
	
	public static int delComment(CommentVO param) {
		String sql = " DELETE t_comment "
				+ " WHERE i_comment = ? AND i_user = ? ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_comment());
				ps.setInt(2, param.getI_user());

			}
		});
	}

}
