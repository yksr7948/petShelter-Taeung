package com.kh.petShelter.adopt.contoller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.petShelter.adopt.model.service.AdoptService;
import com.kh.petShelter.adopt.model.vo.Adopt;
import com.kh.petShelter.adopt.model.vo.AdoptAttachment;
import com.kh.petShelter.adopt.model.vo.AdoptImg;
import com.kh.petShelter.adopt.model.vo.AdoptReview;
import com.kh.petShelter.adopt.model.vo.Application;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/adopt")
public class AdoptController {

	@Autowired
	private AdoptService adoptService;
	
	public static final String SERVICEKEY = "7842697a62796b733634444d425966";
	
	//	------------------------------ 입양 동물 관련 ------------------------------
	
	// List 페이지로 이동
	@GetMapping("/animalList.al")
	public String adoptList(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
							@RequestParam(value = "category", defaultValue = "ALL") String category,
							Model model) throws IOException {
		
	    // 전체 데이터 가져오기
	    JsonArray row_info = getAllDataInfo();

	    ArrayList<Adopt> infoList = new ArrayList<>();
	    
	    // 카테고리에 맞는 데이터만 필터링
	    for (int i = 0; i < row_info.size(); i++) {
	        JsonObject row = row_info.get(i).getAsJsonObject();
	        if (category.equals("ALL") || row.get("SPCS").getAsString().equals(category)) {
	            infoList.add(new Adopt(row.get("ANIMAL_NO").getAsString(), 
	                                   row.get("NM").getAsString(),
	                                   row.get("SPCS").getAsString(), 
	                                   row.get("BREEDS").getAsString(),
	                                   row.get("SEXDSTN").getAsString(), 
	                                   row.get("AGE").getAsString(),
	                                   row.get("BDWGH").getAsString()));
	        }
	    }

	    // 페이징처리
	    int pageSize = 12;
	    int totalItems = infoList.size();
	    int totalPages = (int) Math.ceil((double) totalItems / pageSize);

	    // 시작, 끝 인덱스 계산
	    int startIndex = (currentPage - 1) * pageSize;
	    int endIndex = Math.min(startIndex + pageSize, totalItems);
	    
	    // 현재 페이지가 총 페이지 수보다 크면 마지막 페이지로 설정
	    if (currentPage > totalPages) {
	        currentPage = totalPages;
	    }

	    //
	    List<Adopt> filterList = infoList.subList(startIndex, endIndex);
	    
	    
		// 전체 데이터(사진) 뽑아오기
		JsonArray row_img = getAllDataImg();

		ArrayList<AdoptImg> imgList = new ArrayList<>();

		for (int i = 0; i < row_img.size(); i++) {

			JsonObject row = row_img.get(i).getAsJsonObject();

			// 사진 종류 THUMB인 것만 가져오기
			if (row.get("PHOTO_KND").getAsString().equals("THUMB")) {
				imgList.add(new AdoptImg(row.get("ANIMAL_NO").getAsString(), 
										 row.get("PHOTO_KND").getAsString(),
										 row.get("PHOTO_NO").getAsString(), 
										 row.get("PHOTO_URL").getAsString()));
			}
		}

		model.addAttribute("total", infoList.size());
		model.addAttribute("adoptList", filterList);
		model.addAttribute("adoptImg", imgList);
		model.addAttribute("category", category);
		model.addAttribute("currentPage", currentPage);

		return "adopt/adoptList";
	}

