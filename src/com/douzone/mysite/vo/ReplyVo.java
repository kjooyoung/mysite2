package com.douzone.mysite.vo;

public class ReplyVo {
	private long no;
	private String contents;
	private String writeDate;
	private long boardNo;
	private long userNo;
	private String userName;
	
	public ReplyVo() {}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public long getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(long boardNo) {
		this.boardNo = boardNo;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "ReplyVo [no=" + no + ", contents=" + contents + ", writeDate=" + writeDate + ", boardNo=" + boardNo
				+ ", userNo=" + userNo + "]";
	}
	
}
