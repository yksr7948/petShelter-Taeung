package com.kh.petShelter.adopt.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.petShelter.adopt.model.dao.AdoptDao;
import com.kh.petShelter.adopt.model.vo.AdoptAttachment;
import com.kh.petShelter.adopt.model.vo.AdoptReview;
import com.kh.petShelter.adopt.model.vo.Application;

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


}
