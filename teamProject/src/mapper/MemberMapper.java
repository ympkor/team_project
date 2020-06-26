package mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.DailyExpense;
import dto.DailyIncome;
import dto.Expense;
import dto.Income;

public interface MemberMapper {
	
	public Expense selectOneEx(int userKey);
	
	public Income selectOneIn(int userKey);
	
	public List<DailyExpense> selectDailyExpense(@Param("userKey")int userKey, @Param("expenseDate")LocalDate expenseDate);
	public List<DailyIncome> selectDailyIncome(@Param("userKey")int userKey, @Param("incomeDate")LocalDate incomeDate);
	
	public List<DailyIncome> selectWeekIncome(@Param("userKey")int userKey
			, @Param("weekDate")LocalDate weekDate);
	public List<DailyExpense> selectWeekExpense(@Param("userKey")int userKey
			, @Param("weekDate")LocalDate weekDate);
	
}
