<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내용</title>
</head>
<body>
<!-- 
	<table>
		<tr>
			<td>글번호 : ${data.i_board}</td>
			<td>제목 : ${data.title }</td>
			<td>내용 : ${data.ctnt }</td>
			<td>조회수 : ${data.hits }</td>
			<td>이름 : ${data.nm }</td>
			<td>작성일 : ${data.r_dt }</td>
		</tr>
	</table>
 -->
	<hr>
	<a href="list"><button>리스트</button></a>
	<c:if test="${LoginUser.i_user == data.i_user}">
		<a href="/board/regmod?i_board=${data.i_board}"><button>글수정</button></a>
		<form id="delFrm" action="/board/del" method="post" onsubmit="return submitDel()">
			<input type="hidden" name="i_board" value="${data.i_board}">
			<input type="submit" value="글삭제">
		</form>
	</c:if>
	<div>글번호 : ${data.i_board}</div>
	<div>제목 : ${data.title }</div>
	<div>조회수 : ${data.hits }</div>
	<div>이름 : ${data.nm }</div>
	<div>작성일 : ${data.r_dt }</div>
	<hr>
	<div>${data.ctnt }</div>
	
	<script>
		function submitDel() {
			if(confirm('삭제하시겠습니까?')) {
				return true;
			}
			return false;
		}
	</script>
</body>
</html>