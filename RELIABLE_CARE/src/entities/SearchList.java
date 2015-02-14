package entities;

public class SearchList extends StudyGroupList {
	
	public Boolean joined;

	public SearchList(String groupId, String groupName, String location,
			String moderatorName, String day, String startTime, String endTime, Boolean joined,String keyword) {
		super(groupId, groupName, location, moderatorName, day, startTime, endTime,keyword);
		this.joined=joined;
	}

	public Boolean getJoined() {
		return joined;
	}

	public void setJoined(Boolean joined) {
		this.joined = joined;
	}

}
