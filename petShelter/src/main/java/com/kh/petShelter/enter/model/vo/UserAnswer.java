package com.kh.petShelter.enter.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswer {
	
	private int userAnswerNo; //사용자 답변 번호
	private int memberNo; //유저 번호
	private int questionNo; //질문 번호
	private int answerNo; //답변 번호
	private String filterCondition;
	private String answerText;
	
}
