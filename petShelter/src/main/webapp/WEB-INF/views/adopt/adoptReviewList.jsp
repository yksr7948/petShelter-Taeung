<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adopt/adoptReviewList_style.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>

	<!-- 입양 리스트 헤더 -->
	<div class="adopt_header">
		<div class="adopt_nav">
			<a href="${pageContext.request.contextPath }">Home</a> >
			 <a href="#">입양</a> >
			  <a href="#">입양 후기</a>
		</div>
		<h1>입양 후기</h1>
	</div>

	<div class="container">
		<header class="review_header">
			<h1>입양 후기 게시판</h1>
			<a class="login-button" href="${pageContext.request.contextPath }/adopt/reviewInsert.ar?memberNo=1">글쓰기</a>
		</header>

		<section class="filter_area">
			<input type="text" class="search_bar" placeholder="검색어를 입력하세요...">
			<select class="filter">
				<option value="recent">최신순</option>
				<option value="popular">인기순</option>
			</select>
		</section>

		<section class="review_list">
			
			<article class="review">
				<div class="review_content">
		            <h2 class="review_title">두 번째 입양 후기</h2>
		            <p class="review_info">작성자: yksr7948 | 2024-08-23</p>
		            <p class="review_excerpt">이 강아지를 입양하게 되어 정말 행복해요...</p>
		        </div>
				<img src="${pageContext.request.contextPath }/img/survey/image-10.png" class="review_thumbnail">
			</article>
			
			<article class="review">
				<div class="review_content">
		            <h2 class="review_title">첫 번째 입양 후기</h2>
		            <p class="review_info">작성자: admin | 2024-08-23</p>
		            <p class="review_excerpt">요즘 두리는 잘 지내고 있어요 그리고...</p>
		        </div>
				<img src="${pageContext.request.contextPath }/img/survey/image-9.png" class="review_thumbnail">
			</article>

		</section>
		
		<div id="pagingArea">
                <ul class="pagination">
                	
					<!-- 페이징바 이전처리 -->
                	<c:choose>
                		<c:when test="${pi.currentPage eq 1 }">
                			<li class="page-item disabled"><a class="page-link" href="#">&laquo;</a></li>
		                    <li class="page-item disabled"><a class="page-link" href="#">&lt;</a></li>
                		</c:when>
                		<c:otherwise>
                			<li class="page-item"><a class="page-link" href="">&laquo;</a></li>
                			<li class="page-item"><a class="page-link" href="">&lt;</a></li>
                		</c:otherwise>
                	</c:choose>
                	
					<!-- 페이징바 번호 뽑아주기 -->
                	<c:forEach begin="${pi.startPage }" end="${pi.endPage }" var="p">
                		<li class="<c:if test='${pi.currentPage == p}'>current-page</c:if>">
        				<a class="page-link" href="">${p}</a>
    					</li>
                	</c:forEach>
                    
                    <!-- 페이징바 다음처리 -->
                    <c:choose>
                    	<c:when test="${pi.currentPage eq pi.maxPage }">
                    		<li class="page-item disabled"><a class="page-link" href="#">&gt;</a></li>
                    		<li class="page-item disabled"><a class="page-link" href="#">&raquo;</a></li>
                    	</c:when>
                    	<c:otherwise>
                    		<li class="page-item"><a class="page-link" href="">&gt;</a></li>
                    		<li class="page-item"><a class="page-link" href="">&raquo;</a></li>
                    	</c:otherwise>
                    </c:choose>
                </ul>
          </div>
	</div>

	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>