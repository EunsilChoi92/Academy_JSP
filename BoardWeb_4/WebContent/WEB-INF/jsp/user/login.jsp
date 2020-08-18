<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h1>로그인</h1>
	<div>
		<form action="/login" method="post" id="frm" onsubmit="return chk()">
			<div><input type="text" name="user_id" placeholder="아이디" required></div>
			<div><input type="password" name="user_pw" placeholder="비밀번호" required></div>
			<div><input type="submit" value="로그인"></div>
		</form>
	</div>
	<a href="/join"><button>회원가입</button></a>
	<script>
		function chk() {
			if(frm.user_id.value < 5) {
				alert('아이디는 5글자 이상이 되어야 합니다.');
				frm.user_id.focus();
				return false;	
			} else if(frm.user_pw.value.length < 5) {
				alert('비밀번호를 확인해주세요.');
				frm.user_pwre.focus();
				return false;
			}
		}
	
	</script>
</body>
</html>