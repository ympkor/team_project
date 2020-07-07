package dto;

import java.util.List;



public class BoardListView {
	private int boardTotalCnt;//총 게시물 개수
	private int currentPageNumber;//현재 페이지 번호
	private List<Board> boardList;// 한 화면에 보여줄 게시물 리스트
	private int pageTotalCnt;//전체페이지 개수
	private int boardCntPerPage;//페이지당 메세지 개수
	private int firstRow;//화면상 맨 위에 있는 로우번호
	private int pagePerPage; //한 화면에서 보여줄 페이지 개수
	private int firstpagePerPage; //페이지에서 보여줄 첫번째 페이지 숫자
	private int firstshowBoardNumber; //게시물처음시작번호
	
	
	public BoardListView() { }
	
	public BoardListView(int boardTotalCnt, int currentPageNumber, 
			List<Board> boardList,int boardCntPerPage, int firstRow,
			int pagePerPage, int firstpagePerPage) {
		this.boardTotalCnt = boardTotalCnt;
		this.currentPageNumber = currentPageNumber;
		this.boardList = boardList;
		
		this.boardCntPerPage = boardCntPerPage;
		this.firstRow = firstRow;
		this.pagePerPage = pagePerPage;
		this.firstpagePerPage = firstpagePerPage;
		
			//전체 페이지 개수 구하기
			if(boardTotalCnt>0) {
				pageTotalCnt=boardTotalCnt/boardCntPerPage;
				if(boardTotalCnt%boardCntPerPage != 0) {
					pageTotalCnt++;
				}
			}else {
					pageTotalCnt=1;
			}
			
		firstshowBoardNumber=boardTotalCnt-10*(currentPageNumber-1);
			
	}

	public int getBoardTotalCnt() {
		return boardTotalCnt;
	}

	public void setBoardTotalCnt(int boardTotalCnt) {
		this.boardTotalCnt = boardTotalCnt;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public List<Board> getBoardList() {
		return boardList;
	}

	public void setBoardList(List<Board> boardList) {
		this.boardList = boardList;
	}

	public int getPageTotalCnt() {
		return pageTotalCnt;
	}

	public void setPageTotalCnt(int pageTotalCnt) {
		this.pageTotalCnt = pageTotalCnt;
	}

	public int getBoardCntPerPage() {
		return boardCntPerPage;
	}

	public void setBoardCntPerPage(int boardCntPerPage) {
		this.boardCntPerPage = boardCntPerPage;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getPagePerPage() {
		return pagePerPage;
	}

	public void setPagePerPage(int pagePerPage) {
		this.pagePerPage = pagePerPage;
	}

	public int getFirstpagePerPage() {
		return firstpagePerPage;
	}

	public void setFirstpagePerPage(int firstpagePerPage) {
		this.firstpagePerPage = firstpagePerPage;
	}

	public int getFirstshowBoardNumber() {
		return firstshowBoardNumber;
	}

	public void setFirstshowBoardNumber(int firstshowBoardNumber) {
		this.firstshowBoardNumber = firstshowBoardNumber;
	}

	
}
