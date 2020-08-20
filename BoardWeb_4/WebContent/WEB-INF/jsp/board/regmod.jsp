<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data == null ? '글 등록':'글수정' }</title>
<style>
	.msg {
		color: red;
	}
</style>
</head>
<body>
	<div>${data == null ? '글 등록':'글 수정' }</div>
	<div>
		<p class="msg">${msg}</p>
		<form id="frm" action="regmod" method="post">
			<input type="hidden" name="i_board" value="${data.i_board}">
			<div>제목  <input type="text" name="title" value="${data.title }"></div>
			<div>내용  <textarea name="ctnt">${data.ctnt }</textarea></div>
			<div>글쓴이  <input type="text" name="nm" value="${LoginUser.nm}" readonly></div>
			<div><input type="submit" value="${data == null ? '글 등록':'글 수정' }"></div>
		</form>
	</div>
</body>
</html>