	// Detail 페이지로 이동
	@GetMapping("detail.ad")
	public String adoptDetail(String animalNo, Model model, @ModelAttribute("msg") String msg) throws IOException {

		// 현재 입양데이터 전체 데이터 뽑아오기
		JsonArray row_info = getAllDataInfo();

		Adopt adopt = new Adopt();

		// animalNo가 같은 데이터만 가져오기
		for (int i = 0; i < row_info.size(); i++) {

			JsonObject row = row_info.get(i).getAsJsonObject();

			if (row.get("ANIMAL_NO").getAsString().equals(animalNo)) {
				adopt.setAnimalNo(row.get("ANIMAL_NO").getAsString());
				adopt.setName(row.get("NM").getAsString());
				adopt.setEnter_date(row.get("ENTRNC_DATE").getAsString());
				adopt.setSpcs(row.get("SPCS").getAsString());
				adopt.setBreeds(row.get("BREEDS").getAsString());
				adopt.setGender(row.get("SEXDSTN").getAsString());
				adopt.setAge(row.get("AGE").getAsString());
				adopt.setWeight(row.get("BDWGH").getAsString());
				adopt.setStatus(row.get("ADP_STTUS").getAsString());
				adopt.setPrtcStatus(row.get("TMPR_PRTC_STTUS").getAsString());
				adopt.setIntroVideo(row.get("INTRCN_MVP_URL").getAsString());
				adopt.setIntroContent(row.get("INTRCN_CN").getAsString());
				adopt.setPrtcContent(row.get("TMPR_PRTC_CN").getAsString());
			}
		}

		// 사진 데이터 전체 가져오기
		JsonArray row_img = getAllDataImg();

		ArrayList<AdoptImg> imgList = new ArrayList<>();

		for (int i = 0; i < row_img.size(); i++) {

			JsonObject row = row_img.get(i).getAsJsonObject();

			// 해당 동물번호인 사진만 가져오기
			if (row.get("ANIMAL_NO").getAsString().equals(animalNo)) {
				imgList.add(new AdoptImg(row.get("ANIMAL_NO").getAsString(),
										 row.get("PHOTO_KND").getAsString(),
										 row.get("PHOTO_NO").getAsString(), 
										 row.get("PHOTO_URL").getAsString()));
			}
		}

		model.addAttribute("info", adopt);
		model.addAttribute("img", imgList);

		// 메세지가 있을 경우 모델에 추가하기
		if (!msg.isEmpty() && msg != null) {
			model.addAttribute("msg", msg);
		}

		return "adopt/adoptDetail";
	}

	// 신청서 저장
	@PostMapping("insertApplication.ai")
	public String insertApplication(Application app, HttpSession session) {

		int result = adoptService.insertApplication(app);

		if (result > 0) {
			// addFlashAttribute :
			// 플래시 속성은 URL에 메시지를 노출시키지 않고 세션에 임시로 저장하는 방식
			session.setAttribute("msg", "신청서가 저장되었습니다. 관리자의 확인 절차를 거친 후, 개별적으로 연락 드리겠습니다.");
		}

		return "redirect:/adopt/detail.ad?animalNo=" + app.getAnimalNo();
	}

	
	
	//	------------------------------ 여기까지 입양 동물 관련 ------------------------------
	
	//	------------------------------ 입양 동물 관련 공통 메소드 ------------------------------
	
	// 공통된 작업 메서드로 정의하여 필요할 때 호출하기
	private String getResponse(String urlString) throws IOException {

		URL url = new URL(urlString); // 요청 URL 객체 준비
		HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
		urlCon.setRequestMethod("GET"); // GET 방식 요청 설정

		BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
		StringBuilder responseStr = new StringBuilder();

		String line;

		while ((line = br.readLine()) != null) {
			responseStr.append(line);
		}

		br.close();
		urlCon.disconnect();

		return responseStr.toString();
	}

	// info 전체 데이터 가져오기
	private JsonArray getAllDataInfo() throws IOException {

		// 현재 입양데이터 전체 데이터 개수 뽑아오기
		String url_total = "http://openapi.seoul.go.kr:8088/" + SERVICEKEY + "/json/TbAdpWaitAnimalView/1/1/";

		String responseTotal = getResponse(url_total);
		JsonObject jobj_total = JsonParser.parseString(responseTotal.toString()).getAsJsonObject();
		JsonObject TbAdpWaitAnimalTotal = jobj_total.getAsJsonObject("TbAdpWaitAnimalView");

		String list_total_count = TbAdpWaitAnimalTotal.get("list_total_count").getAsString();

		// 데이터 전체 가져오기
		String url_info = "http://openapi.seoul.go.kr:8088/" + SERVICEKEY;
		url_info += "/json/TbAdpWaitAnimalView/1/" + list_total_count + "/";

		String responseInfo = getResponse(url_info);
		JsonObject jobj_info = JsonParser.parseString(responseInfo.toString()).getAsJsonObject();
		JsonObject TbAdpWaitAnimalView = jobj_info.getAsJsonObject("TbAdpWaitAnimalView");
		JsonArray row_info = TbAdpWaitAnimalView.getAsJsonArray("row");

		return row_info;
	}

	// image 전체 데이터 가져오기
	private JsonArray getAllDataImg() throws IOException {

		// 현재 입양데이터 전체 데이터 개수 뽑아오기
		String url_total = "http://openapi.seoul.go.kr:8088/" + SERVICEKEY + "/json/TbAdpWaitAnimalPhotoView/1/1/";

		String responseTotal = getResponse(url_total);
		JsonObject jobj_total = JsonParser.parseString(responseTotal.toString()).getAsJsonObject();
		JsonObject TbAdpWaitAnimalPhotoTotal = jobj_total.getAsJsonObject("TbAdpWaitAnimalPhotoView");

		String list_total_count = TbAdpWaitAnimalPhotoTotal.get("list_total_count").getAsString();

		String url_img = "http://openapi.seoul.go.kr:8088/" + SERVICEKEY;
		url_img += "/json/TbAdpWaitAnimalPhotoView/1/" + list_total_count + "/";

		String responseImg = getResponse(url_img);
		JsonObject jobj_img = JsonParser.parseString(responseImg.toString()).getAsJsonObject();
		JsonObject TbAdpWaitAnimalPhotoView = jobj_img.getAsJsonObject("TbAdpWaitAnimalPhotoView");
		JsonArray row_img = TbAdpWaitAnimalPhotoView.getAsJsonArray("row");

		return row_img;
	}

	
	//	------------------------------ 여기까지 공통 메소드 ------------------------------
	
