package com.kh.petShelter.adopt.contoller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.petShelter.adopt.model.vo.Adopt;
import com.kh.petShelter.adopt.model.vo.AdoptImg;

@Controller
@RequestMapping("/adopt")
public class AdoptController {

    public static final String SERVICEKEY = "7842697a62796b733634444d425966";

    @GetMapping("/list.al")
    public String adoptList(@RequestParam(value="currentPage", defaultValue="1") int currentPage, Model model) throws IOException {
        
    	int startIndexInfo = 0;
    	
    	// 페이지징바 계산 처리
    	if(currentPage == 1) {
    		startIndexInfo = currentPage;
    	}else {
    		startIndexInfo = ((currentPage - 1) * 12) + 1;
    	}
    	int endIndexInfo =  currentPage * 12;
    	
    	// 입양대기 동물 현황 (정보)
        String url_info = "http://openapi.seoul.go.kr:8088/" + SERVICEKEY;
        url_info += "/json/TbAdpWaitAnimalView/" + startIndexInfo + "/" + endIndexInfo + "/";
        
        // 입양대기 동물 현황 (정보) 요청 및 응답 처리
        String responseInfo = getResponse(url_info);
        JsonObject jobj_info = JsonParser.parseString(responseInfo.toString()).getAsJsonObject();
        JsonObject TbAdpWaitAnimalView = jobj_info.getAsJsonObject("TbAdpWaitAnimalView");
        JsonArray row_info = TbAdpWaitAnimalView.getAsJsonArray("row");
        
        // list_total_count를 가져와서 모델에 추가
        String list_total_count = TbAdpWaitAnimalView.get("list_total_count").getAsString();
        
        ArrayList<Adopt> infoList = new ArrayList<>();
        
        for(int i=0; i<row_info.size(); i++) {
        	
        	JsonObject row = row_info.get(i).getAsJsonObject();
        	
        	infoList.add(new Adopt(row.get("ANIMAL_NO").getAsString()
        					  ,row.get("NM").getAsString()
        					  ,row.get("ENTRNC_DATE").getAsString()
        					  ,row.get("SPCS").getAsString()
        					  ,row.get("BREEDS").getAsString()
        					  ,row.get("SEXDSTN").getAsString()
        					  ,row.get("AGE").getAsString()
        					  ,row.get("BDWGH").getAsString()
        					  ,row.get("ADP_STTUS").getAsString()
        					  ,row.get("TMPR_PRTC_STTUS").getAsString()
        					  ,row.get("INTRCN_MVP_URL").getAsString()
        					  ,row.get("INTRCN_CN").getAsString()
        					  ,row.get("TMPR_PRTC_CN").getAsString()));
        }
        
    	int endIndexImg = Integer.parseInt(list_total_count) * 9;
        
        // 입양대기 동물 현황 (사진)
        String url_img = "http://openapi.seoul.go.kr:8088/" + SERVICEKEY;
        url_img += "/json/TbAdpWaitAnimalPhotoView/1/" + endIndexImg + "/";
        
        //입양대기 동물 현황 (사진) 요청 및 응답 처리
        String responseImg = getResponse(url_img);
        JsonObject jobj_img = JsonParser.parseString(responseImg.toString()).getAsJsonObject();
        JsonObject TbAdpWaitAnimalPhotoView = jobj_img.getAsJsonObject("TbAdpWaitAnimalPhotoView");
        JsonArray row_img = TbAdpWaitAnimalPhotoView.getAsJsonArray("row");
        
        ArrayList<AdoptImg> imgList = new ArrayList<>();
        
        for(int i=0; i<row_img.size(); i++) {
        	
        	JsonObject row = row_img.get(i).getAsJsonObject();
        	
        	//사진 종류 THUMB인 것만 가져오기
        	if(row.get("PHOTO_KND").getAsString().equals("THUMB")) {
        		imgList.add(new AdoptImg(row.get("ANIMAL_NO").getAsString()
        								,row.get("PHOTO_KND").getAsString()
        								,row.get("PHOTO_NO").getAsString()
        								,row.get("PHOTO_URL").getAsString()));
        	}
        }
        
        model.addAttribute("total", list_total_count);
        model.addAttribute("adoptImg", imgList);
        model.addAttribute("adoptList", infoList);
        model.addAttribute("currentPage", currentPage);
        
        return "adopt/adoptList";
    }
    
    //공통된 작업 메서드로 정의하여 필요할 때 호출하기
    private String getResponse(String urlString) throws IOException{
    	
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
}
