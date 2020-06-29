package mapper;

import java.util.List;

import dto.Member;

public interface JoinMapper {
	//회원추가
	public void insertMember(Member member);
	
	//로그인, 아이디중복확인
	public Member selectById(String userId);
	
	//이메일로 아이디 찾기
	public String idSelectByEmail(String email);
	
	//이메일중복확인
	public Member selectByEmail(String email);
	
	//아이디로 비밀번호 찾기
	public String pwSelectById(String userId);
	
	//회원번호로 전체정보조회
	public Member selectByUserKey(String userKey);
	
	//수정하기
	//삭제하기
}
