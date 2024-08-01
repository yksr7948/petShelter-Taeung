<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!-- 기존 jstl 1.2에서 사용할수있었던 uri가 di가 변경됨에 따라 uri도 변경 -->
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/alertifyjs@1.14.0/build/alertify.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/alertifyjs@1.14.0/build/css/alertify.min.css" rel="stylesheet">

<style>
	div{
		box-sizing:border-box;
	}
	#header{
		width:80%;
		height:100px;
		padding-top:20px;
		margin:auto;
	}
	#header>div{
		width:100%;
		margin-bottom:10px;
	}
	#header_1{
		height : 40%;
	}
	#header_2{
		height : 60%;
	}
	#header_1>div{
		height : 100%;
		float : left;
	}
	#header_1_left {
		width:30%;
		position:relative;
	}
	#header_1_center {
		width:40%;
	}
	#header_1_right{
		width:30%;
	}
	#header_1_left>img{
		height : 80%;
		position : absolute;
		margin:auto;
		top:0px;
		bottom:0px;
		right:0px;
		left:0px;
	}
	#header_1_right{
		text-align : center;
		line-height : 35px;
		font-size : 12px;
		text-indent:35px;
	}
	#header_1_right>a{
		margin : 5px;
	}
	#header_1_right>a:hover{
		cursor : pointer;
	}
	#header_2>ul{
		width:100%;
		height:100%;
		list-style-type:none;
		margin:auto;
		padding:0;
	}
	#header_2>ul>li{
		float:left;
		width:25%;
		height:100%;
		line-height:55px;
		text-align:center;
	}
	#header_2>ul>li a{
		text-decoration:none;
		color:black;
		font-size:18px;
		font-weight:900;
	}
	#header_2{
		border-top:1px solid lightgray;
	}
	#header a{
		text-decoration:none; 
		color:black;
	} 
	
	/*세부 페이지마다 필요한 공통 스타일*/
	.content{
		background-color: skyblue;
		width:80%;
		margin:auto;
	}
	.innerOuter{
		border:1px solid blue;
		width:80%;
		margin:auto;
		padding: 5% 10%;
		background-color: white;
	}
	
</style>


</head>
<body>
	<div id="header">
		<div id="header_1">
			<div id="header_1_left">
				<img src="https://khedu.co.kr/resources/images/main/logo.svg">
				<!-- 로고설정 -->
			</div>
			<div id="header_1_center"></div>
			<div id="header_1_right">
				<c:choose>
					<%--로그인 전 --%>
					<c:when test="${empty loginUser }">
						<a href="/boot/member/insert.me">회원가입</a> <a data-toggle="modal" data-target="#loginModal">로그인</a>
					</c:when>
					<%-- 로그인 후 --%>				
					<c:otherwise>
						<label>${loginUser.userName }님 환영합니다</label> &nbsp;&nbsp; 
						<a href="/boot/member/mypage.me">마이페이지</a> <a href="/boot/member/logout.me">로그아웃</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div id="header_2">
			<ul>
				<li><a href="${ pageContext.request.contextPath }">HOME</a></li>
				<li><a href="">공지사항</a></li>
				<li><a href="/boot/board/list.bo">자유게시판</a></li>
				<li><a href="">사진게시판</a></li>
			</ul>
		</div>
	</div>
	
	<c:if test="${not empty alertMsg}">
		<script>
			alertify.alert("${alertMsg}");
		</script>
		<c:remove var="alertMsg"/>
	</c:if>	
	
	
	<!-- 로그인 클릭시 사용될 모달영역 -->
	<div class="modal fade" id="loginModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">LOGIN</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- 로그인 요청 처리할 form태그 -->
				<form action="/boot/member/login.me" method="post">
					<!-- Modal body -->
					<div class="modal-body">
						<label for="userId">ID :</label>
						<input type="text" class="form-control mb-2 mr-sm-2" 
								placeholder="ENTER ID" id="userId" name="userId"> <br>
						
						<label for="userPwd">PASSWORD :</label>
						<input type="password" class="form-control mb-2 mr-sm-2" 
								placeholder="ENTER PASSWORD" id="userPwd" name="userPwd">
								
					</div>
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">로그인</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
					</div>
				</form>

			</div>
		</div>
	</div>

	<br clear="both">


</body>
</html>