package controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import dto.AssetOfMember;
import dto.Member;
import mapper.JoinMapper;
import service.AssetNewsService;
import service.AssetOfMemberService;
import service.MemberService;

@Controller
@RequestMapping("/member")
@SessionAttributes("userKey")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	JoinMapper joinMapper;
	
	@Autowired 
	AssetOfMemberService aomService;
	
	//회원가입버튼 눌렀을 때, 회원가입폼으로 이동
	@GetMapping("/join")
	public String getMemberAddForm() {
		return "join";
	}
	
	//회원가입이 다 완료되면 DB에 정보가 들어감과 동시에 자산을 추가하는 창으로 이동
	@PostMapping("/asset/view")
	public String MemberAddproc(Model model, Member member) {
		memberService.addMember(member);//회원추가
		memberService.addCash(member.getUserKey());//추가된 회원의 회원번호를 넣어서 추가해줌
		Member m = joinMapper.selectById(member.getUserId());//회원아이디로 회원의 전체 정보를 조회후
		model.addAttribute("userKey", m.getUserKey());//전체정보중에 회원번호만 뽑아서 session추가해줌	
		
		List<AssetOfMember> aomList = aomService.getAssetListById(m.getUserKey());	//userKey값에 해당하는 자산을 배열로 받음
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

		model.addAttribute("aomList", aomList);
		model.addAttribute("sumTotal", sumTotal);
		model.addAttribute("sumAsset", sumAssets);
		model.addAttribute("sumDebt", sumDebt);
		model.addAttribute("newsArr", jsonArray);
		return "showAsset";
	}
	
	//아이디 중복체크
	@PostMapping(value="/equalsId", produces="text/html;charset=UTF-8")
	public @ResponseBody String EqualsId(Member member) {
		String equalId = memberService.equalId(member);
		return equalId;
	}
	
	//이메일 중복체크
	@PostMapping(value="/equalsEmail", produces="text/html;charset=UTF-8")
	public @ResponseBody String EqualsEmail(Member member) {
		String equalEmail = memberService.equalEmail(member);
		return equalEmail;
	}
	
	//로그인버튼 눌렀을 때, 로그인폼으로 이동
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	//로그아웃버튼 눌렀을 때, 로그아웃폼으로 이동
	@GetMapping("/logout")
	public String getLogout() {
		return "logout";
	}
	
	//로그인시 아이디와 비밀번호 유효성 체크해주고 그 결과에 대한 값을 알람창으로 띄워줌
	@PostMapping(value="/login", produces="text/html;charset=UTF-8")
	public @ResponseBody String Loginproc(Model model, Member member) {
		//세션값을 저장하기 위해서 사용
		Member m = joinMapper.selectById(member.getUserId());
		if(m!=null) {
			//System.out.println(m.getUserKey());
			Model uk = model.addAttribute("userKey", m.getUserKey());
			//System.out.println("세션:"+uk);
			String str=memberService.login(member);
			//System.out.println(str);
			return str;
		} else {
			String str=memberService.login(member);
			//System.out.println(str);
			return str;
		}
	}
	
	//아이디찾기 버튼을 눌렀을 때 아이디찾기폼으로 이동
	@GetMapping("/searchId")
	public String getId() {
		return "searchId";
	}
	
	//아이디 찾기시 해당되는 결과에 대응되는 값을 출력
	@PostMapping(value="/searchId", produces="text/html;charset=UTF-8")
	public @ResponseBody String searchIdproc(String email, String name) {
//		System.out.println(email);
//		System.out.println(name);
		String str = memberService.searchId(email, name);
		//System.out.println(str);
		return str;
	}
	
	//비밀번호찾기 버튼을 눌렀을 때 비밀번호찾기폼으로 이동
	@GetMapping("/searchPw")
	public String getPw() {
		return "searchPw";
	}
	
	//비밀번호찾기시 해당되는 결과에 대응되는 값을 출력
	@PostMapping(value="/searchPw", produces="text/html;charset=UTF-8")
	public @ResponseBody String searchPwproc(String userId, String name, String email) {
//		System.out.println(email);
//		System.out.println(name);
//		System.out.println(userId);
		String str = memberService.searchPw(userId, name, email);
		return str;
	}
	
	//마이페이지버튼클릭시 마이페이지로 넘어감
	@GetMapping("/mypage")
	public String getMember(Model m, @ModelAttribute("userKey")String userKey) {
		Member member = memberService.showMember(userKey);
		m.addAttribute("member", member);
		return "mypage";
	}
	
	//마이페이지에 있는 수정하기버튼클릭시 수정페이지로 넘어감
	//처음에 DB에 입력되어있는 값을 보여줌
	@GetMapping("/update")
	public String getUpdate(Model m, @ModelAttribute("userKey")String userKey) {
		Member member= joinMapper.selectByUserKey(userKey);
		m.addAttribute("member", member);
		return "updateMember";
	}
	
	//수정페이지에서 수정한 내용을 다시 DB로 보내줌
	@PostMapping("/update")
	@ResponseBody
	public Member updateProc(Member member) {
		memberService.updateMember(member);
		return member;
	}
	
	//삭제버튼시 넘어감
	@GetMapping("/delete")
	public String delProc(@ModelAttribute("userKey")int userKey,Model m) {
		//joinMapper.deleteByUserKey(userKey);
		String result=null;
		try {
			result=	memberService.deleteMember(userKey);
		} catch (Exception e) {
			result = "데이터 삭제 실패했습니다" ;
		}				
		m.addAttribute("result", result);
		return "deleteConfirm";
	}
	
	//마이페이지 넘어갈 때 본인확인용 비밀번호
	@GetMapping("/mypageProc")
	public String getmypageproc(Model m, @ModelAttribute("userKey")String userKey) {
		Member member= joinMapper.selectByUserKey(userKey);
		m.addAttribute("member", member);
		return "mypageProc";
	}
	
	@PostMapping(value="/mypageProc", produces="text/html;charset=UTF-8")
	public @ResponseBody String mypageProc(Member member) {
		//System.out.println(member);
		String str = memberService.mypagePw(member);
		//System.out.println(str);
		return str;
	}
}
