package com.kh.petShelter.enter.model.service;

import java.util.List;
import java.util.Map;

import com.kh.petShelter.enter.model.vo.Animal;
import com.kh.petShelter.enter.model.vo.Attachment;
import com.kh.petShelter.enter.model.vo.Enter;
import com.kh.petShelter.enter.model.vo.EnterAnswer;
import com.kh.petShelter.enter.model.vo.EnterForm;
import com.kh.petShelter.enter.model.vo.EnterLocation;
import com.kh.petShelter.enter.model.vo.EnterQuestion;
import com.kh.petShelter.enter.model.vo.Reply;
import com.kh.petShelter.enter.model.vo.UserAnswer;

public interface EnterService {
	
	//전체 항목 수 조회 메소드
	int getTotalItems(Integer locationNo);

	List<Enter> selectList(int startIndex, int itemsPerPage, Integer locationNo);

	List<EnterLocation> getLocationList();

	int insertEnter(Enter enter);

	int selectEnterNo();

	int insertAttachment(Attachment attachment);

	int insertEnterForm(EnterForm form);

	Enter selectEnter(int enterNo);

	List<Attachment> selectAttachments(int enterNo);

	EnterForm selectEnterForm(int enterNo);

	int increaseCount(int enterNo);
	
	boolean isLiked(int enterNo, int memberNo);

	int likeCount(int enterNo);

	int unlikeEnter(int enterNo, int memberNo);

	int likeEnter(int enterNo, int memberNo);

	int addLikeCount(int enterNo);

	int removeLikeCount(int enterNo);

	int insertReply(Reply reply);

	List<Reply> replyList(int enterNo);

	EnterQuestion firstQuestion();

	int totalQuestions();

	EnterQuestion nextQuestion(int currentQuestionNo);

	List<Animal> recommendedAnimals(Map<String, Object> filterConditions);

	List<EnterAnswer> answersByQuestionNo(int questionNo);

	int insertUserAnswer(UserAnswer userAnswer);

	List<UserAnswer> getUserAnswersByMemberNo(int memberNo);

	String getAnswerByAnswerNo(int answerNo);


}
