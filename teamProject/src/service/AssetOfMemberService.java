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
	
	public AssetOfMember getAssetById(int memAssetId) {
		return awmMapper.getAssetById(memAssetId);
	}
	
	public AssetOfMember getLastAssetByUserKey(int userKey) {
		return awmMapper.getLastAssetByUserKey(userKey);
	}

	public List<AssetOfMember> getAssetListById(int userKey) {
		return awmMapper.selectAssetListById(userKey);
	}

	public void addAsset(AssetOfMember aom) {
		awmMapper.addAsset(aom);
	}
	
	public void editAsset(AssetOfMember aom) {
		awmMapper.editAsset(aom);
	}
	
	public void delAsset(int memAssetId) {
		awmMapper.delAsset(memAssetId);
	}
	
	public void addAssetToIncome(AssetOfMember aom) {
		awmMapper.addAssetToIncome(aom);
	}
	
	public void addAssetToExpense(AssetOfMember aom) {
		awmMapper.addAssetToExpense(aom);
	}
}
