package com.kh.petShelter.enter.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.petShelter.enter.model.service.EnterService;
import com.kh.petShelter.enter.model.vo.Animal;
import com.kh.petShelter.enter.model.vo.Attachment;
import com.kh.petShelter.enter.model.vo.Enter;
import com.kh.petShelter.enter.model.vo.EnterAnswer;
import com.kh.petShelter.enter.model.vo.EnterForm;
import com.kh.petShelter.enter.model.vo.EnterLocation;
import com.kh.petShelter.enter.model.vo.EnterQuestion;
import com.kh.petShelter.enter.model.vo.Reply;
import com.kh.petShelter.enter.model.vo.UserAnswer;
import com.kh.petShelter.member.model.vo.Member;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/enter")
public class EnterController {
	
	@Autowired
    private EnterService enterService;
	
	 private String mapToDatabaseValue(String userValue) {
	        switch (userValue) {
	        case "강아지":
	            return "dog";
	        case "고양이":
	            return "cat";
	        case "소형견":
	            return "small";
	        case "중형견":
	            return "medium";
	        case "대형견":
	            return "large";
	        case "1인":
	            return "N";
	        case "2인":
	        case "3인":
	        case "4인이상":
	            return "Y";
	        case "아파트/빌라":
	            return "apartment";
	        case "오피스텔/단독주택(마당 미 보유)":
	            return "house_without_yard";
	        case "단독주택(마당 보유)":
	            return "house_with_yard";
	        case "기타":
	            return "other";
	        case "있다":
	            return "N";
	        case "없다":
	            return "Y";
	        case "2시간 미만":
	            return "less_than_2";
	        case "2시간 이상 6시간 미만":
	            return "2_to_6";
	        case "6시간 이상":
	            return "more_than_6";
	        case "걱정된다":
	            return "Y";
	        case "걱정되지 않는다":
	            return "N";
	        case "활발하고 밝은 아이":
	            return "active";
	        case "얌전하고 독립적인 아이":
	            return "calm";
	        default:
	            return userValue; // 매핑되지 않은 값은 그대로 반환
			}
		}

