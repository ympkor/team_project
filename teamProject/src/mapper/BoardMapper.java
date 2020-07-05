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
	//코멘쓰면 그 보드아이디에 코멘수도 하나증가
	public void updateincreaseBoardCommentCount(int boardId);
	//코멘삭제하면 그 보드아이디에 코멘수도 감소
	public void updatedecreaseBoardCommentCount(int boardId);
	
	
	//유저키에 맞는 게시물과 코멘트 지우기
	public void deleteBoardbyuserkeyAll(int userKey);
	public void deleteCommentbyuserkeyAll(int userKey);
}
