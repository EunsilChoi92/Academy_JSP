<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.koreait.pjt.vo.BoardVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	// List<BoardVO> list = (List)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@300&display=swap')
	;

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
.search {
	display: flex;
	justify-content: flex-end;
}
.search form input[type="submit"] {
	margin-right: 5px;
}
input[type="search"] {
	width: 150px;
}
input[type="submit"] {
	background: rgb(247, 247, 247);
	border: 1px solid darkgray;
	padding: 3px;
}
.containerpImg {
	display: inline-block;	
	width: 25px;
	height: 25px;
	border-radius: 50%;
	overflow: hidden;
	display: flex;
	align-items: center;
}
.pImg {
	object-fit: cover;
	max-width:100%;
}
/* 좋아요 하트 */
.material-icons {
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
					<span>${LoginUser.nm}님 환영합니다!</span> <a href="/logout"><button>로그아웃</button></a>
					<a href="/profile"><button>프로필</button></a>
				</div>
				<div class="btnAndSelect">
					<div class="btn">
						<a href="regmod"><button>글쓰기</button></a>
					</div>
					<div class="selectPagingCnt">
						<form id="selFrm" action="/board/list" method="get">
							<input type="hidden" name="selSearch" value="${param.selSearch}">
							<input type="hidden" name="page" value="${(param.page == null) ? 1 : param.page}"> 
							<input type="hidden" name="searchText" value="${param.searchText}"> 
								<select name="record_cnt" onchange="changeRecordCnt()">
								<c:forEach begin="10" end="30" step="5" var="item">
									<c:choose>
										<c:when test="${param.record_cnt == item}">
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
					<th> </th>
					<th width="100px">작성자</th>
					<th width="60px">조회수</th>
					<th width="180px">작성일</th>
				</tr>
				<tr>
					<td colspan="6" style="color: red; text-align: center">게시판 테러,
						광고, 스팸글 금지^^ 어길 시 지상렬(feat. 김효진)
					</th>
				</tr>
				<c:if test="${empty list}">
					<td colspan="6">게시글이 없습니다.</td>
				</c:if>
				<c:forEach items="${list}" var="item">
					<tr class="itemRow" onclick="moveToDetail(${item.i_board})">
						<td>${item.i_board }</td>
						<td>
							${item.title}
							<span style="color: darkgray; font-size: 14px; padding-left: 3px;">(${item.cmt_cnt })</span>
							<span class="material-icons">
			                	<c:if test="${item.yn_like == 1 }">
			                		favorite
			                	</c:if>
			                	<c:if test="${item.yn_like == 0 }">
			                		favorite_border
			                	</c:if>
		                	</span>
		                	<span style="color: darkgray; font-size: 14px; padding-left: 3px;">${item.like_cnt }</span>
						</td>
						<td>
							<div class="containerpImg">
								<c:choose>
					               <c:when test="${item.profile_img != null}">
					                  <img class="pImg" src="/img/user/${LoginUser.i_user}/${item.profile_img }">
					               </c:when>
					               <c:otherwise>
					                  <img class="pImg" src="/img/default_profile.jpg">
					               </c:otherwise>
					            </c:choose>
							</div>
						</td>
						<td>${item.nm }</td>
						<td>${item.hits }</td>
						<td>${item.r_dt }</td>
					</tr>
				</c:forEach>
			</table>
			<div class="search">
				<!-- 검색!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
				<form id="searchFrm" action="/board/list" method="get">
					<select name="selSearch">
						<option value="titleCtnt">제목+내용</option>
						<option value="title">제목</option>
						<option value="ctnt">내용</option>
						<option value="writer">글쓴이</option>
					</select>
					<input type="search" name="searchText">
					<input type="submit" value="검색">
				</form>
				<a href="/board/list?page=${param.page}&record_cnt=${param.record_cnt}&searchText="><button>취소</button></a>
			</div>
			<div class="pagingCnts">
				<c:forEach var="index" begin="1" end="${pagingCnt }">
					<c:choose>
						<c:when test="${page == index}">
							<span>${index}</span>
						</c:when>
						<c:otherwise>
							<a href="/board/list?page=${index}&record_cnt=${param.record_cnt}&selSearch=${param.selSearch}&searchText=${param.searchText}">${index}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
	</div>
	<script>
		function moveToDetail(i_board) {
			console.log('moveToDetail - i_board : ' + i_board);
			/*location.href = '/board/detail?i_board=' + i_board + '&page=${page}&record_cnt=' + selFrm.record_cnt.value;*/
			location.href = '/board/detail?i_board=' + i_board + '&page=${page}&record_cnt=${param.record_cnt}&searchText=${param.searchText}&selSearch=${param.selSearch}';
		}
		function changeRecordCnt() {
			selFrm.submit();
		}


	</script>
</body>
</html>