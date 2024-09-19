package com.kh.petShelter.enter.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnterAnswer {
	 private int answerNo; //답변번호
	 private int questionNo; //질문번호
	 private String answerText; //답변 텍스트
	 private String filterCondition; //필터링 조건
	 
}
