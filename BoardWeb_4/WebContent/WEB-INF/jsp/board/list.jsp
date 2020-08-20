<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.koreait.pjt.vo.BoardVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    
<%
	// List<BoardVO> list = (List)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<style>
	table {
		border-collapse: collapse;
	}

	.itemRow:hover {
		background: lightgray;
		cursor: pointer;
	}

	td, th {
		border: 1px solid black;
		text-align: center;	
	}

	
</style>
</head>
<body>
	<div id="container">
		<h1>게시판 리스트</h1>
		<div>${LoginUser.nm}님 환영합니다!</div>
		<a href="regmod"><button>글쓰기</button></a>
		<table>
			<tr>
				<th width="40px">No</th>
				<th width="350px">제목</th>
				<th width="100px">작성자</th>
				<th width="60px">조회수</th>
				<th width="150px">작성일</th>
			</tr>
			<tr>
				<td colspan="5" style="color: red; text-align: center">게시판 테러, 광고, 스팸글 금지^^ 어길 시 삼대가 폭풍설사</th>
			</tr>
			<c:if test="${empty list}">
				<td colspan="5">게시글이 없습니다.</td>
			</c:if>
			<c:forEach items="${list}" var="item">
				<tr class="itemRow" onclick="moveToDetail(${item.i_board})">
					<td>${item.i_board }</td>
					<td>${item.title}</td>
					<td>${item.nm }</td>
					<td>${item.hits }</td>
					<td>${item.r_dt }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script>
		function moveToDetail(i_board) {
			console.log('moveToDetail - i_board : ' + i_board);
			location.href = 'detail?i_board=' + i_board;
		}
	</script>
</body>
</html>