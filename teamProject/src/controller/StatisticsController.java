package controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.DailyExpense;
import dto.DailyIncome;
import mapper.MemberMapper;
import service.StatisticsService;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	@Autowired
	MemberMapper mMapper;
	
	@Autowired
	StatisticsService stService;
		
	@GetMapping("/show")	
	public String showStatistics(Model m,@RequestParam(required = false, value="date" )@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate date) {
		if (date==null) {date= LocalDate.now(); }
		List<DailyExpense> de= mMapper.selectDailyExpense(1, date);
		List<DailyIncome> di = mMapper.selectDailyIncome(1, date);
		
		Map<String, List> weekEandI =stService.getWeekData(1, date);
		List<DailyExpense> weekExpense= weekEandI.get("weekExpense");
		List<DailyIncome> weekIncome = weekEandI.get("weekIncome");
		
		List<DailyExpense> monthExpense=stService.getMonthExpenseData(1, date);
		List<DailyIncome> monthIncome=stService.getMonthIncomData(1, date);
		
		int[] yearExpenseData = stService.getYearExpenseData(1, date);
		int[] yearIncomeData = stService.getYearIncomeData(1, date);
		
		LocalDate lastmonth = date.minusMonths(1);
		LocalDate nextmonth = date.plusMonths(1);
		LocalDate lastyear = date.minusYears(1);
		LocalDate nextyear = date.plusYears(1);
		
		m.addAttribute("dailyExpense", de);
		m.addAttribute("dailyIncome", di);
		m.addAttribute("weekExpense", weekExpense);
		m.addAttribute("weekIncome", weekIncome);
		m.addAttribute("monthExpense", monthExpense);
		m.addAttribute("monthIncome", monthIncome);
		m.addAttribute("yearExpenseData", yearExpenseData);
		m.addAttribute("yearIncomeData", yearIncomeData);
		m.addAttribute("date", date);
		m.addAttribute("lastmonth", lastmonth);
		m.addAttribute("nextmonth", nextmonth);
		m.addAttribute("lastyear", lastyear);
		m.addAttribute("nextyear", nextyear);
		return "statistics";
	}
}
