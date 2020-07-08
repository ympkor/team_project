<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<title>로그인창</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/loginJs.js?1"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.1/css/all.css" integrity="sha384-xxzQGERXS00kBmZW/6qxqJPyxW3UR0BPsL4c8ILaIWXva5kFi7TxkIIaMiKtqV1Q" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,600" rel="stylesheet">
<link rel="stylesheet" href="/css/loginCss.css">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;800&display=swap" rel="stylesheet">
</head>
<body>
	<div id="login_container">
		<div id="header">
			더조은가계부
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
		 	<div class="wrap-slider">
    			<div class="css-slider">
		        	<div class="mask">
						<ul>
							<li class="firstanimation first">
								<img src="/img/1.jpg"/>
								<!-- <div class="tooltip"></div> -->
							</li>
							<li class="secondanimation second">
								<img src="/img/2.jpg"/>
								<!-- <div class="tooltip"></div> -->
							</li>
							<li class="thirdanimation third">
								<img src="/img/3.jpg"/>
								<!-- <div class="tooltip"></div> -->
							</li>
							<li class="fourthanimation fourth">
								<img src="/img/4.jpg"/>
								<!-- <div class="tooltip"></div> -->
							</li>
						</ul>
		            </div>
	           		<div class="progress-bar"></div>
        		</div>
    		</div>
		</div>
	</div>
</body>