package com.kh.petShelter.enter.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.petShelter.enter.model.vo.Animal;
import com.kh.petShelter.enter.model.vo.Attachment;
import com.kh.petShelter.enter.model.vo.Enter;
import com.kh.petShelter.enter.model.vo.EnterAnswer;
import com.kh.petShelter.enter.model.vo.EnterForm;
import com.kh.petShelter.enter.model.vo.EnterLike;
import com.kh.petShelter.enter.model.vo.EnterLocation;
import com.kh.petShelter.enter.model.vo.EnterQuestion;
import com.kh.petShelter.enter.model.vo.Reply;
import com.kh.petShelter.enter.model.vo.UserAnswer;

@Repository
public class EnterDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int getTotalItems(Integer locationNo) {
		
		return sqlSession.selectOne("enterMapper.getTotalItems", locationNo);
	}

	 public List<Enter> selectList(int startIndex, int itemsPerPage, Integer locationNo) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("startIndex", startIndex);
	    params.put("endIndex", startIndex + itemsPerPage - 1); // 페이징 계산
	    params.put("locationNo", locationNo);
	    return sqlSession.selectList("enterMapper.selectList", params);
	        
	}

	//지역 조회
	public List<EnterLocation> getLocationList() {
		
		return sqlSession.selectList("enterMapper.getLocationList");
	}
	
	//게시글 삽입
	public int insertEnter(Enter enter) {
		
		return sqlSession.insert("enterMapper.insertEnter", enter);
	}
	
	//신청서까지 저장하기 위해 저장된 게시글 불러오기
	public int selectEnterNo() {
		
		return sqlSession.selectOne("enterMapper.selectEnterNo");
	}

	//첨부파일 저장
	public int insertAttachment(Attachment attachment) {
		
		return sqlSession.insert("enterMapper.insertAttachment", attachment);
	}
	
	//신청서 삽입
	public int insertEnterForm(EnterForm form) {
		
		return sqlSession.insert("enterMapper.insertEnterForm", form);
	}
	
	//게시물 선택
	public Enter selectEnter(int enterNo) {
		
		return sqlSession.selectOne("enterMapper.selectEnter", enterNo);
	}
	
	//사진 가져오기
	public List<Attachment> selectAttachments(int enterNo) {
		
		return sqlSession.selectList("enterMapper.selectAttachments", enterNo);
	}
		
	//신청서 보기
	public EnterForm selectEnterForm(int enterNo) {
		
		return sqlSession.selectOne("enterMapper.selectEnterForm", enterNo);
	}
	
	//조회수 증가
	public int increaseCount(int enterNo) {
		
		return sqlSession.update("enterMapper.increaseCount", enterNo);
	}
	
	//좋아요 여부 판별
	public boolean isLike(int enterNo, int memberNo) {
		EnterLike enterLike = new EnterLike(enterNo, memberNo);
        Integer count = sqlSession.selectOne("enterMapper.isLiked", enterLike);
        
        return count != null && count > 0;
	}
	
	//좋아요 수 증가
	public int likeCount(int enterNo) {

		return sqlSession.selectOne("enterMapper.likeCount", enterNo);
	}
	
	//좋아요 취소
	public int unlikeEnter(int enterNo, int memberNo) {
		 EnterLike enterLike = new EnterLike(enterNo, memberNo);
		 
	     return sqlSession.delete("enterMapper.unlikeEnter", enterLike);
	}
	
	//좋아요 하기
	public int likeEnter(int enterNo, int memberNo) {
		EnterLike enterLike = new EnterLike(enterNo, memberNo);
		
        return sqlSession.insert("enterMapper.likeEnter", enterLike);
	}

	public int addLikeCount(int enterNo) {
		
		return sqlSession.update("enterMapper.addLikeCount", enterNo);
	}

	public int removeLikeCount(int enterNo) {
		
		return sqlSession.update("enterMapper.removeLikeCount", enterNo);
	}
	
	//댓글 작성
	public int insertReply(Reply reply) {
		
		return sqlSession.insert("enterMapper.insertReply", reply);
	}

	public List<Reply> replyList(int enterNo) {
	
		return sqlSession.selectList("enterMapper.replyList", enterNo);
	}
	
	//첫번째 질문 가져오기
	public EnterQuestion firstQuestion() {
		
		return sqlSession.selectOne("enterMapper.firstQuestion");
	}
	
	//전체 질문 가져오기
	public int totalQuestions() {
		
		return sqlSession.selectOne("enterMapper.totalQuestions");
	}
	
	//현재 질문 후 다음 질문 가져오기
	public EnterQuestion nextQuestion(int currentQuestionNo) {
		
		return sqlSession.selectOne("enterMapper.nextQuestion", currentQuestionNo);
	}
	
	//동물 리스트 가져오기
	public List<Animal> recommendedAnimals(Map<String, Object> filterConditions) {
		
		return sqlSession.selectList("enterMapper.recommendedAnimals", filterConditions);
	}

	public List<EnterAnswer> answersByQuestionNo(int questionNo) {
		
		return  sqlSession.selectList("enterMapper.answersByQuestionNo", questionNo);
	}
	
	//답변 저장하기
	public int insertUserAnswer(UserAnswer userAnswer) {
		
		EnterAnswer enterAnswer = sqlSession.selectOne("enterMapper.getAnswerByAnswerNo", userAnswer.getAnswerNo());
		userAnswer.setFilterCondition(enterAnswer.getFilterCondition());
		userAnswer.setAnswerText(enterAnswer.getAnswerText()); // answerText 설정
		
		return sqlSession.insert("enterMapper.insertUserAnswer",userAnswer);
	}

	public List<UserAnswer> getUserAnswersByMemberNo(int memberNo) {
		
		return sqlSession.selectList("enterMapper.getUserAnswersByMemberNo", memberNo);
	}

	public String getAnswerByAnswerNo(int answerNo) {
		
		EnterAnswer enterAnswer = sqlSession.selectOne("enterMapper.getAnswerByAnswerNo", answerNo);
		
		return enterAnswer != null ? enterAnswer.getAnswerText() : null; // answerText 반환

	}


}
