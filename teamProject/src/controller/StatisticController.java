package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/statistics")
public class StatisticController {
//	@Autowired
//	MemeberMapper mMapper;
	
	@RequestMapping("/show")
	public String showTonggye(Model m) {
//		Expense e = mMapper.selectOneEx(1);
//		Income i = mMapper.selectOneIn(1);
//		System.out.println("지출"+e);
//		System.out.println("수익"+i);
//		m.addAttribute("expense", e);
//		m.addAttribute("income", i);		
		return "statistics";
	}
	
	
}
