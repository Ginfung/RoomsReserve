<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<form id=searchForm>
<table class="searchform">
		<tr>

			<td><input id=searchBox name=searchBox type=text style="height: 32px;width: 400px;border-radius: 2px !important;"
				placeholder=" Ex: Enter a GroupName or a Keyword" autofocus
				class="formFields" /></td>
			<td>
				<button class="btn btn-default" style="color: #fff;  background-color: #3071a9;  border-color: #285e8e;
    margin-left: -18px;" type="button" onClick="searchGroups()">
					<i class="glyphicon glyphicon-search"></i>
				</button>
			</td>
		</tr>
		</table>
		<div id="searchResults" style="width: 315%; margin-left: -165px;"></div>

</form>
	
		