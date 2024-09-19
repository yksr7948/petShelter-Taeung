package com.kh.petShelter.survey.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/survey")
public class SurveyController {

	// 테스트 시작 페이지로 이동
	@GetMapping("/index.si")
	public String surveyIndex() {
		
		return "survey/surveyIndex";
	}
	
	// 질문 페이지로 이동
	@GetMapping("/question.sq")
	public String surveyQuestion() {

		return "survey/surveyQnA";
	}
	
}
