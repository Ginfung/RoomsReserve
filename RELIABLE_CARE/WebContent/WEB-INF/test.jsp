<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<title>Study Groups</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap.vertical-tabs.css">

<!-- Custom styles for this template -->
<link href="css/cover.css" rel="stylesheet">
<link href="css/customStyle.css" rel="stylesheet">
<link rel="stylesheet" href="css/main.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="js/bootbox.min.js"></script>
<script src="js/updateGroup.js"></script>


<script>
function updateStudyGroup(gid,grpName,keyword, loc, day, startTime, endTime)
{
//$('#updateGroupDiv').load("UpdateGroup.jsp"); 
	$.ajax({
								url : "http://localhost:8080/RELIABLE_CARE/UpdateGroup.jsp",
									context : document.body,
									success : function(response) {
										$("#updateGroupDiv").html(response);
										$("#myModal").modal('show');
										onUpdatePageLoad(gid,grpName,keyword,loc,day,startTime,endTime);

									}

								});

 

}
function clickSearchGroups()
{
	$.ajax({
			url : "http://localhost:8080/RELIABLE_CARE/SearchGroups.jsp",
			context : document.body,
			success : function(response) {
				$("#SearchStudyGroup").html(response);
				var elem = document.getElementById("searchBox");
elem.onkeyup = function(e){
    if(e.keyCode == 13){
       searchGroups();
    }
};
			}
		});

}



	function searchGroups() {
		var dataString = $("#searchForm").serialize();
		$
				.ajax({
					type : "GET",
					url : "SearchGroupsServlet",
					data : dataString,
					dataType : "text",
					success : function(response) {

						$
								.ajax({
									url : "http://localhost:8080/RELIABLE_CARE/SearchResults.jsp",
									context : document.body,
									success : function(response) {
										$("#searchResults").html(response);

									}
								});
					}
				});
	}
	
	function leaveGroup(gid)
	{
		var val = "leaveGroup=" + gid;
		//alert("Here! " + gid);
		$
				.ajax({
					type : "GET",
					data : val,
					url : "LeaveGroupServlet",
					dataType : "text",
					success : function(response1) {
						// document.getElementById("updateProgfileMsg").innerHTML="<p><i>"+response+"</i></p>";
						$
								.ajax({
									url : "http://localhost:8080/RELIABLE_CARE/MyStudyGroups.jsp",
									context : document.body,
									success : function(response) {
										$("#MyStudyGroups").html(response);

									}

								});

					}
				});
		
	}
	
	function joinGroup(gid)
{
	var val = "JoinGroup=" + gid;
		//alert("Here! " + gid);
		$
				.ajax({
					type : "GET",
					data : val,
					url : "JoinGroupServlet",
					dataType : "text",
					success : function(response1) {
					searchGroups();

					}
				});

	}
	function clickMyStudyGroups() {
		$.ajax({
					type : "GET",
					url : "ListMyStudyGroupsServlet",
					dataType : "text",
					success : function(response1) {
						// document.getElementById("updateProgfileMsg").innerHTML="<p><i>"+response+"</i></p>";
						$
								.ajax({
									url : "http://localhost:8080/RELIABLE_CARE/MyStudyGroups.jsp",
									context : document.body,
									success : function(response) {
										$("#MyStudyGroups").html(response);

									}

								});

					}
				});
	}

	$(document).ready(function() {
		clickMyStudyGroups();
	});

	function unsubscribeGroup(gid) {

		var val = "removeFromGroup=" + gid;
		//alert("Here! " + gid);
		$
				.ajax({
					type : "GET",
					data : val,
					url : "ListMyStudyGroupsServlet",
					dataType : "text",
					success : function(response1) {
						// document.getElementById("updateProgfileMsg").innerHTML="<p><i>"+response+"</i></p>";
						$
								.ajax({
									url : "http://localhost:8080/RELIABLE_CARE/MyStudyGroups.jsp",
									context : document.body,
									success : function(response) {
										$("#MyStudyGroups").html(response);

									}

								});

					}
				});

	}
	function clickProfile() {//alert("In here!");
		$.ajax({
			url : "http://localhost:8080/RELIABLE_CARE/ProfileForm.jsp",
			context : document.body,
			success : function(response) {
				$("#MyProfile").html(response);
			}
		});
		// $("#MyProfile").load("); 
	}
	function clickCreateStudyGroup() {

		//alert("In here!");
		$.ajax({
			url : "http://localhost:8080/RELIABLE_CARE/CreateStudyGroup.html",
			context : document.body,
			success : function(response) {
				$("#CreateStudyGroup").html(response);
				$.ajax({
					type : "GET",
					url : "CreateStudyGroupServlet",
					dataType : "text",
					success : function(response1) {
						// document.getElementById("updateProgfileMsg").innerHTML="<p><i>"+response+"</i></p>";
						var select = document.getElementById("dayOfStudy");
						var room1 = response1.toString();

						var room = room1.substring(1, room1.length - 1).split(
								",");
						var option = document.createElement('option');
						option.text = option.value = "SELECT";
						option.disabled = true;
						option.selected = true;
						select.appendChild(option);

						for ( var i = 0; i < room.length; i++) {
							var option = document.createElement('option');
							var x = room[i];
							option.text = option.value = room[i].trim();
							select.appendChild(option);
						}
							

					}

				});

			}
		});

	}

	function changeDay() {

		var myselect = document.getElementById("dayOfStudy");
		var val = "opType=getRooms&" + "dayChanged="
				+ myselect.options[myselect.selectedIndex].value;
		$.ajax({
			type : "GET",
			data : val,
			url : "CreateStudyGroupServlet",
			dataType : "text",
			success : function(response1) {
				// document.getElementById("updateProgfileMsg").innerHTML="<p><i>"+response+"</i></p>";
				var select = document.getElementById("roomNumber");
				var room1 = response1.toString();

				var room = room1.substring(1, room1.length - 1).split(",");
				$('#roomNumber').empty();
				$('#startTime').empty();
				$('#endTime').empty();
				//<option value="" disabled selected style="display:none;">Label</option>
				var option = document.createElement('option');
				option.text = option.value = "SELECT";
				option.disabled = true;
				option.selected = true;
				select.appendChild(option);
				for ( var i = 0; i < room.length; i++) {
					var option = document.createElement('option');
					var x = room[i];
					option.text = option.value = room[i].trim();
					select.appendChild(option);
				}

			}

		});

	}

	function changeRoomNumber() {

		var myselect = document.getElementById("roomNumber");
		var myselectDay = document.getElementById("dayOfStudy");
		var val = "opType=getTimes&" + "roomNumberChanged="
				+ myselect.options[myselect.selectedIndex].value;
		val += "&forDay="
				+ myselectDay.options[myselectDay.selectedIndex].value;
		$.ajax({
			type : "GET",
			data : val,
			url : "CreateStudyGroupServlet",
			dataType : "text",
			success : function(response1) {
				// document.getElementById("updateProgfileMsg").innerHTML="<p><i>"+response+"</i></p>";
				var select = document.getElementById("startTime");
				var room11 = response1.split("],");
				;
				var room1 = room11[0].toString();
				var room = room1.substring(2, room1.length).split(",");
				$('#startTime').empty();
				$('#endTime').empty();
				//<option value="" disabled selected style="display:none;">Label</option>
				var option = document.createElement('option');
				option.text = option.value = "SELECT";
				option.disabled = true;
				option.selected = true;
				select.appendChild(option);
				for ( var i = 0; i < room.length; i++) {
					var option = document.createElement('option');
					var x = room[i];
					option.text = option.value = room[i].trim();
					select.appendChild(option);
				}
		

			}

		});

	}

