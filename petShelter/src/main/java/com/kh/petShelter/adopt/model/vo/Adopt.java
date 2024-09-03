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
	private String prtcStatus; //임시보호상태
	private String introVideo; //소개 동영상
	private String introContent; //소개 내용
	private String prtcContent; //임시보호내용
	
	public Adopt(String animalNo, String name, String spcs, String breeds,
				 String gender, String age, String weight) {
		super();
		this.animalNo = animalNo;
		this.name = name;
		this.spcs = spcs;
		this.breeds = breeds;
		this.gender = gender;
		this.age = age;
		this.weight = weight;
	}
}
