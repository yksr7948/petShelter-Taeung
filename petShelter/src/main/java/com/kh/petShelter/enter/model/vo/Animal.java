package com.kh.petShelter.enter.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
	
	 private int animalNo; //동물 번호
	 private String animalType; //동물 종류
	 private String breed; //동물 품종
	 private String animalSize; //동물 크기
	 private String withFamilies; //가족 구성원
	 private String livingSpace; //거주지 형태
	 private String experienceNeeded; //반려동물 경험이 필요한지
	 private String aloneTime; //하루 혼자 있는 시간
	 private String sheddingConcern; //털 빠짐 여부
	 private String personality; //성격
	 private String imageUrl; //동물 사진
	 private String animalDescription; //동물 설명
}
