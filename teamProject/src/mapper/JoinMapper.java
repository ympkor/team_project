package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.Member;

public interface JoinMapper {
	//회원추가, 마이페이지 진입시 비밀번호 입력
	public void insertMember(Member member);
	
	//로그인, 아이디중복확인
	public Member selectById(String userId);
	
	//이메일로 아이디 찾기
	public String idSelectByEmail(@Param("email")String email, @Param("name")String name);
	
	//이메일중복확인
	public Member selectByEmail(String email);
	
	//아이디로 비밀번호 찾기
	public String pwSelectById(String userId);
	
	//회원번호로 전체정보조회
	public Member selectByUserKey(String userKey);
	
	//수정하기
	public void updateByUserKey(Member member);
	
	//삭제하기
	public void deleteByUserKey(int userKey);
	
	//회원가입시 처음에 현금값 초기화를 위한 추가
	public void insertCash(int userKey);
}
