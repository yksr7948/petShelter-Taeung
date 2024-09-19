<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/enter/enterWriter_style.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="enter_write_container">
    <h1>게시글 작성</h1>
    
    <form id="writeForm" method="post" action="${pageContext.request.contextPath}/enter/write.en" enctype="multipart/form-data">
        <!-- 지역 선택 -->
        <div class="form-group">
            <label for="locationNo">지역:</label>
            <select id="locationNo" name="locationNo" required>
                <option value="">선택하세요</option>
                <c:forEach var="location" items="${locationList}">
                    <option value="${location.locationNo}">${location.locationName}</option>
                </c:forEach>
            </select>
        </div>

        <!-- 제목 -->
        <div class="form-group">
            <label for="enterTitle">제목:</label>
            <input type="text" id="enterTitle" name="enterTitle" required>
        </div>
        
        <!-- 사진 업로드 -->
        <div class="form-group">
            <label for="enterFile">사진:</label>
            <input type="file" id="enterFile" name="enterFile" accept="image/*" required>
        </div>
        
        <!-- 내용 -->
        <div class="form-group">
            <label for="enterContent">내용:</label>
            <textarea id="enterContent" name="enterContent" rows="10" required></textarea>
        </div>
        
        <!-- 신청서 작성 버튼 -->
        <button type="button" id="openFormModal">신청서 작성</button>
        
        <!-- 신청서 작성 모달 -->
        <div id="formModal" class="modal">
            <div class="modal-content">
                <span class="close">&times;</span>
                <h2>신청서 작성</h2>
                <!-- 신청서 작성 폼 -->
                <div class="form-group">
                    <label for="enterType">품종:</label>
                    <input type="text" id="enterType" name="enterType" required>
                </div>
                <div class="form-group">
                    <label for="gender">성별:</label>
                    <input type="radio" id="genderMale" name="gender" value="남" required> 남
                    <input type="radio" id="genderFemale" name="gender" value="여" required> 여
                </div>
                <div class="form-group">
                    <label for="age">나이:</label>
                    <input type="number" id="age" name="age" required>
                </div>
                <div class="form-group">
                    <label for="inoculation">접종여부:</label>
                    <input type="radio" id="inoculationYes" name="inoculation" value="Y" required> 예
                    <input type="radio" id="inoculationNo" name="inoculation" value="N" required> 아니요
                </div>
                <div class="form-group">
                    <label for="neutering">중성화여부:</label>
                    <input type="radio" id="neuteringYes" name="neutering" value="Y" required> 예
                    <input type="radio" id="neuteringNo" name="neutering" value="N" required> 아니요
                </div>
                <div class="form-group">
                    <label for="significant">특이사항:</label>
                    <textarea id="significant" name="significant" rows="5"></textarea>
                </div>
                <div class="form-group">
                    <label for="phone">보호자 연락처:</label>
                    <input type="text" id="phone" name="phone" required>
                </div>
                <button type="button" id="saveForm">저장</button>
            </div>
        </div>
        
        <!-- 작성 완료 버튼 -->
        <div class="form-group" style="text-align: right;">
            <button type="submit" id="submitBtn" disabled>작성 완료</button>
        </div>
    </form>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

<script>
$(document).ready(function(){
    var isFormFilled = false;

    // 모달 열기
    $("#openFormModal").on("click", function(){
        $("#formModal").show();
    });

    // 모달 닫기
    $(".close").on("click", function(){
        $("#formModal").hide();
    });

    // 신청서 저장
    $("#saveForm").on("click", function(){
        $("#formModal").hide();
        isFormFilled = true;
        checkFormStatus();
    });

    // 신청서 작성 완료 여부 확인
    function checkFormStatus() {
        if (isFormFilled) {
            $("#submitBtn").prop("disabled", false);
        }
    }
});
</script>