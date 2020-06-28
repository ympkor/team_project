package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
		LocalDate startDate = null;
		//LocalDate endDate = null;
		//주별 수입 지출 정보를 가지고 있는 맵
		Map<String, List> weekEandI = new HashMap<String, List>();
		//각 수입,지출 정보를 담을 리스트 생성
		List<DailyExpense> weekExpense=new ArrayList<DailyExpense>();
		List<DailyIncome> weekIncom=new ArrayList<DailyIncome>();
		
		//현재날짜의 요일을 숫자로 나타내기 위한 로직
		Calendar time = Calendar.getInstance();
		time.set(date.getYear(), date.getMonthValue()-1, date.getDayOfMonth());		
		int dayOfWeek= time.get(Calendar.DAY_OF_WEEK);
		//일요일은1~ 토요일 7 까지 
		for (int i = 1; i <= 7; i++) {
			if(i==dayOfWeek) {
				//각 요일숫자를 이용해서 시작요일을 그 주의 일요일로 지정
				startDate = date.minusDays(i-1);
				//endDate = date.plusDays(7-i);
				for (int j = 0; j < 7; j++) {
					//매일의 사용 금액, 수입을 저장하기 위해서 객체를 만들어줌
					DailyExpense dayExpense = new DailyExpense();
					DailyIncome dayIncome = new DailyIncome();
					//사용금액이 없는 날도 객체를 만들어 주기 위해서 매 날짜마다 날짜객체를 생성
					LocalDate weekdate = startDate.plusDays(j);
					//만들어진 날짜를 사용금액, 수입을 저장한 객체에 세팅해줌
					dayExpense.setExpenseDate(weekdate);
					dayIncome.setIncomeDate(weekdate);
					
					int expensesAmount=0;
					//그 날에 사용한 금액이 들어있는 객체
					DailyExpense eamount = mMapper.selectWeekExpense(userKey, weekdate);
					//사용한 금액이 없으면 객체가 null로 나오므로 객체가 있으면 객체의 사용금액을 넣어주고 없으면 그대로 0이 됨
					if(eamount != null) {
						expensesAmount=eamount.getAmount();
					}
					//첫째날의 
					dayExpense.setAmount(expensesAmount);
					weekExpense.add(dayExpense);
					int incomeAmount=0;
					//그 날에 수입액수가 들어있는 객체
					DailyIncome iamount = mMapper.selectWeekIncome(userKey, weekdate);
					if(iamount != null) {
						incomeAmount=iamount.getAmount();
					}
					dayIncome.setAmount(incomeAmount);
					weekIncom.add(dayIncome);				
				}
				//주별로 만들어진 수입, 지출 리스트를 해시맵에 저장 
				weekEandI.put("weekIncome", weekIncom);
				weekEandI.put("weekExpense",weekExpense);
			}
		}	
		//만들어진 해시맵을 리턴해줌
		return weekEandI;	
	}

	public List<DailyExpense> getMonthExpenseData(int userKey,LocalDate date) {
		
		LocalDate startDate =LocalDate.of(date.getYear(), date.getMonthValue(), 1);		
		LocalDate endmonth =date.plusMonths(1);		
		LocalDate endDate = LocalDate.of(endmonth.getYear(),endmonth.getMonthValue(),1);
		
		return mMapper.selectMonthExpense(userKey, startDate, endDate);
	}
	public List<DailyIncome> getMonthIncomData(int userKey,LocalDate date) {
		LocalDate startDate =LocalDate.of(date.getYear(), date.getMonthValue(), 1);		
		LocalDate endmonth =date.plusMonths(1);		
		LocalDate endDate = LocalDate.of(endmonth.getYear(),endmonth.getMonthValue(),1);
		
		return mMapper.selectMonthIncome(userKey, startDate, endDate);
	}
	
	//배열로 월별 지출금액 받아오기
	public int[] getYearExpenseData(int userKey,LocalDate date) {
		List<DailyExpense> yearExpense= mMapper.selectYearExpense(userKey, date.getYear());
		int[] yearExpenseData = {0,0,0,0,0,0,0,0,0,0,0,0};
		for (DailyExpense ye : yearExpense) {
			for (int i = 1; i <= 12; i++) {
				if(i==ye.getExpenseDate().getMonthValue()) {
					yearExpenseData[i-1]=ye.getAmount();
				}
			}		
		}
		return yearExpenseData;
	}
	//배열로 월별 수익금액 받아오기
	public int[] getYearIncomeData(int userKey,LocalDate date) {
		List<DailyIncome> yearIncome= mMapper.selectYearIncome(userKey, date.getYear());
		int[] yearIncomeData = {0,0,0,0,0,0,0,0,0,0,0,0};
		for (DailyIncome yi : yearIncome) {
			for (int i = 1; i <= 12; i++) {
				if(i==yi.getIncomeDate().getMonthValue()) {
					yearIncomeData[i-1]=yi.getAmount();
				}
			}		
		}
		return yearIncomeData;
	}
}
