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
import mapper.MemberMapper;
import service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardMapper boardMapper;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	BoardService boardService;
	
	@GetMapping("/show")
	public String showBoard(Model m) {
		List<Board> bList = boardMapper.selectAll();
		//for (Board b : bList) { System.out.println("그냥보기"+b); }
		m.addAttribute("bList", bList);
		return "board";
	}
	//글 다 쓰고 나서 등록누르면
	@PostMapping(value ="/show" )
	public String showBoard(Model m,Board board) {
		//System.out.println("작성자 넣기전"+board);
		//유저키로 유저아이디를 받아옴
		String writer=memberMapper.getUserIdByuserKey(board.getUserKey());
		//받아온 유저아이디를 작성자에 넣어줌 
		//System.out.println("받아온 작성자"+writer);
		board.setWriter(writer);
		//System.out.println("작성자 넣은후"+board);
		boardMapper.regBoard(board);
		List<Board> bList = boardMapper.selectAll();
		m.addAttribute("bList", bList);
		return "board";
	}
	//글쓰기 버튼 누르면 연결되게
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
		//for (Board b : bList) { System.out.println("게시판하나보기"+b); }
		//보드아이디에 있는 코멘트 가져오기
		List<Comment> cList = boardMapper.selectbyBId(boardId);
		//for (Comment co : cList) {System.out.println(co);}
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
		String commentWriter = memberMapper.getUserIdByuserKey(comment.getUserKey());
		comment.setCommentWriter(commentWriter);
		//System.out.println(comment);
		boardService.regComment(comment);		
		
		//코멘트 보드 가져와서 수정된것 다시 보여줄 데이터 만들어주기
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
	
	//코멘수정
		@PostMapping("/commentupdate")
		public String  updateComment(Comment comment, Model m) {
			//System.out.println(comment);
			boardMapper.updateComment(comment);
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
		//코멘삭제
		@RequestMapping("/deletecomment")
		public String  deleteComment(Model m, int boardId, int commentId) {
			//선택된 코멘트 삭제, 코멘수 감소
			boardService.delComment(boardId, commentId);
			
			Board currentBoard = boardMapper.selecOneBoard(boardId);
			//이전글, 다음글 제목가져오기
			List<Board> bList = boardMapper.contentOneShow(boardId);
			//보드아이디에 있는 코멘트 가져오기
			List<Comment> cList = boardMapper.selectbyBId(boardId);
			m.addAttribute("currentBoard", currentBoard);
			m.addAttribute("bList", bList);
			m.addAttribute("currentboardId", boardId);
			m.addAttribute("cList", cList);
			return "contentOneShow";
		}
		
}
