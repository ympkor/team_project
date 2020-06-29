package mapper;

import java.util.List;

import dto.AssetOfMember;

public interface AssetOfMemberMapper {

	public List<AssetOfMember> selectAomById(int userKey);
	
	public void addAsset(AssetOfMember aom);

}
