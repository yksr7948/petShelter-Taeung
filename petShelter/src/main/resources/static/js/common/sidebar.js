//사이드바의 top속성값을 가져와 정수형로 변환
var sidebarTop = parseInt($(".sideBar").css('top'));
var topButtonTop = parseInt($(".tothetop").css('top'));

// 스크롤 시 실행
$(window).scroll(function() {
      // 현재 스크롤 위치
      var currentTop = $(window).scrollTop();
      
      // 새로운 top 위치 계산
      var changeTop = currentTop + sidebarTop + "px";
      var changeTopButtonTop = currentTop + topButtonTop + "px";

      // CSS 속성을 애니메이션으로 변경
      $(".sideBar").stop().animate({
        "top" : changeTop
      }, 500);
      
      $(".tothetop").stop().animate({
            "top": changeTopButtonTop
        }, 500);
  });

//설문조사 페이지로 이동
function survey(){
	location.href="/petShelter/survey/index.si";
}

// 스크롤 TOP 이동 기능
function tothetop(){
	window.scrollTo({ top : 0 });
}