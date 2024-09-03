package com.kh.petShelter.adopt.model.service;

import com.kh.petShelter.adopt.model.vo.AdoptAttachment;
import com.kh.petShelter.adopt.model.vo.AdoptReview;
import com.kh.petShelter.adopt.model.vo.Application;

public interface AdoptService {

	//신청서 저장
	int insertApplication(Application app);
	
	//후기 게시글 번호 추출
	int selectReviewNo();
	
	//후기 게시글 저장
	int insertReview(AdoptReview review);
	
	//썸네일 저장
	int insertAttachment(AdoptAttachment att);

}
