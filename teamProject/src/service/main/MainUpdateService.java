package service.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dto.main.Expense;
import dto.main.ExpenseUpdate;
import dto.main.Income;
import dto.main.IncomeUpdate;
import dto.main.Transfer;
import dto.main.TransferUpdate;
import mapper.main.MainUpdateMapper;

@Service
public class MainUpdateService {
	@Autowired
	private MainUpdateMapper mainUpdateMapper;
	
	//income 업데이트
	public void updateIncomeByIncomeId(Income income) {
		mainUpdateMapper.updateIncomeByIncomeId(income);
	}

	//expense 업데이트
	private void updateExpenseByExpenseId(Expense expense) {
		mainUpdateMapper.updateExpenseByExpenseId(expense);
	}

	//transfer 업데이트
	private void updateTransferByTransferId(Transfer transfer) {
		mainUpdateMapper.updateTransferByTransferId(transfer);
	}
	
	//select sum(income)
	public int selectSUMIncomeByUserKeyAndAssetsId(int userKey, int assetsId) {
		Integer sumIncome = 0;
		sumIncome = mainUpdateMapper.selectSUMINCOMEByUserKeyAndAssetsId(userKey, assetsId);
		if(sumIncome == null) {sumIncome = 0;}
		return sumIncome;
	}
	
	//select sum(expense)
	public int selectSUMExpenseByUserKeyAndAssetsId(int userKey,  int assetsId) {
		Integer sumExpense = 0;
		sumExpense = mainUpdateMapper.selectSUMExpenseByUserKeyAndAssetsId(userKey, assetsId);
		if(sumExpense == null) {sumExpense = 0;}
		return sumExpense;
	}
	
	//asset of member의 amount 업데이트(플러스) 기능
	public void plusAOMAmountById(int amount, int memAssetId) {
		mainUpdateMapper.plusAOMAmountById(amount, memAssetId);
	}
	
	//asset of member의 amount 업데이트(마이너스) 기능
	private void minusAOMAountById(int amount, int memAssetId) {
		mainUpdateMapper.minusAOMAmountById(amount, memAssetId);
	}
	
	//delete Income By incomeId
	public void deleteIncomeByIncomeId(int incomeId) {
		mainUpdateMapper.deleteIncomeByIncomeId(incomeId);
	}
	
	//delete Expense By ExpenseId
	public void deleteExpenseByExpenseId(int expenseId) {
		mainUpdateMapper.deleteExpenseByExpenseId(expenseId);
	}

	//delete Transfer By TransferId
	private void deleteTransferByTransferId(int transferId) {
		mainUpdateMapper.deleteTransferByTransferId(transferId);
	}
	
	//insert Expense
	public void insertExpense(Expense expense) {
		mainUpdateMapper.insertExpense(expense);
	}
	
	//insert Income
	public void insertIncome(Income income) {
		mainUpdateMapper.insertIncome(income);
	}

	//transfer insert
	public void insertTransfer(Transfer transfer) {
		mainUpdateMapper.insertTransfer(transfer);
	}
	
	//수입 항목을 update하고 AOM도 update해주는 서비스
	@Transactional
	public void updateIncomeAndUpdateAOM(IncomeUpdate incomeUpdate) {
		//income업데이트
		updateIncomeByIncomeId(new Income(incomeUpdate.getIncomeId(), incomeUpdate.getUserKey(), incomeUpdate.getNewAmount(), incomeUpdate.getIncomeDate(), incomeUpdate.getAssetsId(), incomeUpdate.getIcId(), incomeUpdate.getMemo(), incomeUpdate.getNewMemAssetId()));
//		aom을 업데이트
		minusAOMAountById(incomeUpdate.getAmount(), incomeUpdate.getMemAssetId());
		plusAOMAmountById(incomeUpdate.getNewAmount(), incomeUpdate.getNewMemAssetId());			
	}

	//수입을 지우고 지출을 insert하고 AOM업데이트
	@Transactional
	public void deleteIncomeInsertExpenseUpdateAOM(IncomeUpdate incomeUpdate) {
//		income id로 income지워주고, 
		deleteIncomeByIncomeId(incomeUpdate.getIncomeId());
//		delete하면 자산도 수정해야한다.
		minusAOMAountById(incomeUpdate.getAmount(), incomeUpdate.getMemAssetId());
		//해당 정보로 다시 expense에 insert 해준다.
		Expense expense = new Expense(incomeUpdate.getIncomeId(), incomeUpdate.getUserKey(), incomeUpdate.getNewAmount(), incomeUpdate.getIncomeDate(), incomeUpdate.getAssetsId(), incomeUpdate.getEcId(), incomeUpdate.getMemo(), incomeUpdate.getNewMemAssetId());
		insertExpense(expense);
//		AOM도 업데이트 해준다
		minusAOMAountById(incomeUpdate.getNewAmount(), incomeUpdate.getNewMemAssetId());
	}

