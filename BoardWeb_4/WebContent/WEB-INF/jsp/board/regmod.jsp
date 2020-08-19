<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기/글수정</title>
<style>
	.msg {
		color: red;
	}
</style>
</head>
<body>
	<div>
		<p class="msg"><c:out value="${msg}"></c:out></p>
		<form id="frm" action="regmod" method="post">
			<div>제목  <input type="text" name="title"></div>
			<div>내용  <textarea name="ctnt"></textarea></div>
			<div>글쓴이  <input type="text" name="i_user" value="${LoginUser.nm}" readonly></div>
			<div><input type="submit" value="등록"></div>
		</form>
	</div>
</body>
</html>