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
	        <a href="${pageContext.request.contextPath }">Home</a> > 
	        <a href="#">입양</a> > 
	        <a href="#">입양 동물</a>
	    </div>
	    <h1>입양 동물</h1>
	</div>
	
	<!-- 입양동물 개수 -->
	<div class="count_category">
		<span class="count">전체 <b>${total }</b> 건</span>
		<span class="category" onchange="category();">카테고리 :
			<select id="category">
				<option value="ALL">전체</option>
				<option value="DOG">강아지</option>
				<option value="CAT">고양이</option>
			</select>
		</span>
	</div>
		
	<!-- 카테고리 js -->
	<script>
		$(function(){
			var currentCategory = "${category}";
			var categoryBox = $("#category");
			
			if(currentCategory){
				categoryBox.val(currentCategory);
			}
		});
		
		function category(){
			var categoryBox = $("#category");
			
			location.href = "${pageContext.request.contextPath}/adopt/animalList.al?category="+categoryBox.val();
		}
	</script>
		
	<!-- 입양동물 리스트 영역 -->
	<div class="adopt_list_area">
		<c:forEach items="${adoptList }" var="animal">
			<!-- 입양동물 리스트 -->
			<div class="card">
				<input type="hidden" value="${animal.animalNo }">
				<!-- 썸네일 사진 -->
				<c:forEach items="${adoptImg }" var="img">
					<c:if test="${animal.animalNo == img.animalNo}">
						<img alt="" src="https://${img.photoUrl }"/>
					</c:if>
				</c:forEach>
				<!-- 유기동물 정보 -->
				<div class="card_content">
					<h2>${animal.name }</h2>
					<span>품종 : ${animal.breeds } </span> <br>
					<span>성별 : ${animal.gender } </span> <br>
				</div>
			</div>
		</c:forEach>	
	</div>
	
	<!-- Detail페이지로 이동 js -->
	<script>
		$(function(){
			$(".card").click(function(){
				var animalNo = $(this).children().first().val();
				location.href = "${pageContext.request.contextPath}/adopt/detail.ad?animalNo=" + animalNo;
			});
		})
	</script>

	<!-- 페이징바 영역 -->	
	<div class="pagingArea"></div>
	
	<!-- 페이징바 js -->
	<script>
	$(function(){
	    var totalPages = Math.ceil(${total}/12);
	    var currentPage = ${currentPage};
	    var currentCategory = "${category}";
	    updatePagingBar(totalPages, currentPage, currentCategory);
	});
	
	function updatePagingBar(totalPages, currentPage, currentCategory){
		
	    var pagingBarHtml = '';
	    
	    // 현재 페이지 그룹
	    var currentGroup = Math.ceil(currentPage / 5);
	    // 첫 번째 페이지 그룹의 첫 페이지 번호
	    var startPage = (currentGroup - 1) * 5 + 1;
	    // 마지막 페이지 그룹의 마지막 페이지 번호
	    var endPage = Math.min(startPage + 4, totalPages);
	    // 이전 그룹 버튼
	    if (currentGroup > 1) {
	        pagingBarHtml += "<a href='${pageContext.request.contextPath}/adopt/animalList.al?currentPage="+(startPage-1)+"&category="+currentCategory+"'>Prev</a>";
	    }
	    
	    // 페이지 번호 생성
	    for (var i = startPage; i <= endPage; i++) {
	        if (i === currentPage) {
	            pagingBarHtml += "<span>" + i + "</span>";
	        } else {
	            pagingBarHtml += "<a href='${pageContext.request.contextPath}/adopt/animalList.al?currentPage="+i+"&category="+currentCategory+"'>"+i+"</a>";
	        }
	    }
	
	    // 다음 그룹 버튼
	    if (endPage < totalPages) {
	        pagingBarHtml += "<a href='${pageContext.request.contextPath}/adopt/animalList.al?currentPage="+(endPage+1)+"&category="+currentCategory+"'>Next</a>";
	    }
	    
	    $(".pagingArea").html(pagingBarHtml);
	}
	</script>
	

<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>