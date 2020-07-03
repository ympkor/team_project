package mapper;

import java.util.List;

import dto.AssetOfMember;

public interface AssetOfMemberMapper {

	public List<AssetOfMember> selectAssetListById(int userKey);
	
	public AssetOfMember getAssetById(int memAssetId);

	public AssetOfMember getLastAssetByUserKey(int userKey);

	public void addAsset(AssetOfMember aom);

	public void editAsset(AssetOfMember aom);
	
	public void delAsset(int memAssetId);
	//탈퇴시 해당 유저키에 있는 자산을 모두 지움
	public void delAssetByuserkeyAll(int userKey);
	
	public void addAssetToIncome(AssetOfMember aom);

	public void addAssetToExpense(AssetOfMember aom);
}