	@GetMapping("/list.en")
	public String enterList(@RequestParam(value="currentPage", defaultValue="1") int currentPage,  @RequestParam(value="locationNo", required = false) Integer locationNo, Model model) {
			
		int itemsPerPage = 10;

        int totalItems = enterService.getTotalItems(locationNo);
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        int startPage = ((currentPage - 1) / 5) * 5 + 1;
        int endPage = Math.min(startPage + 4, totalPages);

        int startIndex = (currentPage - 1) * itemsPerPage;
        List<Enter> enterList = enterService.selectList(startIndex, itemsPerPage, locationNo);
        List<EnterLocation> locationList = enterService.getLocationList();
        
     // 현재 시간과 비교하여 시간차를 계산한 후 변환
        Date now = new Date(System.currentTimeMillis());
        List<Enter> processedEnterList = enterList.stream().map(enter -> {
            long timeDiff = now.getTime() - enter.getCreateDate().getTime();
            enter.setFormattedTime(calculateTimeAgo(timeDiff)); // 시간 차이 설정
            
         // 하루 이내에 작성된 글인지 확인하여 isNew를 설정
            long diffInMillis = Math.abs(System.currentTimeMillis() - enter.getCreateDate().getTime());
            long diffInHours = TimeUnit.MILLISECONDS.toHours(diffInMillis);
            enter.setIsNew(diffInHours < 24);
            
            return enter;
        }).collect(Collectors.toList());

        model.addAttribute("total", totalItems);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("enterList", enterList);
        model.addAttribute("locationList", locationList);
        model.addAttribute("selectedLocation", locationNo);  // 선택된 지역 번호 전달

        return "enter/enterList";		
	}
	// 시간을 "몇 년 전", "몇 시간 전" 등으로 변환하는 메서드
    private String calculateTimeAgo(long timeDiff) {
        // 초 단위로 변환
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeDiff);
        // 분 단위로 변환
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeDiff);
        // 시간 단위로 변환
        long hours = TimeUnit.MILLISECONDS.toHours(timeDiff);
        // 일 단위로 변환
        long days = TimeUnit.MILLISECONDS.toDays(timeDiff);

        if (days >= 365) {
            return (days / 365) + "년 전";
        } else if (days >= 30) {
            return (days / 30) + "달 전";
        } else if (days >= 7) {
            return (days / 7) + "주 전";
        } else if (days > 0) {
            return days + "일 전";
        } else if (hours > 0) {
            return hours + "시간 전";
        } else if (minutes > 0) {
            return minutes + "분 전";
        } else {
            return "방금 전";
        }
    }
    
    //글 작성 메소드
    @GetMapping("/write.en")
    public String writerPage(Model model) {
        List<EnterLocation> locationList = enterService.getLocationList();
        model.addAttribute("locationList", locationList);
        return "enter/enterWriter";
    }
    
    @PostMapping("/write.en")
    public String insertEnter(Enter enter, 
                              @RequestParam("enterFile") MultipartFile[] upfiles, 
                              HttpSession session,
                              @RequestParam("enterType") String enterType,
                              @RequestParam("gender") String gender,
                              @RequestParam("age") int age,
                              @RequestParam("inoculation") String inoculation,
                              @RequestParam("neutering") String neutering,
                              @RequestParam("significant") String significant,
                              @RequestParam("phone") String phone) {

        // 1. 게시글 정보 저장
    	enter.setCreateDate(new Date(System.currentTimeMillis()));
        int result = enterService.insertEnter(enter);
        
        if (result > 0) {
            int enterNo = enterService.selectEnterNo();  // 방금 작성된 게시글 번호 가져오기
            enter.setEnterNo(enterNo);

            // 2. 사진 정보 저장
            List<Attachment> attachments = new ArrayList<>();
            for (MultipartFile upfile : upfiles) {
                if (!upfile.isEmpty()) {
                    String changeName = saveFile(upfile, session);
                    Attachment attachment = new Attachment();
                    attachment.setEnterNo(enterNo);
                    attachment.setOriginName(upfile.getOriginalFilename());
                    attachment.setChangeName("resources/uploadFiles/" + changeName);
                    attachment.setFilePath("resources/uploadFiles/");
                    attachment.setFileLevel(3);  // 파일 레벨 설정
                    attachments.add(attachment);
                    enterService.insertAttachment(attachment);
                }
            }

            // 3. 신청서 정보 저장
            EnterForm form = new EnterForm();
            form.setEnterNo(enterNo);  // 방금 작성된 게시글 번호를 신청서에 설정
            form.setMemberNo(enter.getMemberNo());  // 로그인 사용자 정보에서 가져와야 함
            form.setEnterType(enterType);
            form.setGender(gender);
            form.setAge(age);
            form.setInoculation(inoculation);
            form.setNeutering(neutering);
            form.setSignificant(significant);
            form.setPhone(phone);
            enterService.insertEnterForm(form);

            return "redirect:/enter/list.en";  // 게시글 리스트 페이지로 리다이렉트
        } else {
            // 실패 시 작성 페이지
            return "enter/enterWriter";
        }
    }

    // 파일 저장 메서드
    private String saveFile(MultipartFile file, HttpSession session) {
        String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
        String originalFilename = file.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        String changeName = UUID.randomUUID().toString() + ext;

        try {
            file.transferTo(new File(savePath + changeName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return changeName;
    }
    
    // 게시물 상세보기 메서드
    @GetMapping("/detail.en")
    public String enterDetail(@RequestParam("enterNo") int enterNo, Model model,HttpSession session) {
    	
    	// 임시로 세션에 Member 객체를 설정 (테스트용)
        if (session.getAttribute("member") == null) {
            Member member = new Member(1, "테스트 사용자");
            session.setAttribute("member", member);
        }
    	
    	
    	Member member = (Member) session.getAttribute("member");
    	
    	 int memberNo = member.getMemberNo();
    	
        // 1. 게시물 정보 조회
        Enter enter = enterService.selectEnter(enterNo);

        // 2. 게시물에 해당하는 첨부파일 조회
        List<Attachment> attachments = enterService.selectAttachments(enterNo);
        
        // 3. 해당 게시물에 연결된 신청서 조회
        EnterForm enterForm = enterService.selectEnterForm(enterNo);
        
        // 4. 조회수 증가
        enterService.increaseCount(enterNo);
        
        // 로그인 된 사용자 좋아요 확인 여부
        boolean isLiked = enterService.isLiked(enterNo, memberNo);
        
        //게시물 좋아요 수 조회
        int likeCount = enterService.likeCount(enterNo);
        
        // 5. 댓글 리스트 조회
        List<Reply> replyList = enterService.replyList(enterNo);
        

        // 모델에 데이터 추가
        model.addAttribute("enter", enter);
        model.addAttribute("attachments", attachments);
        model.addAttribute("enterForm", enterForm);
        model.addAttribute("replyList", replyList);
        model.addAttribute("isLiked", isLiked);
        model.addAttribute("likeCount", likeCount);
        model.addAttribute("memberName", member.getMemberName()); // 추가 예정

        return "enter/enterDetail";
    }
    
    //좋아요 토글 메서드
    @PostMapping("/toggleLike")
    @ResponseBody
    public String toggleLike(@RequestParam("enterNo") int enterNo, HttpSession session) {
    	Member member = (Member) session.getAttribute("member");
    	
    	 int memberNo = member.getMemberNo();  // 세션에서 memberNo 가져오기

        
        boolean isLiked = enterService.isLiked(enterNo, memberNo);
        if (isLiked) {
            enterService.unlikeEnter(enterNo, memberNo);
            enterService.removeLikeCount(enterNo);
        } else {
            enterService.likeEnter(enterNo, memberNo);
            enterService.addLikeCount(enterNo);
        }

        int likeCount = enterService.likeCount(enterNo);
        
        return String.valueOf(likeCount); //문자열로 변환
    }
    
    //댓글 작성 메서드
    @PostMapping("/addComment")
    public String addComment(@RequestParam("enterNo") int enterNo, @RequestParam("reContent") String reContent, HttpSession session, Model model) {
    	
    	  // 임시로 세션에 Member 객체를 설정 (테스트용)
        if (session.getAttribute("member") == null) {
            Member member = new Member();
            member.setMemberNo(1); // 임시로 사용자 번호 설정
            member.setMemberName("테스트 사용자"); // 임시로 사용자 이름 설정
            session.setAttribute("member", member);
        }
    	
    	// 세션에서 로그인된 사용자 정보 가져오기
        Member member = (Member) session.getAttribute("member");
        
        int memberNo = member.getMemberNo();
        
        // 댓글 객체 생성 및 설정
        Reply reply = new Reply();
        reply.setEnterNo(enterNo);
        reply.setReContent(reContent);
        reply.setMemberNo(memberNo);

        // 댓글 작성 서비스 호출
        int result = enterService.insertReply(reply);
        
        //댓글 작성 후 상세보기로
        return "redirect:/enter/detail.en?enterNo=" + enterNo;
    
    }
    
    
    //질문 페이지로 이동
    @GetMapping("/startQna")
    public String startQna(Model model) {
        // 첫 번째 질문
        EnterQuestion firstQuestion = enterService.firstQuestion();
        
        if (firstQuestion != null) {
            List<EnterAnswer> answers = enterService.answersByQuestionNo(firstQuestion.getQuestionNo());
            firstQuestion.setAnswers(answers); // 답변을 질문 객체에 추가
        }
        
        model.addAttribute("currentQuestion", firstQuestion);
        model.addAttribute("questionNumber", 1);  // 첫 번째 질문 번호
        model.addAttribute("totalQuestions", enterService.totalQuestions());
        return "enter/enterQna";
    }
    
    // 다음 질문을 비동기로 처리하는 메소드
    @PostMapping("/nextQuestion")
    @ResponseBody
    public Map<String, Object> nextQuestion(@RequestParam(value = "currentQuestionNo", required = true) int currentQuestionNo, @RequestParam("answerNo") int answerNo, HttpSession session) {
        
        // 세션에 사용자 답변 저장
        List<UserAnswer> userAnswers = (List<UserAnswer>) session.getAttribute("userAnswers");
        if (userAnswers == null) {
            userAnswers = new ArrayList<>();
            session.setAttribute("userAnswers", userAnswers);
        }
        
        // 임시로 memberNo 설정 (테스트용)
        Member member = (Member) session.getAttribute("member");
        if (member == null) {
            member = new Member();
            member.setMemberNo(1);  // 임의로 memberNo 설정
            session.setAttribute("member", member);
        }
        
		// 필터 조건을 설정
		String filterCondition = null;
		String answerText = enterService.getAnswerByAnswerNo(answerNo); // answerNo로부터 answerText 가져오기
	    String mappedValue = mapToDatabaseValue(answerText); // 필터링 조건 변환
	    
		switch (currentQuestionNo) {
	    case 1:
	        filterCondition = "ANIMAL_TYPE";
	        break;
	    case 2:
	        filterCondition = "ANIMAL_SIZE";
	        break;
	    case 3:
	        filterCondition = "WITH_FAMILIES";
	        break;
	    case 4:
	        filterCondition = "LIVING_SPACE";
	        break;
	    case 5:
	        filterCondition = "EXPERIENCE_NEEDED";
	        break;
	    case 6:
	        filterCondition = "ALONE_TIME";
	        break;
	    case 7:
	        filterCondition = "SHEDDING_CONCERN";
	        break;
	    case 8:
	        filterCondition = "PERSONALITY";
	        break;
	    default:
	        filterCondition = "UNKNOWN";
	        break;
	}
        
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setQuestionNo(currentQuestionNo);
        userAnswer.setAnswerNo(answerNo);
        userAnswer.setMemberNo(member.getMemberNo());  // 임시로 설정한 memberNo 사용
        userAnswer.setFilterCondition(filterCondition); // 필터 조건을 설정
        userAnswer.setAnswerText(mappedValue);
        
        userAnswers.add(userAnswer);
        
        
        enterService.insertUserAnswer(userAnswer);
        
        
        // 다음 질문 가져오기
        EnterQuestion nextQuestion = enterService.nextQuestion(currentQuestionNo);
        Map<String, Object> result = new HashMap<>();
        
        if (nextQuestion != null) {
        	List<EnterAnswer> answers = enterService.answersByQuestionNo(nextQuestion.getQuestionNo());
            nextQuestion.setAnswers(answers);
        	
            result.put("nextQuestion", nextQuestion);
            result.put("questionNumber", currentQuestionNo + 1);  // 다음 질문 번호
        } else {
            result.put("isComplete", true);  // 모든 질문 완료
        }
        
        return result;
    }
    
    // 질문 완료 후 결과 도출
    @GetMapping("/qnaResult")
    public String qnaResult(HttpSession session, Model model) {
        
        if (session.getAttribute("member") == null) {
            Member member = new Member();
            member.setMemberNo(1); // 임시로 사용자 번호 설정
            member.setMemberName("테스트 사용자"); // 임시로 사용자 이름 설정
            session.setAttribute("member", member);
        }     
        
        Member member = (Member) session.getAttribute("member");
         
        // 세션에서 사용자 답변 가져오기
        List<UserAnswer> userAnswers = enterService.getUserAnswersByMemberNo(member.getMemberNo());
        
        // 사용자 답변을 Map으로 변환
        Map<String, Object> filterConditions = new HashMap<>();
        for (UserAnswer userAnswer : userAnswers) {
            String filterCondition = userAnswer.getFilterCondition();
            
            if (filterCondition != null && filterCondition.contains("=")) {
                filterCondition = filterCondition.split("=")[0].trim();  // '=' 이전의 조건만 사용
            }
            
            String mappedValue = mapToDatabaseValue(userAnswer.getAnswerText()); // 매핑된 값 확인
            if (mappedValue != null && !mappedValue.isEmpty()) {
                filterConditions.put(filterCondition, mappedValue);
            }
        }

        // 필터 조건이 제대로 들어갔는지 확인하는 로그
        if (filterConditions.isEmpty()) {
            System.out.println("필터 조건이 비어 있습니다.");
        } else {
            for (Map.Entry<String, Object> entry : filterConditions.entrySet()) {
                System.out.println("필터 조건 - Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }
        }

        // 필터 조건을 서비스로 전달
        List<Animal> recommendedAnimals = enterService.recommendedAnimals(filterConditions);
        
        model.addAttribute("recommendedAnimals", recommendedAnimals);
        System.out.println(recommendedAnimals);  // 결과 확인 로그

        return "enter/qnaResult";
    }
}

