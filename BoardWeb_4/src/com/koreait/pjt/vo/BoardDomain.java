package com.koreait.pjt.vo;

/**
 * @author Administrator
 *
 */
public class BoardDomain extends BoardVO{
	private String nm;
	private int yn_like;
	private int record_cnt; // 페이지당 나오는 레코드 수 (글 수)
	private int sIdx;
	private int eIdx;
	private String searchText;
	private String selSearch;
	private String profile_img;
	private int like_cnt;
	private int cmt_cnt;

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public int getYn_like() {
		return yn_like;
	}

	public void setYn_like(int yn_like) {
		this.yn_like = yn_like;
	}

	public int getRecord_cnt() {
		return record_cnt;
	}

	public void setRecord_cnt(int record_cnt) {
		this.record_cnt = record_cnt;
	}

	public int getsIdx() {
		return sIdx;
	}

	public void setsIdx(int sIdx) {
		this.sIdx = sIdx;
	}

	public int geteIdx() {
		return eIdx;
	}

	public void seteIdx(int eIdx) {
		this.eIdx = eIdx;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSelSearch() {
		return selSearch;
	}

	public void setSelSearch(String selSearch) {
		this.selSearch = selSearch;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public int getLike_cnt() {
		return like_cnt;
	}

	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}

	public int getCmt_cnt() {
		return cmt_cnt;
	}

	public void setCmt_cnt(int cmt_cnt) {
		this.cmt_cnt = cmt_cnt;
	}
	
	
	
	

}
