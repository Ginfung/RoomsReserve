<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<c:choose>
	<c:when test="${empty sessionScope.studyGroupList }">
	<p style="margin-left: -478px;"><i> No Study Groups</i></p>
	</c:when>
	<c:otherwise>
<table border="1" style="border: none;">
	<tr>
		<th style="text-align: center;">Group Name</th>
		<th style="text-align: center;">Moderator Name</th>
		<th style="text-align: center;">Location</th>
		<th style="text-align: center;">Day</th>
		<th style="text-align: center;">Start Time</th>
		<th style="text-align: center;">End Time</th>
		<th style="text-align: center;"></th>
	</tr>
	<c:set var="counterVar" value="0" />

	<c:forEach items="${sessionScope.studyGroupList}" var="result">
		<tr>
			<td><label class="listTable">${result.groupName}</label></td>
			<td><label class="listTable">${result.moderatorName}</label></td>
			<td><label class="listTable">${result.location}</label></td>
				<td><label class="listTable">${result.day}</label></td>
				<td><label class="listTable">${result.startTime}</label></td>
				<td><label class="listTable">${result.endTime}</label></td>
				<td>
				<c:choose>
				<c:when test="${ result.moderator eq true }">
				<button type="button" onClick="unsubscribeGroup('${result.groupId}')" class="btn btn-primary deleteButton" style="background-color: #DA5C1A !important;  border-color: #DA5C1A  !important;">Delete</button>
				<button type="button" onClick="updateStudyGroup('${result.groupId}','${result.groupName}','${result.keyword}','${result.location}','${result.day}','${result.startTime}','${result.endTime}')" class="btn btn-primary">Update</button>
				
				</c:when>
				<c:otherwise>
				<button type="button" onClick="leaveGroup('${result.groupId}')" class="btn btn-primary">Leave</button>
				
				</c:otherwise>
				</c:choose>
			

		</tr>



		<c:set var="counterVar" value="${counterVar + 1}" />
	</c:forEach>
</table>
</c:otherwise>
</c:choose>