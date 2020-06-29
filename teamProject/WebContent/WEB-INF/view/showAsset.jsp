<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 자산</title>
<style>
</style>
</head>

<body style="background: #FFDF24">
	<div
		style="width =: 520px; height: 100%; text-align: center; font-size: 20px; float: left;">
		<div style="height: 150px;">
			<h1>내 자산 현황</h1>
			<h1>총 ${sumAsset}원</h1>
			<br>
		</div>
		<c:forEach items="${aomList}" var="list">
			<div
				style="width: 500px; height: 100px; border: 1px solid black; border-radius: 5px; margin: 5px; background: yellow; opacity: 0.8; padding: 10px;">
				${list.name} 님의 ${list.type}<br> ${list.assetsName}<br>
				${list.amount}원<br> ${list.memo}
				<div style="text-align: right; margin-top: -20px;">
					<input type="button" name="editAsset" value="수정"> <input
						type="button" name="deleteAsset" value="삭제">
				</div>
			</div>
			<br>
		</c:forEach>

		<input
			style="opacity: 0.5; border-radius: 5px; background: black; color: white; font-size: 20px; width: 520px; height: 100px; margin: 5px; padding: 10px;"
			type="button" name="addAsset" value="내 자산 추가" onClick="location.href='add'">
	</div>
	<div>

		<%-- ${newsList} --%>

		<%-- <c:forEach var="news" items="${newsList}">
			${news.title}<br>
			${news.pubDate}<br>
			${news.description}<br>
			${news.link}<br>
		</c:forEach> --%>

	</div>

</body>
</html>