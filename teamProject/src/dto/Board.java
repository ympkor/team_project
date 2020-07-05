package dto;

import java.time.LocalDateTime;

public class Board {
	private int boardId;
	private int userKey;
	private String title;
	private String content;
	LocalDateTime regDate;
	private int likes;//좋아요수
	private int hits;//읽은수
	private int commentCount;//댓글수
	private String writer;
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getUserKey() {
		return userKey;
	}
	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	@Override
	public String toString() {
		return "Board [boardId=" + boardId + ", userKey=" + userKey + ", title=" + title + ", content=" + content
				+ ", regDate=" + regDate + ", likes=" + likes + ", hits=" + hits + ", commentCount=" + commentCount
				+ ", writer=" + writer + "]";
	}
	
	
	
	
}
