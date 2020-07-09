package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.Board;
import dto.Comment;

public interface BoardMapper {

	public void regBoard(Board board);
	public Board selecOneBoard(int boardId);
	
	public List<Board> selectAll();
	public List<Board> contentOneShow(int boardId);
	
	public void updatehits(int boardId);
	
	public List<Comment> selectbyBId(int boardId);
	public void writeComment(Comment comment);
	public void updateComment(Comment comment);
	public void deleteComment(int commentId);
	
	//현게시물 이전 게시물
	public Board getbeforeBoard(int boardId);
	//현게시물 다음 게시물
	public Board getnextBoard(int boardId);
	
	//총 게시물개수 반환
	public int getBoardTotalCnt();
	//10개씩 게시물 가져오기
	public List<Board> getBoardListPerPage(@Param("firstRow")int firstRow, @Param("boardCntPerPage")int boardCntPerPage);
	
	//코멘쓰면 그 보드아이디에 코멘수도 하나증가
	public void updateincreaseBoardCommentCount(int boardId);
	//코멘삭제하면 그 보드아이디에 코멘수도 감소
	public void updatedecreaseBoardCommentCount(int boardId);
	
	
	//유저키에 맞는 게시물과 코멘트 지우기
	public void deleteBoardbyuserkeyAll(int userKey);
	public void deleteCommentbyuserkeyAll(int userKey);
	
	//게시물 좋아요수 하나 늘리기
	public void increaseBoardlike(int boardId);
	//게시물에 좋아요한 유저 저장하기
	public void regLikebyUIdBId(@Param("boardId")int boardId, @Param("userKey")int userKey);
	//게시물에 좋아요수 가져오기
	public int getlikeNum(int boardId);
	
	//게시물에 user가 좋아요한 정보가 있으면 1을 반환
	public int checklikeByUIdBId(@Param("boardId")int boardId, @Param("userKey")int userKey);
	//게시물 좋아요수 하나 감소
	public void decreaseBoardlike(int boardId);
	//좋아요취소시 좋아요정보삭제
	public void cancelLikeByUIdBId(@Param("boardId")int boardId, @Param("userKey")int userKey);
	
	//게시물 수정하기
	public void updateBoard(Board board);
}
