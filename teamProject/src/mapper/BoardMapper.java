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
	
}
