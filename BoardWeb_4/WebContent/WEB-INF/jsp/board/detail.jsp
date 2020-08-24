<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.koreait.pjt.vo.CommentVO"%>
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
            margin-bottom: 100px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        table {
            width: 800px;
            border-collapse: collapse;
            margin-bottom: 10px;
        }
                /* .comment에도 table이 있어서 .content 선택자 추가~~ */
        .content table tr:nth-child(1) {
            height: 25px;
            border-top: 1px solid darkgray;
        }
        .content table tr:nth-child(1) td:nth-child(1) {
            text-align: center;
        }
        .content table tr:nth-child(1) td:nth-child(2) {
            padding-left: 10px;
            box-sizing: border-box;
            width: 450px;
        }
        .content table tr:nth-child(1) td:nth-child(3) {
            padding-left: 10px;
            box-sizing: border-box;
        }
        .content table tr:nth-child(2) {
            height: 25px;
        }
        .content table tr:nth-child(2) td:first-child {
            padding-left: 10px;
            box-sizing: border-box;
            width: 200px;
            border: none;
        }
        .content table tr:nth-child(3) {
            border-top: 1px solid darkgray;
            border-bottom: 1px solid darkgray;
        }
        .content table tr:nth-child(3) td {
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
		        /* 댓글 부분 */
        .comment {
            margin-top: 20px;
        }
        .comment table {
            border-collapse: collapse;
        }
        .comment table tr:first-child {
            border-top: 1px solid darkgray;
        }
        .comment table tr:last-child {
            border-bottom: 1px solid darkgray !important;
        }
        .comment table .parentComment {
            border-top: 1px dashed rgb(184, 182, 182);
            border-bottom: 1px dashed rgb(184, 182, 182);
        }
        .comment table .parentComment td {
            padding: 5px 0;
        }
        .comment table .parentComment td:first-child {
            width: 120px;
        }
        .comment table .parentComment td:last-child {
            padding-left: 10px;
            box-sizing: border-box;
        }
        .sysdate {
            color: gray;
            font-size: 15px;
            margin-left: 10px
        }
        .btns {
            display: flex;
            justify-content: flex-end;
        }
        .btns a {
            color: darkgray;
            text-decoration-line: none;
            margin-right: 15px;
        }
        /* 댓글 입력 부분 */
        form {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        textarea {
            width: 85%;
            height: 120px;
            resize: none;
            padding: 5px;
            box-sizing: border-box;
        }
        /* 대댓글~~ */
        .btns span {
            color: darkgray;
            margin-right: 15px;
            cursor: pointer;
        }
        .addCommentTextarea {
            width: 82%;
            margin: 10px 0;
        }
        /* 대댓글 구조 짜기 ㅜㅜ */
        .childComment:not(.firstChildComment) {
            margin-top: 20px;
            border-top: 1px dotted rgb(207, 207, 207);
        }
        .childComment td:nth-child(2) {
            width: 130px;
        }
        .childComment td:last-child {
            padding: 3px 0;
        }
        .arrowMark {
            color: rgb(122, 122, 122);
            padding-right: 5px;
        }
		

</style>
</head>
<body>
	<div id="container">
		<div class="content">
			<table>
	            <tr>
	                <td rowspan="2">${data.i_board}</td>
	                <td rowspan="2">${data.title }</td>
	                <td>${data.nm }</td>
	                <td rowspan="2">조회수 ${data.hits }</td>
	                <td rowspan="2">
	                	<c:if test="${data.yn_like eq 1 }">
	                	❤
	                	</c:if>
	                	<c:if test="${data.yn_like eq 0 }">
	                	ㄴㄴ
	                	</c:if>
	                </td>
	            </tr>
	            <tr>
	                <td>${data.r_dt == data.m_dt ? data.r_dt : data.m_dt }</td>
	            </tr>
	            <tr>
	                <td colspan="5" valign="top">${data.ctnt }</td>
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
		<!-- 여기부터 댓글부분 -->
		    <div class="comment">
	            <table id="table"> <!-- id table 왜 준거지?? -.-? -->
					<!-- 실제 글 i_board와 commentList의 i_board가 같다면 출력 -->
					<!-- 
					<c:if test="${data.i_board ne item.i_board}">
						<tr class="parentComment">
							<td colspan="3">등록된 댓글이 없습니다.</td>
						</tr>
					</c:if>
					 -->
					<c:forEach items="${commentList}" var="item">
						<c:if test="${data.i_board eq item.i_board}">
							<tr class="parentComment">
								<td>${item.nm }</td>
								<td id="td${item.i_comment}" colspan="2">
									<div>
									${item.commentCtnt}
										<span class="sysdate">${item.r_dt }</span>
									</div>
									<div class="btns">
			                            <span onclick="CommentInput('td${item.i_comment}')">댓글</span><span><a href="#">수정</a><a href="">삭제</a></span>
			                        </div>
		                        </td>
		                    </tr>
						</c:if>
					</c:forEach>
	                <!-- 여기는 대댓부분 여기를 고쳐봅시당닫아다아다아다 -->

	                <!-- 여기까지 대댓부분-->
	            </table>
	            <!-- 댓글 입력창, 그대로 놔두기! -->
	            <form id="addCommentFrm" action="/comment/regmod" method="post" onsubmit="return eleValid(addCommentFrm.commentCtnt)">
	                <input type="hidden" name="i_board" value="${data.i_board }">
	                <textarea name="commentCtnt" placeholder="댓글을 입력하세요.(최대 300자)"></textarea>
	                <input type="submit" value="등록">
	            </form>
        </div>
        <!-- 댓글 여기까지 -->
	</div>

	<script>
		function submitDel() {
			if(confirm('삭제하시겠습니까?')) {
				return true;
			}
			return false;
		}
		// 댓글 버튼 누르면 해당 td 안에 form태그 추가
        function showCommentInput(id) {
            var parentTr = document.getElementById(id);
            var formTag = document.createElement('form');
            formTag.setAttribute("id", "addCommentForm")
            formTag.setAttribute('action', ' ');
            formTag.setAttribute('onsubmit', 'return eleValid(addCommentForm.childCommentCtnt)')
            var textareaTag = document.createElement('textarea');
            textareaTag.setAttribute('class', 'addCommentTextarea');
            textareaTag.setAttribute('placeholder', '댓글을 입력하세요.(최대 300자)');
            textareaTag.setAttribute('name', 'childCommentCtnt');
            textareaTag.setAttribute('method', 'post');
            var submitbtn = document.createElement('input');
            submitbtn.setAttribute('type', 'submit');
            submitbtn.setAttribute('value', '등록')

            formTag.appendChild(textareaTag);
            formTag.appendChild(submitbtn);

            parentTr.appendChild(formTag);
        }

        // showCommentInput(id) 함수로 추가된 element 삭제
        function removeCommentInput() {
            var formTag = document.getElementById('addCommentForm');
            formTag.parentNode.removeChild(formTag);
        }

        // addCommentForm를 id로 가진 요소가 있는지 검사 후 대댓글 form 추가 또는 삭제
        function CommentInput(id) {
            var formTag = document.getElementById('addCommentForm');
            if(!formTag) {
                showCommentInput(id);
                return;
            }
            if(formTag.parentNode == document.getElementById(id)) {
                removeCommentInput();
                return;
            }
            removeCommentInput();
            showCommentInput(id);
        }

        // textarea 글자 수 제한
        function eleValid(ele) {
            var valueLength = ele.value.length;
			if(valueLength === 0) {
				alert('댓글을 입력해주세요.');
				ele.focus();
				return false;
			} else if(valueLength > 300) {
            var val = ele.value;
                alert('300자 이하로 입력해주세요.');
                ele.value = val.substr(0, 300);
                ele.focus();
                return false;
            }
            return true;
		}
	</script>
</body>
</html>