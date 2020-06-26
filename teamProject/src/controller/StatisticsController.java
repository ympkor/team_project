package controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping("/show")
	public String showStatistics(Model m,@RequestParam(required = false, value="day" )@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate day) {
		 if (day==null) {
			day= LocalDate.now();
		}
		List<DailyExpense> de= mMapper.selectDailyExpense(1, day);
		List<DailyIncome> di = mMapper.selectDailyIncome(1, day);
		
		Map<String, List> weekEandI =stService.getWeekData(1, day);
		List<DailyExpense> weekexpense= weekEandI.get("expense");
		List<DailyIncome> weekincome = weekEandI.get("income");
			
		
		m.addAttribute("dailyExpense", de);
		m.addAttribute("dailyIncome", di);
		m.addAttribute("weekexpense", weekexpense);
		m.addAttribute("weekincome", weekincome);
		//System.out.println(LocalDate.of(2020, 6, 24));
		
		return "statistics";
	}
	
}
