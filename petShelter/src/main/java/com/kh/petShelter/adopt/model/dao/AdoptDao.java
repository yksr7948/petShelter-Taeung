package com.kh.petShelter.adopt.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.petShelter.adopt.model.vo.AdoptAttachment;
import com.kh.petShelter.adopt.model.vo.AdoptReview;
import com.kh.petShelter.adopt.model.vo.Application;
import com.kh.petShelter.adopt.model.vo.PageInfo;

@Repository
public class AdoptDao {

	// 신청서 작성
	public int insertApplication(Application app, SqlSessionTemplate sqlSession) {

		return sqlSession.insert("adoptMapper.insertApplication", app);
	}
	
	// 후기 게시글 전체 개수 조회
	public int reviewCount(SqlSessionTemplate sqlSession) {
		
		return sqlSession.selectOne("adoptMapper.reviewCount");
	}
	
	// 후기 게시글 조회
	public ArrayList<AdoptReview> selectReviewList(PageInfo pi, SqlSessionTemplate sqlSession) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage()-1)*limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		return (ArrayList)sqlSession.selectList("adoptMapper.selectReviewList", null, rowBounds);
	}
	
	// 후기 게시글 썸네일 조회
	public ArrayList<AdoptAttachment> selectReviewThumbList(SqlSessionTemplate sqlSession) {
		
		return (ArrayList)sqlSession.selectList("adoptMapper.selectReviewThumbList");
	}

	// 게시글 번호 추출
	public int selectReviewNo(SqlSessionTemplate sqlSession) {
		
		return sqlSession.selectOne("adoptMapper.selectReviewNo");
	}
	
	// 후기 게시글 저장
	public int insertReview(AdoptReview review, SqlSessionTemplate sqlSession) {
		
		return sqlSession.insert("adoptMapper.insertReview", review);
	}

	// 썸네일 저장
	public int insertAttachment(AdoptAttachment att, SqlSessionTemplate sqlSession) {

		return sqlSession.insert("adoptMapper.insertAttachment", att);
	}

	// 후기 게시글 상세정보 가져오기
	public AdoptReview selectReviewDetail(String reviewNo, SqlSessionTemplate sqlSession) {

		return sqlSession.selectOne("adoptMapper.selectReviewDetail", reviewNo);
	}

	// 조회수 증가
	public int increaseCount(String reviewNo, SqlSessionTemplate sqlSession) {

		return sqlSession.update("adoptMapper.increaseCount", reviewNo);
	}
	
	// 검색 결과 개수 조회
	public int resultCount(String searchVal, SqlSessionTemplate sqlSession) {
		
		return sqlSession.selectOne("adoptMapper.resultCount", searchVal);
	}

	// 검색 결과 조회
	public ArrayList<AdoptReview> searchResult(String searchVal, SqlSessionTemplate sqlSession) {

		return (ArrayList)sqlSession.selectList("adoptMapper.searchResult", searchVal);
	}

	// 검색 결과 썸네일 조회
	public ArrayList<AdoptAttachment> searchThumResult(String searchVal, SqlSessionTemplate sqlSession) {
		
		return (ArrayList)sqlSession.selectList("adoptMapper.searchThumResult", searchVal);
	}

}
