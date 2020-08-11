<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
<style>
	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');
	html, body {
		width: 100%;
		height: 100%;
		margin: 0;
		padding: 0;
		font-family: 'Noto Sans KR', sans-serif;
	}
	.container {
		width: inherit;
		height: inherit;
		display: flex;
        justify-content: center;
	}
	.container .contents {
		width: 50%;
		height: 70%;
		margin-top: 30px;
		border: 3px solid black;
	}	
	.container .contents .header {
		width: 100%;
		height: 120px;
		background: tomato;
	}
	.container .contents .header h1 {
		text-align: center;
	}
	.container .contents .header a  {
		
		color: black;
		text-decoration: none;
	}
	.container .contents .header div {
		display: flex;
		justify-content: flex-end;
	}
	.container .contents .header .err {
		display: flex;
		justify-content: center;
	}
	.container .contents .header button {
		background: white;
		border: 1px solid lightgray;
		font-size: 1em;
		width: 100px;
	}
	.container .contents .header button:hover {
		background: #ecf0f1;
		cursor: pointer;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="contents">
			<header class="header">
				<h1>상세페이지</h1>
				<div class="err">${err}</div>
				<div class="button"><button onclick="doDel(${data.i_board})">삭제</button></div>
			</header>
			<div>글번호 : ${data.i_board}</div>
			<div>제목 : ${data.title}</div>
			<div>내용 : ${data.ctnt}</div>
			<div>작성자 : ${data.i_student}</div>
			<script>
				function doDel(i_board) {
					if(confirm('삭제 하시겠습니까?')) {
						location.href='/boardDel?i_board=' + i_board; //get방식
					}
				}
			</script>
		</div>
	</div>
</body>
</html>