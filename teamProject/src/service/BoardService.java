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
}
