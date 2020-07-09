package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dto.Board;
import dto.BoardListView;
import dto.Comment;
import mapper.BoardMapper;

@Service
public class BoardService {
	@Autowired
	BoardMapper boardMapper;

	//코멘쓰면 보드아이디에 코멘수 증가,코멘트 인서트
	@Transactional
	public void regComment(Comment comment) {
		int boardId = comment.getBoardId();
		//보드아이디 맞는 코멘수 증가
		boardMapper.updateincreaseBoardCommentCount(boardId);
		boardMapper.writeComment(comment);		
	}
	//코멘삭제 보드아이디에 코멘수 감소,코멘트 삭제
	@Transactional
	public void delComment(int boardId, int commentId ) {		
		//보드아이디 맞는 코멘수 감소
		boardMapper.updatedecreaseBoardCommentCount(boardId);
		boardMapper.deleteComment(commentId);
	}
	
	//게시물 삭제시 코멘트랑 같이 삭제
	@Transactional
	public void delBoard(int userKey) {		
		boardMapper.deleteCommentbyuserkeyAll(userKey);
		boardMapper.deleteBoardbyuserkeyAll(userKey);
	}
	
	//게시물 10개씩 보여줌
	public BoardListView showBoard(int currentPageNumber) {
		int boardTotalCnt = boardMapper.getBoardTotalCnt();
		int boardCntPerPage=10;
		int firstRow=0;
		if(currentPageNumber>0) {
			firstRow = (currentPageNumber-1)*boardCntPerPage;				
		}
		int pageTotalCnt = 1;
		if(boardTotalCnt>0) {
			pageTotalCnt=boardTotalCnt/boardCntPerPage;
			if(boardTotalCnt%boardCntPerPage != 0) {
				pageTotalCnt++;
			}
		}else {
			pageTotalCnt=1;
		}
		
		int pagePerPage =5;
		int block = (currentPageNumber-1)/5;
		int firstpagePerPage = block*5+1;
		if(pageTotalCnt - currentPageNumber >= 5) {
			pagePerPage = 5;
		}else {
			pagePerPage = pageTotalCnt - firstpagePerPage;			
		}
		List<Board> boardList=boardMapper.getBoardListPerPage(firstRow, boardCntPerPage);
		BoardListView blist = new BoardListView(boardTotalCnt, 
				currentPageNumber, boardList, boardCntPerPage, 
				firstRow, pagePerPage,firstpagePerPage);
		
		return blist;
	}
	//좋아요 누르면 게시물 좋아요수 하나 올리고 디비에 저장하기
	@Transactional
	public void likeupdate(int boardId, int userKey) {
		int check =boardMapper.checklikeByUIdBId(boardId, userKey);
		if(check!=1) {
			//좋아요수 올리고
			boardMapper.increaseBoardlike(boardId);
			//유저가 좋아요한 보드 저장하기
			boardMapper.regLikebyUIdBId(boardId, userKey);			
		}
	}
	//좋아요 취소누르면 게시물 좋아요수 하나 줄이고 디비에 false로 수정
	@Transactional
	public void cancellikeupdate(int boardId, int userKey) {
		int check =boardMapper.checklikeByUIdBId(boardId, userKey);
		if(check==1) {
			//좋아요수 감소
			boardMapper.decreaseBoardlike(boardId);
			//유저가 좋아요한 보드정보 삭제
			boardMapper.cancelLikeByUIdBId(boardId, userKey);		
		}
	}
	
	//보드아이디와 유저키로 좋아요했는지 여부 true,false로 반환
	public String likechecking(int boardId, int userKey) {
		int checklike=boardMapper.checklikeByUIdBId(boardId, userKey);
		String likecheck="";
		if (checklike==1) {
			likecheck="true";
		}else {
			likecheck="false";
		}
		return likecheck;
	}
	
}
