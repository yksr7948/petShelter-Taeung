<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adopt/adoptList_style.css">
</head>
<body>


<%@include file="/WEB-INF/views/common/header.jsp" %>

	<!-- 입양 리스트 헤더 -->
	<div class="adopt_list_header">
	    <div class="adopt_list_nav">
	        <a href="#">Home</a> > 
	        <a href="#">입양</a> > 
	        <a href="#">입양 동물</a>
	    </div>
	    <h1 class="adopt_list_title">입양 동물</h1>
	</div>
	
		<!-- 입양동물 개수 -->
		<div class="list_count">
			<span>전체 <b>${total }</b> 건</span>
		</div>
		
		<!-- 입양동물 리스트 영역 -->
		<div class="adopt_list_area">
			<c:forEach items="${adoptList }" var="animal">
				<!-- 입양동물 리스트 -->
				<div class="card">
					<!-- 썸네일 사진 -->
					<c:forEach items="${adoptImg }" var="img">
						<c:if test="${animal.animalNo == img.animalNo}">
							<img alt="" src="https://${img.photoUrl }"/>
						</c:if>
					</c:forEach>
					<!-- 유기동물 정보 -->
					<div class="card_content">
						<h2>${animal.name }</h2>
						<span>번호 : ${animal.animalNo } </span> <br>
						<span>품종 : ${animal.breeds } </span> <br>
						<span>성별 : ${animal.gender } </span> <br>
						<span>나이 : ${animal.age } </span> <br>
						<span>체중 : ${animal.weight } </span> <br>
					</div>
				</div>
			</c:forEach>
			
		</div>
	
		<!-- 페이징바 영역 -->	
		<div class="pagingArea"></div>
		
		<!-- 페이징바 js -->
		<script>
		$(function(){
		    var totalPages = Math.ceil(${total}/12);
		    var currentPage = ${currentPage};
		    updatePagingBar(totalPages, currentPage);
		});
		
		function updatePagingBar(totalPages, currentPage){
			
		    var pagingBarHtml = '';
		    
		    // 현재 페이지 그룹
		    var currentGroup = Math.ceil(currentPage / 5);
		    // 첫 번째 페이지 그룹의 첫 페이지 번호
		    var startPage = (currentGroup - 1) * 5 + 1;
		    // 마지막 페이지 그룹의 마지막 페이지 번호
		    var endPage = Math.min(startPage + 4, totalPages);
		    // 이전 그룹 버튼
		    if (currentGroup > 1) {
		        pagingBarHtml += "<a href='${pageContext.request.contextPath}/adopt/list.al?currentPage=" + (startPage - 1) + "'>Prev</a>";
		    }
		    
		    // 페이지 번호 생성
		    for (var i = startPage; i <= endPage; i++) {
		        if (i === currentPage) {
		            pagingBarHtml += "<span>" + i + "</span>";
		        } else {
		            pagingBarHtml += "<a href='${pageContext.request.contextPath}/adopt/list.al?currentPage=" + i + "'>" + i + "</a>";
		        }
		    }
		
		    // 다음 그룹 버튼
		    if (endPage < totalPages) {
		        pagingBarHtml += "<a href='${pageContext.request.contextPath}/adopt/list.al?currentPage=" + (endPage + 1) + "'>Next</a>";
		    }
		    
		    $(".pagingArea").html(pagingBarHtml);
		}
		</script>
	

<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>