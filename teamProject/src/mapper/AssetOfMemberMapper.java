package mapper;

import java.util.List;

import dto.AssetOfMember;

public interface AssetOfMemberMapper {

	public List<AssetOfMember> selectAomById(int userKey);
	
	public AssetOfMember getAssetById(int memAssetId);

	public void addAsset(AssetOfMember aom);

	public void editAsset(AssetOfMember aom);
	
	public void delAsset(int memAssetId);
}
