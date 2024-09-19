package com.kh.petShelter.enter.model.vo;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
	private int replyNo;
	private int enterNo;
	private String reContent;
	private int memberNo;
	private Date createDate;
	private String status;
	private int parentNo;
	
	// 답글 리스트를 저장할 필드 추가
    private List<Reply> childReplies;
}
