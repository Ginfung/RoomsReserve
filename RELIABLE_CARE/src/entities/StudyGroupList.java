package entities;

public class StudyGroupList {

	public String groupId;
	public String groupName;
	public String location;
	public String moderatorName;
	public String day;
	public String startTime;
	public String endTime;
	public String moderator;
	public String keyword;
	
	public StudyGroupList(String groupId,String groupName,String location,String moderatorName,String day,String startTime,String endTime,String keyword)
	{
		this.groupId=groupId;
		this.groupName=groupName;
		this.location=location;
		this.moderatorName=moderatorName;
		this.day=day;
		this.startTime=startTime;
		this.endTime=endTime;
		this.keyword=keyword;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getModeratorName() {
		return moderatorName;
	}

	public void setModeratorName(String moderatorName) {
		this.moderatorName = moderatorName;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getModerator() {
		return moderator;
	}

	public void setModerator(String m) {
		this.moderator = m;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
