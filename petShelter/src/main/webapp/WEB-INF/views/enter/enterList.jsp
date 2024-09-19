<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>분양 게시판</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/enter/enterList_style.css">
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="enter_list_header">
    <div class="enter_list_nav">
        <a href="#">Home</a> >
        <a href="#">분양</a> >
        <a href="#">분양 게시판</a>
    </div>
    <h1 class="enter_list_title">분양 게시판</h1>
</div>

<div class="enter_list_filters">
    <form id="filterForm" method="get" action="${pageContext.request.contextPath}/enter/list.en">
        <label for="locationNo">지역:</label>
        <select id="locationNo" name="locationNo">
            <option value="">전국</option>
            <c:forEach var="location" items="${locationList}">
                <option value="${location.locationNo}" <c:if test="${location.locationNo == selectedLocation}">selected</c:if>>
                    ${location.locationName}
                </option>
            </c:forEach>
        </select>
        <button type="submit">확인</button>
    </form>
</div>

<div class="enter_list_count">
    <span>전체 <b>${total}</b> 건</span>
</div>

<div class="enter_list_content">
    <table>
        <thead>
            <tr>
                <th>No</th>
                <th>카테고리</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>작성시간</th>
                <th>조회수</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="enter" items="${enterList}">
                <tr onclick="location.href='${pageContext.request.contextPath}/enter/detail.en?enterNo=${enter.enterNo}'" style="cursor: pointer;">
                    <td>${enter.enterNo}</td>
                    <td>${enter.locationName}</td>
                    <td>
                        ${enter.enterTitle}
                        <c:if test="${enter.isNew}">
                            <span class="new-label">New</span>
                        </c:if>
                    </td>
                    <td>${enter.memberNo}</td>
                    <td>${enter.formattedTime}</td>
                    <td>${enter.enterCount}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<div class="pagingArea">
    <c:if test="${startPage > 1}">
        <a href="${pageContext.request.contextPath}/enter/list.en?currentPage=${startPage - 1}&locationNo=${selectedLocation}">이전</a>
    </c:if>

    <c:forEach begin="${startPage}" end="${endPage}" var="pageNum">
        <c:choose>
            <c:when test="${pageNum == currentPage}">
                <span>${pageNum}</span>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/enter/list.en?currentPage=${pageNum}&locationNo=${selectedLocation}">${pageNum}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${endPage < totalPages}">
        <a href="${pageContext.request.contextPath}/enter/list.en?currentPage=${endPage + 1}&locationNo=${selectedLocation}">다음</a>
    </c:if>
</div>

<!-- 글쓰기 버튼 -->
<div class="write_button_area">
    <button onclick="location.href='${pageContext.request.contextPath}/enter/write.en'">글쓰기</button>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>