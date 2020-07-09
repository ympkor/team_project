package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import dto.Board;
import dto.BoardListView;
import dto.Comment;
import mapper.BoardMapper;
import mapper.MemberMapper;
import service.BoardService;

@Controller
@RequestMapping("/board")
@SessionAttributes("userKey")
public class BoardController {
	@Autowired
	BoardMapper boardMapper;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	BoardService boardService;
	
	@GetMapping("/show")
	public String showBoard(Model m,@RequestParam(defaultValue = "1")int pNum) {
		BoardListView bList= boardService.showBoard(pNum);
		/* for (Board b : bList.getBoardList()) { System.out.println(b); }*/
		//System.out.println("총게시물갯수"+bList.getBoardTotalCnt());
		//System.out.println("총페이지수"+bList.getPageTotalCnt());
		m.addAttribute("bList", bList);
		//String day=bList.getBoardList().get(0).getRegDate().toString();
		//System.out.println("투스트링한거"+day);
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
		//1페이지 정보를 불러옴
		BoardListView bList= boardService.showBoard(1);
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
	public String showOneCntetnBoard(@ModelAttribute("userKey")int userKey,Model m, int boardId) {
		//게시물클릭하면 조회수 1올라가기
		boardMapper.updatehits(boardId);
		//System.out.println(boardId);
		//보드아이디와 유저키로 좋아요있는지 확인(true,false로 반환)
		String likecheck= boardService.likechecking(boardId, userKey);
		//현재글 가져오기
		Board currentBoard = boardMapper.selecOneBoard(boardId);
		//System.out.println(currentBoard);
		//이전글, 다음글 제목가져오기
		Board beforeBoard = boardMapper.getbeforeBoard(boardId); 
		Board nextBoard = boardMapper.getnextBoard(boardId);		
		//System.out.println("이전게시물"+beforeBoard); 
		//System.out.println("다음게시물"+nextBoard); 
		//보드아이디에 있는 코멘트 가져오기
		List<Comment> cList = boardMapper.selectbyBId(boardId);
		//for (Comment co : cList) {System.out.println(co);}
		m.addAttribute("currentBoard", currentBoard);
		m.addAttribute("beforeBoard", beforeBoard);
		m.addAttribute("nextBoard", nextBoard);
		m.addAttribute("currentboardId", boardId);
		m.addAttribute("cList", cList);
		m.addAttribute("likecheck", likecheck);
		return "contentOneShow";
	}
	
	//코멘쓰기
	@PostMapping("/commentwrite")
	public String writeComment(@ModelAttribute("userKey")int userKey,Comment comment, Model m) {
		//System.out.println(comment);
		String commentWriter = memberMapper.getUserIdByuserKey(comment.getUserKey());
		comment.setCommentWriter(commentWriter);
		//System.out.println(comment);
		boardService.regComment(comment);		
		
		//보드아이디와 유저키로 좋아요있는지 확인(true,false로 반환)
		String likecheck= boardService.likechecking(comment.getBoardId(), userKey);
		//코멘트 보드 가져와서 수정된것 다시 보여줄 데이터 만들어주기
		List<Comment> cList = boardMapper.selectbyBId(comment.getBoardId());
		//for (Comment co : cList) { System.out.println(co); }		
		Board currentBoard = boardMapper.selecOneBoard(comment.getBoardId());
		List<Board> bList = boardMapper.contentOneShow(comment.getBoardId());
		m.addAttribute("currentBoard", currentBoard);
		m.addAttribute("bList", bList);
		m.addAttribute("currentboardId", comment.getBoardId());
		m.addAttribute("cList", cList);
		m.addAttribute("likecheck", likecheck);
		return "contentOneShow";
	}
	
	//코멘수정
		@PostMapping("/commentupdate")
		public String  updateComment(@ModelAttribute("userKey")int userKey,Comment comment, Model m) {
			//System.out.println(comment);
			boardMapper.updateComment(comment);
			List<Comment> cList = boardMapper.selectbyBId(comment.getBoardId());
			//for (Comment co : cList) { System.out.println(co); }		
			//보드아이디와 유저키로 좋아요있는지 확인(true,false로 반환)
			String likecheck= boardService.likechecking(comment.getBoardId(), userKey);
			Board currentBoard = boardMapper.selecOneBoard(comment.getBoardId());
			List<Board> bList = boardMapper.contentOneShow(comment.getBoardId());
			m.addAttribute("currentBoard", currentBoard);
			m.addAttribute("bList", bList);
			m.addAttribute("currentboardId", comment.getBoardId());
			m.addAttribute("cList", cList);
			m.addAttribute("likecheck", likecheck);
			return "contentOneShow";
		}
		//코멘삭제
		@RequestMapping("/deletecomment")
		public String  deleteComment(@ModelAttribute("userKey")int userKey,Model m, int boardId, int commentId) {
			//선택된 코멘트 삭제, 코멘수 감소
			boardService.delComment(boardId, commentId);
			
			//보드아이디와 유저키로 좋아요있는지 확인(true,false로 반환)
			String likecheck= boardService.likechecking(boardId, userKey);
			Board currentBoard = boardMapper.selecOneBoard(boardId);
			//이전글, 다음글 제목가져오기
			List<Board> bList = boardMapper.contentOneShow(boardId);
			//보드아이디에 있는 코멘트 가져오기
			List<Comment> cList = boardMapper.selectbyBId(boardId);
			m.addAttribute("currentBoard", currentBoard);
			m.addAttribute("bList", bList);
			m.addAttribute("currentboardId", boardId);
			m.addAttribute("cList", cList);
			m.addAttribute("likecheck", likecheck);
			return "contentOneShow";
		}
		
		//좋아요 눌렀을때 ajax로 
		@PostMapping("/likeupdate")
		@ResponseBody
		public int likeupdate(String boardId, String userKey) {
			//System.out.println("보드아이디"+boardId);
			//System.out.println("유저키"+userKey);
			int bId=(boardId!="") ? Integer.parseInt(boardId) : 0;
			int uKey=(userKey!="") ? Integer.parseInt(userKey) : 0;
			int likes=0;
			try {
				boardService.likeupdate(bId, uKey);
				likes=boardMapper.getlikeNum(bId);
				return likes;
			} catch (Exception e) {
				e.printStackTrace();
				return likes;
			}
		}
		
		//좋아요 취소후  ajax로
		@PostMapping("/cancellikeupdate")
		@ResponseBody
		public int cancellikeupdate(String boardId, String userKey) {
			//System.out.println("보드아이디"+boardId);
			//System.out.println("유저키"+userKey);
			int bId=(boardId!="") ? Integer.parseInt(boardId) : 0;
			int uKey=(userKey!="") ? Integer.parseInt(userKey) : 0;
			int likes=0;
			try {
				boardService.cancellikeupdate(bId, uKey);
				likes=boardMapper.getlikeNum(bId);
				return likes;
			} catch (Exception e) {
				e.printStackTrace();
				return likes;
			}
		}
}
