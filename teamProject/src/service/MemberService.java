package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dto.Member;
import mapper.AssetOfMemberMapper;
import mapper.BoardMapper;
import mapper.JoinMapper;
import mapper.MemberMapper;

@Service
public class MemberService {
	
	@Autowired
	JoinMapper joinMapper;
	@Autowired
	BoardMapper boardMapper;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	AssetOfMemberMapper aomMapper;
	//회원가입시 아이디 중복체크
	public String equalId(Member member) {
		Member dbId = joinMapper.selectById(member.getUserId());
//		System.out.println("dbMember: " + dbId);
//		System.out.println("member: " +member.getUserId());
		if(dbId == null) {
			dbId = new Member(0, "", "", "", "");
		}
		if(!(member.getUserId().equals(dbId.getUserId()))) {
			return "중복되지 않음";
		} else {
			return "중복됨";
		}
	}
	
	//회원가입시 이메일 중복체크
	public String equalEmail(Member member) {
		Member dbEmail = joinMapper.selectByEmail(member.getEmail());
		if(dbEmail == null) {
			dbEmail = new Member(0, "", "", "", "");
		}
		if(!(member.getEmail().equals(dbEmail.getEmail()))) {
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
		//System.out.println("아이디"+member.getUserId());
		Member dbId = joinMapper.selectById(member.getUserId());
		if(dbId == null) {
			dbId = new Member(0, "", "", "", "");
		}
		if(!(member.getUserId().equals(dbId.getUserId()))) {
			return "아이디틀림";
		} else {
			if(member.getPassword().equals(dbId.getPassword())) {
				return "성공";
			} else {
				return "패스워드 틀림";
			}
		}
	}
	
	//이메일로 아이디 검색
	public String searchId(String email) {
		//System.out.println("입력받은 " + email);
		String dbId = joinMapper.idSelectByEmail(email);//DB에서 이메일 값을 가져옴
		//System.out.println(dbMember.size());
		if(!(dbId==null)) {
			return email+"님의 아이디입니다.<br>"+dbId;
		} else {
			return "없는 이메일";
		}
	}
	
	//아이디로 비밀번호 검색
	public String searchPw(String userId) {
		String dbPw = joinMapper.pwSelectById(userId);
		if(!(dbPw==null)) {
			return userId+"님의 비밀번호입니다.<br>"+dbPw;
		} else {
			return "없는 아이디";
		}
	}
	
	//회원번호로 전체정보조회
	public Member showMember(String userKey) {
		return joinMapper.selectByUserKey(userKey);
	}
	
	//수정하기
	public void updateMember(Member member) {
		joinMapper.updateByUserKey(member);
	}
	
	//탈퇴하기 
	@Transactional
	public String deleteMember(int userKey) {
		/* 
		 * //코멘트 보드 지우기 boardMapper.deleteBoardbyuserkeyAll(userKey);
		 * boardMapper.deleteCommentbyuserkeyAll(userKey);
		 */
		//수입,지출 지우기
		memberMapper.deleteExpenseByuserkeyAll(userKey);
		memberMapper.deleteIncomeByuserkeyAll(userKey);
		//이체기록지우기
		memberMapper.deleteTransferByuserkeyAll(userKey);
		//자산지우기
		aomMapper.delAssetByuserkeyAll(userKey);
		//멤버에서 지우기
		joinMapper.deleteByUserKey(userKey);
		return "삭제성공";
	}
	
	//마이페이지 진입시 비밀번호 비교
	public String mypagePw(Member member) {
		System.out.println("아이디"+member.getUserId());
		Member dbId = joinMapper.selectById(member.getUserId());
		if(dbId == null) {
			dbId = new Member(0, "", "", "", "");
		}
		if(member.getPassword().equals(dbId.getPassword())) {
			return "비밀번호맞음";
		} else {
			return "비밀번호틀림";
		}
	}
	
	//회원가입시 현금 초기화
	public void addCash(int userKey) {
		System.out.println("serviceuserkey:"+userKey);
		joinMapper.insertCash(userKey);
	}
}
