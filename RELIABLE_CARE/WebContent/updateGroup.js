
function onUpdatePageLoad(gid,grpName,keyword,loc,day,startTime,endTime)
{
	 document.getElementById("keywordU").value=keyword;
	 document.getElementById("groupNameU").value=grpName;

	$.ajax({
		type : "GET",
		url : "CreateStudyGroupServlet",
		dataType : "text",
		success : function(response1) {
			// document.getElementById("updateProgfileMsg").innerHTML="<p><i>"+response+"</i></p>";
			var select = document.getElementById("dayOfStudyU");
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
			select.value = day;
			changeDayU(loc,startTime,endTime);
			//var element = document.getElementById('dayOfStudyU');
		    //element.value = day;
		   // changeDayU(day);
		   /* element = document.getElementById('startTimeU');
		    element.value = startTime;
		    element = document.getElementById('endTimeU');
		    element.value = endTime;*/
		    

		}

	
	});
}
	function x()
	{
		alert("here!");
	}
	function changeDayU(loc,startTime,endTime) {

		var myselect = document.getElementById("dayOfStudyU");
		var val = "opType=getRooms&" + "dayChanged="
				+ myselect.options[myselect.selectedIndex].value;
		$.ajax({
			type : "GET",
			data : val,
			url : "CreateStudyGroupServlet",
			dataType : "text",
			success : function(response1) {
				// document.getElementById("updateProgfileMsg").innerHTML="<p><i>"+response+"</i></p>";
				var select = document.getElementById("roomNumberU");
				var room1 = response1.toString();

				var room = room1.substring(1, room1.length - 1).split(",");
				$('#roomNumberU').empty();
				$('#startTimeU').empty();
				$('#endTimeU').empty();
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
				
				if(typeof(day) != "undefined")
					{
				select.value = loc;
				changeRoomNumberU(startTime,endTime);
					}

			}

		});

	}

	function changeRoomNumberU(startTime,endTime) {

		var myselect = document.getElementById("roomNumberU");
		var myselectDay = document.getElementById("dayOfStudyU");
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
				var select = document.getElementById("startTimeU");
				var room11 = response1.split("],");
				;
				
				var room1 = room11[0].toString();
				var room = room1.substring(2, room1.length).split(",");
				if(typeof(startTime) != "undefined" && typeof(endTime) != "undefined")
				{
					var temp= new Array();
					for(var i=0;i<room.length;i++)
						{
						temp.push(room[i]);
						}
					temp.push(startTime);
					//temp.push(endTime);
					temp.sort(function (a, b) {
						  return new Date('1970/01/01 ' + a) - new Date('1970/01/01 ' + b);
						});
					room=temp;
			
				}
				
				$('#startTimeU').empty();
				$('#endTimeU').empty();
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
				var select1 = document.getElementById("endTimeU");
				var room22 = room11[1].toString();
				var room2 = room22.substring(2, room22.length - 2).split(",");
				if(typeof(startTime) != "undefined" && typeof(endTime) != "undefined")
				{
					var temp= new Array();
					for(var i=0;i<room2.length;i++)
						{
						temp.push(room2[i]);
						}
					//temp.push(startTime);
					temp.push(endTime);
					temp.sort(function (a, b) {
						  return new Date('1970/01/01 ' + a) - new Date('1970/01/01 ' + b);
						});
					room2=temp;
			
				}
				$('#endTimeU').empty();
				
				if(typeof(startTime) != "undefined" && typeof(endTime) != "undefined")
				{
			select.value = startTime;
			changeStartTimes(endTime);
			
			
				}

			}

		});

	}

	function changeStartTimes(endTime)
	{
		var myselect = document.getElementById("roomNumberU");
		var myselectDay = document.getElementById("dayOfStudyU");
		var myStartTime=document.getElementById("startTimeU");
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
				if(typeof(endTime) != "undefined")
				{
					var temp= new Array();
					for(var i=0;i<endTimes.length;i++)
						{
						temp.push(endTimes[i]);
						}
					//temp.push(startTime);
					temp.push(endTime);
					temp.sort(function (a, b) {
						  return new Date('1970/01/01 ' + a) - new Date('1970/01/01 ' + b);
						});
					endTimes=temp;
			
				}
				var select1 = document.getElementById("endTimeU");
			
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
				if(typeof(endTime) != "undefined")
				{
					select1.value = endTime;
		
				}
			
			
			}
			});
				

	}

	
	
