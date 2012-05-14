
function testAcc(){
	var Acc=$("#selectAcc").val();
	var nomAcc=$('#nomAcc').val();
	
	if (Acc=='autre'){		
		$('#libelleAcc').attr('value',nomAcc);
		$("#inputAcc").attr('style','');		
		}
	else{
		$("#inputAcc").attr('style','display: none;visibility: hidden;');
		$('#libelleAcc').attr('value',Acc);
		
	}
}

function valid(){
	var SelectAccValue=$('#selectAcc').val();
	if (SelectAccValue!="autre"){
		$('#libelleAcc').attr('value',SelectAccValue);		
	}
}

function testAccAdmin(){
	var Acc=$("#selectAcc").val();
	
	if (Acc=='autre'){		
		$('#libelleAcc').attr('value','');
		$("#inputAcc").attr('style','');		
		}
	else{
		$("#inputAcc").attr('style','display: none;visibility: hidden;');
		$('#libelleAcc').attr('value',Acc);
}
}

function testAccProfi(){
	var Acc=$("#selectLibelleAcc").val();
	
	if (Acc=='autre'){
		$("#inputAcc").attr('style','');		
		}
	else{
		$("#inputAcc").attr('style','display: none;visibility: hidden;');		
}
}

