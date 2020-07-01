<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% int count = 0; %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>가계부</title>
	<link rel="stylesheet" href="/css/main/main_modal.css">
	<link rel="stylesheet" href="/css/main/mainTestCss.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript" src="/js/main/main_modal.js"></script>
	<script type="text/javascript" src="/js/main/mainTestJs.js"></script>
</head>

<body>
	<!-- 모달 창 div-->
	<div id="modal" class="modal hidden">
		<!-- 모달창이 열렸을 때, 모달창 외부를 클릭하면 모달창이 닫히는 기능을 넣고 싶다. 그 기능을 위한 layout 만들어놓기 -->
		<div class="modal_overlay"></div>
		<!-- 모달이 열렸을때 사용자에게 보여줄 content가 담길 div이다 -->
		<div class="modal_content">
			<!-- insert, expense용 form을 각각 만들어놓고, 사용자가 수입/지출 탭을 선택함에 따라 해당되는 input을 보여주자 -->
			<!-- 여긴 수입 Form -->
			<form id="update_income_form" class="hidden">
			<!-- 사용자가 선택할 수 있는 수입/지출 카테고리 -->
			<div class="update_category_container">
				<div class="update_category">
					<label for="update_income">수입</label>
					<input type="radio" id="update_income_category_income" name="category" value="1" onchange="iFSwitchIFtoEF()">
					<label for="update_expense">지출</label>
					<input type="radio" id="update_income_category_expense" name="category" value="2" onchange="iFSwitchEFtoIF()">
				</div>
			</div>
				<div id="update_form_container">
					<input type="number" id="update_income_id" name="incomeId" class="hidden">
					<!-- 날짜 선택 창 -->
					<div class="update_date">
						<label for="update_income_date">날짜</label>
						<input type="date" id="update_income_date" class="update_income_date" name="incomeDate" value="" required="required" min="1950-01-01" max="2199-12-12">
					</div>
					<!-- assets_id 선택 창. 사용자에겐 자산 명으로 보여주자 -->
					<div class="update_assets">
						<label for="update_assets_income">자산</label>
						<select id="update_assets_income" name="assetsId" required="required">
							<option id="update_assets_income_selected" value="" selected>선택하세요</option>
							<!-- 로그인한 사용자의 자산 항목을 보여주자 -->
							<c:forEach var="aaom" items="${aaomList}">
								<script>
									document.querySelector('#update_assets_income').innerHTML += '<option value=${aaom.assetsId}>${aaom.assetsName}</option>';
								</script>
							</c:forEach>
						</select>
						<!-- 보유중인 자산 항목을 선택하면, 그 assets_id에서 보유중인 자산들 보여주기 -->
						<select id="update_aom_income" name="memAssetId" required="required">
							<option id="update_aom_income_selected" value="" selected disabled>선택하세요</option>
						</select>
						<!-- update_assets_income이 변경되면 그 aom에서 user_key와 assets_id로 검색해서 그 값을 뿌려주는 기능을 넣어주자 -->
						<script>
							document.querySelector('#update_assets_income').addEventListener('change', function() {
								let assetsId = document.querySelector('#update_assets_income').value;
								showUpdateIncomeAom(assetsId);
							});
						</script>
					</div>
					<!-- income_ic_id 선택 창. 사용자에겐 카테고리 명으로 보여주자 -->
					<div class="update_income_category">
						<label for="update_income_category">분류</label>
						<select id="update_income_category" name="icId" required="required">
							<option id="update_income_category_selected" value="" selected>선택하세요</option>
							<c:forEach var="ic" items="${icList}">
								<script>
									document.querySelector('#update_income_category').innerHTML += '<option value=${ic.icId}>${ic.icName}</option>';
								</script>
							</c:forEach>
						</select>
						<select name="ecId" id="update_income_category_switch_expense" required class="hidden">
							<option value="0" selected disabled>선택하세요</option>
							<c:forEach var="ec" items="${ecList}">
								<script>
									document.querySelector('#update_income_category_switch_expense').innerHTML += '<option value=${ec.ecId}>${ec.ecName}</option>';
								</script>
							</c:forEach>
						</select>
					</div>
					<!-- income_amount입력 창 -->
					<div class="update_income_amount">
						<label for="update_income_amount">금액</label>
						<input type="number" id="update_income_amount" name="amount" required min="-999999999" max="999999999">
					</div>
					<!-- income_memo 입력 창 -->
					<div class="update_income_memo">
						<label for="update_income_memo">내용</label>
						<input type="text" id="update_income_memo" name="memo" maxlength="22">
					</div>
					<!-- 입력하기 버튼 -->
					<div class="update_income_button">
						<button type="submit" id="update_income_button">저장</button>
						<button type="button" class="close_modal">닫기</button>
					</div>
				</div>
			</form>

			<!-- 여긴 지출 Form -->
			<form id="update_expense_form" class="hidden">
				<div class="update_category">
					<label for="update_income">수입</label>
					<input type="radio" id="update_expense_category_income" name="category" value="1" onchange="eFSwitchIFtoEF()">
					<label for="update_expense">지출</label>
					<input type="radio" id="update_expense_category_expense" name="category" value="2" onchange="eFSwitchEFtoIF()">
				</div>
				<div id="update_form_container">
					<input type="number" id="update_expense_id" name="expenseId" class="hidden">
					<!-- 날짜 선택 창 -->
					<div class="update_date">
						<label for="update_expense_date">날짜</label>
						<input type="date" id="update_expense_date" class="update_expense_date" name="expenseDate" value=""
							required="required" min="1950-01-01" max="2199-12-12">
					</div>
					<!-- assets_id 선택 창. 사용자에겐 자산 명으로 보여주자 -->
					<div class="update_assets">
						<label for="update_assets_expense">자산</label>
						<select id="update_assets_expense" name="assetsId" required="required">
							<option id="update_assets_expense_selected" value="" selected>선택하세요</option>
							<!-- 로그인한 사용자의 자산 항목을 보여주자 -->
							<c:forEach var="aaom" items="${aaomList}">
								<script>
									document.querySelector('#update_assets_expense').innerHTML += '<option value=${aaom.assetsId}>${aaom.assetsName}</option>';
								</script>
							</c:forEach>
						</select>
						<!-- 보유중인 자산 항목을 변경하면 그 자산항목의 id로 검색해서 AOM을 보여주자 -->
						<select id="update_aom_expense" name="memAssetId" required="required">
							<option id="update_aom_expense_selected" value="" selected disabled>선택하세요</option>
						</select>
						<!-- update_assets_income이 변경되면 그 aom에서 user_key와 assets_id로 검색해서 그 값을 뿌려주는 기능을 넣어주자 -->
						<script>
							document.querySelector('#update_assets_expense').addEventListener('change', function() {
								let assetsId = document.querySelector('#update_assets_expense').value;
								showUpdateExpenseAom(assetsId);
							});
						</script>
					</div>
					<!-- income_ic_id 선택 창. 사용자에겐 카테고리 명으로 보여주자 -->
					<div class="update_expense_category">
						<label for="update_expense_category">분류</label>
						<select id="update_expense_category" name="ecId" required="required">
							<option id="update_expense_category_selected" value="0" selected>선택하세요</option>
							<c:forEach var="ec" items="${ecList}">
								<script>
									document.querySelector('#update_expense_category').innerHTML += '<option value=${ec.ecId}>${ec.ecName}</option>';
								</script>
							</c:forEach>
						</select>
						<select name="icId" id="update_expense_category_switch_income" class="hidden">
							<option value="0" selected disabled>선택하세요</option>
							<c:forEach var="ic" items="${icList}">
								<script>
									document.querySelector('#update_expense_category_switch_income').innerHTML += '<option value=${ic.icId}>${ic.icName}</option>';
								</script>
							</c:forEach>
						</select>
					</div>
					<!-- income_amount입력 창 -->
					<div class="update_expense_amount">
						<label for="update_expense_amount">금액</label>
						<input type="number" id="update_expense_amount" name="amount" required min="-999999999" max="999999999">
					</div>
					<!-- income_memo 입력 창 -->
					<div class="update_expense_memo">
						<label for="update_expense_memo">내용</label>
						<input type="text" id="update_expense_memo" name="memo" maxlength="22">
					</div>
					<!-- 입력하기 버튼 -->
					<div class="update_expense_button">
						<button type="submit" id="update_expense_button">저장</button>
						<button type="button" class="close_modal">닫기</button>
					</div>
				</div>
			</form>

			<!-- 수입 삭제 폼 -->
			<form id="delete_income_Form" class="hidden">
				<div class="delete_income_category">
					<label for="delete_income_category">수입</label>
					<input type="text" id="delete_income_category" name="type" value="수입" readonly>
				</div>
				<div id="delete_form_container">
					<input type="number" id="delete_income_id" name="incomeId" class="hidden">
					<!-- 날짜 선택 창 -->
					<div class="delete_income_date">
						<label for="delete_income_date">날짜</label>
						<input type="date" id="delete_income_date" name="incomeDate" value="" readonly>
					</div>
					<!-- assets_id 선택 창. 사용자에겐 자산 명으로 보여주자 -->
					<div class="delete_income_assets">
						<label for="delete_income_assets">자산</label>
							<select id="delete_income_assets_id" name="assetsId" aria-readonly="true">
								<option id="delete_income_assets_id_selected" value="" selected></option>
							</select>
							<select id="delete_income_memAssetId" name="memAssetId" required>
								<option id="delete_income_memAssetId_selected" value="" selected disabled></option>
							</select>
					</div>
					<!-- income_ic_id 선택 창. 사용자에겐 카테고리 명으로 보여주자 -->
					<div class="delete_income_category">
						<label for="delete_income_category_id">분류</label>
						<select id="delete_income_category_id" name="icId" aria-readonly="true">
							<option id="delete_income_category_id_selected" value="" selected></option>
						</select>
					</div>
					<!-- income_amount입력 창 -->
					<div class="delete_income_amount">
						<label for="delete_income_amount">금액</label>
						<input type="number" id="delete_income_amount" name="amount" readonly>
					</div>
					<!-- income_memo 입력 창 -->
					<div class="delete_income_memo">
						<label for="delete_income_memo">내용</label>
						<input type="text" id="delete_income_memo" name="memo" readonly>
					</div>
					<!-- 입력하기 버튼 -->
					<div class="update_expense_button">
						<button type="submit" id="detele_income_button_confirm">삭제</button>
						<button type="button" class="close_modal">닫기</button>
					</div>
				</div>
			</form>

			<!-- 지출 삭제 폼 -->
			<form id="delete_expense_Form" class="hidden">
				<div class="delete_expense_category">
					<label for="delete_expense_category">지출</label>
					<input type="text" id="delete_expense_category" name="type" value="지출" readonly>
				</div>
				<div id="delete_form_container">
					<input type="number" id="delete_expense_id" name="expenseId" class="hidden">
					<!-- 날짜 선택 창 -->
					<div class="delete_expense_date">
						<label for="delete_expense_date">날짜</label>
						<input type="date" id="delete_expense_date" name="expenseDate" value="" readonly>
					</div>
					<!-- assets_id 선택 창. 사용자에겐 자산 명으로 보여주자 -->
					<div class="delete_expense_assets">
						<label for="delete_expense_assets">자산</label>
							<select id="delete_expense_assets_id" name="assetsId" aria-readonly="true">
								<option id="delete_expense_assets_id_selected" value="" selected></option>
							</select>
							<select id="delete_expense_memAssetId" name="memAssetId" required>
								<option id="delete_expense_memAssetId_selected" value="" selected disabled></option>
							</select>
					</div>
					<!-- expense_ic_id 선택 창. 사용자에겐 카테고리 명으로 보여주자 -->
					<div class="delete_expense_category">
						<label for="delete_expense_category_id">분류</label>
						<select id="delete_expense_category_id" name="ecId" aria-readonly="true">
							<option id="delete_expense_category_id_selected" value="" selected></option>
						</select>
					</div>
					<!-- expense_amount입력 창 -->
					<div class="delete_expense_amount">
						<label for="delete_expense_amount">금액</label>
						<input type="number" id="delete_expense_amount" name="amount" readonly>
					</div>
					<!-- expense_memo 입력 창 -->
					<div class="delete_expense_memo">
						<label for="delete_expense_memo">내용</label>
						<input type="text" id="delete_expense_memo" name="memo" readonly>
					</div>
					<!-- 입력하기 버튼 -->
					<div class="update_expense_button">
						<button type="submit" id="detele_expense_button_confirm">삭제</button>
						<button type="button" class="close_modal">닫기</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="grid_container">
		<!-- 상단 메뉴 부분 -->
		<header class="grid_header">
			<div class="topmenu">
				<div class="basic"><a href="/main/getCal">가계부</a></div>
				<div class="statistics"><a href="/statistics/show">통계</a></div>
				<div class="assest"><a href="/asset/view">자산</a></div>
				<div class="board"><a href="/board/show">게시판</a></div>
			</div>
		</header>

		<!-- 측면 메뉴 부분 -->
		<aside class="grid_aside">
			<div class="mainPage_sideMenuContainer">
				<hr>
				<h3>측면 메뉴 DIV</h3>
				<hr>
				<!-- 사용자가 정보를 입력하면 Insert해주는 Form을 만들자. ajax방식 -->
				<!-- 사용자가 선택할 수 있는 수입/지출 카테고리 -->
				<div class="insert_category_container">
					<div class="insert_category">
						<label for="insert_income">수입</label>
						<input type="radio" id="insert_income" name="insertCategory" value="income" onchange="showIncomeForm()">
						<!-- 지출은 미리 선택되게 해놓기 -->
						<label for="insert_expense">지출</label>
						<input type="radio" id="insert_expense" name="insertCategory" value="expense" onchange="showExpenseForm()"
							checked>
					</div>
				</div>
				<!-- insert, expense용 form을 각각 만들어놓고, 사용자가 수입/지출 탭을 선택함에 따라 해당되는 input을 보여주자 -->
				<!-- 여긴 수입 Form -->
				<form id="insert_income_form" class="hidden">
					<div id="insert_form_container">
						<!-- 날짜 선택 창 -->
						<div class="insert_date">
							<label for="insert_income_date">날짜</label>
							<input type="date" id="insert_income_date" class="insert_income_date" name="incomeDate" value=""
								required="required" min="1950-01-01" max="2199-12-12">
						</div>
						<!-- assets_id 선택 창. 사용자에겐 자산 명으로 보여주자 -->
						<div class="insert_assets">
							<label for="insert_assets_income">자산</label>
							<select id="insert_assets_income" name="assetsId" required="required">
								<option value="" disabled selected>선택하세요</option>
								<!-- 로그인한 사용자의 자산 항목을 보여주자 -->
								<c:forEach var="aaom" items="${aaomList}">
									<script>
										document.querySelector('#insert_assets_income').innerHTML += '<option value=${aaom.assetsId}>${aaom.assetsName}</option>';
									</script>
								</c:forEach>
							</select>
							<!-- 보유중인 자산 항목을 변경하면 그 자산항목의 id로 검색해서 AOM을 보여주자 -->
							<select id="insert_aom_income" name="memAssetId" required="required">
								<option id="insert_aom_income_selected" value="" selected disabled>선택하세요</option>
							</select>
							<!-- insert_assets_income 변경되면 그 aom에서 user_key와 assets_id로 검색해서 그 값을 뿌려주는 기능을 넣어주자 -->
							<script>
								document.querySelector('#insert_assets_income').addEventListener('change', function() {
									let assetsId = document.querySelector('#insert_assets_income').value;
									showInsertIncomeAom(assetsId);
								});
							</script>
						</div>
						<!-- income_ic_id 선택 창. 사용자에겐 카테고리 명으로 보여주자 -->
						<div class="insert_income_category">
							<label for="insert_income_category">분류</label>
							<select id="insert_income_category" name="icId" required="required">
								<option value="" disabled selected>선택하세요</option>
								<c:forEach var="ic" items="${icList}">
									<script>
										document.querySelector('#insert_income_category').innerHTML += '<option value=${ic.icId}>${ic.icName}</option>';
									</script>
								</c:forEach>
							</select>
						</div>
						<!-- income_amount입력 창 -->
						<div class="insert_income_amount">
							<label for="insert_income_amount">금액</label>
							<input type="number" id="insert_income_amount" name="amount" required min="-999999999" max="999999999">
						</div>
						<!-- income_memo 입력 창 -->
						<div class="insert_income_memo">
							<label for="insert_income_memo">내용</label>
							<input type="text" id="insert_income_memo" name="memo" maxlength="22">
						</div>
						<!-- 저장 버튼 -->
						<div class="insert_income_button">
							<button type="submit" id="insert_income_button">저장</button>
							<button type="reset">입력초기화</button>
						</div>
					</div>
				</form>
				<!-- 여긴 지출 Form -->
				<form id="insert_expense_form">
					<div id="insert_form_container">
						<!-- 날짜 선택 창 -->
						<div class="insert_date">
							<label for="insert_expense_date">날짜</label>
							<input type="date" id="insert_expense_date" class="insert_expense_date" name="expenseDate" value=""
								required="required" min="1950-01-01" max="2199-12-12">
						</div>
						<!-- assets_id 선택 창. 사용자에겐 자산 명으로 보여주자 -->
						<div class="insert_assets">
							<label for="insert_assets_expense">자산</label>
							<select id="insert_assets_expense" name="assetsId" required="required">
								<option value="" disabled selected>선택하세요</option>
								<!-- 로그인한 사용자의 자산 항목을 보여주자 -->
								<c:forEach var="aaom" items="${aaomList}">
									<script>
										document.querySelector('#insert_assets_expense').innerHTML += '<option value=${aaom.assetsId}>${aaom.assetsName}</option>';
									</script>
								</c:forEach>
							</select>
							<!-- 보유중인 자산 항목을 변경하면 그 자산항목의 id로 검색해서 AOM을 보여주자 -->
							<select id="insert_aom_expense" name="memAssetId" required="required">
								<option id="insert_aom_expense_selected" value="" selected>선택하세요</option>
							</select>
							<!-- insert_assets_income 변경되면 그 aom에서 user_key와 assets_id로 검색해서 그 값을 뿌려주는 기능을 넣어주자 -->
							<script>
								document.querySelector('#insert_assets_expense').addEventListener('change', function() {
									let assetsId = document.querySelector('#insert_assets_expense').value;
									showInsertExpenseAom(assetsId);
								});
							</script>
						</div>
						<!-- income_ic_id 선택 창. 사용자에겐 카테고리 명으로 보여주자 -->
						<div class="insert_expense_category">
							<label for="insert_expense_category">분류</label>
							<select id="insert_expense_category" name="ecId" required="required">
								<option value="" disabled selected>선택하세요</option>
								<c:forEach var="ec" items="${ecList}">
									<script>
										document.querySelector('#insert_expense_category').innerHTML += '<option value=${ec.ecId}>${ec.ecName}</option>';
									</script>
								</c:forEach>
							</select>
						</div>
						<!-- income_amount입력 창 -->
						<div class="insert_expense_amount">
							<label for="insert_expense_amount">금액</label>
							<input type="number" id="insert_expense_amount" name="amount" required min="-999999999" max="999999999">
						</div>
						<!-- income_memo 입력 창 -->
						<div class="insert_expense_memo">
							<label for="insert_expense_memo">내용</label>
							<input type="text" id="insert_expense_memo" name="memo" maxlength="22">
						</div>
						<!-- 저장 버튼 -->
						<div class="insert_expense_button">
							<button type="submit" id="insert_expense_button">저장</button>
							<button type="reset">입력초기화</button>
						</div>
					</div>
				</form>
			</div>
		</aside>

		<!-- 캘린더 부분(메인) -->
		<main class="grid_main">
			<div class="mainPage_sectionContainer">
				<hr>
				<h3>캘린더 DIV</h3>
				<hr>
				<table>
					<thead>
						<tr>
							<th>
								<div><button type="button" id="prevMonth">전월</button></div>
							</th>
							<th colspan="5">
								<div>
									<input type="month" id="selecDate" name="selecDate" value='' min="1950-01-01" max="2199-12-12">
								</div>
							</th>
							<th>
								<div><button type="button" id="nextMonth">다음월</button></div>
							</th>
						</tr>
						<tr>
							<th><div>일</div></th>
							<th><div>월</div></th>
							<th><div>화</div></th>
							<th><div>수</div></th>
							<th><div>목</div></th>
							<th><div>금</div></th>
							<th><div>토</div></th>
						</tr>
					</thead>
					<tbody id="tbody">
						<tr>
							<c:forEach var="i" begin="2" end="${cal.firstDay}">
								<td class="dateTd">
									<div class="dateDiv"><span class="dateSpan"></span>
										<div class="enrty"><div class="entry_income"></div><div class="entry_expense"></div></div>
									</div>
								</td><%count++;%>
							</c:forEach>
							<c:forEach var="i" begin="1" end="${cal.daysOfMonth[cal.month-1]}">
								<td class="dateTd"><div class="dateDiv"><span class="dateSpan">${i}</span><div>
										<c:choose>
											<c:when test="${i<10}">
												<div class="entry d0${i}"><div class="entry_income di0${i}"></div><div class="entry_expense de0${i}"></div></div>
											</c:when>
											<c:otherwise>
												<div class="entry d${i}"><div class="entry_income di${i}"></div><div class="entry_expense de${i}"></div></div>
											</c:otherwise>
										</c:choose>
										</div>
									</div>
								</td><%count++;if(count%7==0){%>
						</tr>
						<tr><%}%>
							</c:forEach>
							</tr>
					</tbody>
				</table>
				<!-- 페이지 로드시에 캘린더 만들어주고 그에 맞는 데이터 뿌려주는 작업해주는곳 -->
				<script>
					document.querySelector('#selecDate').value = new Date().toISOString().substring(0, 7);
				</script>
				<c:forEach var="miic" items="${miicList}">
					<script type="text/javascript">
						var entry = document.querySelector('.di${miic.incomeDate.toString().substring(8, 10)}');
						entry.innerHTML += '<div class=income><span class=icName>${miic.icName}</span> <span class=icAmount>${miic.amount}원</span></div>';
					</script>
				</c:forEach>
				<c:forEach var="meec" items="${meecList}">
					<script type="text/javascript">
						var entry = document.querySelector('.de${meec.expenseDate.toString().substring(8, 10)}');
						entry.innerHTML += '<div class=expense><span class=ecName>${meec.ecName}</span> <span class=ecAmount>${meec.amount}원</span></div>';
					</script>
				</c:forEach>
			</div>
		</main>

		<!-- 상세 데이터 출력 부분 -->
		<section class="grid_section">
			<div class="mainPage_detailContainer">
				<hr><h3>상세데이터 div</h3><hr>
				<div id="detailList">
				</div>
				<!-- 메인페이지가 로드되면 상세페이지에 해당하는 데이터를 뿌려주는 작업을 해주는곳 -->
				<script type="text/javascript">
					//detailList안쪽에 div>span으로 날짜 표시해주기.
					let detailList = document.querySelector('#detailList');
					//detailDate공간에서 보여줄 자료는, 선택한 날짜. 해당 일의 수입/지출별 총 금액
					let str = '<div class=detailDateDiv><div class=detailDate><span class=detailDate>' + new Date().toISOString().substring(0, 10) + '</span></div>'
						+ '<div class=detailSumI><span class=detailSumI></span></div><div class=detailSumE><span class=detailSumE></span></div></div>'
						+ '<div class=detailContext><div class=detail_context_income></div><div class=detail_context_expense></div></div>';
					detailList.innerHTML = str;
					let button;
				</script>
				<c:set var="sumI" value="0"/>
				<c:set var="i" value="0"/>
				<c:forEach var="iica" items="${iicaList}">
					<c:set var="sumI" value="${sumI+iica.amount}"/>
					<script type="text/javascript">
						str = '<div class="di_income${i} detailItem"><div class=detailIcName><span class=detailIcName>${iica.icName}</span></div>';
						str += '<div class=detailEntry><span class=detailIMemo>${iica.memo}</span><br><span class=detailAName>${iica.assetsName}</span></div>';
						str += '<div class=detailIAmount><span class=detailIAmount>${iica.amount}</span></div><div class=delete_income_button><button type=button class="delete_income_button${i}">삭제</button></div></div>';
						document.querySelector('.detail_context_income').innerHTML += str;
					</script>
					<c:set var="i" value="${i+1}"/>
				</c:forEach>
				<script type="text/javascript">
					document.querySelector('span[class=detailSumI]').innerHTML = '${sumI}';				
				</script>
				<c:set var="i" value="0"/>
				<c:forEach var="iica" items="${iicaList}">
					<script>
						button = 'button.delete_income_button${i}';
						document.querySelector(button).addEventListener('click', function() {
							event.stopPropagation();
							document.querySelector('#modal').classList.remove('hidden');
							document.querySelector('#delete_income_Form').classList.remove('hidden');
							let assetsId = '${iica.assetsId}';
							let jsonData = {"assetsId":assetsId};
							let deleteIncomeMemAssetId = document.querySelector('#delete_income_memAssetId');
							$.ajax({
								url:'/main/postAOM',
								type:'post',
								data:jsonData,
								success:function(aomList) {
									let deleteAOMStr = '<option id="delete_income_memAssetId_selected" value="" selected>선택하세요</option>';
									if(aomList == null) {deleteIncomeMemAssetId.innerHTML = deleteAOMStr;
									}else {
										for(let i = 0; i < aomList.length; i++) {
											deleteAOMStr += '<option value='+aomList[i].memAssetId+'>'+aomList[i].memo+"</option>";
										}
										deleteIncomeMemAssetId.innerHTML = deleteAOMStr;
									}
								}
							});
							document.querySelector('#delete_income_id').value = '${iica.incomeId}';
							document.querySelector('#delete_income_date').value = new Date().toISOString().substring(0, 10);
							document.querySelector('#delete_income_assets_id_selected').value = '${iica.assetsId}';
							document.querySelector('#delete_income_assets_id_selected').innerText = '${iica.assetsName}';
							document.querySelector('#delete_income_category_id_selected').value = '${iica.icId}';
							document.querySelector('#delete_income_category_id_selected').innerText = '${iica.icName}';
							document.querySelector('#delete_income_amount').value = '${iica.amount}';
							document.querySelector('#delete_income_memo').value = '${iica.memo}';
						});
					</script>
					<c:set var="i" value="${i+1}"/>
				</c:forEach>
				<c:set var="sumE" value="0"/>
				<c:set var="i" value="0"/>
				<c:forEach var="eeca" items="${eecaList}">
					<c:set var="sumE" value="${sumE+eeca.amount}"/>
					<script type="text/javascript">
						str = '<div class="di_expense${i} detailItem"><div class=detailEcName><span class=detailEcName>${eeca.ecName}</span></div>';
						str += '<div class=detailEntry><span class=detailEMemo>${eeca.memo}</span><br><span class=detailAName>${eeca.assetsName}</span></div>';
						str += '<div class=detailEAmount><span class=detailEAmount>${eeca.amount}</span></div><div class=delete_expense_button><button type=button class="delete_expense_button${i}">삭제</button></div></div>';
						document.querySelector('.detail_context_expense').innerHTML += str;
					</script>
					<c:set var="i" value="${i+1}"/>
				</c:forEach>
				<script type="text/javascript">
					document.querySelector('span[class=detailSumE]').innerHTML = '${sumE}';
				</script>
				<c:set var="i" value="0"/>
				<c:forEach var="eeca" items="${eecaList}">
					<script>
						button = 'button.delete_expense_button${i}';
						document.querySelector(button).addEventListener('click', function() {
							event.stopPropagation();
							document.querySelector('#modal').classList.remove('hidden');
							document.querySelector('#delete_expense_Form').classList.remove('hidden');
							let assetsId = '${eeca.assetsId}';
							let jsonData = {"assetsId":assetsId};
							let deleteExpenseMemAssetId = document.querySelector('#delete_expense_memAssetId');
							$.ajax({
								url:'/main/postAOM',
								type:'post',
								data:jsonData,
								success:function(aomList) {
									let deleteAOMStr = '<option id="delete_expense_memAssetId_selected" value="" selected>선택하세요</option>';
									if(aomList == null) {deleteExpenseMemAssetId.innerHTML = deleteAOMStr;
									}else {
										for(let i = 0; i < aomList.length; i++) {
											deleteAOMStr += '<option value='+aomList[i].memAssetId+'>'+aomList[i].memo+"</option>";
										}
										deleteExpenseMemAssetId.innerHTML = deleteAOMStr;
									}
								}
							});
							document.querySelector('#delete_expense_id').value = '${eeca.expenseId}';
							document.querySelector('#delete_expense_date').value = new Date().toISOString().substring(0, 10);
							document.querySelector('#delete_expense_assets_id_selected').value = '${eeca.assetsId}';
							document.querySelector('#delete_expense_assets_id_selected').innerText = '${eeca.assetsName}';
							document.querySelector('#delete_expense_category_id_selected').value = '${eeca.ecId}';
							document.querySelector('#delete_expense_category_id_selected').innerText = '${eeca.ecName}';
							document.querySelector('#delete_expense_amount').value = '${eeca.amount}';
							document.querySelector('#delete_expense_memo').value = '${eeca.memo}';
						});
					</script>
					<c:set var="i" value="${i+1}"/>
				</c:forEach>
				<script>
					//이제부터는 출력된 오늘 날짜의 상세데이터에 onclick시 modal창을 불러오는 fucntion을 주입
					let classStr;
					let detailItem;
				</script>
				<c:set var="i" value="0" />
				<c:forEach var="iica" items="${iicaList}">
					<c:if test="${iicaList != null}">
						<script type="text/javascript">
							classStr = 'div.di_income${i}';
							detailItem = document.querySelector(classStr);
							detailItem.addEventListener('click', function() {
								document.querySelector('#modal').classList.remove('hidden');
								document.querySelector('#update_income_category_income').checked = true;
								document.querySelector('#update_income_form').classList.remove('hidden');
								document.querySelector('#update_expense_form').classList.add('hidden');
								document.querySelector('#update_income_id').value = '${iica.incomeId}';
								document.querySelector('#update_income_date').value = '${iica.incomeDate}';
								document.querySelector('#update_assets_income_selected').value = '${iica.assetsId}';
								document.querySelector('#update_assets_income_selected').innerText = '${iica.assetsName}';
								showUpdateIncomeAom('${iica.assetsId}');
								document.querySelector('#update_income_category_selected').value = '${iica.icId}';
								document.querySelector('#update_income_category_selected').innerText = '${iica.icName}';
								document.querySelector('#update_income_amount').value = '${iica.amount}';
								document.querySelector('#update_income_memo').value = '${iica.memo}';
							});
						</script>
					</c:if>
					<c:set var="i" value="${i+1}"/>
				</c:forEach>
				<c:set var="i" value="0" />
				<c:forEach var="eeca" items="${eecaList}">
					<c:if test="${eecaList != null}">
						<script type="text/javascript">
							classStr = 'div.di_expense${i}';
							detailItem = document.querySelector(classStr);
							detailItem.addEventListener('click', function() {
								document.querySelector('#modal').classList.remove('hidden');
								document.querySelector('#update_expense_category_expense').checked = true;
								document.querySelector('#update_income_form').classList.add('hidden');
								document.querySelector('#update_expense_form').classList.remove('hidden');
								document.querySelector('#update_expense_date').value = '${eeca.expenseDate}';
								document.querySelector('#update_assets_expense_selected').value = '${eeca.assetsId}';
								document.querySelector('#update_assets_expense_selected').innerText = '${eeca.assetsName}';
								showUpdateExpenseAom('${eeca.assetsId}');
								document.querySelector('#update_expense_category_selected').value = '${eeca.ecId}';
								document.querySelector('#update_expense_category_selected').innerText = '${eeca.ecName}';
								document.querySelector('#update_expense_amount').value = '${eeca.amount}';
								document.querySelector('#update_expense_memo').value = '${eeca.memo}';
							});
						</script>
					</c:if>
					<c:set var="i" value="${i+1}"/>
				</c:forEach>
			</div>
		</section>
		
		<!-- 최하단 안내문구? -->
		<footer class="grid_footer">
		
		</footer>
	</div>
</body>
</html>