function getEndTimes()
{
var myselect = document.getElementById("roomNumber");
		var myselectDay = document.getElementById("dayOfStudy");
		var myStartTime=document.getElementById("startTime");
		var val = "opType=getEndTimes&" + "roomNumberChanged="
				+ myselect.options[myselect.selectedIndex].value;
		val += "&forDay="
				+ myselectDay.options[myselectDay.selectedIndex].value;
				val+="&startTime="+ myStartTime.options[myStartTime.selectedIndex].value;
				$.ajax({
			type : "GET",
			data : val,
			url : "CreateStudyGroupServlet",
			dataType : "text",
			success : function(response1) {
			
			var tmpStr = response1.toString();

				var endTimes = tmpStr.substring(1, tmpStr.length - 1).split(
								",");
			
				var select1 = document.getElementById("endTime");
			
				$('#endTime').empty();
				//<option value="" disabled selected style="display:none;">Label</option>
				var option = document.createElement('option');
				option.text = option.value = "SELECT";
				option.disabled = true;
				option.selected = true;
				select1.appendChild(option);
				for ( var i = 0; i < endTimes.length; i++) {
					var option = document.createElement('option');
					option.text = option.value = endTimes[i].trim();
					select1.appendChild(option);
				}
			
			
			
			}
			});
				

}
	function helpMethod() {
		bootbox
				.dialog({
					message : "<div><p>1.You become the moderator for the group that you create</p><p>2.You can oly create a study group starting in the current week.</p><p>3.All study group meetings are recurring by default.</p><p>4.Contact us help@studyGroups.com or 123-456-7890.</p></div>",
					title : "Things you need to know",
					buttons : {

						main : {
							label : "Got It!",
							className : "btn-primary",
							callback : function() {

							}
						}
					}
				});
	}

	function logout() {
		document.location.href = "LogoutServlet";
	}

	function updateUserProfile() {
		dataString = $("#proflieForm").serialize();
		$
				.ajax({
					type : "GET",
					url : "UpdateUserProfileServlet",
					data : dataString,
					dataType : "text",
					success : function(response) {
						document.getElementById("updateProgfileMsg").innerHTML = "<p><i>"
								+ response + "</i></p>";
						document.location.href = "#";
					}
				});
	}

	function createStudyGroupFunc() {
		var gName= document.getElementById("groupName").value;
		if(gName=="")return;
		dataString = $("#createStudyGroupForm").serialize();
		dataString += "&opType=Create";
		$.ajax({
			type : "GET",
			url : "CreateStudyGroupServlet",
			data : dataString,
			dataType : "text",
			success : function(response) {
				document.getElementById("CreateGroupMsg").innerHTML = "<p><i>"
						+ response + "</i></p>";
				document.getElementById("createStudyGroupForm").reset();
				$('#roomNumber').empty();
				$('#startTime').empty();
				$('#endTime').empty();

			}
		});
	}
