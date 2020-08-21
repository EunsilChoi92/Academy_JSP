<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>

	/*#container {
		width: 500px;
		height: 500px;
	}
	h1 {
		text-align: center;		
	}
	.err {
		color: red;
		grid-area: err;
	}
	#frm {
		display: grid;
		grid-template-rows: repeat(6, 1fr);
		grid-template-columns: repeat(5, 1fr);
		grid-template-areas:
			". . id . ."
			". . pw . ."
			". . pwre . ."
			". . nm . ."
			". . email . ."
			". . btn . .";	
	}
	#area1, #area2, #area3, #area4, #area5, #area6 {
		width: 98%;
	}
	#area1 {
		grid-area: id;
	}
	#area2 {
		grid-area: pw;
	}
	#area3 {
		grid-area: pwre;
	}
	#area4 {
		grid-area: nm;
	}
	#area5 {
		grid-area: email;
	}
	#area6 {
		grid-area: btn;
	}*/
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
	#frm {
		margin: 20px;
		
	}
	input {
		margin-top: 5px;
		width: 400px;
		height: 40px;
		padding-left: 10px;
		box-sizing: border-box;
	}
	#area6 {
		padding: 0;
        display: flex;
        justify-content: center;
        margin-top: 10px;
    }
   	button, input[type="submit"] {
		border: 1px solid darkgray;			
	}
	
</style>
</head>
<body>
	<div id="container">
		<div>
		<h1>회원가입</h1>
		<div class="err">${msg}</div>
			<div>
				<form action="/join" id="frm" method="post" onsubmit="return chk()">
					<div id="area1"><input type="text" id="user_id" name="user_id" placeholder="아이디" value="${data.user_id}" required></div>
					<div id="area2"><input type="password" id="user_pw" name="user_pw" placeholder="비밀번호" required></div>
					<div id="area3"><input type="password" id="user_pwre" name="user_pwre" placeholder="비밀번호 확인" required></div>
					<div id="area4"><input type="text" name="nm" id="user_nm" placeholder="이름" value="${data.nm}" required></div>
					<div id="area5"><input type="email" id="email" name="email" placeholder="이메일" value="${data.email}"></div>
					<div id="area6"><input type="submit" id="submit_btn" value="회원가입"></div>
				</form>
			</div>
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