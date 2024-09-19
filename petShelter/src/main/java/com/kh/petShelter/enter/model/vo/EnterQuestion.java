package com.kh.petShelter.enter.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnterQuestion {
	
	private int questionNo; //질문번호
    private String questionText; // 무슨 질문
    private List<EnterAnswer> answers;
}
