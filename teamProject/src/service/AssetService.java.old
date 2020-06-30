package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.Member;
import mapper.AssetMapper;

@Service
public class AssetService {

	@Autowired
	private AssetMapper assetMapper;
	
	public Member getMember(int userKey) {
		return assetMapper.selectMemberById(userKey);
	}

}
