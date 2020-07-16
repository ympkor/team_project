package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.AssetOfMember;
import dto.SearchDto;
import service.AssetNewsService;
import service.AssetOfMemberService;

@Controller
@RequestMapping("/asset")
@SessionAttributes("userKey")
public class AssetOfMemberController {

	@Autowired AssetOfMemberService aomService;

	//자산페이지 호출시
	@RequestMapping("/view")
	public String getMemberAsset(HttpSession session, Model m) throws JsonProcessingException{

		if(session.getAttribute("userKey")==null) {
			return "callForLogin";
		}
		int userKey =(int)session.getAttribute("userKey");
		
		//userKey값에 해당하는 자산을 배열로 저장
		List<AssetOfMember> aomList = aomService.selectAssetListById(userKey);
		List<AssetOfMember> assetList = aomService.selectOnlyAssetListById(userKey);
		List<AssetOfMember> debtList = aomService.selectOnlyDebtListById(userKey);
		List<AssetOfMember> zeroList = aomService.selectOnlyZeroListById(userKey);

		//그래프 구현을 위해 자산합계, 부채합계, 총합계를 따로 계산하여 보내줌
		int i = 0;
		int sumAssets = 0;
		int sumDebts = 0;

		while(aomList.size() > i) {
			if (aomList.get(i).getAmount() > 0) {
				sumAssets += aomList.get(i).getAmount();
			}else if (aomList.get(i).getAmount() < 0) {
				sumDebts += aomList.get(i).getAmount();
			}
			i++;
		}
		int sumTotal = sumAssets + sumDebts;

		//자산, 부채 건수 집계
		int j=0;
		int cntAssets = 0;
		int cntDebts = 0;
		while(aomList.size() > j) {
			if (aomList.get(j).getType().equals("자산")) {
				cntAssets++;
			}else if (aomList.get(j).getType().equals("부채")) {
				cntDebts++;
			}
			j++;
		}

		//자산관련 뉴스 출력 부분
		//해당 멤버 객체생성하고 키워드와 기사 숫자 불러오기
		AssetOfMember aom = aomService.getNewsSettingsInfo(userKey);
		String newsKeywords = aom.getNewsKeywords();
		int newsCounts = aom.getNewsCounts();

		//뉴스 객체 생성하고 설정값 대입
		AssetNewsService ans = new AssetNewsService();
		ans.setKeywords(newsKeywords);
		ans.setDisplays(newsCounts);

		//뉴스 생성 메서드 실행 후 json배열 형태로 자산페이지 전달
		StringBuilder newsString = ans.getNews();
		JSONObject jsonObject = new JSONObject(newsString.toString());
		JSONArray jsonArray = jsonObject.getJSONArray("items");
		
		ObjectMapper mapper = new ObjectMapper();
		String assetsListJ = mapper.writeValueAsString(assetList);

		m.addAttribute("assetsListJ", assetsListJ);
		m.addAttribute("aomList", aomList);
		m.addAttribute("assetList", assetList);
		m.addAttribute("debtList", debtList);
		m.addAttribute("zeroList", zeroList);
		m.addAttribute("sumTotal", sumTotal);
		m.addAttribute("sumAsset", sumAssets);
		m.addAttribute("sumDebt", sumDebts);
		m.addAttribute("assetRatioValue", sumAssets);
		m.addAttribute("debtRatioValue", sumDebts);
		m.addAttribute("newsArr", jsonArray);
		m.addAttribute("newsKeywords", newsKeywords);
		m.addAttribute("newsCounts", newsCounts);
		m.addAttribute("cntAssets", cntAssets);
		m.addAttribute("cntDebts", cntDebts);
		return "showAsset";
	}

	//자산추가 클릭시
	@RequestMapping("/add")
	public String showAddForm(HttpSession session) {
		if(session.getAttribute("userKey")==null) {
			return "callForLogin";
		}
		int userKey =(int)session.getAttribute("userKey");
		return "addAssetForm";
	}

	//신규자산 입력 후 submit 클릭시
	@RequestMapping("/addAsset")
	public String addAsset(HttpSession session, 
			Model m, AssetOfMember aom, AssetOfMember toCal) {
		
		if(session.getAttribute("userKey")==null) {
			return "callForLogin";
		}
		int userKey =(int)session.getAttribute("userKey");
		
		aomService.addAsset(aom);

		//자산입력시 수입지출내역 기록에 체크했을 경우
		if (aom.getSync()==1) {
			//각 필드 입력값이 동일한 객체 추가생성 (main으로 넘기기 위함)
			toCal = aomService.getLastAssetByUserKey(userKey);

			//type(자산/부채) 확인해서 부채인경우 +값으로 변경
			//income, expense 테이블에서는 +값으로 존재
			int amountOfMain = toCal.getAmount();

			if (amountOfMain>0) {
				aomService.addAssetToIncome(toCal);
			} else if (amountOfMain<0){
				toCal.setAmount(amountOfMain*-1);
				aomService.addAssetToExpense(toCal);
			}
			return "addAssetResult";
		} else {
			return "addAssetResult";
		}
	}

	//각각의 자산에서 '수정' 클릭시
	@RequestMapping("/edit")
	public String showeditForm(HttpSession session, 
			int memAssetId, Model m, AssetOfMember aomBefore) {
		
		if(session.getAttribute("userKey")==null) {
			return "callForLogin";
		}
		int userKey =(int)session.getAttribute("userKey");
		
		//해당 자산 객체 생성하여 수정 폼으로 넘김
		aomBefore = aomService.getAssetById(memAssetId);
		aomBefore.setUserKey(userKey);
		int originalAmount = aomBefore.getAmount();
		aomBefore.setAmountBefore(originalAmount);
		m.addAttribute("aom", aomBefore);
		return "editAssetForm";
	}

