<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div><a href="/profile"><button>돌아가기</button></a></div>
<!-- 이전 비밀번호 확인 -->
<c:if test="${isAuth == false || isAuth == null }"> 
	<div>이전 비밀번호 확인</div>
		<div>${msg }</div>
		<form id="chgPwFrm" action="/changePw" method="post">
			<input type="hidden" name="type" value="1">
			<div>
				<input type="password" name="pw" placeholder="현재 비밀번호">
			</div>
			<div>
				<input type="submit" value="확인">
			</div>
		</form>
</c:if>
<!-- 비밀번호 변경 -->
<c:if test="${isAuth == true }"> 
	<div>비밀번호 변경</div>
	<form id="chgPwFrm" action="/changePw" method="post" onsubmit="return chkChangePw()">
		<input type="hidden" name="type" value="2">
		<div>
			<input type="password" name="pw" placeholder="변경 비밀번호">
		</div>
		<div>
			<input type="password" name="repw" placeholder="변경 비밀번호 확인">
		</div>
		<div>
			<input type="submit" value="확인">
		</div>
	</form>
</c:if>

<script>
	function chkChangePw() {
		if(chgPwFrm.pw.value != chgPwFrm.repw.value) {
			alert("비밀번호를 확인해주세요!");
			chgPwFrm.pw.value = null;
			chgPwFrm.repw.value = null;
			chgPwFr

		}
		chgPwFrm.submit();
		return true;
	}
	function init() {
		let type = chgPwFrm.type.value;
		console.log(type);
		
	}
	
	init();

</script>
</body>
</html>