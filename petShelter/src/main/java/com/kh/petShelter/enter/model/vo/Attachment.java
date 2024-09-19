package com.kh.petShelter.enter.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {
	private int fileNo;
	private int enterNo;
	private String originName;
	private String changeName;
	private String filePath;
	private String status;
	private int fileLevel;  
}

