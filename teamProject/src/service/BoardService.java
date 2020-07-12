package service;

import java.util.ArrayList;
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
		
	//게시물 10개씩 보여줌
	public BoardListView showBoard(int currentPageNumber, int sortNum) {
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
		//보드리스트10개씩 가져오기
		List<Board> boardList=null;
		switch (sortNum) {
		case 1://등록순
			boardList=boardMapper.getBoardListPerPageSortbyregDate(firstRow, boardCntPerPage);
			break;
		case 2://조회순
			boardList=boardMapper.getBoardListPerPageSortbyhits(firstRow, boardCntPerPage);
			break;
		case 3://추천순
			boardList=boardMapper.getBoardListPerPageSortbylikes(firstRow, boardCntPerPage);
			break;
		case 4://댓글많은순
			boardList=boardMapper.getBoardListPerPageSortbycommentcount(firstRow, boardCntPerPage);
			break;
		}
		
		BoardListView blist = new BoardListView(boardTotalCnt, currentPageNumber, 
				boardList, boardCntPerPage, firstRow, pagePerPage, firstpagePerPage,
				sortNum);
		return blist;
	}
	
	//게시물 한개 내용 보여줄것, 전후 게시물
	public List<Board> getcurrentAndBeforeAndNextBoard(int boardId,int sortNum){
		List<Board> cbnList=new ArrayList<Board>();		
		switch (sortNum) {
		case 1://등록순
			cbnList.add(boardMapper.selecOneBoard(boardId));//보여줄거
			cbnList.add(boardMapper.getbeforeBoardSortByregDate(boardId));//이전게시물
			cbnList.add(boardMapper.getnextBoardSortByregDate(boardId));//다음게시물
			break;
		case 2://조회순
			cbnList.add(boardMapper.selecOneBoard(boardId));//보여줄거
			cbnList.add(boardMapper.getbeforeBoardSortByview(boardId));//이전
			cbnList.add(boardMapper.getnextBoardSortByview(boardId));//다음
			break;
		case 3://추천순
			cbnList.add(boardMapper.selecOneBoard(boardId));//보여줄거
			cbnList.add(boardMapper.getbeforeBoardSortBylikes(boardId));//이전
			cbnList.add(boardMapper.getnextBoardSortBylikes(boardId));//다음
			break;
		case 4://댓글많은순
			cbnList.add(boardMapper.selecOneBoard(boardId));//보여줄거
			cbnList.add(boardMapper.getbeforeBoardSortBycommentN(boardId));//이전
			cbnList.add(boardMapper.getnextBoardSortBycommentN(boardId));//다음
			break;
		}
		
		return cbnList;
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
	
	//게시물 삭제시 게시물에 달린 댓글,좋아요도 함께 지움
	@Transactional
	public void deleteBoard(int boardId) {
		//게시물삭제
		boardMapper.deleteBoardbyBoardId(boardId);
		//코멘삭제
		boardMapper.deleteCommentbyBoardIdAll(boardId);
		//좋아요삭제
		boardMapper.deletelikebyBoardIdAll(boardId);
	}
	
}
