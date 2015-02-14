<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Update Study Group</h4>
            </div>
            <div class="modal-body">
               <div id="updateStudyGroupMsg"></div>
               
<form id=updateStudyGroupForm>
<input id=gidU name=gidU type=hidden />
	<table id="updateStudyGroupTable" class="studyGroupTableform">
		<tr>

			<td><label for=groupNameU class="formlabel">Group Name :</label></td>
			<td><input id=groupNameU name=groupNameU type=text 
				placeholder="Study Group Name" autofocus class="CreateStudyFieldsU"/></td>
		</tr>
		<tr>
			<td><label for=dayOfStudyU class="formlabel">Day :</label></td>
			<td><select id=dayOfStudyU name=dayOfStudyU onChange="changeDayU()"
				class="CreateStudyFieldsU">
			</select></td>
		</tr>
		<tr>
			<td><label for=roomNumberU class="formlabel">Room Number
					:</label></td>
			<td><select id=roomNumberU name=roomNumberU
				onChange="changeRoomNumberU()"
				class="CreateStudyFieldsU roomNumberDropDown">

			</select></td>
		</tr>
		
		<tr>
			<td><label for=startTimeU class="formlabel">Start Time :</label></td>
			<td><select id=startTimeU name=startTimeU onChange="changeStartTimes()"
				class="CreateStudyFieldsU">
			</select></td>
		</tr>
		<tr>
			<td><label for=endTimeU class="formlabel">End Time :</label></td>
			<td><select id=endTimeU name=endTimeU class="CreateStudyFieldsU">
					
			</select></td>
		</tr>
		<tr>

			<td><label for=keywordU class="formlabel">Keyword :</label></td>
			<td><input id=keywordU name=keywordU type=text
				placeholder="This will help in searching for the study Group"
				class="CreateStudyFieldsU" /></td>
		</tr>
	</table>
	</form>            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="updateGrpDetails()">Save changes</button>
            </div>
        </div>
    </div>
</div>