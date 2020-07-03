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

@Controller
@RequestMapping("/asset")
@SessionAttributes({"userKey", "memAssetId"})
public class AssetOfMemberController {

	@Autowired AssetOfMemberService aomService;

	@RequestMapping("/view")
	public String getMemberAsset(@ModelAttribute("userKey") int userKey, Model m){
		List<AssetOfMember> aomList = aomService.getAssetListById(userKey);	//userKey값에 해당하는 자산을 배열로 받음
		int i = 0;

		int sumAssets = 0;
		int sumDebt = 0;
		while(aomList.size() > i) {
			if (aomList.get(i).getAmount() > 0) {
				sumAssets += aomList.get(i).getAmount();
			}else if (aomList.get(i).getAmount() < 0) {
				sumDebt += aomList.get(i).getAmount();
			}
			i++;
		}
		int sumTotal = sumAssets + sumDebt;
		//그래프 구현을 위해 자산합계, 부채합계, 총합계를 따로 계산하여 보내줌

		AssetNewsService ans = new AssetNewsService();
		StringBuilder newsString = ans.getNews();
		JSONObject jsonObject = new JSONObject(newsString.toString());
		JSONArray jsonArray = jsonObject.getJSONArray("items");

		m.addAttribute("aomList", aomList);
		m.addAttribute("sumTotal", sumTotal);
		m.addAttribute("sumAsset", sumAssets);
		m.addAttribute("sumDebt", sumDebt);
		m.addAttribute("newsArr", jsonArray);
		return "showAsset";
	}

	@RequestMapping("/add")
	public String showAddForm(@ModelAttribute("userKey")int userKey) {
		return "addAssetForm";
	}

	@RequestMapping("/addAsset")
	public String addAsset(@ModelAttribute("userKey")int userKey, 
			Model m, AssetOfMember aom, AssetOfMember toCal) {
		
		String assetType = aom.getType();
		int amount = aom.getAmount();
		if (assetType.equals("자산") && amount>0) {
			amount*=1;
		} else if (assetType.equals("자산") && amount<0) {
			amount*=-1;
		} else if (assetType.equals("부채") && amount>0) {
			amount*=-1;
		} else if (assetType.equals("부채") && amount<0) {
			amount*=1;
		}
		aom.setAmount(amount);
		aomService.addAsset(aom);
		
		toCal = aomService.getLastAssetByUserKey(userKey);
		String assetTypeToMain = toCal.getType();
		int amountOfMain = toCal.getAmount();
		if (assetTypeToMain.equals("부채")) {
			amountOfMain*=-1;
		}
		toCal.setAmount(amountOfMain);
		
		if (assetTypeToMain.equals("자산")) {
			aomService.addAssetToIncome(toCal);
		} else if (assetTypeToMain.equals("부채")){
			aomService.addAssetToExpense(toCal);
		}
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
		if (assetType.equals("자산") && amount>0) {	//자산인 경우 플러스, 부채는 마이너스로 저장
			amount*=1;
		} else if (assetType.equals("자산") && amount<0) {
			amount*=-1;
		} else if (assetType.equals("부채") && amount>0) {
			amount*=-1;
		} else if (assetType.equals("부채") && amount<0) {
			amount*=1;
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
		m.addAttribute("delStr", delResult);
		aomService.delAsset(memAssetId);
		return "deleteAssetResult";
	}
}
