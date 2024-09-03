<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adopt/adoptDetail_style.css">
</head>
<body>

	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	
	<!-- 입양 리스트 헤더 -->
	<div class="adopt_list_header">
	    <div class="adopt_list_nav">
	        <a href="#">Home</a> > 
	        <a href="#">입양</a> > 
	        <a href="#">입양 동물</a>
	    </div>
	</div>
		
	<!-- 입양 동물 정보 영역 -->
	<div class="animal-area">
	    <!-- 사진 영역 img-area-->
	    <div class="img-area"> 
	        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel" data-interval="false">
	            <div class="carousel-inner">
	                <c:forEach items="${img}" var="img" varStatus="status">
	                    <c:if test="${status.first}">
	                        <div class="carousel-item active">
	                            <img alt="" src="https://${img.photoUrl}">
	                        </div>
	                    </c:if>
	                    <c:if test="${status.first == false}">
	                        <div class="carousel-item">
	                            <img alt="" src="https://${img.photoUrl}">
	                        </div>
	                    </c:if>
	                </c:forEach>
	            </div>
	            <!-- 인디케이터 추가 -->
                <ol class="carousel-indicators">
                    <c:forEach items="${img}" var="img" varStatus="status">
                        <li data-target="#carouselExampleControls" data-slide-to="${status.index}" class="${status.first ? 'active' : ''}"></li>
                    </c:forEach>
                </ol>
	            <!-- 이전,다음 버튼 -->
	            <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
	                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	                <span class="sr-only">Previous</span>
	            </a>
	            <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
	                <span class="carousel-control-next-icon" aria-hidden="true"></span>
	                <span class="sr-only">Next</span>
	            </a>
	        </div>
	    </div>
	    
	    <!-- 정보 영역 -->
	    <div class="info-area">
	    	<h1>${info.name }</h1>
	    	<table>
	    		<tbody>
	    			<tr>
	    				<th>입소날짜</th>
	    				<td>${info.enter_date }</td>
	    			</tr>
	    			<tr>
	    				<th>종류</th>
	    				<td>${info.spcs }</td>
	    			</tr>
	    			<tr>
	    				<th>품종</th>
	    				<td>${info.breeds }</td>
	    			</tr>
	    			<tr>
	    				<th>성별</th>
	    				<td>${info.gender }</td>
	    			</tr>
	    			<tr>
	    				<th>나이</th>
	    				<td>${info.age }</td>
	    			</tr>
	    			<tr>
	    				<th>체중</th>
	    				<td>${info.weight }</td>
	    			</tr>
	    		</tbody>
	    	</table>
	    </div>
	</div>
	
	<!-- 유기동물 소개 영상 영역 -->
	<div class="video-area">
		<!-- 'https://youtu.be/nAtBYFqjDGQ' -->
		<!-- 유튜브 자체에서 iframe으로 직접 접근하는 것을 막아둠 -->
		<!-- 유튜브 video 페이지에 접근하려면 링크를 임베드 형식으로 변환해주어야 함 -->
		<c:set var="videoUrl" value="${info.introVideo}" />
		<!-- jstl 함수를 사용해서 원하는 값만 추출 -->
		<c:set var="videoId" value="${fn:substringAfter(videoUrl, 'youtu.be/')}"/>
		<c:set var="embedUrl" value="https://www.youtube.com/embed/${videoId}" />
		
		<iframe src="${embedUrl }" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
    </div>
    
    <!-- 유기동물 소개 글 영역 -->
    <div class="content-area" align="center">
    	<h1 style="font-size: 48px;">
    		안녕하세요! <br> 
    		제 이름은 <span style="color: #009d8f;">${info.name }</span>예요.
    	</h1>
    	<h4>${info.introContent }</h4>
    </div>
    
    <!-- 가이드 영역 -->
    <div class="guide-area">
    	<span>
    		<b style="font-size: 24px;"> &lt;입양절차 안내&gt;</b> <br>
    		입양신청서는 입양교육을 들어야 신청이 가능합니다. <br>
			입양신청서가 접수되면, 입양업무 담당자가 서류심사를 한 후, <br> 
			입양이 가능하다고 판단된 분께만 개별연락을 드리며, <br>
			1차 전화상담, 2차 방문상담 후 입양여부를 최종 결정하게 됩니다.
    	</span>
    </div>
    
    <!-- 신청버튼, 이전버튼 -->
    <div class="button-area" align="center">
    	<button class="login-button" data-toggle="modal" data-target="#application">입양 신청하기</button>
    	<button class="login-button" onclick="goBack();">이전으로</button>
    </div>
    
    <!-- 입양 신청 모달 -->
    <div class="modal fade" id="application">
		<div class="modal-dialog modal-lg">
	    	<div class="modal-content">
	      	
	      	<!-- Modal Header -->
	      	<div class="modal-header">
	        	<h2 class="modal-title">입양 신청서</h2>
	        	<button type="button" class="close" style="color: #fff" data-dismiss="modal">&times;</button>
	      	</div>
	      	
	      	<!-- Modal body -->
	        <form action="${pageContext.request.contextPath}/adopt/insertApplication.ai" method="post">
	      		<div class="modal-body">
	      			
	      			<input type="hidden" name="animalNo" value="${info.animalNo }">
	      			<input type="hidden" name="memberNo" value="1">
	      			<input type="hidden" id="phone" name="phone">
	      			<input type="hidden" id="email" name="email">
	      			
					<table class="application-table">
						<tbody>
							<tr>
								<th>입양 동물 : </th>
								<td><input id="animalName" type="text" name="animalName" value="${info.name }" readonly></td>
							</tr>
							<tr>
								<th>신청자 이름 : </th>
								<td> <input type="text" id="memberName" name="memberName" required> </td>
							</tr>
							<tr>
								<th>연락처 : </th>
								<td> 
									<input type="text" class="memberPhone" id="phone1" value="010" maxlength="3" required>
									-
									<input type="text" class="memberPhone" id="phone2" maxlength="4" required>
									-
									<input type="text" class="memberPhone" id="phone3" maxlength="4" required>
								</td>
							</tr>
							<tr>
								<th>이메일 : </th>
								<td>
									<input type="text" id="emailId" class="memberEmail" requried>
									@
									<input type="text" id="emailUrl" class="memberEmail" requried>
									<select id="selectBox" onchange="updateEmailUrl();">
										<option value="">직접 입력</option>
										<option value="naver.com">네이버</option>
										<option value="gmail.com">구글</option>
										<option value="daum.net">다음</option>
										<option value="nate.com">네이트</option>
									</select>
								</td>
							</tr>
							<tr>
								<th id="reasonItem">입양 이유 :</th>
								<td> <textarea id="adoptReason" name="reason" required></textarea> </td>
							</tr>
							<tr>
								<th>반려동물 유무 :</th>
								<td>  
									<input type="radio" name="hasPet" id="has-yes" value="있음" class="radioBox">
									<label for="has-yes">있음</label>
									<input type="radio" name="hasPet" id="has-no" value="없음" class="radioBox">
									<label for="has-no">없음</label>
								</td>
							</tr>
							<tr>
								<th>입양 경험 :</th>
								<td>  
									<input type="radio" name="adoptExp" id="exp-yes" value="있음" class="radioBox">
									<label for="exp-yes">있음</label> 
									<input type="radio" name="adoptExp" id="exp-no" value="없음" class="radioBox">
									<label for="exp-no">없음</label>									
								</td>
							</tr>
						</tbody>
					</table>
	     		</div>
	
	      		<!-- Modal footer -->
		    	<div class="modal-footer">
			        <button type="submit" class="login-button">신청하기</button>
			        <button type="button" class="login-button" data-dismiss="modal">닫기</button>
			    </div>
	        </form>
	    </div>
	  </div>
	</div>
	
	<!-- Modal js -->
	<script>
		// select박스 선택 시 자동 입력
	    function updateEmailUrl(){
			
		    var selectBox = $("#selectBox");
		    var emailUrl = $("#emailUrl");
		    var selectVal = selectBox.val();
		     				
		    if(selectVal != ""){
		    	emailUrl.val(selectVal);
		     	emailUrl.prop("readonly", true);
		     	}else{
		     	emailUrl.val(selectVal);
		     	emailUrl.prop("readonly", false);
		   		}
	   	}
		
		// 연락처, 이메일 결합
		$(function(){
			$("form").on("submit", function(){
				// 연락처
				var phone1 = $("#phone1").val();
				var phone2 = $("#phone2").val();
				var phone3 = $("#phone3").val();
				
				var combinedPhone = phone1 + "-" + phone2 + "-" + phone3;
				
				$("#phone").val(combinedPhone);
				
				// 이메일
				var emailId = $("#emailId").val();
				var emailUrl = $("#emailUrl").val();
				
				var combinedEmail = emailId + "@" + emailUrl;
				
				$("#email").val(combinedEmail);
			});
		});
	</script>
    
    <!-- 이전 버튼 js -->
    <script>
    	// 이전버튼
    	function goBack(){
    		window.history.back();
    	}
    </script>
    
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
