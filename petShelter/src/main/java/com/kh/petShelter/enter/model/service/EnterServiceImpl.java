package com.kh.petShelter.enter.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.petShelter.enter.model.dao.EnterDao;
import com.kh.petShelter.enter.model.vo.Animal;
import com.kh.petShelter.enter.model.vo.Attachment;
import com.kh.petShelter.enter.model.vo.Enter;
import com.kh.petShelter.enter.model.vo.EnterAnswer;
import com.kh.petShelter.enter.model.vo.EnterForm;
import com.kh.petShelter.enter.model.vo.EnterLocation;
import com.kh.petShelter.enter.model.vo.EnterQuestion;
import com.kh.petShelter.enter.model.vo.Reply;
import com.kh.petShelter.enter.model.vo.UserAnswer;

@Service
public class EnterServiceImpl implements EnterService {
	
	@Autowired
	private EnterDao enterDao;

	@Override
	public int getTotalItems(Integer locationNo) {
		
		return enterDao.getTotalItems(locationNo);
	}

	@Override
	public List<Enter> selectList(int startIndex, int itemsPerPage, Integer locationNo) {
		
		return enterDao.selectList(startIndex,itemsPerPage,locationNo);
	}

	@Override
	public List<EnterLocation> getLocationList() {
	
		return enterDao.getLocationList();
	}

	@Override
	public int insertEnter(Enter enter) {
		
		return enterDao.insertEnter(enter); 
	}

	@Override
	public int selectEnterNo() {
		
		return enterDao.selectEnterNo();
	}

	@Override
	public int insertAttachment(Attachment attachment) {
		
		return enterDao.insertAttachment(attachment);
	}

	@Override
	public int insertEnterForm(EnterForm form) {
		
		return enterDao.insertEnterForm(form);
		
	}

	@Override
	public Enter selectEnter(int enterNo) {
		
		return enterDao.selectEnter(enterNo);
	}

	@Override
	public List<Attachment> selectAttachments(int enterNo) {
		
		return enterDao.selectAttachments(enterNo);
	}

	@Override
	public EnterForm selectEnterForm(int enterNo) {
		
		return enterDao.selectEnterForm(enterNo);
	}

	@Override
	public int increaseCount(int enterNo) {
	
		return enterDao.increaseCount(enterNo);
	}
	
	//좋아요 판별
	@Override
	public boolean isLiked(int enterNo, int memberNo) {
		
		return enterDao.isLike(enterNo,memberNo);
	}
	
	//좋아요 수 조회
	@Override
	public int likeCount(int enterNo) {
		
		return enterDao.likeCount(enterNo);
	}
	
	//좋아요 취소
	@Override
	public int unlikeEnter(int enterNo, int memberNo) {
		
		return enterDao.unlikeEnter(enterNo,memberNo);
	}
	
	//좋아요 하기
	@Override
	public int likeEnter(int enterNo, int memberNo) {
		
		return enterDao.likeEnter(enterNo,memberNo);
	}
	
	//좋아요 수 증가
	@Override
	public int addLikeCount(int enterNo) {
		
		return enterDao.addLikeCount(enterNo);
	}
	
	//좋아요 수 감소
	@Override
	public int removeLikeCount(int enterNo) {
		
		return enterDao.removeLikeCount(enterNo);
	}
	
	//댓글 작성
	@Override
	public int insertReply(Reply reply) {
		
		return enterDao.insertReply(reply);
	}
	
	//댓글 리스트 조회
	@Override
	public List<Reply> replyList(int enterNo) {
		
		return enterDao.replyList(enterNo);
	}
	
	//첫 번째 질문 가져오기
	@Override
	public EnterQuestion firstQuestion() {
		
		return enterDao.firstQuestion();
	}

	//전체 질문 수 반환
	@Override
	public int totalQuestions() {
		
		return enterDao.totalQuestions();
	}

	//현재 질문 후 다음 질문 가져오기
	@Override
	public EnterQuestion nextQuestion(int currentQuestionNo) {
		
		return enterDao.nextQuestion(currentQuestionNo);
	}
	
	
	//질문에 맞는 답변 가져오기
	@Override
	public List<EnterAnswer> answersByQuestionNo(int questionNo) {
		
		return enterDao.answersByQuestionNo(questionNo);
	}
	
	//사용자 답변 저장
	@Override
	public int insertUserAnswer(UserAnswer userAnswer) {
		
		
		return enterDao.insertUserAnswer(userAnswer);
	}

	@Override
	public List<UserAnswer> getUserAnswersByMemberNo(int memberNo) {
		
		return enterDao.getUserAnswersByMemberNo(memberNo);
	}

	@Override
	public String getAnswerByAnswerNo(int answerNo) {
		
		return enterDao.getAnswerByAnswerNo(answerNo);
	}

	//답변에 따라 동물 리스트 반환
	@Override
	public List<Animal> recommendedAnimals(Map<String, Object> filterConditions) {
		
		return enterDao.recommendedAnimals(filterConditions);
		
	}

	
	
}
