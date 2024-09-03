const contextPath = document.querySelector("#contextPath").getAttribute("data-context-path");
const qna = document.querySelector("#qna");
const result = document.querySelector("#result");
const endPoint = 10;
const select = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];

//애니메이션 효과
window.onload = function() {
	qna.style.animation = "fadeIn 1s";

	//애니메이션 끝나면 함수 호출해서 질문 가져오기
	let qIdx = 0;
	goNext(qIdx);
}

//다음 질문, 답변 가져오기
function goNext(qIdx) {

	if (qIdx === endPoint) {
		goResult();
		return;
	}
	var q = document.querySelector(".qBox");
	var status = document.querySelector(".statusBar");
	var count = document.querySelector(".statusCount");

	count.innerHTML = (qIdx + 1) + "/" + qnaList.length;
	q.innerHTML = qnaList[qIdx].q;

	//답변개수만큼 답변 가져오기
	for (let i in qnaList[qIdx].a) {
		//버튼에 집어넣을 답변 값 같이 가져가기
		addAnswer(qnaList[qIdx].a[i].answer, qIdx, i);
	}
	//status 진행 상황에 따라 게이징 바 올라가기
	status.style.width = (100 / endPoint) * (qIdx + 1) + "%";
}

//답변 가져오기
function addAnswer(answerText, qIdx, idx) {
	var a = document.querySelector(".aBox");
	var answer = document.createElement("button");
	answer.classList.add("answerList");
	answer.classList.add("my-3");
	answer.classList.add("py-3");
	answer.classList.add("fadeIn");
	a.appendChild(answer);
	//버튼에 답변 값 넣어주기
	answer.innerHTML = answerText;

	answer.addEventListener("click", function() {
		var children = document.querySelectorAll(".answerList");
		for (let i = 0; i < children.length; i++) {
			children[i].disabled = true;
			children[i].style.WebkitAnimation = "fadeOut 1s";
			children[i].style.animation = "fadeOut 1s";
		}
		setTimeout(() => {
			var target = qnaList[qIdx].a[idx].type;
			//반복문을 통해 target의 type에 접근
			for (let i = 0; i < target.length; i++) {
				select[target[i]] += 1;
			}

			for (let i = 0; i < children.length; i++) {
				children[i].style.display = "none";
			}
			goNext(++qIdx);
		}, 450)
	}, false);
}

//결과페이지로 이동
function goResult() {
	qna.style.WebkitAnimation = "fadeOut 1s";
	qna.style.animation = "fadeOut 1s";

	setTimeout(() => {
		result.style.WebkitAnimation = "fadeIn 1s";
		result.style.animation = "fadeIn 1s";
		setTimeout(() => {
			qna.style.display = "none";
			result.style.display = "block";
		}, 450)
	});
	calResult();
	setResult();
}

//결과 계산
function calResult() {
	//결과 정렬
	// ...select: 전개구문 select배열을 펼치는 기능
	//select 최대값을 가져와 result 대입
	var finalResult = select.indexOf(Math.max(...select));

	return finalResult;
}

function setResult() {
	let point = calResult();
	const resultName = document.querySelector(".resultName");
	const imgDiv = document.querySelector("#resultImg");
	const resultDesc = document.querySelector(".resultDesc");
	var imgURL = contextPath + "/img/survey/image-" + point + ".png"

	resultName.innerHTML = resultList[point].name;

	var resultImg = document.createElement("img");
	resultImg.src = imgURL;
	resultImg.alt = point;
	imgDiv.appendChild(resultImg);

	resultDesc.innerHTML = resultList[point].desc;

}