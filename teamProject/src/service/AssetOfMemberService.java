package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.AssetOfMember;
import mapper.AssetOfMemberMapper;


@Service
public class AssetOfMemberService {

	@Autowired
	private AssetOfMemberMapper awmMapper;
	
	public List<AssetOfMember> getAssetOfMember(int userKey) {
		return awmMapper.selectAomById(userKey);
	}

	public void addAsset(AssetOfMember aom) {
		awmMapper.addAsset(aom);
	}
	
}
