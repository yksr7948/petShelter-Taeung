package com.kh.petShelter.enter.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enter {
	private int enterNo; //게시글 번호
	private int memberNo; //회원 번호
	private int fileNo; //파일 번호
	private int locationNo; //지역 번호
	private String enterTitle; //제목
	private String enterContent; //글 내용
	private Date createDate; //작성일
	private int enterCount; //조회수
	private Date enterUpdate; //수정일
	private String status; //활성화 여부
	private int likeCount; //좋아요 수
	
	private String locationName;
	private String formattedTime; // 변환된 시간 (몇 시간 전 등)
	private boolean isNew;
	
    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }
}


