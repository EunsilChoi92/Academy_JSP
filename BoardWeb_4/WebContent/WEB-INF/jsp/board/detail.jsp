<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내용</title>
<style>
        @import url('https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@300&display=swap');
        * {
            margin: 0;
            padding: 0;
            font-family: 'Noto Serif KR', serif;
            font-size: 16px;
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
        table {
            width: 800px;
            border-collapse: collapse;
            margin-bottom: 10px;
        }
        tr:nth-child(1) {
            height: 25px;
            border-top: 1px solid darkgray;
        }
        tr:nth-child(1) td:nth-child(1) {
            text-align: center;
        }
        tr:nth-child(1) td:nth-child(2) {
            padding-left: 10px;
            box-sizing: border-box;
            width: 500px;
        }
        tr:nth-child(1) td:nth-child(3) {
            padding-left: 10px;
            box-sizing: border-box;
        }
        tr:nth-child(2) {
            height: 25px;
        }
        tr:nth-child(2) td:first-child {
            padding-left: 10px;
            box-sizing: border-box;
            width: 200px;
            border: none;
        }
        tr:nth-child(3) {
            border-top: 1px solid darkgray;
            border-bottom: 1px solid darkgray;
        }
        tr:nth-child(3) td {
            height: 500px;
            padding: 20px 0;
        }
        .moddelBtn {
        	display: grid;
        	grid-template-columns: repeat(3, 1fr);
        	grid-template-areas: 
        		"mod list del";
        }
        .list {
        	grid-area: list;
        	justify-self: center;
        }
        
        .mod {
        	grid-area: mod;
        	justify-self: center;
        }
        .del {
        	grid-area: del;
        }
        #delFrm {
        	display: flex;
        	justify-content: center;
        }
		button, input[type="submit"] {
			border: 1px solid darkgray;	
			width: 100px;
			height: 30px;		
		}

</style>
</head>
<body>
	<div id="container">
		<div>
			<table>
	            <tr>
	                <td rowspan="2">${data.i_board}</td>
	                <td rowspan="2">${data.title }</td>
	                <td>${data.nm }</td>
	                <td rowspan="2">조회수 ${data.hits }</td>
	            </tr>
	            <tr>
	                <td>${data.r_dt == data.m_dt ? data.r_dt : data.m_dt }</td>
	            </tr>
	            <tr>
	                <td colspan="4" valign="top">${data.ctnt }</td>
	            </tr>
	        </table>
			<div class="moddelBtn">
				<div class="list">
					<a href="list"><button>글목록</button></a>			
				</div>
				<c:if test="${LoginUser.i_user == data.i_user}">
					<div class="mod">
						<a href="/board/regmod?i_board=${data.i_board}"><button>글수정</button></a>					
					</div>
					<form id="delFrm" action="/board/del" method="post" onsubmit="return submitDel()">
						<input type="hidden" name="i_board" value="${data.i_board}">
						<div class="del">
							<input type="submit" value="글삭제">
						</div>
					</form>
				</c:if> 
			</div>
		</div>
	</div>

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