function go_cart(){
	if (document.formm.quantity.value == "") {
	    alert("수량을 입력하여 주세요.");
	    document.formm.quantity.focus();
	}else{
		document.formm.action ="cartInsert";
		document.formm.submit();
	}
}



function go_cart_delete(){
	//document.formm.cseq[] - 자바스크립트에서 jsp 페이지 내에 
	//같은 name의 두개이상의 입력란들은 모두 배열로 인식
	
	//라디오버튼에도 적용이 되며, 배열로 인식되었다는 말은
	//그 배열의 length(크기) 속성이 존재하고 사용이 가능다는 뜻
	
	//같은 이름으로 구성된 체크박스나 라디오버튼이 한개면 배열의 length는 
	//undefind 값을 갖고, 두개 이상이라면 배열의 갯수를 갖음
	
	//아래는 체크박스(name:cseq) 갯수가 한개일때, 두개이상일때를 구분.
	//처리한 코드로 각각 몇개인지 수를 세고 하나도 체크되지 않았다면 되돌아가는 코드임
	
	var count = 0;  //체크된 갯수를 카운트 하기위한 변수
	
	if(document.formm.cseq.length==undefined){   //장바구니에 물건이 하나일때 = 체크박스가 하나일때
		//한개라면, 그 체크박스가 체크 되어 있는지만 검사해서 count 변수에 1을 또는 0을 저장
		if( document.formm.cseq.checked == true )
			count++;
	}else{  //체크박스가 두개 이상일때
		for( var i=0; i< document.formm.cseq.length ; i++){
			if( document.formm.cseq[i].checked == true )
				count++;
		}
	}	
	if( count == 0 ){
		alert("삭제할 항목을 선택해주세요");
	} else{
		document.formm.action = "cartDelete";
	    document.formm.submit();
	}
}



function go_order_insert(){
	document.formm.action ="orderInsert";
	document.formm.submit();
}



function go_order(){
	document.formm.action = "orderOne";
	document.formm.submit();
}



function withdrawalConfirm(){
	var answer = confirm("회원탈퇴를하면 장바구니 및 이용내역이 모두 없어집니다. 탈퇴하시겠습니까?");
	if( answer ){
		location.href="Withdrawal";
	}
}