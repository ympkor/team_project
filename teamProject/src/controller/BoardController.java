package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.Board;
import mapper.BoardMapper;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardMapper boardMapper;
	
	@GetMapping("/show")
	public String showBoard(Model m) {
		List<Board> bList = boardMapper.selectAll();
		/*
		 * for (Board b : bList) { System.out.println(b); }
		 */
		m.addAttribute("bList", bList);
		return "board";
	}
	@PostMapping(value ="/show" )
	public String showBoard(Board board) {
		//System.out.println(board);
		boardMapper.regBoard(board);
		return "board";
	}
	@RequestMapping("/write")
	public String writeBoard() {
		return "writeBoard";
	}
}
