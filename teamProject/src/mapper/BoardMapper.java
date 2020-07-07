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
}
