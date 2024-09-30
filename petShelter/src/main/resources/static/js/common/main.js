let currentIndex = 0;
const slides = document.querySelectorAll('.poster-slide');
const totalSlides = slides.length;

function moveSlide(direction) {
    currentIndex += direction;

    if (currentIndex < 0) {
        currentIndex = totalSlides - 1;
    } else if (currentIndex >= totalSlides) {
        currentIndex = 0;
    }

    updateSliderPosition();
}

function updateSliderPosition() {
    const sliderContainer = document.querySelector('.poster-slider-container');
    sliderContainer.style.transform = `translateX(${-currentIndex * 100}%)`;
}

// 자동 슬라이드 기능
setInterval(() => {
    moveSlide(1);
}, 100000); 

function animalTest1(){
	location.href="/petShelter/survey/index.si"
};
function animalTest2(){
	location.href="/petShelter/enter/startQna"
};