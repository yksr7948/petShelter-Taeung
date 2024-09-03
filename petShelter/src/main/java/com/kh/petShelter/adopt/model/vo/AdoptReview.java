package com.kh.petShelter.adopt.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdoptReview {
	private int reviewNo;
	private String memberNo;
	private String reviewTitle;
	private String reviewContent;
	private int count;
	private Date createDate;
	private Date modifyDate;
	private String status;
}
