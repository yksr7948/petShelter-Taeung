//사이드바의 top속성값을 가져와 정수형로 변환
var topButtonTop = parseInt($(".tothetop").css('top'));

// 스크롤 시 실행
$(window).scroll(function() {
      // 현재 스크롤 위치
      var currentTop = $(window).scrollTop();
      
      // 새로운 top 위치 계산
      var changeTopButtonTop = currentTop + topButtonTop + "px";
      
      $(".tothetop").stop().animate({
            "top": changeTopButtonTop
        }, 500);
  });


// 스크롤 TOP 이동 기능
function tothetop(){
	window.scrollTo({ top : 0 });
}