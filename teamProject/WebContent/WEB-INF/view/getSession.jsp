<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 세션 임시 생성용 페이지 -->
	<script type="text/javascript">
		window.addEventListener('DOMContentLoaded', function() {
			alert('세션 생성 완료.' + '${userKey}');
			location.href='/main/getCal';
		});
	</script>
</body>
</html>