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
		padding-top: 50px;
		margin-bottom: 100px;
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	.contents {
		width: 800px;
	}
	h1 {
		text-align: center;
		font-size: 35px;
	}	
	.topMsg {
		display: flex;
		justify-content: space-between;
		margin: 20px 0 10px;
	}
	.welcome span {
		padding-right: 10px;
		font-size: 18px;
		
	}
	.btnAndSelect {
		width: 15%;
		display: flex;
		justify-content: space-between;
	}
	button {
		background: rgb(247, 247, 247);
		border: 1px solid darkgray;
		padding: 3px;
	}
	table {
		border-collapse: collapse;
		margin-bottom: 10px;
	}
	tablte tr {
		height: 50px;
	}
	.itemRow {
		height: 30px;
	}
	.itemRow:hover {
		background: rgb(214, 213, 213);
		cursor: pointer;
		transition: .3s;
	}
	
	th {
		border-top: 1px solid darkgray;
		border-bottom: 1px solid darkgray;
	}

	td {
		border-top: 1px solid rgb(214, 213, 213);
		text-align: center;	
	}
	tr:last-child td {
		border-bottom: 1px solid darkgray;
	}
	.pagingCnts {
		width: 100%;
		display: flex;
		justify-content: center;
	}
	.pagingCnts a {
		margin-right: 20px;
		text-decoration: none;
		font-size: 20px;
		color: black;
	}
	.pagingCnts span {
		margin-right: 20px;
		text-decoration: none;
		font-size: 20px;
		color: darkgray;
	}

	
</style>
</head>
<body>
	<div id="container">
		<div class="contents">
			<h1>목 록</h1>
			<div class="topMsg">
				<div class="welcome">
					<span>${LoginUser.nm}님 환영합니다!</span>
					<a href="/logout"><button>로그아웃</button></a>
				</div>
				<div class="btnAndSelect">
					<div class="btn">
						<a href="regmod"><button>글쓰기</button></a>
					</div>
					<div class="selectPagingCnt">
						<form id="selFrm" action="/board/list?page=${param.page == null? 1 : param.page }">
							<select name="record_cnt" onchange="changeRecordCnt()">
								<c:forEach begin="10" end="50" step="10" var="item">								
									<c:choose>
										<c:when test="${param.record_cnt == item || (param.record_cnt == null && item == 10)}">
											<option value="${item}" selected>${item}개</option>
										</c:when>
										<c:otherwise>
											<option value="${item}">${item}개</option>
										</c:otherwise> 
									</c:choose>
								</c:forEach>
							</select>
						</form>
					</div>
				</div>
			</div>
			<table>
				<tr height="50px">
					<th width="60px">No</th>
					<th width="400px">제목</th>
					<th width="100px">작성자</th>
					<th width="60px">조회수</th>
					<th width="180px">작성일</th>
				</tr>
				<tr>
					<td colspan="5" style="color: red; text-align: center">게시판 테러, 광고, 스팸글 금지^^ 어길 시 지상렬(feat. 김효진)</th>
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
			
			<!-- 여기; 고치기!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
			<div class="pagingCnts">
				<c:forEach var="index" begin="1" end="${pagingCnt }">
					<c:if test="${index == param.page || (param.page == null && index == 1) }">
						<span>
							<c:out value="${index}" />
						</span>
					</c:if>
					<c:if test="${index != param.page && index == 1}">
						<a href="/board/list?page=${index }" >
							<c:out value="${index}" />
						</a>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	<script>
		function moveToDetail(i_board) {
			console.log('moveToDetail - i_board : ' + i_board);
			location.href = 'detail?i_board=' + i_board;
		}
		function changeRecordCnt() {
			selFrm.submit();
		}

	</script>
</body>
</html>