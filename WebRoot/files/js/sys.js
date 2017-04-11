function generateYear(){
    		var year = document.getElementById("year");
    		for(var i=1901;i<=new Date().getYear();i++){
    			var option = document.createElement("option");
    			option.value = i;
    			option.innerText = i;
    			year.appendChild(option);
    		}
    	}
    	
    	function generateMonth(){
    		var month = document.getElementById("month");
    		for(var i=2;i<=12;i++){
    			var option = document.createElement("option");
    			if(i<10){
    				option.value = '0' + i;
	    			option.innerText = '0' + i;
    			}else{
	    			option.value = i;
	    			option.innerText = i;
	    		}
    			month.appendChild(option);
    		}
    	}
    	
    	function generateDay(){
    		var day = document.getElementById("day");
    		for(var i=2;i<=31;i++){
    			var option = document.createElement("option");
    			if(i<10){
    				option.value = '0' + i;
	    			option.innerText = '0' + i;
    			}else{
	    			option.value = i;
	    			option.innerText = i;
	    		}
    			day.appendChild(option);
    		}
    	}
    	
    	function init(){
    		generateYear();
    		generateMonth();
    		generateDay();
    	}
    	
    	function makePreferences(){
    		var pres = document.getElementsByName("pre");
    		var preference = "";
    		for(var i=0;i<pres.length;i++){
    			var pre = pres[i];
    			if(pre.checked==true){
    				var id = pre.value;  //2
    				preference = preference + id + ",";
    			}
    		}
    		preference = preference.substr(0,preference.length-1);  //2,3,4
    		
    		var input = document.createElement("input");
    		input.type="hidden";
    		input.name="preference";
    		input.value=preference;
    		document.getElementById("form").appendChild(input);
    	}
    	
    	function makeBirthday(){
    		var year = document.getElementById("year").value;
    		var month = document.getElementById("month").value;
    		var day = document.getElementById("day").value;
    		//1980-09-09
    		var birthday = year + "-" + month + "-" + day;
    		
    		var form = document.getElementById("form");
    		var input = document.createElement("input");
    		input.type="hidden";
    		input.name="birthday";
    		input.value=birthday;
    		
    		form.appendChild(input);
    	}
    	
    	
    	function doSubmit(){
    		
    		makeBirthday();
    		makePreferences();
    		return true;
    		
    	}