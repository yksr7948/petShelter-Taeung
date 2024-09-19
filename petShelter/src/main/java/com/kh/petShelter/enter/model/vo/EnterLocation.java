package com.kh.petShelter.enter.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnterLocation {
	private int locationNo; //지역 번호
	private String locationName; //지역 이름
}