	//수입을 지우고 이체를 insert하고 AOM을 업데이트
	@Transactional
	public void deleteIncomeInsertTransferUpdateAOM(IncomeUpdate incomeUpdate) {
//		income을 삭제
		deleteIncomeByIncomeId(incomeUpdate.getIncomeId());
//		자산 수정
		minusAOMAountById(incomeUpdate.getAmount(), incomeUpdate.getMemAssetId());
//		이체를 인서트
		insertTransferAndUpdateAom(new Transfer(0, incomeUpdate.getUserKey(), incomeUpdate.getNewMemAssetId(), incomeUpdate.getMemAssetIdTo(), incomeUpdate.getNewAmount(), incomeUpdate.getIncomeDate(), incomeUpdate.getMemo()));
	}

	//지출항목을 업데이트 해주고, income과 expense의 sum(amount)를 select하고 aom의 amout를 업데이트 한다.
	@Transactional
	public void updateEpAndAmount(ExpenseUpdate expenseUpdate) {
//		Expense를 업데이트
		updateExpenseByExpenseId(new Expense(expenseUpdate.getExpenseId(), expenseUpdate.getUserkey(), expenseUpdate.getNewAmount(), expenseUpdate.getExpenseDate(), expenseUpdate.getAssetsId(), expenseUpdate.getEcId(), expenseUpdate.getMemo(), expenseUpdate.getNewMemAssetId()));
//		aom을 업데이트
		plusAOMAmountById(expenseUpdate.getAmount(), expenseUpdate.getMemAssetId());
		minusAOMAountById(expenseUpdate.getNewAmount(), expenseUpdate.getNewMemAssetId());
	}

	//지출항목을 지워주고, income항목에 insert해주고 aom의 amount를 업데이트 해주자.
	@Transactional
	public void deleteEpInsertInUpdateAOM(ExpenseUpdate expenseUpdate) {
		//Expense를 delete
		deleteExpenseByExpenseId(expenseUpdate.getExpenseId());
		plusAOMAmountById(expenseUpdate.getAmount(), expenseUpdate.getMemAssetId());
		//Income을 insert
		insertIncome(new Income(0, expenseUpdate.getUserkey(), expenseUpdate.getNewAmount(), expenseUpdate.getExpenseDate(), expenseUpdate.getAssetsId(), expenseUpdate.getIcId(), expenseUpdate.getMemo(), expenseUpdate.getNewMemAssetId()));
//		aom도 업데이트
		plusAOMAmountById(expenseUpdate.getNewAmount(), expenseUpdate.getNewMemAssetId());
	}

	//지출 삭제, 이체 인서트, AOM업데이트
	@Transactional
	public void deleteExpenseInsertTransferUpdateAOM(ExpenseUpdate expenseUpdate) {
		deleteExpenseByExpenseId(expenseUpdate.getExpenseId());
		plusAOMAmountById(expenseUpdate.getAmount(), expenseUpdate.getMemAssetId());
		insertTransferAndUpdateAom(new Transfer(0, expenseUpdate.getUserkey(), expenseUpdate.getNewMemAssetId(), expenseUpdate.getMemAssetIdTo(), expenseUpdate.getNewAmount(), expenseUpdate.getExpenseDate(), expenseUpdate.getMemo()));
	}
	
	//이체항목을 업데이트하고 AOM을 업데이트
	@Transactional
	public void updateTransferAndAmount(TransferUpdate transferUpdate) {
		//transfer를 업데이트
		updateTransferByTransferId(new Transfer(transferUpdate.getTransferId(), transferUpdate.getUserKey(), transferUpdate.getNewMemAssetIdFrom(), transferUpdate.getNewMemAssetIdTo(), transferUpdate.getNewAmount(), transferUpdate.getTransferDate(), transferUpdate.getMemo()));
		//업데이트 하기 전에 이체 했던 내역 되돌리기
		plusAOMAmountById(transferUpdate.getAmount(), transferUpdate.getMemAssetIdFrom());
		minusAOMAountById(transferUpdate.getAmount(), transferUpdate.getMemAssetIdTo());
		//신규 이체로 자산금액 업데이트
		minusAOMAountById(transferUpdate.getNewAmount(), transferUpdate.getNewMemAssetIdFrom());
		plusAOMAmountById(transferUpdate.getNewAmount(), transferUpdate.getNewMemAssetIdTo());
	}

