package com.koreait.pjt.vo;

public class CommentVO {
	private int i_comment;
	private int i_board;
	private int i_user;
	private String commentCtnt;
	private String r_dt;
	private String m_dt;
	private String nm;
	private String profile_img;
	
	public int getI_comment() {
		return i_comment;
	}
	public void setI_comment(int i_comment) {
		this.i_comment = i_comment;
	}
	public int getI_board() {
		return i_board;
	}
	public void setI_board(int i_board) {
		this.i_board = i_board;
	}
	public int getI_user() {
		return i_user;
	}
	public void setI_user(int i_user) {
		this.i_user = i_user;
	}
	public String getCommentCtnt() {
		return commentCtnt;
	}
	public void setCommentCtnt(String ctnt) {
		this.commentCtnt = ctnt;
	}
	public String getR_dt() {
		return r_dt;
	}
	public void setR_dt(String r_dt) {
		this.r_dt = r_dt;
	}
	public String getM_dt() {
		return m_dt;
	}
	public void setM_dt(String m_dt) {
		this.m_dt = m_dt;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	
}
