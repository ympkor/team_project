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

import com.mysql.cj.xdevapi.JsonParser;

import dto.AssetOfMember;
import service.AssetNewsService;
import service.AssetOfMemberService;

@Controller
@RequestMapping("/asset")
@SessionAttributes("userKey")
public class AssetOfMemberController {
	
	@Autowired AssetOfMemberService aomService;

	@RequestMapping("/view")
	public String getMemberAsset(@ModelAttribute("userKey")int userKey, Model m) {
		System.out.println(userKey);
		List<AssetOfMember> aomList = aomService.getAssetOfMember(userKey);
		int i = 0;
		int sumAssets = 0;
		while(aomList.size() > i) {
			sumAssets += aomList.get(i).getAmount();
			i++;
		}
		
//		AssetNewsService ans = new AssetNewsService();
//		StringBuilder newsString = ans.getNews();
//		System.out.println("스트링빌더값"+newsString);
//
//		JSONObject jsnObject = new JSONObject(newsString.toString());
//		JSONArray jsonArray = jsnObject.getJSONArray("items");
//		System.out.println("json배열값"+jsonArray);

		m.addAttribute("aomList", aomList);
		m.addAttribute("sumAsset", sumAssets);
//		m3.addAttribute("newsList", jsonArray);

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

		m.addAttribute("aomList", aomList);
		m.addAttribute("sumAsset", sumAssets);
		
		return "addAssetForm";
	}

	@RequestMapping("/addAsset")
	public String addAsset(@ModelAttribute("userKey")int userKey, Model m, AssetOfMember aom) {
		aomService.addAsset(aom);
		m.addAttribute("aom", aom);
		return "addResult";
	}
	
}
