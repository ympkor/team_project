package controller.main;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.main.AssetsAssetsOfMember;
import dto.main.AssetsOfMember;
import dto.main.Calendar;
import dto.main.ExpenseCategory;
import dto.main.ExpenseExpenseCategoryAssets;
import dto.main.IncomeCategory;
import dto.main.IncomeIncomeCategoryAssets;
import dto.main.MemberExpenseExpenseCategory;
import dto.main.MemberIncomeIncomeCategory;
import dto.main.SumAmounts;
import dto.main.Transfer;
import dto.main.TransferAssetsOfMemeberFromAssetsOfMemeberTo;
import service.main.MainPageService;

@Controller
@SessionAttributes("userKey")
@RequestMapping("/main")
public class MainPageController {
	@Autowired
	private MainPageService mainService;
	
//	로그인 없이 사용하기 위한 임시 세션값 설정
	@GetMapping("/getSession")
	public String getSession(Model m) {
		m.addAttribute("userKey", 1);
		return "getSession";
	}
	
	//로그인후 보여줄 첫 화면
	@GetMapping("/getCal")
	public String getMain(Model m, @RequestParam(name = "selecDate", defaultValue = "1")String selecDate) throws JsonProcessingException {
		if(m.getAttribute("userKey") == null) {
			return "callForLogin";
		}
		int userKey = (int)m.getAttribute("userKey");
		if(selecDate.equals("1")) {
			selecDate = LocalDate.now().toString();
		}
		Calendar cal = new Calendar(LocalDate.parse(selecDate));
		
		String curDate = selecDate.substring(0, 7);
		//페이지가 처음 생성되었을 때 뿌려줄 데이터들을 전부 설정
		List<MemberIncomeIncomeCategory> miicList = mainService.selectMIICByUserKeyAndDate(userKey, curDate); //달력에 뿌려줄 날짜별 수입 데이터
		List<MemberExpenseExpenseCategory> meecList = mainService.selectMEECByUserKeyAndDate(userKey, curDate);//달력에 뿌려줄 날짜별 지출 데이터
		List<Transfer> transferByMonthList = mainService.selectTransferByUserKeyAndDate(userKey, curDate);
		List<IncomeIncomeCategoryAssets> iicaList = mainService.selectIICAByUserKeyAndDate(userKey, LocalDate.now().toString());//상세내역에 뿌려줄 오늘 기준 수입 상세 데이터
		List<ExpenseExpenseCategoryAssets> eecaList = mainService.selectEECAByUserKeyAndDate(userKey, LocalDate.now().toString());//상세내역에 뿌려줄 오늘 기준 지출 상세 데이터
		List<TransferAssetsOfMemeberFromAssetsOfMemeberTo> taomfaomtList = mainService.selectTAOMFAOMTByUserKeyAndDate(userKey, LocalDate.now().toString());//상세내역에 뿌려줄 오늘 기준 이체 상세 데이터
		List<IncomeCategory> icList = mainService.selectAllIC();//insert, update에서 사용자에게 보여줄 수입카테고리
		List<ExpenseCategory> ecList = mainService.selectAllEc();//insert, update에서 사용자에게 보여줄 지출 카테고리
		List<AssetsAssetsOfMember> aaomList = mainService.selectAAOMByUserKey(userKey);//insert,update에서 사용자에게 보여줄 사용자가 등록한 자산항목들
		SumAmounts sumAmounts = mainService.selectSUMIEByUserKeyAndDate(userKey, curDate);//해당 월의 수입과 지출의 총 내역
		//페이지 로드 시 리스트를 함수에 변수로 넣기 위해서 JSON으로 변환 첨부했다.
		ObjectMapper mapper = new ObjectMapper();
		String aaomListJ = mapper.writeValueAsString(aaomList);
		String tbmListJ = mapper.writeValueAsString(transferByMonthList);
		String sumAmountsJ = mapper.writeValueAsString(sumAmounts);
		String taomfaomtListJ = mapper.writeValueAsString(taomfaomtList);
		String iicaListJ = mapper.writeValueAsString(iicaList);
		String eecaListJ = mapper.writeValueAsString(eecaList);
		String miicListJ = mapper.writeValueAsString(miicList);
		String meecListJ = mapper.writeValueAsString(meecList);
		
		m.addAttribute("taomfaomtListJ", taomfaomtListJ);
		m.addAttribute("sumAmountsJ", sumAmountsJ);
		m.addAttribute("tbmListJ", tbmListJ);
		m.addAttribute("aaomListJ", aaomListJ);
		m.addAttribute("cal", cal);
		m.addAttribute("miicListJ", miicListJ);
		m.addAttribute("meecListJ", meecListJ);
		m.addAttribute("iicaListJ", iicaListJ);
		m.addAttribute("eecaListJ", eecaListJ);
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
	
	//날짜가 변경되면 달력에 뿌려줄 날짜별 이체 데이터 반환 
	@PostMapping("/postTBM")
	public @ResponseBody List<Transfer> getTBMList(@ModelAttribute("userKey")int userKey, @RequestParam(name = "selecDate")String transferDate) {
		List<Transfer> tbmList = mainService.selectTransferByUserKeyAndDate(userKey, transferDate);
		return tbmList;
	}
	
	//날짜가 변경되면 해당 월의 합계를 반환
	@PostMapping("/postSumAmounts")
	public @ResponseBody SumAmounts getSumAmounts(@ModelAttribute("userKey")int userKey, @RequestParam("currentDate")String currentDate) {
		return mainService.selectSUMIEByUserKeyAndDate(userKey, currentDate);
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
	
	//상세데이터에 출력할 이체 데이터 반환
	@PostMapping("/postTAOMFAOMT")
	public @ResponseBody List<TransferAssetsOfMemeberFromAssetsOfMemeberTo> getTAOMFAOMTList(@ModelAttribute("userKey")int userKey, @RequestParam(name = "currentDate")String transferDate) {
		List<TransferAssetsOfMemeberFromAssetsOfMemeberTo> taomfaomtList = mainService.selectTAOMFAOMTByUserKeyAndDate(userKey, transferDate);
		return taomfaomtList;
	}
	
	//IncomeCategory List를 반환
	@PostMapping("/postICList")
	public @ResponseBody List<IncomeCategory> getICList() {
		List<IncomeCategory> icList = mainService.selectAllIC();
		return icList;
	}

	//ExpenseCategory List를 반환
	@PostMapping("/postECList")
	public @ResponseBody List<ExpenseCategory> getECList() {
		List<ExpenseCategory> ecList = mainService.selectAllEc();
		return ecList;
	}
	
	//userKey에 해당하는 자산과 자산항목을 반환(자산항목 보여주기용)
	@PostMapping("/postAAOM")
	public @ResponseBody List<AssetsAssetsOfMember> getAAOMList(int userKey) {
		List<AssetsAssetsOfMember> aaomList = mainService.selectAAOMByUserKey(userKey);
		return aaomList;
	}
	
	//assetsId를 받아서 assets of member 조회 후 반환
	@PostMapping("/postAOM")
	public @ResponseBody List<AssetsOfMember> getAOMList(@ModelAttribute("userKey")int userKey, @RequestParam(name = "assetsId")int assetsId) {
		List<AssetsOfMember> aomList =  mainService.selectAOMByUserKeyAndAssetsId(userKey, assetsId);
		return aomList;
	}
	
	//memAssetId로 AOM조회후 객체 반환
	@PostMapping("/postGetAOM")
	public @ResponseBody AssetsOfMember selectAOMById(@RequestParam(name = "memAssetId")int memAssetId) {
		AssetsOfMember aom = mainService.selectAOMByMemAssetId(memAssetId);
		return aom;
	}
}