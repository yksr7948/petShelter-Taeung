package com.kh.petShelter.enter.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnterForm {
	private int fromNo; // 신청서 번호
	private int memberNo; // 회원 번호
	private int enterNo;
	private String enterType; // 품종
	private String gender; // 성별
	private int age; // 나이
	private String inoculation; // 접종 여부 (예: 'Y' - 접종, 'N' - 미접종)
	private String neutering; // 중성화 여부 (예: 'Y' - 중성화, 'N' - 중성화 아님)
	private String significant; // 특이사항
	private String phone; // 보호자 연락처
}
