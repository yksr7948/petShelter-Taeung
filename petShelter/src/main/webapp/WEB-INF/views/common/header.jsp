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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<style>
 /* 전체설정 */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: "Jua", sans-serif;
        }
        body {
            background-color: #f4f4f9;
            color: #333;
        }
        a {
            text-decoration: none;
        }
        li {
            list-style-type: none;
        }
        
        /* 헤더 */
        .hbody {
            background-color: #fff;
            width: 100%;
            height: 130px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            position: relative; /* Ensure the header is positioned */
        }
        .nav {
            display: flex;
            justify-content: center;
            align-items: center;
            padding-right: 50px;
            color: #001F3F;
        }
        
        /* 로고 */
        .logo {
            width: 20%;
        }
        .logo>img {
            width: 330px; 
            height: 130px;
        }
        
        /* 메뉴 */
        .menu {
            width: 60%;
            height: 130px;
            z-index: 2;
        }
        .menu > ul {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;
            height: 100%;
        }
        .menu > ul > li {
            position: relative;
            width: 180px;
            height: 100%;
            transition: all 0.3s ease;
        }
        .menu > ul > li > p {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100%;
            width: 100%;
            transition: color 0.3s;
            font-size: 24px;
        }
        .menu > ul > li:hover {
            background-color: #001F3F;
            border-radius: 20px;
            color: #fff;
        }
        .menu > ul > li > p:hover {
            color: #fff;
            cursor: pointer;
        }
        .menu > ul > li:hover .submenu {
            display: flex;
        }

        /* 서브메뉴 */
        .submenu {
            display: none;
            position: fixed;
            top: 130px;
            left: 0;
            width: 100%;
            height: 300px;
            background-color: #001f3f0b;
            padding: 20px;
            justify-content: center;
            align-items: center;
            z-index: 10;
        }
        .submenu h1{
            color: #001F3F;
            margin: -50px 50px 0px -300px;
            font-size: 40px;
        }
        .submenu>ul>li{
            border-radius: 20px;
            text-align: center;
            justify-content: center;
            align-items: center;
            width: 140px;
            margin-bottom: 30px;
        }
        .submenu>ul>li>a{
            font-size: 24px;
            color: #001F3F;
        }
        .submenu>ul>li a:hover{
            font-weight: bold;
            color: #7FDBFF;
        }
        
        /* 로그인 버튼 */
        .login-area {
            width: 20%;
            text-align: right;
            height: 100%;
        }
        .login-button {
            background-color: #fff;
            text-align: center;
            color: #001F3F;
            padding: 10px 20px;
            margin-left: 20px;
            border: 2px solid #001F3F;
            border-radius: 5px;
            font-size: 18px;
            font-weight: 900;
            cursor: pointer;
            transition: background-color 0.3s, color 0.3s, transform 0.2s;
        }
        .login-button:hover {
            background-color: #001F3F;
            color: white;
            transform: translateY(-2px);
        }

        /*화면 크기 1300이하*/
        @media screen and (max-width: 1300px) {
            .nav {
                padding-right: 0px;
            }
        }
        /*화면 크기 1100이하*/
        @media screen and (max-width: 1100px) {
            .menu {
                display: none;
            }
            .nav {
                padding-right: 0px;
            }
            .login-area {
                display: none;
            }
        }
</style>


</head>
<body>
    <header>
        <div class="hbody">
            <div class="nav">
                <div class="logo">
                    <a href="index.jsp"><img src="resources/logo-title-removebg.png" alt="Logo"></a>
                </div>
                <div class="menu">
                    <ul>
                        <li>
                            <p>유기동물 관련</p>
                            <div class="submenu">
                                <ul>
                                    <h1>유기동물 관련</h1>
                                    <li><a href="#">유기동물 1</a></li>
                                    <li><a href="#">유기동물 2</a></li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <p>입양관련</p>
                            <div class="submenu">
                                <ul>
                                    <h1>입양 관련</h1>
                                    <li><a href="#">입양 관련 1</a></li>
                                    <li><a href="#">입양 관련 2</a></li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <p>분양관련</p>
                            <div class="submenu">
                                <ul>
                                    <h1>분양 관련</h1>
                                    <li><a href="#">분양 관련 1</a></li>
                                    <li><a href="#">분양 관련 2</a></li>
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