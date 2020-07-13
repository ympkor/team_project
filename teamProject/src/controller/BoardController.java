package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	
	//게시물10개씩 제목보여주기
	@GetMapping("/show")
	public String showBoard(Model m,@RequestParam(defaultValue = "1")int pNum,
			@RequestParam(defaultValue = "1")int sortNum) {
		
		BoardListView bList=boardService.showBoard(pNum,sortNum);
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
		//1페이지 정보를 불러옴,등록순으로
		BoardListView bList= boardService.showBoard(1,1);
		m.addAttribute("bList", bList);
		return "board";
	}
	//글쓰기 버튼 누르면 연결되게
	@RequestMapping("/write")
	public String writeBoard(HttpSession session) {
		if(session.getAttribute("userKey")==null) {
			return "callForLogin";
		}		
		return "writeBoard";
	}
	
	//게시판 내용 한개 보여주기
	@GetMapping("/contentOneShow")
	public String showOneCntetnBoard(HttpSession session,Model m,
			int boardId,int pNum,@RequestParam(defaultValue = "1")int sortNum) {
		int userKey=0;
		if(session.getAttribute("userKey")!=null) {
			userKey =(int)session.getAttribute("userKey");
		}
		
		//게시물클릭하면 조회수 1올라가기
		boardMapper.updatehits(boardId);
		//System.out.println(boardId);
		//보드아이디와 유저키로 좋아요있는지 확인(true,false로 반환)
		String likecheck= boardService.likechecking(boardId, userKey);
		//현재글 가져오기
		List<Board> cnbList= boardService.getcurrentAndBeforeAndNextBoard(boardId, sortNum);
		Board currentBoard = cnbList.get(0);		
		//이전글, 다음글 제목가져오기
		Board beforeBoard = cnbList.get(1);
		Board nextBoard = cnbList.get(2);
		
		//보드아이디에 있는 코멘트 가져오기 10개 최신순으로 가져오기
		List<Comment> cList = boardMapper.selectbyBId(boardId);
		//for (Comment co : cList) {System.out.println(co);}
		m.addAttribute("currentBoard", currentBoard);
		m.addAttribute("beforeBoard", beforeBoard);
		m.addAttribute("nextBoard", nextBoard);
		m.addAttribute("currentboardId", boardId);
		m.addAttribute("cList", cList);
		m.addAttribute("likecheck", likecheck);
		m.addAttribute("pNum", pNum);
		m.addAttribute("sortNum", sortNum);
		return "contentOneShow";
	}
	
	//코멘쓰기
	@PostMapping("/commentwrite")
	public String writeComment(@ModelAttribute("userKey")int userKey,String pNum
			,Comment comment, Model m,@RequestParam(defaultValue = "1")String sNum) {
		int sortNum = Integer.parseInt(sNum);
		String commentWriter = memberMapper.getUserIdByuserKey(comment.getUserKey());
		comment.setCommentWriter(commentWriter);
		//System.out.println(comment);
		boardService.regComment(comment);		
		
		//보드아이디와 유저키로 좋아요있는지 확인(true,false로 반환)
		String likecheck= boardService.likechecking(comment.getBoardId(), userKey);
		//코멘트 보드 가져와서 수정된것 다시 보여줄 데이터 만들어주기
		List<Comment> cList = boardMapper.selectbyBId(comment.getBoardId());
		//for (Comment co : cList) { System.out.println(co); }		
		//현재글 가져오기
		List<Board> cnbList= boardService.getcurrentAndBeforeAndNextBoard(comment.getBoardId(), sortNum);
		Board currentBoard = cnbList.get(0);		
		//이전글, 다음글 제목가져오기
		Board beforeBoard = cnbList.get(1);
		Board nextBoard = cnbList.get(2);
		m.addAttribute("currentBoard", currentBoard);		
		m.addAttribute("currentboardId", comment.getBoardId());
		m.addAttribute("cList", cList);
		m.addAttribute("likecheck", likecheck);
		m.addAttribute("beforeBoard", beforeBoard);
		m.addAttribute("nextBoard", nextBoard);
		m.addAttribute("pNum", pNum);
		m.addAttribute("sortNum", sortNum);
		return "contentOneShow";
	}
	
	//코멘수정
		@PostMapping("/commentupdate")
		public String  updateComment(@ModelAttribute("userKey")int userKey,String pNum
				,Comment comment, Model m,@RequestParam(defaultValue = "1")String sNum) {
			int sortNum = Integer.parseInt(sNum);
			boardMapper.updateComment(comment);
			List<Comment> cList = boardMapper.selectbyBId(comment.getBoardId());
			//for (Comment co : cList) { System.out.println(co); }		
			//보드아이디와 유저키로 좋아요있는지 확인(true,false로 반환)
			String likecheck= boardService.likechecking(comment.getBoardId(), userKey);
			//현재글 가져오기
			List<Board> cnbList= boardService.getcurrentAndBeforeAndNextBoard(comment.getBoardId(), sortNum);
			Board currentBoard = cnbList.get(0);		
			//이전글, 다음글 제목가져오기
			Board beforeBoard = cnbList.get(1);
			Board nextBoard = cnbList.get(2);
			m.addAttribute("currentBoard", currentBoard);
			m.addAttribute("currentboardId", comment.getBoardId());
			m.addAttribute("cList", cList);
			m.addAttribute("likecheck", likecheck);
			m.addAttribute("beforeBoard", beforeBoard);
			m.addAttribute("nextBoard", nextBoard);
			m.addAttribute("pNum", pNum);
			m.addAttribute("sortNum", sortNum);
			return "contentOneShow";
		}
		
		//코멘삭제
		@RequestMapping("/deletecomment")
		public String  deleteComment(@ModelAttribute("userKey")int userKey,Model m
				,int pNum, int boardId, int commentId
				,@RequestParam(defaultValue = "1")int sortNum) {
			//선택된 코멘트 삭제, 코멘수 감소
			boardService.delComment(boardId, commentId);
			//보드아이디와 유저키로 좋아요있는지 확인(true,false로 반환)
			String likecheck= boardService.likechecking(boardId, userKey);
			//보드아이디에 있는 코멘트 가져오기
			List<Comment> cList = boardMapper.selectbyBId(boardId);
			
			//현재글 가져오기
			List<Board> cnbList= boardService.getcurrentAndBeforeAndNextBoard(boardId, sortNum);
			Board currentBoard = cnbList.get(0);		
			//이전글, 다음글 제목가져오기
			Board beforeBoard = cnbList.get(1);
			Board nextBoard = cnbList.get(2);
			m.addAttribute("currentBoard", currentBoard);
			m.addAttribute("currentboardId", boardId);
			m.addAttribute("cList", cList);
			m.addAttribute("likecheck", likecheck);
			m.addAttribute("beforeBoard", beforeBoard);
			m.addAttribute("nextBoard", nextBoard);
			m.addAttribute("pNum", pNum);
			m.addAttribute("sortNum", sortNum);
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
		
		//게시물보는곳에서 수정버튼 누를때
		@RequestMapping("/update")
		public String updateBoard(int boardId,Model m,int pNum, int sortNum) {
			//System.out.println("수정할 보드아이디"+boardId);
			//보드아이디로 보드정보를 객체에 받아옴
			Board board=boardMapper.selecOneBoard(boardId);
			//객체를 수정페이지에 넘겨줌
			m.addAttribute("board", board);
			m.addAttribute("pNum", pNum);			
			m.addAttribute("sortNum", sortNum);			
			return "updateBoard";
		}
		
		//게시물 수정후 글 수정 누르면 //바뀐 게시판 내용 보여주기
		@PostMapping("/contentOneShow")
		public String showupdateBoard(@ModelAttribute("userKey")int userKey,Model m
				,String pNum, Board board,@RequestParam(defaultValue = "1")int sortNum) {
			//디비에 업데이트 시키기
			boardMapper.updateBoard(board);
			//보드아이디와 유저키로 좋아요있는지 확인(true,false로 반환)
			String likecheck= boardService.likechecking(board.getBoardId(), userKey);
			//현재글 가져오기
			List<Board> cnbList= boardService.getcurrentAndBeforeAndNextBoard(board.getBoardId(), sortNum);
			Board currentBoard = cnbList.get(0);		
			//이전글, 다음글 제목가져오기
			Board beforeBoard = cnbList.get(1);
			Board nextBoard = cnbList.get(2);	
			
			//보드아이디에 있는 코멘트 가져오기
			List<Comment> cList = boardMapper.selectbyBId(board.getBoardId());
			m.addAttribute("currentBoard", currentBoard);
			m.addAttribute("beforeBoard", beforeBoard);
			m.addAttribute("nextBoard", nextBoard);
			m.addAttribute("currentboardId", board.getBoardId());
			m.addAttribute("cList", cList);
			m.addAttribute("likecheck", likecheck);
			m.addAttribute("pNum", pNum);
			m.addAttribute("sortNum", sortNum);
			return "contentOneShow";
		}
		
		//게시물 삭제버튼 누르면 게시물과 댓글 함께 삭제
		@PostMapping("/delboard")
		@ResponseBody
		public String deleteBoard(int boardId) {
			String result="";
			try {
				boardService.deleteBoard(boardId);
				result="delsuccess";
			} catch (Exception e) {
				e.printStackTrace();
				result="delfail";				
			}
			return result;
		}
}
