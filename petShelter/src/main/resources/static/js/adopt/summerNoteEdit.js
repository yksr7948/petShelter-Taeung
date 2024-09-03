const contextPath = document.querySelector("#contextPath").getAttribute("data-context-path");
var summernote = document.querySelector("#summernote");

let imgs = "";
let isFormSubmitting = false;

$(document).ready(function() {

	var toolbar = [
		// 글꼴 설정
		['fontname', ['fontname']],
		// 글자 크기 설정
		['fontsize', ['fontsize']],
		// 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
		['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
		// 글자색
		['color', ['forecolor', 'color']],
		// 표만들기
		['table', ['table']],
		// 글머리 기호, 번호매기기, 문단정렬
		['para', ['ul', 'ol', 'paragraph']],
		// 줄간격
		['height', ['height']],
		// 그림첨부, 링크만들기, 동영상첨부
		['insert', ['picture', 'link', 'video']],
		// 코드보기, 확대해서보기, 도움말
		['view', ['codeview', 'fullscreen', 'help']]
	];

	var setting = {
		height: 800,
		maxWidth: 900,
		maxHeight: 800,
		focus: true,
		disableResizeEditor: true,
		lang: 'ko-KR',
		toolbar: toolbar,
		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '돋움체', '바탕체'],
		fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72'],
		callbacks: {	//이미지 첨부하는 부분
			onImageUpload: function(files) {
				uploadSummernoteImageFile(files[0], this);
			}
		}
	};
	$('#summernote').summernote(setting);
});

//contentType:false => 직접 헤더를 설정하지 않고 브라우저가 적절한 방식으로 선택하도록 설정
//processData:flase => 데이터를 쿼리string으로 변환하지 않음
function uploadSummernoteImageFile(file, editor) {
	data = new FormData();
	data.append("file", file);
	
	$.ajax({
		data: data,
		type: "POST",
		url: contextPath+"/adopt/uploadImage.ai",
		contentType: false,
		processData: false,
		success: function(data) {
			//항상 업로드된 파일의 url이 있어야 한다.
			$(editor).summernote('insertImage', contextPath+data.url);
			imgs += data.url; ///summernoteImage/1ae733bf-5a38-4001-8e8f-b30489b0abdb.png
			
		}
	});
}

$('form').on('submit', function() {
    isFormSubmitting = true;
});

//페이지 벗어날 때 서머노트 안의 이미지 파일들 서버 상에서 삭제
//페이지 이동 전에 beforeunload함수 실행
$(window).bind("beforeunload", function(e){
	if (!isFormSubmitting) {
		$.ajax({
			url: contextPath+"/adopt/deleteSummernoteImageFile.ai",
			data: {
				imgs: imgs
			},
			method: "POST",
			success: function(data){
				console.log(data);
			},
			error: function(data){
				console.log(data);
			}
		});
		//크롬은 문자열을 리턴해야만 페이지를 나가겠냐는 confirm창이 뜬다.
		return "";
	}
});

$("div.note-editable").on('drop', function(e) {
	for (i = 0; i < e.originalEvent.dataTransfer.files.length; i++) {
		uploadSummernoteImageFile(e.originalEvent.dataTransfer.files[i], $("#summernote")[0]);
	}
	e.preventDefault();
})


