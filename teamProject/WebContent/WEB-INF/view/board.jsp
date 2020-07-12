<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/boardJS.js?ver=2"></script>
<link rel="stylesheet" href="/css/board.css?ver=2">
<link rel="stylesheet" href="/css/topMenu.css?asd=2">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,600" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
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
<div id="boardcontent">
<div class="boardtitle">FreeBoard</div>

<div class="boardcontentinsidediv">
<div class="boardlistdiv">
<table class="boardlist">
	<colgroup>
		<col width="4%">
		<col width="58%">
		<col width="15%">
		<col width="15%">
		<col width="4%">
		<col width="4%">
	</colgroup>
	<thead>
		<tr ><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>추천</th><th>조회</th></tr>
	</thead>
	<tbody>
		<c:if test="${bList.boardTotalCnt==0}">
			<tbody><tr><td class="nocontent" colspan="6">게시물이 없습니다</td></tr></tbody>
		</c:if>
		<c:forEach var="b" items="${bList.boardList}" varStatus="vs">
		<tbody onclick="showContent(this)" class="${b.boardId}">						
		<tr class="boardtitlehead"><td>${bList.firstshowBoardNumber-vs.index}</td>
			<td class="titles"><span class="innertitle">${b.title}</span>
				<span class="commentNumtotal"><c:if test="${b.commentCount>0}">[<span class="commentcount">${b.commentCount }</span>]</c:if>
				</span>
			</td>
		<td>${b.writer }</td>
		<td class="regDate">
			<!-- 오늘 날짜를 nowdate 에 저장 해서 같은 날이면 시간을 불러오고 다른날이면 날짜만 불러오게-->
			<c:set var="now" value="<%=new java.util.Date()%>" />
			<c:set var="nowdate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set>
			<c:choose>
			 	<c:when test="${b.regDate.toString().substring(0,10).equals(nowdate)}">${b.regDate.toString().substring(11,16)}</c:when>
				<c:otherwise>${b.regDate.toString().substring(0,10)}</c:otherwise>	
			</c:choose>			
		</td>
		<td>${b.likes }</td><td>${b.hits}</td></tr>
		<%-- <tr class="boardID" ><td colspan="6"><div class="contentdiv" >${b.boardId}</div></td></tr> --%>
		</tbody> 
		</c:forEach>
	</tbody>
</table>
</div>
<div id="writeBoarddiv"><button id="writeBoard">글쓰기</button></div>
<div class="boardpagenation">
<!-- 페이지가 6개 이상일때부터  -->
<!-- 첫번째블럭 : 이전없음, 총보여줄 페이지 개수는 5개 -->
 <c:if test="${bList.pageTotalCnt>5}">
<c:if test="${bList.currentPageNumber<=5}">
	<c:forEach var="p" begin="1" end="5" varStatus="vs">
		<c:choose>
		<c:when test="${bList.currentPageNumber==p}">
			<a class="currentP" href="/board/show?pNum=${p}">[${p}]</a>
		</c:when>
		<c:otherwise><a href="/board/show?pNum=${p}">[${p}]</a></c:otherwise>
		</c:choose>
		<c:if test="${vs.last==true}"><a href="/board/show?pNum=${p+1}">다음&gt;</a></c:if>
	</c:forEach>	
</c:if>
<!-- 가운데블럭 : 이전, 다음 있음 총보여줄 페이지 개수는 5개-->
<c:if test="${bList.currentPageNumber>5 && 
	(bList.pageTotalCnt-bList.firstpagePerPage)>=5}">	
	<c:forEach var="p" begin="${bList.firstpagePerPage}" end="${bList.firstpagePerPage+4}" varStatus="vs">
		<c:if test="${vs.first==true}"><a href="/board/show?pNum=${vs.begin-1}">&lt;이전</a></c:if>
		<c:choose>
		<c:when test="${bList.currentPageNumber==p}">
			<a class="currentP" href="/board/show?pNum=${p}">[${p}]</a>
		</c:when>
		<c:otherwise><a href="/board/show?pNum=${p}">[${p}]</a></c:otherwise>
		</c:choose>
		<c:if test="${vs.last==true}"><a href="/board/show?pNum=${p+1}">다음&gt;</a></c:if>
	</c:forEach>	
</c:if>
<!-- 마지막블럭 : 보여줄 페이지가 5개 이내, 이전있음, 다음 없음-->
<c:if test="${bList.currentPageNumber> 5 &&
  (bList.pageTotalCnt-bList.firstpagePerPage)<5}">     
	<c:forEach var="p" begin="${bList.firstpagePerPage}" end="${bList.firstpagePerPage+bList.pagePerPage}" varStatus="vs">
		<c:if test="${ vs.first==true}"><a href="/board/show?pNum=${vs.begin-1}">&lt;이전</a></c:if>
		<c:choose>
		<c:when test="${bList.currentPageNumber==p}">
			<a class="currentP" href="/board/show?pNum=${p}">[${p}]</a>
		</c:when>
		<c:otherwise><a href="/board/show?pNum=${p}">[${p}]</a></c:otherwise>
		</c:choose>
	</c:forEach>	
</c:if>
</c:if>
<!-- 페이지가 5개 이내 이전,다음 없음 -->
<c:if test="${bList.pageTotalCnt<=5}">
	<c:forEach var="p" begin="1" end="${bList.pageTotalCnt}">
		<c:choose>
		<c:when test="${bList.currentPageNumber==p}">
			<a class="currentP" href="/board/show?pNum=${p}">[${p}]</a>
		</c:when>
		<c:otherwise><a href="/board/show?pNum=${p}">[${p}]</a></c:otherwise>
		</c:choose>	
	</c:forEach>
</c:if> 
</div>
</div>

</div>
</div>
<script type="text/javascript">
var pNum = ${bList.currentPageNumber};
</script>
</body>