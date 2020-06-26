package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.Member;
import mapper.JoinMapper;
import service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
//	@Autowired
//	JoinMapper joinMapper;
	
	@GetMapping("/join")
	public String getMemberAddForm() {
		return "join";
	}
	
	@PostMapping("/money")
	public String MemberAddproc(Member member) {
		System.out.println("join:"+member);
		memberService.addMember(member);
		return "money";
	}
	//아이디 중복체크
	@PostMapping(value="equalsId", produces="text/html;charset=UTF-8")
	public @ResponseBody String EqualsId(Member member) {
		//System.out.println("Test");
		//System.out.println("equalId : "+member);
		String equalId = memberService.equalId(member);
		//System.out.println(equalId);
		return equalId;
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@PostMapping(value="login", produces="text/html;charset=UTF-8")
	public @ResponseBody String Loginproc(Member member) {
		//System.out.println("Test");
		//System.out.println(member.getUserKey());
		//Member m = joinMapper.selectById(member.getUserId());
		//System.out.println(m.getUserKey());
		String str=memberService.login(member);
		System.out.println(str);
		return str;
	}
}
