<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>탈퇴 결과창</title>
</head>
<body>
<c:choose>
	<c:when test="${result=='삭제성공' }"><%session.invalidate();%>
		<script type="text/javascript">
			alert("탈퇴에 성공하셨습니다. 확인버튼을 누르시면 로그인창으로 이동됩니다.");
			window.location.replace("/member/login");
		</script>
	 </c:when>
	<c:otherwise>
		<script type="text/javascript">
			alert("탈퇴 실패하셨습니다. 확인버튼을 누르시면 마이페이지창으로 이동됩니다.");
			window.location.replace("/member/mypage");
		</script>
	</c:otherwise>
</c:choose>


</body>
</html>