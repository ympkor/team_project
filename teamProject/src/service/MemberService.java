package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.Member;
import mapper.JoinMapper;

@Service
public class MemberService {
	
	@Autowired
	JoinMapper joinMapper;
	//회원가입시 아이디 중복체크
	public String equalId(Member member) {
		Member dbMember = joinMapper.selectById(member.getUserId());
		System.out.println("dbMember: " + dbMember);
		System.out.println("member: " +member.getUserId());
		if(dbMember == null) {
			dbMember = new Member(0, "", "", "", "");
		}
		if(!(member.getUserId().equals(dbMember.getUserId()))) {
			return "중복되지 않음";
		} else {
			return "중복됨";
		}
	}
	
	//회원 추가
	public void addMember(Member member) {
		joinMapper.insertMember(member);
	}
	
	//로그인시 아이디와 비번 유효성 체크
	public String login(Member member) {
		Member dbMember = joinMapper.selectById(member.getUserId());
		if(dbMember == null) {
			dbMember = new Member(0, "", "", "", "");
		}
		if(!(member.getUserId().equals(dbMember.getUserId()))) {
			return "아이디틀림";
		} else {
			if(member.getPassword().equals(dbMember.getPassword())) {
				return "성공";
			} else {
				return "패스워드 틀림";
			}
		}
	}
}
