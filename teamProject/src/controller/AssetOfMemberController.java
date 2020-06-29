package controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.cj.xdevapi.JsonParser;

import dto.AssetOfMember;
import service.AssetNewsService;
import service.AssetOfMemberService;

@Controller
@RequestMapping("/asset")
public class AssetOfMemberController {

	@Autowired AssetOfMemberService aomService;

	@RequestMapping("/view")
	public String getMemberAsset(Model m1, Model m2, Model m3) {
		List<AssetOfMember> aomList = aomService.getAssetOfMember(1);
		int i = 0;
		int sumAssets = 0;
		while(aomList.size() > i) {
			sumAssets += aomList.get(i).getAmount();
			i++;
		}
		
		AssetNewsService ans = new AssetNewsService();
		StringBuilder newsString = ans.getNews();
		System.out.println("스트링빌더값"+newsString);

		JSONObject jsnObject = new JSONObject(newsString.toString());
		JSONArray jsonArray = jsnObject.getJSONArray("items");
		System.out.println("json배열값"+jsonArray);

		m1.addAttribute("aomList", aomList);
		m2.addAttribute("sumAsset", sumAssets);
		m3.addAttribute("newsList", jsonArray);

		return "showAsset";
	}
	
	@RequestMapping("/add")
	public String showAddForm(Model m) {
		List<AssetOfMember> aomList = aomService.getAssetOfMember(1);
		int i = 0;
		int sumAssets = 0;
		while(aomList.size() > i) {
			sumAssets += aomList.get(i).getAmount();
			i++;
		}

		m.addAttribute("aomList", aomList);
		m.addAttribute("sumAsset", sumAssets);
		
		
		return "addAssetForm";
	}

	@RequestMapping("/addAsset")
	public String addAsset(Model m, AssetOfMember assetOfMember) {
		aomService.addAsset(assetOfMember);
		m.addAttribute("aom", assetOfMember);
		System.out.println(assetOfMember);
		return "addResult";
	}
	
}
