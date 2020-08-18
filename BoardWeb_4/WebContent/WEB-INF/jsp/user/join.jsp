<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
	#container {
		width: 500px;
		display: grid;
		grid-template-row: repeat(5, 100px);
		grid-template-column: repeat(5, 100px);
	}
	.err {
		color: red;
	}
</style>
</head>
<body>
	<h1>회원가입</h1>
	<div id="container">
	<div class="err">${msg}</div>
		<div>
			<form action="/join" id="frm" method="post" onsubmit="return chk()">
				<div><label><input type="text" name="user_id" placeholder="아이디" value="${data.user_id}" required></label></div>
				<div><label><input type="password" name="user_pw" placeholder="비밀번호" required></label></div>
				<div><label><input type="password" name="user_pwre" placeholder="비밀번호 확인" required></label></div>
				<div><input type="text" name="nm" placeholder="이름" value="${data.nm}" required></div>
				<div><input type="email" name="email" placeholder="이메일" value="${data.email}"></div>
				<div><input type="submit" value="회원가입"></div>
			</form>
		</div>
	</div>
	<script>
		function chk(){
			// 각각 input한테 id값 안주고 form한테 id값을 준 다음 formID값.name 또는 formID값.value 하면 그 input에 접근 가능
			if(frm.user_id.value.length < 5) {
				alert('아이디는 5글자 이상이 되어야 합니다.');
				frm.user_id.focus();
				return false;	
			} else if(frm.user_pw.value.length < 5) {
				alert('비밀번호는 5글자 이상이 되어야 합니다.');
				frm.user_pw.focus();
				return false;
			} else if(frm.user_pw.value !== frm.user_pwre.value) {
				alert('비밀번호를 확인해주세요.');
				frm.user_pwre.focus();
				return false;
			} else if(frm.nm.value.length > 0) {
				const korean = /[^가-힣]/;
				if(korean.test(frm.nm.value)) {
					alert('이름은 한글만 입력해주세요.');
					frm.nm.focus();
					return false;
				}
			} 
			if(frm.email.value.length > 0) {
				const email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
				//console.log('result : ' + email.test(frm.email.value));
				//return false;
				
				if(!email.test(frm.email.value)) {
					alert('이메일을 확인해주세요.');
					frm.email.focus();
					return false;
				}
			}
		}	
		</script>
</body>
</html>