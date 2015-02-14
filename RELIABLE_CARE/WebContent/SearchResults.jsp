<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when test="${empty sessionScope.searchGroupList }">
	<p style="margin-left: -120px;"><i> No Match Found</i></p>
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
	
	<c:forEach items="${sessionScope.searchGroupList}" var="result">
		<tr>
			<td><label class="listTable">${result.groupName}</label></td>
			<td><label class="listTable">${result.moderatorName}</label></td>
			<td><label class="listTable">${result.location}</label></td>
			<td><label class="listTable">${result.day}</label></td>
			<td><label class="listTable">${result.startTime}</label></td>
			<td><label class="listTable">${result.endTime}</label></td>
			<td><c:choose>
					<c:when test="${result.joined != true }">
						<button type="button"
							onClick="joinGroup('${result.groupId}')"
							class="btn btn-primary">Join</button>
					</c:when>
					<c:otherwise>
						<i>Joined</i>
					</c:otherwise>
				</c:choose></td>
		</tr>



		<c:set var="counterVar" value="${counterVar + 1}" />
	</c:forEach>
	</c:otherwise>
	</c:choose>
	
</table>

