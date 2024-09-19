<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adopt/adoptReviewInsert_style.css">
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
		<header class="review_insert_header">
			<h1>입양 후기 작성</h1>
		</header>
		
		<form action="reviewInsert.ar" method="post" enctype="multipart/form-data">
			<table class="review-table">
				<tbody>
					<tr>
						<th>제목</th>
						<td><input type="text" id="review_title" name="reviewTitle" required></td>
					</tr>
					<tr>
						<th>소개글</th>
						<td><input type="text" id="review_intro" name="reviewIntro" required></td>
					</tr>
					<tr>
						<th>썸네일</th>
						<td><input type="file" id="review_attachment" name="reviewThumb" required></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea id="summernote" name="reviewContent" required></textarea></td>
					</tr>
				</tbody>
			</table>
		
			<div class="button-area" align="right">
	    		<button class="login-button" type="submit">저장하기</button>
	    		<button class="login-button" type="button" onclick="goBack();">이전으로</button>
	    	</div>
    	</form>
	</div>
	
	<div id="contextPath" data-context-path="${pageContext.request.contextPath}"></div>
	<!-- 서머노트 관련 js -->
	<script src="${pageContext.request.contextPath}/js/adopt/summerNoteEdit.js"></script>
    <script>
    	// 이전버튼
    	function goBack(){
    		window.history.back();
    	}
    </script>
	
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>