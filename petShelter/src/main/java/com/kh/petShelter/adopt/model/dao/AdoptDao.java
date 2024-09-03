package com.kh.petShelter.adopt.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.petShelter.adopt.model.vo.AdoptAttachment;
import com.kh.petShelter.adopt.model.vo.AdoptReview;
import com.kh.petShelter.adopt.model.vo.Application;

@Repository
public class AdoptDao {

	// 신청서 작성
	public int insertApplication(Application app, SqlSessionTemplate sqlSession) {

		return sqlSession.insert("adoptMapper.insertApplication", app);
	}

	// 후기 게시글 저장
	public int insertReview(AdoptReview review, SqlSessionTemplate sqlSession) {
		
		return sqlSession.insert("adoptMapper.insertReview", review);
	}

	// 썸네일 저장
	public int insertAttachment(AdoptAttachment att, SqlSessionTemplate sqlSession) {

		return sqlSession.insert("adoptMapper.insertAttachment", att);
	}

	// 게시글 번호 추출
	public int selectReviewNo(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("adoptMapper.selectReviewNo");
	}

}
