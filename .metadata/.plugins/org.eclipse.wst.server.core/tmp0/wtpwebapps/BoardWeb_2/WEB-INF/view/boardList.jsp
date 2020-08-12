<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.koreait.board.vo.BoardVO"%>
    
<%
	List<BoardVO> list = (List)request.getAttribute("data");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
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
	}
	.container .contents .header {
		width: 100%;
		height: 120px;
	}
	.container .contents .header h1 {
		text-align: center;
	}
	.container .contents .header a  {
		display: flex;
		justify-content: flex-end;
		color: black;
		text-decoration: none;
	}
	.container .contents .header a button {
		background: white;
		border: 1px solid lightgray;
		font-size: 1em;
		width: 100px;
	}
	.container .contents .header a button:hover {
		background: #ecf0f1;
		cursor: pointer;
	}
    .container .contents table {
        border-collapse: collapse;
        border-left: none;
    	border-right: none;
        width: 100%;
    }
    .container .contents table .itemRow:hover {
    	background: #ecf0f1;
    	cursor: pointer;
    }
    .container .contents table tr th:nth-child(odd) {
    	width: 20%;
    }
    .container .contents table tr, td, th {
        border: 1px solid lightgray;
        border-left: none;
    	border-right: none;
        width: 100px;
        height: 30px;
        text-align: center;
    }
</style>
</head>
<body>
	<div class="container">
		<div class="contents">
			<header class="header">
				<h1>게시판 리스트</h1>
				<a href="/boardWrite"><button>글쓰기</button></a> <!-- 글쓰는 화면 띄우는 용도인 boardWrite.jsp와 -->
					<!-- 폼으로 받아서 insert 날리고 실제 처리하는 boardWriteProc.jsp 파일 두 개가 필요함 -->
			</header>
			<table>
				<tr class="itemRow">
					<th>No</th>
					<th>제목</th>
					<th>작성자</th>
				</tr>
				<% for(BoardVO vo : list) {%>
				<tr class="itemRow" onclick="moveToDetail(<%=vo.getI_board()%>)">
					<td><%=vo.getI_board() %></td>
					<td><%=vo.getTitle() %></td>
					<td><%=vo.getI_student() %> </td>
				</tr>
				<% } %> 
			</table>
		</div>
	</div>
	<script>
		function moveToDetail(i_board) {
			console.log('moveToDetail - i_board : ' + i_board);
			location.href = 'boardDetail?i_board=' + i_board;
		}
	</script>
</body>
</html>

