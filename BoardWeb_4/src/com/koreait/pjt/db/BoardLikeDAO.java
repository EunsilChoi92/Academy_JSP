package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.koreait.pjt.vo.BoardVO;

public class BoardLikeDAO {
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

	
	// 미완성!
	public static void toggleLike() {
		
	}
}
