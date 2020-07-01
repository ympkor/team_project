package controller.main;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import dto.main.AssetsAssetsOfMember;
import dto.main.AssetsOfMember;
import dto.main.Calendar;
import dto.main.Expense;
import dto.main.ExpenseCategory;
import dto.main.ExpenseExpenseCategoryAssets;
import dto.main.IncomeUpdate;
import dto.main.IncomeCategory;
import dto.main.IncomeIncomeCategoryAssets;
import dto.main.UpdateAndRefresh;
import dto.main.MemberExpenseExpenseCategory;
import dto.main.MemberIncomeIncomeCategory;
import dto.main.SumAmounts;
import service.main.MainPageService;
import service.main.MainUpdateService;

@Controller
@RequestMapping("/main")
@SessionAttributes("userKey")
public class MainPageController {
	@Autowired
	private MainPageService mainService;
//	int userKey = 1;//나중에 세션으로 받을 아이디에 해당되는 key값
	
	//로그인후 보여줄 첫 화면
	@GetMapping("/getCal")
	public String getMain(@ModelAttribute("userKey")int userKey, Model m, @RequestParam(defaultValue = "1")String selecDate) {
		if(selecDate.equals("1")) {
			selecDate = LocalDate.now().toString();
		}
		Calendar cal = new Calendar(LocalDate.parse(selecDate));
		
		String curDate = selecDate.substring(0, 7);
		//페이지가 처음 생성되었을 때 뿌려줄 데이터들을 전부 설정
		List<MemberIncomeIncomeCategory> miicList = mainService.selectMIICByUserKeyAndDate(userKey, curDate); //달력에 뿌려줄 날짜별 수입 데이터
		List<MemberExpenseExpenseCategory> meecList = mainService.selectMEECByUserKeyAndDate(userKey, curDate);//달력에 뿌려줄 날짜별 지출 데이터
		List<IncomeIncomeCategoryAssets> iicaList = mainService.selectIICAByUserKeyAndDate(userKey, LocalDate.now().toString());//상세내역에 뿌려줄 오늘 기준 수입 상세 데이터
		List<ExpenseExpenseCategoryAssets> eecaList = mainService.selectEECAByUserKeyAndDate(userKey, LocalDate.now().toString());//상세내역에 뿌려줄 오늘 기준 지출 상세 데이터
		List<IncomeCategory> icList = mainService.selectAllIC();//insert, update에서 사용자에게 보여줄 수입카테고리
		List<ExpenseCategory> ecList = mainService.selectAllEc();//insert, update에서 사용자에게 보여줄 지출 카테고리
		List<AssetsAssetsOfMember> aaomList = mainService.selectAAOMByUserKey(userKey);//insert,update에서 사용자에게 보여줄 사용자가 등록한 자산항목들
		SumAmounts sumAmounts = mainService.selectSUMIEByUserKeyAndDate(userKey, curDate);
		m.addAttribute("cal", cal);
		m.addAttribute("miicList", miicList);
		m.addAttribute("meecList", meecList);
		m.addAttribute("iicaList", iicaList);
		m.addAttribute("eecaList", eecaList);
		m.addAttribute("icList", icList);
		m.addAttribute("ecList", ecList);
		m.addAttribute("aaomList", aaomList);
		m.addAttribute("sumAmounts", sumAmounts);
		return "mainPage";
	}
	
	//날짜가 변경되면 달력 정보를 변경해줄 달력 객체 반환
	@PostMapping(value = "/postCal")
	@ResponseBody
	public Calendar postMain(String selecDate) {
		selecDate += "-01";
		Calendar cal = new Calendar(LocalDate.parse(selecDate));
		return cal;
	}
	
	//날짜가 변경되면 달력에 뿌려줄 날짜별 수입 데이터 객체 반환
	@PostMapping(value = "/postMIIC")
	public @ResponseBody List<MemberIncomeIncomeCategory> getMIICList(@ModelAttribute("userKey")int userKey, @RequestParam(name = "selecDate")String selecDate) {
//		System.out.println("컨트롤러 메서드 실행됨, 받은 selecDate 값 : "+selecDate);
		List<MemberIncomeIncomeCategory> miicList = mainService.selectMIICByUserKeyAndDate(userKey, selecDate);
//		System.out.println("컨트롤러에서 서비스 실행 후 반환된 리스트 : "+miicList);
		return miicList;
	}
	
	//날짜가 변경되면 달력에 뿌려줄 날짜별 지출 데이터 객체 반환 
	@PostMapping("/postMEEC")
	public @ResponseBody List<MemberExpenseExpenseCategory> getMEECList(@ModelAttribute("userKey")int userKey, @RequestParam(name = "selecDate")String expenseDate) {
		List<MemberExpenseExpenseCategory> meecList = mainService.selectMEECByUserKeyAndDate(userKey, expenseDate);
		return meecList;
	}
	
	//날짜를 선택하면 그 날에 해당되는 수입상세내역 반환
	@PostMapping("/postIICA")
	public @ResponseBody List<IncomeIncomeCategoryAssets> getIICAList(@ModelAttribute("userKey")int userKey, @RequestParam(name = "currentDate")String incomeDate) {
		List<IncomeIncomeCategoryAssets> iicaList = mainService.selectIICAByUserKeyAndDate(userKey, incomeDate);
		return iicaList;
	}
	
	//날짜를 선택하면 그 날에 해당되는 지출상세내역 반환
	@PostMapping("/postEECA")
	public @ResponseBody List<ExpenseExpenseCategoryAssets> getEECAList(@ModelAttribute("userKey")int userKey, @RequestParam(name = "currentDate")String expenseDate) {
		List<ExpenseExpenseCategoryAssets> eecaList = mainService.selectEECAByUserKeyAndDate(userKey, expenseDate);
		return eecaList;
	}
	
	//assetsId를 받아서 assets of member 조회 후 반환
	@PostMapping("/postAOM")
	public @ResponseBody List<AssetsOfMember> getAOMList(@ModelAttribute("userKey")int userKey, @RequestParam(name = "assetsId")int assetsId) {
		List<AssetsOfMember> aomList =  mainService.selectAOMByUserKeyAndAssetsId(userKey, assetsId);
		return aomList;
	}
}