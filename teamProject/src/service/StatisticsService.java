package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.DailyExpense;
import dto.DailyIncome;
import mapper.MemberMapper;

@Service
public class StatisticsService {

	@Autowired
	MemberMapper mMapper;
	
	public Map<String, List> getWeekData(int userKey,LocalDate date){
		String key = date.getDayOfWeek().toString();
		LocalDate startDate = null;
		LocalDate endDate = null;
		Map<String, List> weekEandI = new HashMap<String, List>();
		List<DailyExpense> weekExpense=new ArrayList<DailyExpense>();
		List<DailyIncome> weekIncom=new ArrayList<DailyIncome>();
		switch (key) {
		case "SUNDAY":
			startDate = date.minusDays(0);
			endDate = date.plusDays(7);
			for (int i = 0; i < 7; i++) {
				DailyExpense dayExpense = new DailyExpense();
				DailyIncome dayIncome = new DailyIncome();
				LocalDate weekdate = startDate.plusDays(i);
				dayExpense.setExpenseDate(weekdate);
				dayIncome.setIncomeDate(weekdate);
				int expensesAmount=0;
				List<DailyExpense> eamount = mMapper.selectWeekExpense(userKey, weekdate);
				if(eamount != null) {
					for (DailyExpense d : eamount) {
						expensesAmount+=d.getAmount();
					}
				}
				dayExpense.setAmount(expensesAmount);
				weekExpense.add(dayExpense);
				int incomeAmount=0;
				List<DailyIncome> iamount = mMapper.selectWeekIncome(userKey, weekdate);
				if(iamount != null) {
					for (DailyIncome ia : iamount) {
						incomeAmount+=ia.getAmount();
					}
				}
				dayIncome.setAmount(incomeAmount);
				weekIncom.add(dayIncome);				
			}
			weekEandI.put("income", weekIncom);
			weekEandI.put("expense",weekExpense);
			break;
		case "MONDAY":
			startDate = date.minusDays(1);
			endDate = date.plusDays(6);
			for (int i = 0; i < 7; i++) {
				DailyExpense dayExpense = new DailyExpense();
				DailyIncome dayIncome = new DailyIncome();
				LocalDate weekdate = startDate.plusDays(i);
				dayExpense.setExpenseDate(weekdate);
				dayIncome.setIncomeDate(weekdate);
				int expensesAmount=0;
				List<DailyExpense> eamount = mMapper.selectWeekExpense(userKey, weekdate);
				if(eamount != null) {
					for (DailyExpense d : eamount) {
						expensesAmount+=d.getAmount();
					}
				}
				dayExpense.setAmount(expensesAmount);
				weekExpense.add(dayExpense);
				int incomeAmount=0;
				List<DailyIncome> iamount = mMapper.selectWeekIncome(userKey, weekdate);
				if(iamount != null) {
					for (DailyIncome ia : iamount) {
						incomeAmount+=ia.getAmount();
					}
				}
				dayIncome.setAmount(incomeAmount);
				weekIncom.add(dayIncome);				
			}
			weekEandI.put("income", weekIncom);
			weekEandI.put("expense",weekExpense);
			break;			
		case "TUESDAY":
			startDate = date.minusDays(2);
			endDate = date.plusDays(5);
			for (int i = 0; i < 7; i++) {
				DailyExpense dayExpense = new DailyExpense();
				DailyIncome dayIncome = new DailyIncome();
				LocalDate weekdate = startDate.plusDays(i);
				dayExpense.setExpenseDate(weekdate);
				dayIncome.setIncomeDate(weekdate);
				int expensesAmount=0;
				List<DailyExpense> eamount = mMapper.selectWeekExpense(userKey, weekdate);
				if(eamount != null) {
					for (DailyExpense d : eamount) {
						expensesAmount+=d.getAmount();
					}
				}
				dayExpense.setAmount(expensesAmount);
				weekExpense.add(dayExpense);
				int incomeAmount=0;
				List<DailyIncome> iamount = mMapper.selectWeekIncome(userKey, weekdate);
				if(iamount != null) {
					for (DailyIncome ia : iamount) {
						incomeAmount+=ia.getAmount();
					}
				}
				dayIncome.setAmount(incomeAmount);
				weekIncom.add(dayIncome);				
			}
			weekEandI.put("income", weekIncom);
			weekEandI.put("expense",weekExpense);
			break;
			
		case "WEDNESDAY":
			startDate = date.minusDays(3);
			endDate = date.plusDays(3);
			for (int i = 0; i < 7; i++) {
				DailyExpense dayExpense = new DailyExpense();
				DailyIncome dayIncome = new DailyIncome();
				LocalDate weekdate = startDate.plusDays(i);
				dayExpense.setExpenseDate(weekdate);
				dayIncome.setIncomeDate(weekdate);
				int expensesAmount=0;
				List<DailyExpense> eamount = mMapper.selectWeekExpense(userKey, weekdate);
				if(eamount != null) {
					for (DailyExpense d : eamount) {
						expensesAmount+=d.getAmount();
					}
				}
				dayExpense.setAmount(expensesAmount);
				weekExpense.add(dayExpense);
				int incomeAmount=0;
				List<DailyIncome> iamount = mMapper.selectWeekIncome(userKey, weekdate);
				if(iamount != null) {
					for (DailyIncome ia : iamount) {
						incomeAmount+=ia.getAmount();
					}
				}
				dayIncome.setAmount(incomeAmount);
				weekIncom.add(dayIncome);				
			}
			weekEandI.put("income", weekIncom);
			weekEandI.put("expense",weekExpense);
			break;
		case "THURSDAY":
			startDate = date.minusDays(4);
			endDate = date.plusDays(2);
			for (int i = 0; i < 7; i++) {
				DailyExpense dayExpense = new DailyExpense();
				DailyIncome dayIncome = new DailyIncome();
				LocalDate weekdate = startDate.plusDays(i);
				dayExpense.setExpenseDate(weekdate);
				dayIncome.setIncomeDate(weekdate);
				int expensesAmount=0;
				List<DailyExpense> eamount = mMapper.selectWeekExpense(userKey, weekdate);
				if(eamount != null) {
					for (DailyExpense d : eamount) {
						expensesAmount+=d.getAmount();
					}
				}
				dayExpense.setAmount(expensesAmount);
				weekExpense.add(dayExpense);
				int incomeAmount=0;
				List<DailyIncome> iamount = mMapper.selectWeekIncome(userKey, weekdate);
				if(iamount != null) {
					for (DailyIncome ia : iamount) {
						incomeAmount+=ia.getAmount();
					}
				}
				dayIncome.setAmount(incomeAmount);
				weekIncom.add(dayIncome);				
			}
			weekEandI.put("income", weekIncom);
			weekEandI.put("expense",weekExpense);
			break;
		case "FRIDAY":
			startDate = date.minusDays(5);
			endDate = date.plusDays(1);
			for (int i = 0; i < 7; i++) {
				DailyExpense dayExpense = new DailyExpense();
				DailyIncome dayIncome = new DailyIncome();
				LocalDate weekdate = startDate.plusDays(i);
				dayExpense.setExpenseDate(weekdate);
				dayIncome.setIncomeDate(weekdate);
				int expensesAmount=0;
				List<DailyExpense> eamount = mMapper.selectWeekExpense(userKey, weekdate);
				if(eamount != null) {
					for (DailyExpense d : eamount) {
						expensesAmount+=d.getAmount();
					}
				}
				dayExpense.setAmount(expensesAmount);
				weekExpense.add(dayExpense);
				int incomeAmount=0;
				List<DailyIncome> iamount = mMapper.selectWeekIncome(userKey, weekdate);
				if(iamount != null) {
					for (DailyIncome ia : iamount) {
						incomeAmount+=ia.getAmount();
					}
				}
				dayIncome.setAmount(incomeAmount);
				weekIncom.add(dayIncome);				
			}
			weekEandI.put("income", weekIncom);
			weekEandI.put("expense",weekExpense);
			break;			
		case "SATURDAY":
			startDate = date.minusDays(4);
			endDate = date.plusDays(2);
			for (int i = 0; i < 7; i++) {
				DailyExpense dayExpense = new DailyExpense();
				DailyIncome dayIncome = new DailyIncome();
				LocalDate weekdate = startDate.plusDays(i);
				dayExpense.setExpenseDate(weekdate);
				dayIncome.setIncomeDate(weekdate);
				int expensesAmount=0;
				List<DailyExpense> eamount = mMapper.selectWeekExpense(userKey, weekdate);
				if(eamount != null) {
					for (DailyExpense d : eamount) {
						expensesAmount+=d.getAmount();
					}
				}
				dayExpense.setAmount(expensesAmount);
				weekExpense.add(dayExpense);
				int incomeAmount=0;
				List<DailyIncome> iamount = mMapper.selectWeekIncome(userKey, weekdate);
				if(iamount != null) {
					for (DailyIncome ia : iamount) {
						incomeAmount+=ia.getAmount();
					}
				}
				dayIncome.setAmount(incomeAmount);
				weekIncom.add(dayIncome);				
			}
			weekEandI.put("income", weekIncom);
			weekEandI.put("expense",weekExpense);
			break;
		}
		return weekEandI;	
	}
	
}
