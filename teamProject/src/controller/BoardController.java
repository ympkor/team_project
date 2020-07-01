package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.Board;
import dto.Comment;
import mapper.BoardMapper;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardMapper boardMapper;
	
	@GetMapping("/show")
	public String showBoard(Model m) {
		List<Board> bList = boardMapper.selectAll();
		// for (Board b : bList) { System.out.println(b); }
		m.addAttribute("bList", bList);
		return "board";
	}
	@PostMapping(value ="/show" )
	public String showBoard(Model m,Board board) {
		//System.out.println(board);
		boardMapper.regBoard(board);
		List<Board> bList = boardMapper.selectAll();
		m.addAttribute("bList", bList);
		return "board";
	}
	@RequestMapping("/write")
	public String writeBoard() {		
		return "writeBoard";
	}
	
	//게시판 내용 한개 보여주기
	@GetMapping("/contentOneShow")
	public String showOneCntetnBoard(Model m, int boardId) {
		//게시물클릭하면 조회수 1올라가기
		boardMapper.updatehits(boardId);
		//System.out.println(boardId);
		//현재글 가져오기
		Board currentBoard = boardMapper.selecOneBoard(boardId);
		//System.out.println(currentBoard);
		//이전글, 다음글 제목가져오기
		List<Board> bList = boardMapper.contentOneShow(boardId);
		//for (Board b : bList) { System.out.println(b); }
		//보드아이디에 있는 코멘트 가져오기
		List<Comment> cList = boardMapper.selectbyBId(boardId);
		for (Comment co : cList) {System.out.println(co);}
		m.addAttribute("currentBoard", currentBoard);
		m.addAttribute("bList", bList);
		m.addAttribute("currentboardId", boardId);
		m.addAttribute("cList", cList);
		return "contentOneShow";
	}
	
	//코멘쓰기
	@PostMapping("/commentwrite")
	public String writeComment(Comment comment, Model m) {
		//System.out.println(comment);
		boardMapper.writeComment(comment);
		List<Comment> cList = boardMapper.selectbyBId(comment.getBoardId());
		//for (Comment co : cList) { System.out.println(co); }		
		Board currentBoard = boardMapper.selecOneBoard(comment.getBoardId());
		List<Board> bList = boardMapper.contentOneShow(comment.getBoardId());
		m.addAttribute("currentBoard", currentBoard);
		m.addAttribute("bList", bList);
		m.addAttribute("currentboardId", comment.getBoardId());
		m.addAttribute("cList", cList);
		return "contentOneShow";
	}
}