	//자산 수정 폼 입력 후 '수정완료' 클릭시
	@RequestMapping("/editAsset")
	public String editAsset(HttpSession session, 
			int memAssetId, Model m,  AssetOfMember aom) {
		
		if(session.getAttribute("userKey")==null) {
			return "callForLogin";
		}
		int userKey =(int)session.getAttribute("userKey");
		
		aom.setUserKey(userKey);

		//새 값을 자산 테이블에 전송하고 완료
		aomService.editAsset(aom);


		//기존값과 신규값을 비교하여 수입/지출 테이블 쿼리 실행
		//변동사항 수입/지출 기록 옵션 선택한 경우(sync==1)에만 실행
		if (aom.getSync()==1) {

			int originalAmount = aom.getAmountBefore();
			int newAmount = aom.getAmount();
			//해당 자산의 금액이 늘어난 경우 차액을 income으로 전달
			if (newAmount>originalAmount) {
				int incAsset = newAmount-originalAmount;
				aom.setAmount(incAsset);
				aomService.editAssetToIncome(aom);

				//해당 자산의 금액이 늘어난 경우 차액을 expense로 전달
			} else if (newAmount<originalAmount) {
				int decAsset = originalAmount-newAmount;
				aom.setAmount(decAsset);
				aomService.editAssetToExpense(aom);;
			}
		}
		return "editAssetResult";
	}

	//각 자산 '삭제' 클릭시
	@RequestMapping("/delete")
	public String delAsset(HttpSession session,int memAssetId, Model m) {
		
		if(session.getAttribute("userKey")==null) {
			return "callForLogin";
		}
		int userKey =(int)session.getAttribute("userKey");
		
		aomService.delAsset(memAssetId);
		return "deleteAssetResult";
	}

	//관련뉴스 '설정' 클릭시
	@RequestMapping("/newsSettings")
	public String newsSettingsView(HttpSession session, Model m) {
		
		if(session.getAttribute("userKey")==null) {
			return "callForLogin";
		}
		int userKey =(int)session.getAttribute("userKey");
		
		AssetOfMember mem = aomService.getNewsSettingsInfo(userKey);
		String newsKeywords = mem.getNewsKeywords();
		int newsCounts = mem.getNewsCounts();
		m.addAttribute("newsKeywords", newsKeywords);
		m.addAttribute("newsCounts", newsCounts);		
		return "newsSettingsForm";
	}

	@RequestMapping("/newsSettingsResult")
	public String newsSettings(HttpSession session, AssetOfMember aom) {
		
		if(session.getAttribute("userKey")==null) {
			return "callForLogin";
		}
		int userKey =(int)session.getAttribute("userKey");
		
		aom.setUserKey(userKey);

		if(aom.getNewsKeywords()=="") {
			aom.setNewsKeywords("자산 금리 저축 적금");
			aomService.setNews(aom);
		} else {
			aomService.setNews(aom);
		}
		return "newsSettingsResult";
	}

	@RequestMapping("/showMemo")
	public String showSearch(HttpSession session, SearchDto search, Model m) throws JsonProcessingException{
		
		if(session.getAttribute("userKey")==null) {
			return "callForLogin";
		}
		int userKey =(int)session.getAttribute("userKey");
		
		//userKey값에 해당하는 자산을 배열로 저장
		List<AssetOfMember> aomList = aomService.selectAssetListById(userKey);
		List<AssetOfMember> assetList = aomService.selectOnlyAssetListById(userKey);
		List<AssetOfMember> debtList = aomService.selectOnlyDebtListById(userKey);
		List<AssetOfMember> zeroList = aomService.selectOnlyZeroListById(userKey);
		
		//그래프 구현을 위해 자산합계, 부채합계, 총합계를 따로 계산하여 보내줌
		int i = 0;
		int sumAssets = 0;
		int sumDebts = 0;

		while(aomList.size() > i) {
			if (aomList.get(i).getAmount() > 0) {
				sumAssets += aomList.get(i).getAmount();
			}else if (aomList.get(i).getAmount() < 0) {
				sumDebts += aomList.get(i).getAmount();
			}
			i++;
		}
		int sumTotal = sumAssets + sumDebts;

		//자산, 부채 건수 집계
		int j=0;
		int cntAssets = 0;
		int cntDebts = 0;
		while(aomList.size() > j) {
			if (aomList.get(j).getType().equals("자산")) {
				cntAssets++;
			}else if (aomList.get(j).getType().equals("부채")) {
				cntDebts++;
			}
			j++;
		}

		search.setUserKey(userKey);
		String memo = search.getMemo();
		List<SearchDto> list = aomService.selectListByMemo(userKey, memo);
		
		ObjectMapper mapper = new ObjectMapper();
		String assetsListJ = mapper.writeValueAsString(assetList);

		m.addAttribute("assetsListJ", assetsListJ);

		m.addAttribute("aomList", aomList);
		m.addAttribute("assetList", assetList);
		m.addAttribute("debtList", debtList);
		m.addAttribute("zeroList", zeroList);
		m.addAttribute("sumTotal", sumTotal);
		m.addAttribute("sumAsset", sumAssets);
		m.addAttribute("sumDebt", sumDebts);
		m.addAttribute("assetRatioValue", sumAssets);
		m.addAttribute("debtRatioValue", sumDebts);
		m.addAttribute("cntAssets", cntAssets);
		m.addAttribute("cntDebts", cntDebts);
		m.addAttribute("result", list);

		return "showMemo";
	}
}
