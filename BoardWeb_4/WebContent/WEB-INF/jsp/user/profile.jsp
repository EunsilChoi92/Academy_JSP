<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필</title>
</head>
<body>
	<h1>프로필</h1>
	<div>
		<div>
			<c:choose>
				<c:when test="${data.profile_img != null}">
					<img alt="프로필 사진" src="/img/user/${LoginUser.i_user}/${data.profile_img}">
				</c:when>
				<c:otherwise>
					<img alt="프로필 사진" src="/img/default_profile.jpg">
				</c:otherwise>
			</c:choose>
		</div>
		<div>
			<form action="/profile" method="post" enctype="multipart/form-data">
				<div>
					<input type="file" name="profile_img" accept="image/*">
					<input type="submit" value="업로드">
				</div>			
			</form>
		</div>
		<div>${data.user_id }</div>
		<div>${data.nm }</div>
		<div>${data.email }</div>
		<div>${data.r_dt }</div>
		<a href="/changePw"><button>비밀번호 변경</button></a>
	</div>
	<script>
		var proc = '${param.proc}';
		switch(proc) {
		case '1' :
			alert('비밀번호를 변경하였습니다.');
			break;
		}
	</script>
</body>
</html>