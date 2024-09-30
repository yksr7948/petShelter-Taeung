package com.kh.petShelter.adopt.model.service;

import java.util.ArrayList;

import com.kh.petShelter.adopt.model.vo.AdoptAttachment;
import com.kh.petShelter.adopt.model.vo.AdoptReview;
import com.kh.petShelter.adopt.model.vo.Application;
import com.kh.petShelter.adopt.model.vo.PageInfo;

public interface AdoptService {

	//신청서 저장
	int insertApplication(Application app);
	
	//후기 게시글 전체 개수 조회
	int reviewCount();
	
	//후기 게시글 조회
	ArrayList<AdoptReview> selectReviewList(PageInfo pi);
	
	//후기 게시글 썸네일 조회
	ArrayList<AdoptAttachment> selectReviewThumbList();
	
	//후기 게시글 번호 추출
	int selectReviewNo();
	
	//후기 게시글 저장
	int insertReview(AdoptReview review);
	
	//썸네일 저장
	int insertAttachment(AdoptAttachment att);

	//후기 게시글 상세정보 가져오기
	AdoptReview selectReviewDetail(String reviewNo);

	//조회수 증가
	int increaseCount(String reviewNo);

}