	//	------------------------------ 입양 후기 ------------------------------
	
	// 입양 후기페이지로 이동
	@GetMapping("reviewList.ar")
	public String adoptReviewList(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
		
		return "adopt/adoptReviewList";
	}
	
	// 입양 후기 작성페이지로 이동
	@GetMapping("reviewInsert.ar")
	public String insertReviewPage(int memberNo) {
		
		return "adopt/adoptReviewInsert";
	}
	
	// 서머노트 이미지 업로드
	@PostMapping(value="uploadImage.ai", produces="application/json")
	@ResponseBody
    public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpSession session) {
		
		JsonObject jsonObject = new JsonObject();

        // 만약 나중에 배포 시 외부파일에 저장할 경우 : "C:\\summernote_image\\";	
        String fileRoot = session.getServletContext().getRealPath("/resources/uploadFiles/");
        
        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자

        // 랜덤 UUID+확장자로 저장될 savedFileName
        String savedFileName = UUID.randomUUID() + extension;	
        
        File targetFile = new File(fileRoot + savedFileName);

        try {
            InputStream fileStream = multipartFile.getInputStream();
            //파일 저장
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
            //접근할 url 주소
            jsonObject.addProperty("url", "/summernoteImage/"+savedFileName);
            jsonObject.addProperty("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);	// 실패시 저장된 파일 삭제
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
        }
        
        String a = jsonObject.toString();

        return a;
    }
	
	// 서머노트 저장 안하고 페이지 나갔을 경우 이미지 삭제
	@PostMapping(value="deleteSummernoteImageFile.ai", produces="application/json")
	@ResponseBody
	public void deleteSummernoteImageFile(@RequestParam("imgs") String imgs, HttpSession session) {
		
		String fileRoot = session.getServletContext().getRealPath("/resources/uploadFiles/");
		
		String[] fileNames = imgs.split("/");
		
		//특정 이름을 가진 파일이 경로에 있을때 파일 삭제
		for(String fileName : fileNames) {
			File file = new File(fileRoot + "/" + fileName);
			
			if(file.exists()) {
				file.delete();
			}
		}
		
	}
	
	// 입양후기 게시글 저장
	@PostMapping("reviewInsert.ar")
	public String insertReview(AdoptReview review, MultipartFile reviewThumb, HttpSession session) {
		
		//후기 게시글 번호 미리 추출
		int reviewNo = adoptService.selectReviewNo();
		
		//후기 게시글 대입
		review.setReviewNo(reviewNo);
		
		int reviewResult = adoptService.insertReview(review);
		
		if(reviewResult > 0) {
			
			AdoptAttachment att = new AdoptAttachment();
			
			String thumb_changeName = saveFile(reviewThumb, session);
			
			att.setReviewNo(reviewNo);
			att.setOriginName(reviewThumb.getOriginalFilename());
			att.setChangeName(thumb_changeName);
			att.setFilePath(session.getServletContext().getRealPath("/resources/uploadFiles/"));
			
			int attResult = adoptService.insertAttachment(att);
			
			if(attResult > 0) {
				session.setAttribute("msg", "정상적으로 게시글이 작성 되었습니다.");
			}else {
				session.setAttribute("msg", "문제가 발생하여 게시글 작성이 실패했습니다.");
			}
			
		}
		
		return "redirect:/adopt/reviewList.ar";
	}
	
	
	//------------------------------ 여기까지 입양 후기 ------------------------------
	
	//	------------------------------ 입양 후기 공통 메소드 ------------------------------
	
	// 파일 업로드 관련
	public String saveFile(MultipartFile multipartFile, HttpSession session) {
      
		String originalFileName = multipartFile.getOriginalFilename(); // 오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 파일 확장자

		String savedFileName = UUID.randomUUID() + extension;
		
		String fileRoot = session.getServletContext().getRealPath("/resources/uploadFiles/");
		try {
			//7.경로와 수정 파일명을 합쳐서 파일 업로드 처리하기
			multipartFile.transferTo(new File(fileRoot+savedFileName));
			
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return savedFileName;
	}

}
