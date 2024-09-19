<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/enter/enterResult_style.css">
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<h1>추천된 반려동물 목록</h1>

<c:choose>
    <c:when test="${empty recommendedAnimals}">
        <p class="no-results">맞는 동물이 없는 것 같습니다. 음.. 다시 해볼까요?</p>
        <div class="retry-section">
            <a href="${pageContext.request.contextPath}/enter/startQna" class="retry-button">다시하기</a>
        </div>
    </c:when>
    <c:otherwise>
        <ul class="animal-list">
            <c:forEach var="animal" items="${recommendedAnimals}">
                <li class="animal-item">
                    <div class="image-frame">
                        <img src="${pageContext.request.contextPath}/img/qna_animals/${animal.imageUrl}" alt="${animal.breed}">
                    </div>
                    <h3>${animal.breed}</h3>
                    <p>${animal.animalDescription}</p>
                </li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>