	//수입 항목을 insert하고 aom을 업데이트 한다.
	@Transactional
	public void insertIncomeAndUpdateAOM(Income income) {
		insertIncome(income);
		plusAOMAmountById(income.getAmount(), income.getMemAssetId());
	}

	//지출 항목을 insert하고 aom을 업데이트 한다
	@Transactional
	public void insertExpenseAndUpdateAOM(Expense expense) {
		insertExpense(expense);
		minusAOMAountById(expense.getAmount(), expense.getMemAssetId());
	}
	
	//수입 항목을 delete하고 aom을 업데이트 한다.
	@Transactional
	public void deleteIncomeAndUpdateAOM(IncomeUpdate incomeUpdate) {
		deleteIncomeByIncomeId(incomeUpdate.getIncomeId());
		minusAOMAountById(incomeUpdate.getAmount(), incomeUpdate.getMemAssetId());
	}

	//지출 항목을 delete하고 aom을 업데이트 한다.
	@Transactional
	public void deleteExpenseAndUpdateAOM(ExpenseUpdate expenseUpdate) {
		deleteExpenseByExpenseId(expenseUpdate.getExpenseId());
		plusAOMAmountById(expenseUpdate.getAmount(), expenseUpdate.getMemAssetId());
	}
	
	//이체 항목을 delete하고 수입을 insert하고 aom을 업데이트
	@Transactional
	public void deleteTransferInsertIncomeUpdateAom(TransferUpdate transferUpdate) {
		//이체를 delete
		deleteTransferByTransferId(transferUpdate.getTransferId());
		//이체 내역을 되돌리기
		plusAOMAmountById(transferUpdate.getAmount(), transferUpdate.getMemAssetIdFrom());
		minusAOMAountById(transferUpdate.getAmount(), transferUpdate.getMemAssetIdTo());
		//수입을 인서트하기
		insertIncome(new Income(0, transferUpdate.getUserKey(), transferUpdate.getNewAmount(), transferUpdate.getTransferDate(), transferUpdate.getAssetsId(), transferUpdate.getIcId(), transferUpdate.getMemo(), transferUpdate.getNewMemAssetIdFrom()));
		//AOM amount를 업데이트 하기
		plusAOMAmountById(transferUpdate.getNewAmount(), transferUpdate.getNewMemAssetIdFrom());
	}

	//이체 항목을 delete하고 지출을 insert하고 aom을 업데이트 한다.
	@Transactional
	public void deleteTransferInsertExpenseUpdateAom(TransferUpdate transferUpdate) {
		//이체를 delete
		deleteTransferByTransferId(transferUpdate.getTransferId());
		//이체 내역을 되돌리기
		plusAOMAmountById(transferUpdate.getAmount(), transferUpdate.getMemAssetIdFrom());
		minusAOMAountById(transferUpdate.getAmount(), transferUpdate.getMemAssetIdTo());
		//지출을 인서트하기
		insertExpense(new Expense(0, transferUpdate.getUserKey(), transferUpdate.getNewAmount(), transferUpdate.getTransferDate(), transferUpdate.getAssetsId(), transferUpdate.getEcId(), transferUpdate.getMemo(), transferUpdate.getNewMemAssetIdFrom()));
		//AOM amount를 업데이트하기
		minusAOMAountById(transferUpdate.getNewAmount(), transferUpdate.getNewMemAssetIdFrom());
	}

	//이체 항목을 delete하고 AOM을 업데이트 한다.
	@Transactional
	public void deleteTransferAndUpdateAOM(TransferUpdate transferUpdate) {
		//이체를 delete
		deleteTransferByTransferId(transferUpdate.getTransferId());
		//이체 내역 되돌리기
		plusAOMAmountById(transferUpdate.getAmount(), transferUpdate.getMemAssetIdFrom());
		minusAOMAountById(transferUpdate.getAmount(), transferUpdate.getMemAssetIdTo());
	}

	//이체 항목을 insert하고 AOM을 업데이트 한다.
	@Transactional
	public void insertTransferAndUpdateAom(Transfer transfer) {
		insertTransfer(transfer);
		minusAOMAountById(transfer.getAmount(), transfer.getMemAssetIdFrom());
		plusAOMAmountById(transfer.getAmount(), transfer.getMemAssetIdTo());
	}

}
