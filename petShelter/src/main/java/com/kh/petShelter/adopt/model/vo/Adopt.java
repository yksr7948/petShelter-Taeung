package com.kh.petShelter.adopt.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adopt {

	private String animalNo; //동물번호
	private String name; //이름
	private String enter_date; //입소날짜
	private String spcs; //종
	private String breeds; //품종
	private String gender; //성별
	private String age; //나이
	private String weight; //체중
	private String status; //입양상태
	private String prtc_status; //임시보호상태
	private String intro_url; //소개 동영상
	private String intro_content; //소개 내용
	private String prtc_content; //임시보호내용
}
