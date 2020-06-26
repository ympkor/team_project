package mapper;

import java.util.List;

import dto.Member;

public interface JoinMapper {
	//회원추가
	public void insertMember(Member member);
	
	//로그인, 아이디중복확인
	public Member selectById(String userId);
}
