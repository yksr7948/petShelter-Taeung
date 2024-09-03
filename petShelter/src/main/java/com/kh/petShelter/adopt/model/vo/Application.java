package com.kh.petShelter.adopt.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
	private int applicationNo;
	private int memberNo;
	private String animalNo;
	private String animalName;
	private String memberName;
	private String phone;
	private String email;
	private String reason;
	private String hasPet;
	private String adoptExp;
	private String applicationDate;
	private String status;
}
