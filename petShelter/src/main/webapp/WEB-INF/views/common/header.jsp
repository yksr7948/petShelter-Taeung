<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/header_style.css">

</head>
<body>
    <header>
        <div class="hbody">
            <div class="nav">
                <div class="logo">
                    <a href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/img/common/logo-title-removebg.png" alt="Logo"></a>
                </div>
                <div class="menu">
                    <ul>
                        <li>
                            <p>유기동물 관련</p>
                            <div class="submenu">
                                <ul>
                                    <h1>유기동물 관련</h1>
                                    <li><a href="#">유기 관련 1</a></li>
                                    <li><a href="#">유기 관련 2</a></li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <p>입양관련</p>
                            <div class="submenu">
                                <ul>
                                    <h1>입양 관련</h1>
                                    <li><a href="${pageContext.request.contextPath}/adopt/list.al">입양 대상 동물</a></li>
                                    <li><a href="#">입양 안내</a></li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <p>분양관련</p>
                            <div class="submenu">
                                <ul>
                                    <h1>분양 관련</h1>
                                    <li><a href="${pageContext.request.contextPath}/enter/list.en">분양 게시판</a></li>
                                    <li><a href="${pageContext.request.contextPath}/enter/startQna">동물 추천</a></li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <p>센터찾기</p>
                            <div class="submenu">
                                <ul>
                                    <h1>센터 찾기</h1>
                                    <li><a href="#">센터 찾기 1</a></li>
                                    <li><a href="#">센터 찾기 2</a></li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <p>커뮤니티</p>
                            <div class="submenu">
                                <ul>
                                    <h1>커뮤니티</h1>
                                    <li><a href="#">커뮤니티 1</a></li>
                                    <li><a href="#">커뮤니티 2</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="login-area">
                    <a class="login-button"><i class="fas fa-sign-in-alt"></i> 로그인</a> 
                    <a class="login-button"><i class="fas fa-user-plus"></i> 회원가입</a>
                </div>
            </div>
        </div>
    </header>
</body>
</html>