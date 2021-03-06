package mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.DailyExpense;
import dto.DailyIncome;
import dto.Expense;
import dto.Income;
import dto.Member;

public interface MemberMapper {
	
	public Expense selectOneEx(int userKey);
	
	public Income selectOneIn(int userKey);
	
	public List<DailyExpense> selectDailyExpense(@Param("userKey")int userKey, @Param("expenseDate")LocalDate expenseDate);
	public List<DailyIncome> selectDailyIncome(@Param("userKey")int userKey, @Param("incomeDate")LocalDate incomeDate);
	
	//주별로 사용한 금액과 벌어들인 금액을 리스트에 받아옴
	public DailyIncome selectWeekIncome(@Param("userKey")int userKey
			, @Param("weekDate")LocalDate weekDate);
	public DailyExpense selectWeekExpense(@Param("userKey")int userKey
			, @Param("weekDate")LocalDate weekDate);
	
	public List<DailyExpense> selectMonthExpense(@Param("userKey")int userKey
			, @Param("startDate")LocalDate startDate,@Param("endDate")LocalDate endDate);
	public List<DailyIncome> selectMonthIncome(@Param("userKey")int userKey
			, @Param("startDate")LocalDate startDate,@Param("endDate")LocalDate endDate);
	
	public List<DailyExpense> selectYearExpense(@Param("userKey")int userKey
			, @Param("year")int year);
	public List<DailyIncome> selectYearIncome(@Param("userKey")int userKey
			, @Param("year")int year);	
	
	//탈퇴시 수입,지출 전부 삭제
	public void deleteIncomeByuserkeyAll(int userKey);
	public void deleteExpenseByuserkeyAll(int userKey);
	
	//탈퇴시 이체기록 전부삭제
	public void deleteTransferByuserkeyAll(int userKey);
	
}
