
function testAcc(){
	var Acc=$("#accPersId").val();
	var nomAcc=$('#nomAcc').val();
	
	if (Acc=='0'){		
		$('#accPersNom').attr('value',nomAcc);
		$("#inputAcc").attr('style','');		
		}
	else{
		$("#inputAcc").attr('style','display: none;visibility: hidden;');
		$('#accPersNom').attr('value',Acc);
		
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

