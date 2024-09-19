package com.kh.petShelter.adopt.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdoptAttachment {
	private int fileNo;
	private int reviewNo;
	private String originName;
	private String changeName;
	private String filePath;
	private String status;
}
