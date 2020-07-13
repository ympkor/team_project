package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.AssetOfMember;
import dto.SearchDto;
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

	public List<AssetOfMember> selectAssetListById(int userKey) {
		return awmMapper.selectAssetListById(userKey);
	}

	public List<AssetOfMember> selectOnlyAssetListById(int userKey) {
		return awmMapper.selectOnlyAssetListById(userKey);
	}
	
	public List<AssetOfMember> selectOnlyDebtListById(int userKey) {
		return awmMapper.selectOnlyDebtListById(userKey);
	}

	public List<AssetOfMember> selectOnlyZeroListById(int userKey) {
		return awmMapper.selectOnlyZeroListById(userKey);
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
	
	public void editAssetToIncome(AssetOfMember aom) {
		awmMapper.editAssetToIncome(aom);
	}
	
	public void editAssetToExpense(AssetOfMember aom) {
		awmMapper.editAssetToExpense(aom);
	}
	
	public AssetOfMember getNewsSettingsInfo(int userKey) {
		return awmMapper.getNewsSettingsInfo(userKey);
	}
	
	public void setNews(AssetOfMember aom){
		awmMapper.setNews(aom);
	}
	
	public List<SearchDto> selectListByMemo(int userKey, String memo) {
		return awmMapper.selectListByMemo(userKey, memo);
	}
}
