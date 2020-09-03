package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.BoardVO;

import oracle.net.aso.r;

public class BoardLikeDAO {
	public static List<BoardDomain> selBoardLikeList(BoardVO param) {
		List<BoardDomain> list = new ArrayList();
		
		String sql = " SELECT B.i_user, B.nm, B.profile_img "
				+ "  FROM t_board4_like A "
				+ " JOIN t_user B "
				+ " ON A.i_user = B.i_user "
				+ " WHERE i_board = ? "
				+ " ORDER BY A.r_dt ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
				
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					BoardDomain vo = new BoardDomain();
					vo.setI_user(rs.getInt("i_user"));
					vo.setNm(rs.getNString("nm"));
					vo.setProfile_img(rs.getNString("profile_img"));
					list.add(vo);
				}
				return 1;
			}
		});
		
		return list;
	}
	
	public static int insBoardLike(BoardVO param) {
		String sql = " INSERT INTO t_board4_like (i_user, i_board) VALUES (?, ?) ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_user());
				ps.setInt(2, param.getI_board());
			}
		});
	}
	
	
	public static int delBoardLike(BoardVO param) {
		String sql = " DELETE t_board4_like WHERE i_board = ? AND i_user = ? ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
				ps.setInt(2, param.getI_user());
			}
		});
	}


	
}
