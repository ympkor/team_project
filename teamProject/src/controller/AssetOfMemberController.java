package controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import dto.AssetOfMember;
import service.AssetNewsService;
import service.AssetOfMemberService;
import service.StatisticsService;

@Controller
@RequestMapping("/asset")
@SessionAttributes("userKey")
public class AssetOfMemberController {

	@Autowired AssetOfMemberService aomService;

	@RequestMapping("/view")
	public String getMemberAsset(@ModelAttribute("userKey")int userKey, Model m) {
		List<AssetOfMember> aomList = aomService.getAssetOfMember(userKey);	//userKey값에 해당하는 자산을 배열로 받음
		int i = 0;
		int sumAssets = 0;
		while(aomList.size() > i) {
			sumAssets += aomList.get(i).getAmount();
			i++;
		}	// 각 자산 합계를 계산

		AssetNewsService ans = new AssetNewsService();
		StringBuilder newsString = ans.getNews();

		JSONObject jsonObject = new JSONObject(newsString.toString());
		JSONArray jsonArray = jsonObject.getJSONArray("items");
		System.out.println(jsonObject);
		System.out.println(jsonObject.getClass());

		// jsonArray는 네이버 자산관련 기사 10개를 json 배열로 받은 값
		// jsp에서 출력할 방법 찾는 중 (혹시 방법 알고 계신 분은 도움 부탁드립니다)

		m.addAttribute("aomList", aomList);
		m.addAttribute("sumAsset", sumAssets);
		m.addAttribute("newsList", jsonArray);
		return "showAsset";
	}

	@RequestMapping("/add")
	public String showAddForm(@ModelAttribute("userKey")int userKey, Model m) {
		
		List<AssetOfMember> aomList = aomService.getAssetOfMember(userKey);
		int i = 0;
		int sumAssets = 0;
		while(aomList.size() > i) {
			sumAssets += aomList.get(i).getAmount();
			i++;
		}
		//위 코드는 자산 추가 페이지에서 현재 보유 자산 보여주기 위한 것인데
		//일단 코드만 두고 jsp에서의 출력은 보류합니다.
		
		m.addAttribute("aomList", aomList);
		m.addAttribute("sumAsset", sumAssets);
		return "addAssetForm";
	}

	@RequestMapping("/addAsset")
	public String addAsset(@ModelAttribute("userKey")int userKey, Model m, AssetOfMember aom) {
		String assetType = aom.getType();
		int amount = aom.getAmount();
		if (assetType.equals("부채")) {	//자산타입이 "부채"인 경우 마이너스 값으로 변경한 다음 db저장
			amount*=-1;
		}
		aom.setAmount(amount);
		aomService.addAsset(aom);
		System.out.println(aom);
		m.addAttribute("aom", aom);
		return "addAssetResult";
	}

	@RequestMapping("/edit")
	public String showeditForm(int memAssetId, Model m, AssetOfMember aom) {
		aom = aomService.getAssetById(memAssetId);
		m.addAttribute("aom", aom);
		return "editAssetForm";
	}

	@RequestMapping("/editAsset")
	public String editAsset(int memAssetId, Model m,  AssetOfMember aom) {
		String assetType = aom.getType();
		int amount = aom.getAmount();
		if (assetType.equals("부채")) {	//자산 수정할때도 마찬가지로 부채인 경우 마이너스 처리
			amount*=-1;
		}
		aom.setAmount(amount);
		aomService.editAsset(aom);
		return "editAssetResult";
	}

	@RequestMapping("/delete")
	public String delAsset(int memAssetId, Model m, AssetOfMember aom) {
		aom = aomService.getAssetById(memAssetId);
		int amount = aom.getAmount();
		String bank = aom.getAssetsName();
		String assetType = aom.getType();
		String delResult = bank+"의 "+assetType+" "+amount+"원 항목이 삭제되었습니다.";
		//자산 삭제 전, 삭제되는 자산정보 문자열로 저장후 메서드 돌고 나서 출력
		m.addAttribute("delrst", delResult);
		aomService.delAsset(memAssetId);
		return "deleteAssetResult";
	}
}
