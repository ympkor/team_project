<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/boardOneContentJS.js?1"></script>
<link rel="stylesheet" href="/css/boardOneContent.css?1">
<link rel="stylesheet" href="/css/topMenu.css?asd=2">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,600" rel="stylesheet">
</head>
<body>
<div class="wrapper">
	<!-- 상단 메뉴 부분 -->
	<header class="topmenu">
		<div class="grid_header">
			<div class="basic"><a id=mainlink href="/main/getCal">MAIN</a></div>
			<div class="statistics"><a id=staticlink href="/statistics/show">GRAPH</a></div>
			<div class="assest"><a id=assetlink href="/asset/view">ASSETS</a></div>
			<div class="board"><a id=boardlink href="/board/show">BOARD</a></div>
			<div class="gomypage"><button class="gomypage">MYPAGE</button></div>
			<div class="gologout"><button class="gologout">LOGOUT</button></div>
		</div>
	</header>
<div id="middlecontent">
<table>
	<tbody>
		<tr><td class="boardtitlehead">${currentBoard.title }</td><tr>
		<tr><td class="writeinfo"><span>작성자 : ${currentBoard.writer }</span> 
				<span>작성일  : <c:set var="now" value="<%=new java.util.Date()%>" />
			<c:set var="nowdate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set>
			<c:choose>
			 	<c:when test="${currentBoard.regDate.toString().substring(0,10).equals(nowdate)}">${currentBoard.regDate.toString().substring(11,16)}</c:when>
				<c:otherwise>${currentBoard.regDate.toString().substring(0,10)}</c:otherwise>	
			</c:choose></span>
			<span>좋아요: <span class="likesNum">${currentBoard.likes}</span></span>
			<span>조회수: ${currentBoard.hits}</span>
			<div class="udbutton">
				<c:if test="${currentBoard.userKey==userKey }"><button class="boardupdate">수정</button><button class="boarddelete">삭제</button></c:if>
				<button class="showboardList">목록보기</button>
			</div>
		</td></tr>		
		<tr><td class="boardcontent">
				<div class="contentdiv"><pre class="contentpre">${currentBoard.content }</pre></div>
		</td></tr>
		<tr class="likeandUandD"><td>
			<c:if test="${userKey!=null}">	
			<div class="likeandcomment" ><button class="commentwriteshow">댓글쓰기</button> <label>좋아요<input class="likecheck" type="checkbox" value="like"></label>
			</div>
			</c:if>	
		</td></tr>
	</tbody>
</table>
<div class="commentwritediv">
<form class="commentwriteinput" action="/board/commentwrite" method="post">
	<input type="hidden" name="pNum" value="${pNum}">
	<input type="hidden" name="sNum" value="${sortNum}">
	<input type="hidden" name="userKey" value="<%=session.getAttribute("userKey")%>">
	<input type="hidden" name="boardId" value="${currentboardId}">
	<TEXTAREA maxlength="150" class="writeboardcontent" name="comment" COLS=30 ROWS=3 required="required"></TEXTAREA>
	 <span id="counter">0/150</span>
	<div class="regcommentdiv"><input class="regCommentsubmit" type="submit" value="댓글등록">
	</div>
</form>
</div>

<div class="commentListshow">
<c:forEach var="co" items="${cList}">
	<div class="commentshow">작성자: <span class="commentwriter">${co.commentWriter}</span>&nbsp;
		<span class="commnetregDate">
		<!-- 오늘 날짜를 nowdate 에 저장 해서 같은 날이면 시간을 불러오고 다른날이면 날짜만 불러오게-->
			<c:set var="now" value="<%=new java.util.Date()%>" />
			<c:set var="nowdate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set>
			<c:choose>
			 	<c:when test="${co.regDate.toString().substring(0,10).equals(nowdate)}">${co.regDate.toString().substring(11,16)}</c:when>
				<c:otherwise>${co.regDate.toString().substring(0,10)}</c:otherwise>	
			</c:choose>
	</span>	
	<div class="commentcontent"><pre>${co.comment}</pre></div>			
	
		<c:if test="${co.userKey==userKey }"><span class="commentUDbuttons"><button class="commentupdate" name="${co.commentId}">수정</button><button class="commentdelete" name="${co.commentId}">삭제</button></span></c:if>
	</div>
</c:forEach> 
</div>
<div class="boardlisttwo">
	<c:if test="${ beforeBoard!=null}"><div class="beforeboard">이전글: <a href="/board/contentOneShow?boardId=${beforeBoard.boardId}&pNum=${pNum}&sortNum=${sortNum}">${beforeBoard.title}</a><br></div></c:if>
	<c:if test="${ nextBoard!=null}"><div class="nextboard">다음글: <a href="/board/contentOneShow?boardId=${nextBoard.boardId}&pNum=${pNum}&sortNum=${sortNum}">${nextBoard.title}</a><br></div></c:if>
</div>
</div><!--middlecontent끝  -->
<div class="gotopdiv" ><a class="gotop" href="#middlecontent" title="위로"></a></div>
</div><!-- wrapper끝  -->
<script type="text/javascript">
var boardId= ${currentBoard.boardId};
var userKey =<%=session.getAttribute("userKey")%>;
var likecheck =${likecheck};
var pNum=${pNum};
var sNum=${sortNum}
</script>
</body>
</html>