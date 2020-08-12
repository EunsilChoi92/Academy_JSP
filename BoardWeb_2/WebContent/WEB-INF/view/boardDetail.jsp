<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
<style>
	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');
	* {
		padding: 0;
		margin: 0;
	}
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
	}	
	.container .contents .header {
		width: 100%;
		height: 120px;
		padding-top: 30px;
		border: 1px solid black;
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
	.container .contents main {
		width: 100%;
		height: 450px;
		margin-bottom: 20px;
		border: 1px solid black;
	} 
	.container .contents main .title {
		
	}
	.container .contents .buttons {
		display: flex;
		justify-content: space-between;
		margin: 0 30px;
	}
	.container .contents .buttons a:nth-child(2) button {
		width: 250px;
	}

	button {
		background: white;
		border: 1px solid lightgray;
		font-size: 1em;
		width: 100px;
	}	
	button:hover {
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
			</header>
			<main>
				<table>
			        <tr>
			            <td>제목</td>
			            <td colspan="2">${data.title}</td>
			        </tr>
			        <tr>
			            <td>작성자</td>
			            <td>${data.i_student}</td>
			            <td>현재시간</td>
			        </tr>
			        <tr>
			            <td colspan="3">${data.ctnt}</td>
			        </tr>
    			</table>
			</main>
			<div class="buttons">
				<a href="/boardMod?i_board=${data.i_board}"><button>수정</button></a>
				<a href="/boardList"><button>목록으로 가기</button></a>
				<button onclick="doDel(${data.i_board})">삭제</button>
			</div>
		</div>
	</div>
	
	<script>
		function doDel(i_board) {
			if(confirm('삭제 하시겠습니까?')) {
				location.href='/boardDel?i_board=' + i_board; //get방식
			}
		}
	</script>
</body>
</html>