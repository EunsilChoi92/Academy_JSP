<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
	<style>
		@import url('https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@300&display=swap');
		* {
			margin: 0;
			padding: 0;
			font-family: 'Noto Serif KR', serif;
			font-size: 20px;
		}
		html, body {
			width: 100%;
		}
		#container {
			width: 100%;
			padding-top: 50px;
			display: flex;
			flex-direction: column;
			align-items: center;
		}	
		h1 {
			text-align: center;
			font-size: 45px;
		}
		.err {
			text-align: center;
			color: red;
		}
		.formBtn {
			margin: 20px;
		}
		input, button {
			margin-top: 5px;
			width: 400px;
			height: 40px;
			padding-left: 10px;
			box-sizing: border-box;
		}
		input[type="submit"] {
			margin-top: 15px;
		}
		button, input[type="submit"] {
			border: 1px solid darkgray;			
		}
	</style>
</head>
<body>
	<div id="container">
		<div>
			<h1>로그인</h1>
			<div>
			<div class="err">${msg}</div>
			<div class="formBtn">
				<form action="/login" method="post" id="frm" onsubmit="return chk()">
					<div><input type="text" name="user_id" placeholder="아이디" value="${user_id}" required></div>
					<div><input type="password" name="user_pw" placeholder="비밀번호" required></div>
					<div><input type="submit" value="로그인"></div>
				</form>
				<div class="btn"><a href="/join"><button>회원가입</button></a></div>
			</div>
			</div>
		</div>
	</div>
	<script>
		function chk() {
			if(frm.user_id.value < 5) {
				alert('아이디는 5글자 이상이 되어야 합니다.');
				frm.user_id.focus();
				return false;	
			} else if(frm.user_pw.value.length < 5) {
				alert('비밀번호를 확인해주세요.');
				frm.user_p0wre.focus();
				return false;
			}
		}
	</script>
</body>
</html>