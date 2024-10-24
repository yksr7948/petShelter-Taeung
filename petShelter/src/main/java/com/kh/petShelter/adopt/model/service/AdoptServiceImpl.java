package com.kh.petShelter.adopt.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.petShelter.adopt.model.dao.AdoptDao;
import com.kh.petShelter.adopt.model.vo.AdoptAttachment;
import com.kh.petShelter.adopt.model.vo.AdoptReview;
import com.kh.petShelter.adopt.model.vo.Application;
import com.kh.petShelter.adopt.model.vo.PageInfo;

@Service
public class AdoptServiceImpl implements AdoptService{

	@Autowired
	private AdoptDao adoptDao;
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 신청서 작성
	@Override
	public int insertApplication(Application app) {
		
		return adoptDao.insertApplication(app, sqlSession);
	}
	
	// 후기 게시글 전체 개수 조회
	@Override
	public int reviewCount() {
		
		return adoptDao.reviewCount(sqlSession);
	}
	
	// 후기 게시글 조회
	@Override
	public ArrayList<AdoptReview> selectReviewList(PageInfo pi) {
		
		return adoptDao.selectReviewList(pi, sqlSession);
	}
	
	// 후기 게시글 썸네일 조회
	@Override
	public ArrayList<AdoptAttachment> selectReviewThumbList() {

		return adoptDao.selectReviewThumbList(sqlSession);
	}

	// 후기 게시글 번호 추출
	@Override
	public int selectReviewNo() {
		return adoptDao.selectReviewNo(sqlSession);
	}
	
	// 후기 게시글 저장
	@Override
	public int insertReview(AdoptReview review) {

		return adoptDao.insertReview(review, sqlSession);
	}
	
	// 썸네일 저장
	@Override
	public int insertAttachment(AdoptAttachment att) {

		return adoptDao.insertAttachment(att, sqlSession);
	}

	// 후기 게시글 상세정보 가져오기
	@Override
	public AdoptReview selectReviewDetail(String reviewNo) {
		
		return adoptDao.selectReviewDetail(reviewNo, sqlSession);
	}

	// 조회수 증가
	@Override
	public int increaseCount(String reviewNo) {

		return adoptDao.increaseCount(reviewNo, sqlSession);
	}

	// 검색 결과 개수 조회
	@Override
	public int resultCount(String searchVal) {
		
		return adoptDao.resultCount(searchVal, sqlSession);
	}
	
	// 검색 결과 조회
	@Override
	public ArrayList<AdoptReview> searchResult(String searchVal) {

		return adoptDao.searchResult(searchVal, sqlSession);
	}

	// 검색 결과 썸네일 조회
	@Override
	public ArrayList<AdoptAttachment> searchThumResult(String searchVal) {
		
		return adoptDao.searchThumResult(searchVal, sqlSession);
	}

}
