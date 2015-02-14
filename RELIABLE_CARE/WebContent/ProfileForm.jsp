<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <div id="updateProgfileMsg"></div>
<form id=proflieForm>
	<table id="profileTable" class="profileTableform">
		<tr>

			<td><label for=name class="formlabel">Name :</label></td>
			<td style="text-align: left;"><input id=name name=name type=text 
				placeholder="Students Name" disabled="disabled" class="formFields" value='${sessionScope.student.name}'/></td>
		</tr>
		<tr>
			<td><label for=phoneNumber class="formlabel">Phone Number :</label></td>
			<td style="text-align: left;"><input id=phoneNumber name=phoneNumber type=number required 
				placeholder="Phone Number" class="formFields" value='${sessionScope.student.phoneNumber}'/></td>
		</tr>
		<tr>
			<td><label for=email class="formlabel">Email Id :</label></td>
			<td style="text-align: left;"><input id=email name=email type=email required 
				placeholder="Ex: abc@xyz.com" class="formFields" value='${sessionScope.student.email}'/></td>
		</tr>
	</table>
	<button type="button" onClick="updateUserProfile()" class="btn btn-primary profileUpdateButton">Update</button>
	</form>