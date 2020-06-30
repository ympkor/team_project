package mapper;

import java.util.List;

import dto.Board;

public interface BoardMapper {

	public void regBoard(Board board);
	
	public List<Board> selectAll();
}
