package com.kh.petShelter.adopt.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdoptImg {

	private String animalNo;
	private String photoKind;
	private String photoNo;
	private String photoUrl;
}
