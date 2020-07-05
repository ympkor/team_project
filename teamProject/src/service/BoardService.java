package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		//보드아이디 맞는 코멘수 가소
		boardMapper.updatedecreaseBoardCommentCount(boardId);
		boardMapper.deleteComment(commentId);
	}
	
	//게시물 삭제시 코멘트랑 같이 삭제
	@Transactional
	public void delBoard(int userKey) {		
		boardMapper.deleteCommentbyuserkeyAll(userKey);
		boardMapper.deleteBoardbyuserkeyAll(userKey);
	}
}
