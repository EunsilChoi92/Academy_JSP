<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data == null ? '글 등록':'글수정' }</title>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@300&display=swap');
    * {
        margin: 0;
        padding: 0;
        font-family: 'Noto Serif KR', serif;
        font-size: 18px;
    }
    html, body {
        width: 100%;
    }
    #container {
        width: 100%;
        padding-top: 150px;
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    .content {
        width: 800px;
    }
    #frm {
        display: flex;
        flex-direction: column;
    }
    .title, .ctnt, .writer {
        display: flex;
        justify-content: space-between;
    }
    /* .writer {
        display: flex;
        align-content: center;
    } */
    input[type="text"], textarea {
        width: 90%;
        margin-bottom: 10px;
        padding-left: 10px;
        box-sizing: border-box;
    }
    textarea {
        resize: none;
        height: 500px;
    }
	.msg {
		color: red;
	}
</style>
</head>
<body>
	<div id="container">
		<div>
			<p class="msg">${msg}</p>
			<div class="content">
			<form id="frm" action="regmod" method="post">
				<input type="hidden" name="i_board" value="${data.i_board}">
				<div class="title">제목<input type="text" name="title" value="${data.title }"></div>
				<div class="ctnt">내용<textarea name="ctnt">${data.ctnt }</textarea></div>
				<div class="writer">글쓴이<input type="text" name="nm" value="${LoginUser.nm}" readonly></div>
				<div class="btn"><input type="submit" value="${data == null ? '글 등록':'글 수정' }"></div>
			</form>
			</div>
		</div>
	</div>
</body>
</html>