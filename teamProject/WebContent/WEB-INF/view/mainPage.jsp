<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% int count = 0; %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>가계부</title>
	<link rel="stylesheet" href="/css/main/main_modal.css?var=1">
	<link rel="stylesheet" href="/css/main/mainTestCss.css?var=1">
	<link rel="stylesheet" href="/css/main/calendar.css?var=1">
	<link rel="stylesheet" href="/css/topMenu.css?asd=2">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,600" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/main/main_modal.js?var=2"></script>
	<script type="text/javascript" src="/js/main/mainTestJs.js?var=2"></script>
</head>
<body>
	<!-- 모달 창 div-->
	<div id="modal" class="modal hidden">
		<!-- 모달창이 열렸을 때, 모달창 외부를 클릭하면 모달창이 닫히는 기능을 넣고 싶다. 그 기능을 위한 layout 만들어놓기 -->
		<div class="modal_overlay"></div>
		<!-- 모달이 열렸을때 사용자에게 보여줄 content가 담길 div이다 -->
		<div class="modal_content">
			<!-- insert, expense, transfer update form을 각각 만들어놓고, 사용자가 수입/지출/이체 탭을 선택함에 따라 해당되는 창 보여주자 -->
			
			<!-- 수입 수정 Form -->
			<form id="update_income_form" class="hidden">
				<div class="update_form_container">
					<div class="update_category">
						<input type="radio" id="update_income_category_income" name="category" value="1" onchange="updateIncomeFormSwitchToIncome()">
						<label for="update_income_category_income">수입</label>
						<input type="radio" id="update_income_category_expense" name="category" value="2" onchange="updateIncomeFormSwitchToExpense()">
						<label for="update_income_category_expense">지출</label>
						<input type="radio" id="update_income_category_transfer" name="category" value="3" onchange="updateIncomeFormSwitchToTransfer()">
						<label for="update_income_category_transfer">이체</label>
					</div>
					<input type="number" id="update_income_id" name="incomeId" class="hidden">
					<!-- 날짜 선택 창 -->
					<div class="update_date">
						<label for="update_income_date">날짜</label>
						<input type="date" id="update_income_date" class="update_income_date" name="incomeDate" value=""
							required="required" min="1950-01-01" max="2199-12-12"> 
					</div>
					<!-- 돈이 빠져나갈 계좌 선택 창 -->
					<div class="update_assets">
						<input type="number" id="update_assets_income_memAssetIdFrom_origin" name="memAssetId" class="hidden">
						<label for="update_assets_income_memAssetIdFrom" id="update_assets_income_memAssetIdFrom_label">자산</label>
						<select id="update_assets_income_memAssetIdFrom" name="newMemAssetId" required="required" onchange="getAssetsIdAndPutAssetsIdToHiddenInputIncome()">
							<option value="" id="update_assets_income_memAssetIdFrom_selected" selected>선택하세요</option>
						</select>
						<!-- 이체가 수입이나 지출로 바뀌었을때 값이 들어간다. -->
						<input type="number" id="update_assets_income_assetsId" name="assetsId" class="hidden">
					</div>
					<!-- 돈이 들어올 계좌 선택 창 -->
					<!-- income_ic_id 선택 창. 사용자에겐 카테고리 명으로 보여주자 -->
					<div class="update_expense_category">
						<!-- <input type="number" id="update_assets_expense_memAssetIdTo_origin" name="memAssetIdTo" class="hidden"> -->
						<label for="update_assets_income_memAssetIdTo" id="update_assets_income_memAssetIdTo_label" class="hidden">입금</label>
						<select id="update_assets_income_memAssetIdTo" name="memAssetIdTo" class="hidden">
							<option value="" selected id="update_assets_income_memAssetIdTo_selected" disabled="disabled">선택하세요</option>
						</select>
						<label for="update_income_expense_category" id="update_income_expense_category_label" class="hidden">분류</label>
						<select id="update_income_expense_category" name="ecId" class="hidden">
							<option value="" selected disabled="disabled">선택하세요</option>
							<c:forEach var="ec" items="${ecList}">
								<script>
									document.querySelector('#update_income_expense_category').innerHTML += '<option value=${ec.ecId}>${ec.ecName}</option>';
								</script>
							</c:forEach>
						</select>
						<label for="update_income_income_category" id="update_income_income_category_label">분류</label>
						<select id="update_income_income_category" name="icId" required>
							<option value="" selected class="update_income_income_category_selected"disabled="disabled">선택하세요</option>
							<c:forEach var="ic" items="${icList}">
								<script>
									document.querySelector('#update_income_income_category').innerHTML += '<option value=${ic.icId}>${ic.icName}</option>';
								</script>
							</c:forEach>
						</select>
					</div>
					<!-- income_amount입력 창 -->
					<div class="update_amount">
						<label for="update_income_amount">금액</label>
						<input type="number" id="update_income_amount_origin" name="amount" class="hidden">
						<input type="text" id="update_income_amount" name="newAmount" required maxlength="11" onkeyup="numberFormat(this)">
					</div>
					<!-- income_memo 입력 창 -->
					<div class="update_memo">
						<label for="update_income_memo">내용</label>
						<input type="text" id="update_income_memo" name="memo" maxlength="22">
					</div>
					<!-- 입력하기 버튼 -->
					<div class="update_button">
						<button type="button" id="update_income_button_insert">신규 등록</button>
						<button type="submit" id="update_income_button" value="update">수정</button>
						<button type="button" id="delete_income_button">삭제</button>
						<button type="button" class="close_modal">닫기</button>
					</div>
				</div>
			</form>

			<!-- 지출 수정 Form -->
			<form id="update_expense_form" class="hidden">
				<div class="update_form_container">
					<div class="update_category">
						<input type="radio" id="update_expense_category_income" name="category" value="1" onchange="updateExpenseFormSwitchToIncome()">
						<label for="update_expense_category_income">수입</label>
						<input type="radio" id="update_expense_category_expense" name="category" value="2" onchange="updateExpenseFormSwitchToExpense()">
						<label for="update_expense_category_expense">지출</label>
						<input type="radio" id="update_expense_category_transfer" name="category" value="3" onchange="updateExpenseFormSwitchToTransfer()">
						<label for="update_expense_category_transfer">이체</label>
					</div>
					<input type="number" id="update_expense_id" name="expenseId" class="hidden">
					<!-- 날짜 선택 창 -->
					<div class="update_date">
						<label for="update_expense_date">날짜</label>
						<input type="date" id="update_expense_date" class="update_expense_date" name="expenseDate" value="" required="required" min="1950-01-01" max="2199-12-12">
					</div>
					<!-- 돈이 빠져나갈 계좌 선택 창 -->
					<div class="update_assets">
						<input type="number" id="update_assets_expense_memAssetIdFrom_origin" name="memAssetId" class="hidden">
						<label for="update_assets_expense_memAssetIdFrom" id="update_assets_expense_memAssetIdFrom_label">자산</label>
						<select id="update_assets_expense_memAssetIdFrom" name="newMemAssetId" required="required" onchange="getAssetsIdAndPutAssetsIdToHiddenInputExpense()">
							<option value="" id="update_assets_expense_memAssetIdFrom_selected" selected disabled="disabled">선택하세요</option>
						</select>
						<!-- 이체가 수입이나 지출로 바뀌었을때 값이 들어간다. -->
						<input type="number" id="update_assets_expense_assetsId" name="assetsId" class="hidden">
					</div>
					<!-- 돈이 들어올 계좌 선택 창 -->
					<!-- income_ic_id 선택 창. 사용자에겐 카테고리 명으로 보여주자 -->
					<div class="update_expense_category">
						<!-- <input type="number" id="update_assets_expense_memAssetIdTo_origin" name="memAssetIdTo" class="hidden"> -->
						<label for="update_assets_expense_memAssetIdTo" id="update_assets_expense_memAssetIdTo_label" class="hidden">입금</label>
						<select id="update_assets_expense_memAssetIdTo" name="memAssetIdTo" class="hidden">
							<option value="" selected id="update_assets_expense_memAssetIdTo_selected" disabled="disabled">선택하세요</option>
						</select>
						<label for="update_expense_expense_category" id="update_expense_expense_category_label">분류</label>
						<select id="update_expense_expense_category" name="ecId" required="required">
							<option value="" selected id="update_expense_expense_category_selected" disabled="disabled">선택하세요</option>
							<c:forEach var="ec" items="${ecList}">
								<script>
									document.querySelector('#update_expense_expense_category').innerHTML += '<option value=${ec.ecId}>${ec.ecName}</option>';
								</script>
							</c:forEach>
						</select>
						<label for="update_expense_income_category" id="update_expense_income_category_label" class="hidden">분류</label>
						<select id="update_expense_income_category" name="icId" class="hidden">
							<option value="" selected disabled="disabled">선택하세요</option>
							<c:forEach var="ic" items="${icList}">
								<script>
									document.querySelector('#update_expense_income_category').innerHTML += '<option value=${ic.icId}>${ic.icName}</option>';
								</script>
							</c:forEach>
						</select>
					</div>
					<!-- income_amount입력 창 -->
					<div class="update_amount">
						<label for="update_expense_amount">금액</label>
						<input type="number" id="update_expense_amount_origin" name="amount" class="hidden">
						<input type="text" id="update_expense_amount" name="newAmount" required maxlength="11" onkeyup="numberFormat(this)">
					</div>
					<!-- income_memo 입력 창 -->
					<div class="update_memo">
						<label for="update_expense_memo">내용</label>
						<input type="text" id="update_expense_memo" name="memo" maxlength="22">
					</div>
					<!-- 입력하기 버튼 -->
					<div class="update_button">
						<button type="button" id="update_expense_button_insert">신규 등록</button>
						<button type="submit" id="update_expense_button">수정</button>
						<button type="button" id="delete_expense_button">삭제</button>
						<button type="button" class="close_modal">닫기</button>
					</div>
				</div>
			</form>

			<!-- 이체 수정 Form -->
			<form id="update_transfer_form" class="hidden">
				<div class="update_form_container">
					<div class="update_category">
						<input type="radio" id="update_transfer_category_income" name="category" value="1" onchange="updateTransferFormSwitchToIncome()">
						<label for="update_transfer_category_income">수입</label>
						<input type="radio" id="update_transfer_category_expense" name="category" value="2" onchange="updateTransferFormSwitchToExpense()">
						<label for="update_transfer_category_expense">지출</label>
						<input type="radio" id="update_transfer_category_transfer" name="category" value="3" onchange="updateTransferFormSwitchToTransfer()">
						<label for="update_transfer_category_transfer">이체</label>
					</div>
					<input type="number" id="update_transfer_id" name="transferId" class="hidden">
					<!-- 날짜 선택 창 -->
					<div class="update_date">
						<label for="update_transfer_date">날짜</label>
						<input type="date" id="update_transfer_date" class="update_transfer_date" name="transferDate" value="" required="required" min="1950-01-01" max="2199-12-12">
					</div>
					<!-- 돈이 빠져나갈 계좌 선택 창 -->
					<div class="update_assets">
						<input type="number" id="update_assets_transfer_memAssetIdFrom_origin" name="memAssetIdFrom" class="hidden">
						<label for="update_assets_transfer_memAssetIdFrom" id="update_assets_transfer_memAssetIdFrom_label">출금</label>
						<select id="update_assets_transfer_memAssetIdFrom" name="newMemAssetIdFrom" required="required" onchange="getAssetsIdAndPutAssetsIdToHiddenInputTransfer()">
							<option value="" id="update_assets_transfer_memAssetIdFrom_selected" selected disabled="disabled">선택하세요</option>
						</select>
						<!-- 이체가 수입이나 지출로 바뀌었을때 값이 들어간다. -->
						<input type="number" id="update_assets_transfer_assetsId" name="assetsId" class="hidden">
					</div>
					<!-- 돈이 들어올 계좌 선택 창 -->
					<!-- income_ic_id 선택 창. 사용자에겐 카테고리 명으로 보여주자 -->
					<div class="update_expense_category">
						<input type="number" id="update_assets_transfer_memAssetIdTo_origin" name="memAssetIdTo" class="hidden">
						<label for="update_assets_transfer_memAssetIdTo" id="update_assets_transfer_memAssetIdTo_label">입금</label>
						<select id="update_assets_transfer_memAssetIdTo" name="newMemAssetIdTo" required="required">
							<option value="" id="update_assets_transfer_memAssetIdto_selected" selected disabled>선택하세요</option>
						</select>
						<label for="update_transfer_expense_category" id="update_transfer_expense_category_label" class="hidden">분류</label>
						<select id="update_transfer_expense_category" name="ecId" class="hidden">
							<option value="" selected disabled="disabled">선택하세요</option>
							<c:forEach var="ec" items="${ecList}">
								<script>
									document.querySelector('#update_transfer_expense_category').innerHTML += '<option value=${ec.ecId}>${ec.ecName}</option>';
								</script>
							</c:forEach>
						</select>
						<label for="update_transfer_income_category" id="update_transfer_income_category_label" class="hidden">분류</label>
						<select id="update_transfer_income_category" name="icId" class="hidden">
							<option value="" selected disabled="disabled">선택하세요</option>
							<c:forEach var="ic" items="${icList}">
								<script>
									document.querySelector('#update_transfer_income_category').innerHTML += '<option value=${ic.icId}>${ic.icName}</option>';
								</script>
							</c:forEach>
						</select>
					</div>
					<!-- income_amount입력 창 -->
					<div class="update_amount">
						<label for="update_transfer_amount">금액</label>
						<input type="number" id="update_transfer_amount_origin" name="amount" class="hidden">
						<input type="text" id="update_transfer_amount" name="newAmount" required maxlength="11" onkeyup="numberFormat(this)">
					</div>
					<!-- income_memo 입력 창 -->
					<div class="update_memo">
						<label for="update_transfer_memo">내용</label>
						<input type="text" id="update_transfer_memo" name="memo" maxlength="22">
					</div>
					<!-- 입력하기 버튼 -->
					<div class="update_button">
						<button type="button" id="update_transfer_button_insert">신규 등록</button>
						<button type="submit" id="update_transfer_button">수정</button>
						<button type="button" id="delete_transfer_button">삭제</button>
						<button type="button" class="close_modal">닫기</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<button type="button" class="show_insert_modal hidden">+</button>
	
	<div class="grid_container">
	
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
		
		<!-- 측면 메뉴 부분 -->
		<aside class="grid_aside">
			<div class="insert_modal_overlay hidden"></div>
			<div class="mainPage_sideMenuContainer">
				<!-- 사용자가 정보를 입력하면 Insert해주는 Form을 만들자. ajax방식 -->
				<!-- 사용자가 선택할 수 있는 수입/지출/이체 카테고리 -->
				<div class="insert_category_container">
					<div class="insert_type">
						<input type="radio" id="insert_income" name="insertCategory" value="income" onchange="showIncomeForm()">
						<label for="insert_income">수입</label>
						<!-- 지출은 미리 선택되게 해놓기 -->
						<input type="radio" id="insert_expense" name="insertCategory" value="expense" onchange="showExpenseForm()" checked>
						<label for="insert_expense">지출</label>
						<input type="radio" id="insert_transfer" name="insertCategory" value="transfer" onchange="showTransferForm()">
						<label for="insert_transfer">이체</label>
					</div>
				</div>
				<!-- insert, expense용 form을 각각 만들어놓고, 사용자가 수입/지출 탭을 선택함에 따라 해당되는 input을 보여주자 -->
				<!-- 여긴 수입 Form -->
				<form id="insert_income_form" class="hidden">
					<div class="insert_form_container">
						<!-- 날짜 선택 창 -->
						<div class="insert_date">
							<label for="insert_income_date">날짜</label>
							<input type="date" id="insert_income_date" class="insert_income_date" name="incomeDate" value=""
								required="required" min="1950-01-01" max="2199-12-12" onblur="syncronizeDateAtInsertForm(this)">
						</div>
						<!-- assets_id 선택 창. 사용자에겐 자산 명으로 보여주자 -->
						<div class="insert_assets">
							<label for="insert_assets_income">자산</label>
							<input id="insert_assets_income_assetsId" name="assetsId" class="hidden">
							<select id="insert_assets_income_memAssetId" name="memAssetId" required="required" onchange="getAssetsIdAndPutDataAtIncomeForm()">
								<option value="" id="insert_assets_income_memAssetId_selected" selected disabled>선택하세요</option>
							</select>
							<script>
								const aaomListJ = JSON.parse('${aaomListJ}');
								showAOMListAtInsertIncome(aaomListJ);
							</script>
						</div>
						<!-- income_ic_id 선택 창. 사용자에겐 카테고리 명으로 보여주자 -->
						<div class="insert_category">
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
						<div class="insert_amount">
							<label for="insert_income_amount">금액</label>
							<input type="text" id="insert_income_amount" name="amount" required maxlength="11" onkeyup="numberFormat(this)" onblur="syncronizeAmountAtInsertForm(this)">
						</div>
						<!-- income_memo 입력 창 -->
						<div class="insert_memo">
							<label for="insert_income_memo">내용</label>
							<input type="text" id="insert_income_memo" name="memo" maxlength="22" onblur="syncronizeMemoAtInsertForm(this)">
						</div>
						<!-- 저장 버튼 -->
						<div class="insert_button">
							<button type="submit" id="insert_income_button">등록</button>
							<button type="button" class="close_insert_modal">닫기</button>
						</div>
					</div>
				</form>
				
				<!-- 여긴 지출 Form -->
				<form id="insert_expense_form">
					<div class="insert_form_container">
						<!-- 날짜 선택 창 -->
						<div class="insert_date">
							<label for="insert_expense_date">날짜</label>
							<input type="date" id="insert_expense_date" class="insert_expense_date" name="expenseDate" value=""
								required="required" min="1950-01-01" max="2199-12-12" onblur="syncronizeDateAtInsertForm(this)">
						</div>
						<!-- assets_id 선택 창. 사용자에겐 자산 명으로 보여주자 -->
						<div class="insert_assets">
							<label for="insert_assets_expense">자산</label>
							<input id="insert_assets_expense_assetsId" name="assetsId" class="hidden">
							<select id="insert_assets_expense_memAssetId" name="memAssetId" required="required" onchange="getAssetsIdAndPutDataAtExpenseForm()">
								<option value="" id="insert_assets_expense_memAssetId_selected" selected disabled>선택하세요</option>
							</select>
							<script>
								showAOMListAtInsertExpense(aaomListJ);
							</script>
						</div>
						<!-- income_ic_id 선택 창. 사용자에겐 카테고리 명으로 보여주자 -->
						<div class="insert_category">
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
						<div class="insert_amount">
							<label for="insert_expense_amount">금액</label>
							<input type="text" id="insert_expense_amount" name="amount" required maxlength="11" onkeyup="numberFormat(this)" onblur="syncronizeAmountAtInsertForm(this)">
						</div>
						<!-- income_memo 입력 창 -->
						<div class="insert_memo">
							<label for="insert_expense_memo">내용</label>
							<input type="text" id="insert_expense_memo" name="memo" maxlength="22" onblur="syncronizeMemoAtInsertForm(this)">
						</div>
						<!-- 저장 버튼 -->
						<div class="insert_button">
							<button type="submit" id="insert_expense_button">등록</button>
							<button type="button" class="close_insert_modal">닫기</button>
						</div>
					</div>
				</form>

				<!-- 여긴 이체 Form -->
				<form id="insert_transfer_form" class="hidden">
					<div class="insert_form_container">
						<!-- 날짜 선택 창 -->
						<div class="insert_date">
							<label for="insert_transfer_date">날짜</label>
							<input type="date" id="insert_transfer_date" class="insert_transfer_date" name="transferDate" value=""
								required="required" min="1950-01-01" max="2199-12-12" onblur="syncronizeDateAtInsertForm(this)">
						</div>
						<!-- 출금할 자산 선택 창 -->
						<div class="insert_assets">
							<label for="insert_assets_transfer_memAssetId">출금</label>
							<select id="insert_assets_transfer_memAssetId" name="memAssetIdFrom" required="required" onchange="getAssetsIdAndPutDataAtTransferForm()">
								<option value="" id="insert_assets_expense_memAssetId_selected" selected disabled>선택하세요</option>
							</select>
							<script>
								showAOMListAtInsertTransferAtFrom(aaomListJ);
							</script>
						</div>
						<!-- 이체할 자산 선택 창 -->
						<div class="insert_category">
							<label for="insert_assets_transfer_memAssetIdTo">입금</label>
							<select id="insert_assets_transfer_memAssetIdTo" name="memAssetIdTo" required="required">
								<option value="" id="insert_assets_transfer_memAssetIdTo_selected" selected disabled>선택하세요</option>
							</select>
							<script>
								showAOMListAtInsertTransferAtTo(aaomListJ);
							</script>
						</div>
						<!-- income_amount입력 창 -->
						<div class="insert_amount">
							<label for="insert_transfer_amount">금액</label>
							<input type="text" id="insert_transfer_amount" name="amount" required maxlength="11" onkeyup="numberFormat(this)" onblur="syncronizeAmountAtInsertForm(this)">
						</div>
						<!-- income_memo 입력 창 -->
						<div class="insert_memo">
							<label for="insert_transfer_memo">내용</label>
							<input type="text" id="insert_transfer_memo" name="memo" maxlength="22" onblur="syncronizeMemoAtInsertForm(this)">
						</div>
						<!-- 저장 버튼 -->
						<div class="insert_button">
							<button type="submit" id="insert_transfer_button">등록</button>
							<button type="button" class="close_insert_modal">닫기</button>
						</div>
					</div>
				</form>
			</div>
		</aside>

		<!-- 상세 데이터 출력 부분 -->
		<section class="grid_section_detail">
			<div class="mainPage_detailContainer">
				<div id="detailList">
					<div class="detailDateDiv">
						<div class="detailDate"><span class="detailDate"></span></div>
						<div class="detailSumI"><span class="detailSumI icAmount">0원</span></div>
						<div class="detailSumE"><span class="detailSumE ecAmount">0원</span></div>
					</div>
					<div class="detailContext">
						<div class="detail_context_income"></div>
						<div class="detail_context_expense"></div>
						<div class="detail_context_transfer"></div>
					</div>
				</div>
				<!-- 메인페이지가 로드되면 상세페이지에 해당하는 데이터를 뿌려주는 작업을 해주는곳 -->
				<script type="text/javascript">
					//detailList안쪽에 div>span으로 날짜 표시해주기.
					let detailList = document.querySelector('#detailList');
					//detailDate공간에서 보여줄 자료는, 선택한 날짜. 해당 일의 수입/지출별 총 금액
					document.querySelector('span.detailDate').innerText = new Date().toISOString().substring(0, 10);
					const taomfaomtList = JSON.parse('${taomfaomtListJ}');
					const eecaList = JSON.parse('${eecaListJ}');
					const iicaList = JSON.parse('${iicaListJ}');
					makeDetailIncomeDIV(iicaList);
					makeDetailExpenseDIV(eecaList);
					makeDetailTransferDIV(taomfaomtList);
				</script>
			</div>
		</section>
		
		<!-- 캘린더 부분(메인) -->
		<main class="grid_main">
			<div class="mainPage_sectionContainer">
				<table>
					<thead>
						<!-- 월 별 통합 데이터 출력 부분 -->
						<tr>
							<th colspan="7">
								<div class="monthly_data">
									<div class="monthly_data_month"><span class="monthly_data_month">${cal.selecDate.toString().substring(5, 7)} 월</span></div>
									<div class="monthly_data_income">
										<span class="monthly_data_name">수입&nbsp;</span><span class="monthly_data_amount_income icAmount"></span>
									</div>
									<div class="monthly_data_expense">
										<span class="monthly_data_name">지출&nbsp;</span><span class="monthly_data_amount_expense ecAmount"></span>
									</div>
									<div class="monthly_data_sum">
										<span class="monthly_data_name">합계&nbsp;</span><span class="monthly_data_amount_sum"></span>
									</div>
								</div>
								<script>
								const sumAmounts = JSON.parse('${sumAmountsJ}');
								getThisMonthSumData(sumAmounts);
								</script>
							</th>
						</tr>
						<tr>
							<th colspan="7">
								<div class="calendar_head">
									<button type="button" id="prevMonth" class="month_button">◀</button>
									<input type="text" id="selecDate" name="selecDate" value="" class="hidden">
									<button type="button" class="datepicker" onclick="$('#selecDate').datepicker('show');">
										<span class="month_string">${cal.selecDate.getMonth()}</span><span style="font-size: 18px;">▾</span>
										<br><span class="year_value">${cal.year}</span></button>
									<button type="button" id="nextMonth" class="month_button">▶</button>
								</div>
						</tr>
						<tr>
							<th class="day"><div class="day sun">일</div></th>
							<th class="day"><div class="day">월</div></th>
							<th class="day"><div class="day">화</div></th>
							<th class="day"><div class="day">수</div></th>
							<th class="day"><div class="day">목</div></th>
							<th class="day"><div class="day">금</div></th>
							<th class="day"><div class="day sat">토</div></th>
						</tr>
					</thead>
					<tbody id="tbody">
						<tr>
							<c:set var="count" value="0"/>
							<c:forEach var="i" begin="2" end="${cal.firstDay}">
								<td class="dateTd_disabled">
									<div class="dateDiv"><span class="dateSpan_disabled"></span>
										<div class="entry"><div class="entry_income"></div><div class="entry_expense"></div class="entry_transfer"><div></div></div>
									</div>
								</td><%count++;%>
							</c:forEach>
							<c:forEach var="i" begin="1" end="${cal.daysOfMonth[cal.month-1]}">
								<%if(count%7 == 0) {%><td class="dateTd"><div class="dateDiv"><span class="dateSpan sun">${i}</span><div><%}
								else if(count%7 == 6) {%><td class="dateTd"><div class="dateDiv"><span class="dateSpan sat">${i}</span><div> <%}
								else{%><td class="dateTd"><div class="dateDiv"><span class="dateSpan">${i}</span><div><%} %>
										<c:choose>
											<c:when test="${i<10}">
												<div class="entry d0${i}"><div class="entry_income di0${i}"></div><div class="entry_expense de0${i}"></div><div class="entry_transfer dt0${i}"></div></div>
											</c:when>
											<c:otherwise>
												<div class="entry d${i}"><div class="entry_income di${i}"></div><div class="entry_expense de${i}"></div><div class="entry_transfer dt${i}"></div></div>
											</c:otherwise>
										</c:choose>
										</div>
									</div>
								</td><%count++;if(count%7==0){%>
						</tr>
						<tr><%}%>
							</c:forEach>
							<%while(count%7!=0){%><!-- 비쥬얼스튜디오 코드 에러 방지용 %}-->
								<td class="dateTd_disabled">
									<div class="dateDiv"><span class="dateSpan_disabled"></span>
										<div class="entry"><div class="entry_income"></div><div class="entry_expense"></div class="entry_transfer"><div></div></div>
									</div>
								</td><%count++;}%></tr>
					</tbody>
				</table>
				<!-- 페이지 로드시에 캘린더 만들어주고 그에 맞는 데이터 뿌려주는 작업해주는곳 -->
				<script>
					document.querySelector('#selecDate').value = new Date().toISOString().substring(0, 7);
					const meecList = JSON.parse('${meecListJ}');
					const miicList = JSON.parse('${miicListJ}');
					const tbmListJ = JSON.parse('${tbmListJ}');
					showThisMonthIncome(miicList);
					showThisMonthExpense(meecList);
					showThisMonthTransfer(tbmListJ);
				</script>
			</div>
		</main>
		
		<!-- 최하단 안내문구? -->
	</div>
</body>
</html>