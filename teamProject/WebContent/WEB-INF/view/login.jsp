<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<title>로그인창</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/loginJs.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.1/css/all.css" integrity="sha384-xxzQGERXS00kBmZW/6qxqJPyxW3UR0BPsL4c8ILaIWXva5kFi7TxkIIaMiKtqV1Q" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,600" rel="stylesheet">
<link rel="stylesheet" href="/css/loginCss.css">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
	<div id="login_container">
		<div id="header">
			<a id="title" href="/member/login">더조은가계부</a>
		</div>
		<div id="left">
			<form class="login_form">
				<h3 id="login_title">로그인</h3>
				<div><input class="login" type="text"  name="userId" placeholder="ID" maxlength="20" required></div>
				<div id="pw">
					<input class="login" type="password" name="password" placeholder="password" maxlength="16" required>
					<i class="far fa-eye"></i>
				</div>
				<div id="error"></div>
				<div class="search">
					<a id="search" href="/member/searchId" onclick="location.href='/member/searchId'">아이디찾기</a>
					<span id="bar"></span>
					<a id="search" href="/member/searchPw" onclick="location.href='/member/searchPw'">비밀번호찾기</a>
				</div>
				<div class="button">
					<input id="login" type="submit" value="로그인">
					<hr id="hr">
					<a id="join" href="/member/join">회원가입</a>
				</div>
			</form>
		</div>
		<div class="right">
			<!-- 부트스트랩에서 긁어옴 -->
		 	<script> $('.carousel').carousel({ interval: 5000 }) </script>
		 	<div id="demo" class="carousel slide" data-ride="carousel"> 
		 		<!-- 슬라이드쇼 -->
		 		<div class="carousel-inner">
		 			<div class="carousel-item active"> 
		 				<img class="d-block w-100" src="/img/main.jpg" alt="First slide"> 
		 				<div style="background-color: rgba( 255, 255, 255, 0.5 );" class="carousel-caption d-none d-md-block">
  							<h5 style ="font-family: 'Nanum Gothic Coding', monospace; color:#51565d; margin:-14px 0; background-color: rgba( 255, 255, 255, 0.5 );">
  								가계부페이지
  							</h5>
  						</div>
	 				</div> 
			 		<div class="carousel-item"> 
			 			<img class="d-block w-100" src="/img/graph.png" alt="Second slide"> 
			 			<div style="background-color: rgba( 255, 255, 255, 0.5 );" class="carousel-caption d-none d-md-block">
  							<h5 style ="font-family: 'Nanum Gothic Coding', monospace; color:#51565d; margin:-14px 0; background-color: rgba( 255, 255, 255, 0.5 );">
  								통계페이지
  							</h5>
  						</div>
			 		</div> 
			 		<div class="carousel-item"> 
			 			<img class="d-block w-100" src="/img/3.jpg" alt="Third slide">
			 			<div style="background-color: rgba( 255, 255, 255, 0.5 );" class="carousel-caption d-none d-md-block">
  							<h5 style ="font-family: 'Nanum Gothic Coding', monospace; color:#51565d; margin:-14px 0; background-color: rgba( 255, 255, 255, 0.5 );">
  								자산페이지
  							</h5>
  						</div> 
			 		</div>
			 		<div class="carousel-item"> 
			 			<img class="d-block w-100" src="/img/board.png" alt="Fourth slide"> 
  						<div style="background-color: rgba( 255, 255, 255, 0.5 );" class="carousel-caption d-none d-md-block">
  							<h5 style ="font-family: 'Nanum Gothic Coding', monospace; color:#51565d; margin:-14px 0; background-color: rgba( 255, 255, 255, 0.5 );">
  								게시판페이지
  							</h5>
  						</div>
			 		</div>  
			 		<!-- 슬라이드 쇼 끝 왼쪽 오른쪽 화살표 버튼  -->
			 		<a class="carousel-control-prev" href="#demo" data-slide="prev"> 
			 			<span class="carousel-control-prev-icon" aria-hidden="true"></span>
			 		</a> 
			 		<a class="carousel-control-next" href="#demo" data-slide="next"> 
			 			<span class="carousel-control-next-icon" aria-hidden="true"></span>
			 		</a>
	 			</div>
	 		</div>
		</div>
	</div>
</body>