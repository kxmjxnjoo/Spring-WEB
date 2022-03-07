<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/headerfooter/header.jsp" %>
<%@ include file="/resources/sub01/sub_image.html" %> 
<%@ include file="/resources/sub01/sub_menu.html" %>

<article>
<form id="join" action="joinForm" method="post" name="formm">
언제나 새로운 즐거움이 가득한 Shoes Shop의 회원가입 페이지 입니다. <br>
Shoes Shop의 회원가입은 무료이며, 회원님의 개인신상에 관한 정보는 ‘정보통신망이용촉진 
및 정보보호등에	관한 법률’에 의해 회원님의 동의없이 제 3자에게 제공되지 않으며, 철저히 
보호되고 있사오니 안심하고 이용하시기	바랍니다.<br><br>	
<textarea rows="15" cols="100">
제 1 장 총 칙

제 1 조 (목적)

이 이용약관(이하 '약관')은 주식회사 Shoew Shop(이하 회사라 합니다)와 이용 고객(이하 '회원')간에 회사가 제공하는 shoesshop.com서비스의 가입조건 및 이용에 관한 다음의 제반 사항과 기타 기본적인 사항을 구체적으로 규정함을 목적으로 합니다.


제 2 조 (이용약관의 효력 및 변경)

(1) 이 약관은 Shoew Shop 웹사이트(이하 'Shoew Shop 웹')에서 온라인으로 공시함으로써 효력을 발생하며, 합리적인 사유가 발생할 경우 관련법령에 위배되지 않는 범위 안에서 개정될 수 있습니다. 개정된 약관은 온라인에서 공지함으로써 효력을 발휘하며, 이용자의 권리 또는 의무 등 중요한 규정의 개정은 사전에 공지합니다.

(2) 회사는 합리적인 사유가 발생될 경우에는 이 약관을 변경할 수 있으며, 약관을 변경할 경우에는 지체 없이 이를 사전에 공시합니다.

(3) 이용고객은 변경된 약관에 동의하지 않으면, 언제나 "서비스" 이용을 중단하고, 이용계약을 해지할 수 있습니다. 약관의 효력발생일 이후의 계속적인 "서비스" 이용은 약관의 변경사항에 대한 이용고객의 동의로 간주됩니다.


제 3 조 (약관외 준칙)

(1) 이 약관은 회사가 제공하는 개별서비스에 관한 이용안내(이하 서비스별 안내라 합니다)와 함께 적용합니다.

(2) 이 약관에 명시되지 아니한 사항에 대해서는 별도의 세부 약관, 상관행, 회사의 공지, 이용안내, 관계법령 및 서비스별 안내의 취지에 따라 적용할 수 있습니다.
</textarea>
<br><br>
<div style="text-align: center;">
	<input type="radio" name="okon" > 동의함 &nbsp; &nbsp; &nbsp;
	<input type="radio" name="okon" checked> 동의안함
</div>

<input type="button" value="Next" class="submit" onClick="go_next();"	style="float: right;" >
</form>
</article>

<%@ include file="/resources/headerfooter/footer.jsp" %>   