package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.Member;
import service.AssetService;

@Controller
@RequestMapping("/asset")
public class AssetController {

	@Autowired AssetService assetService;
	
	@GetMapping("/view")
	public String getMemberAsset(Model m, int userKey) {
		Member memAsset = assetService.getMember(userKey);
		m.addAttribute("mem", memAsset);
		System.out.println(memAsset.getName());
		System.out.println(memAsset.getUserId());
		System.out.println(memAsset.getUserKey());
		return "showAsset";
	}

}
