package com.kh.petShelter.enter.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnterLike {
	private int enterNo; //게시글 번호
	private int memberNo; //회원 번호
}
