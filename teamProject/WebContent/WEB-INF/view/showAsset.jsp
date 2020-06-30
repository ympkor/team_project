<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 자산</title>
<script type="text/javascript" src="/js/searchIdJs.js">


</script>
<style>
</style>
</head>

<body>
	<div>
		<div>
			<h1>내 자산 현황</h1>
			<h1>총 ${sumAsset}원</h1>
			<br>
		</div>
		<c:forEach items="${aomList}" var="list">
			<div>
				${list.name} 님의 ${list.type}<br> ${list.assetsName}<br>
				${list.amount}원<br> ${list.memo}
				<div>
					<a href="edit?memAssetId=${list.memAssetId}">
					수정</a>
					<a href="delete?memAssetId=${list.memAssetId}" onclick="return confirm('정말 삭제하시겠습니까?')">
					삭제</a>
				</div>
			</div>
			<br>
		</c:forEach>

		<input type="button" name="addAsset" value="내 자산 추가" onClick="location.href='add'">
	</div>
	<div>
		
		${newsList}

		<%-- <c:forEach var="news" items="${newsList}">
			${news.title}<br>
			${news.pubDate}<br>
			${news.description}<br>
			${news.link}<br> %>
		</c:forEach> --%>
		
	</div>

</body>
</html>