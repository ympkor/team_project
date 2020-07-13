<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	var result = confirm("로그아웃을 하시겠습니까?");
	if(result){
		location.href="/member/logout";
	} else {
		history.go(-1);
	}
</script>
</head>
<body>
	
</body>
</html>