</script>

</head>

<body>
	<div id="fullBgLandingPage" />
	<div class="container" style=" width: 95%;">
		<div class="site-wrapper">

			<div class="site-wrapper-inner">

				<div class="cover-container" style="width: 100% !important;">

					<div class="masthead clearfix" style="width: 89%;">
						<div class="inner">
							<h3 class="masthead-brand">${sessionScope.message}</h3>
							<nav>
								<ul class="nav masthead-nav">
									<li><a id="help" href="#" onCLick="helpMethod()">Help</a></li>
									<li><a id="logout" href="#" onClick="logout()">Sign Out</a></li>
								</ul>
							</nav>
						</div>
					</div>


					<div class="inner cover">

						<div class="row" style="min-height: 600px;width: 106%;">
							<div class="col-sm-6 tablayout">

								<div class="col-xs-3">
									<!-- required for floating -->
									<!-- Nav tabs -->
									<ul class="nav nav-tabs tabs-left myNavTabs">
										<li class="active"><a href="#MyStudyGroups"
											onClick="clickMyStudyGroups()" data-toggle="tab">My Study
												Groups</a></li>
										<li><a href="#SearchStudyGroup" onClick="clickSearchGroups()" data-toggle="tab">Find
												a Study Group</a></li>
										<li><a href="#CreateStudyGroup"
											onClick="clickCreateStudyGroup()" data-toggle="tab">Create
												a Study Group</a></li>
										<li><a href="#MyProfile" onClick="clickProfile()"
											data-toggle="tab">My Profile</a></li>
									</ul>
								</div>

								<div class="col-xs-9">
									<!-- Tab panes -->
									<div class="tab-content">
										<div class="tab-pane active" id="MyStudyGroups">My Study
											Groups</div>
										<div class="tab-pane" id="SearchStudyGroup" style="margin-top: 5px;margin-left: 205px;">Find a Study
											Group</div>
										<div class="tab-pane create" id="CreateStudyGroup"></div>
										<div class="tab-pane" id="MyProfile"></div>
									</div>
								</div>

								<div class="clearfix"></div>

							</div>

						</div>

					</div>

				</div>



			</div>

		</div>

	</div>




<div id="updateGroupDiv"></div>
</body>
</html>
