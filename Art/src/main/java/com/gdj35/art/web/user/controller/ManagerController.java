package com.gdj35.art.web.user.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdj35.art.common.bean.PagingBean;
import com.gdj35.art.common.service.IPagingService;
import com.gdj35.art.web.user.service.IManagerService;


@Controller
public class ManagerController {
	
	@Autowired
	public IPagingService iPagingService;
	
	@Autowired
	public IManagerService iManagerService;
	
	@RequestMapping(value="/writingManager")
	public ModelAndView writinerManager(ModelAndView mav) {
		
		mav.setViewName("HD/writingManager");
		return mav;
	}
	
	@RequestMapping(value="/editManagerUpdate")
	public ModelAndView editManagerUpdate(ModelAndView mav,@RequestParam HashMap<String,String> params) throws Throwable {
		
		System.out.println(params);
		
		HashMap<String,String> row = iManagerService.getNotice(params);
		
		System.out.println(row);
		
		mav.addObject("oneRow", row);
		
		mav.setViewName("HD/editManager");
		return mav;
	}
	
	@RequestMapping(value="/editManager")
	public ModelAndView editManager(ModelAndView mav,@RequestParam HashMap<String,String> params) throws Throwable {
		
		System.out.println(params);
		
		HashMap<String,String> row = iManagerService.getNotice(params);
		
		System.out.println(row);
		
		mav.addObject("oneRow", row);
		
		mav.setViewName("HD/editManager");
		return mav;
	}
	
	@RequestMapping(value="/addGong")
	public ModelAndView addGong(ModelAndView mav,@RequestParam HashMap<String,String> params) throws Throwable {
		
		mav.setViewName("HD/addGong");
		return mav;
	}

	@RequestMapping(value="/user_detail(memo)")
	public ModelAndView user_detailM(ModelAndView mav) {
		
		mav.setViewName("HD/user_detail(memo)");
		return mav;
	}
	
	@RequestMapping(value="/user_detail(post)")
	public ModelAndView user_detailP(ModelAndView mav) {
		
		mav.setViewName("HD/user_detail(post)");
		return mav;
	}
	
	
	
	@RequestMapping(value="/user_board")
	public ModelAndView user_board(ModelAndView mav,
									@RequestParam HashMap<String,String> params) throws Throwable {
		 
		System.out.println(params);
		
		
		int page=1;
		
		
		 if(params.get("page") != null) { 
			 page= Integer.parseInt(params.get("page"));
		  }
		 
		//Total count를 가져온다 T
		int cnt = iManagerService.getTCnt(params);
		
		PagingBean pb = iPagingService.getPagingBean(page, cnt, 12, 10);
		
		params.put("endCnt", Integer.toString(pb.getEndCount()));
		params.put("startCnt", Integer.toString(pb.getStartCount()));
		
		
		//Main list를 가져온다. 그래서 M으로 지정
		List<HashMap<String,String>> list = 
					iManagerService.getMList(params);
		
		/*
		 * HashMap<String,String> user =iManagerService.getUser(params);
		 */
		
		System.out.println(list);
		System.out.println(params);
		/* mav.addObject("user", user); */
		mav.addObject("cnt", cnt);
		mav.addObject("pb", pb);
		mav.addObject("page", page);
		mav.addObject("list", list);
		mav.addObject("now", "member");
		mav.setViewName("HD/user_board");
		return mav;
	}
	
	
	
	
	@RequestMapping(value = "/user_datailP",
					method = RequestMethod.POST,
					produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String user_datailP(@RequestParam HashMap<String, String> params) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> modelMap = new HashMap<String,Object>();
		
		System.out.println(params);
		
		// 목록취득
		HashMap<String,String> user=iManagerService.getUser(params);
		//detail post의 작품 리스트를 가져오는 메서드
		List<HashMap<String,String>> list = iManagerService.getDPList(params);
		//메모 목록 취득
		List<HashMap<String,String>> listM = iManagerService.getDMList(params); 

		System.out.println("this is parmas >>>>"+params);
		
		/*
		 * PagingBean pb = iPagingService.getPagingBean(page, maxCount, viewCnt,
		 * pageCnt)
		 */
		 
		System.out.println("USER >>>"+user);
		System.out.println("LIST >>>"+list);
		System.out.println("LISTM >>>"+listM);
		/* modelMap.put("pb",pb); */
		
		modelMap.put("listM", listM);
		modelMap.put("list", list);
		modelMap.put("user", user);
		
		return mapper.writeValueAsString(modelMap);
	}
	
	@RequestMapping(value = "/delOneRow",
			method = RequestMethod.POST,
			produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String delOneRow(@RequestParam HashMap<String, String> params) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> modelMap = new HashMap<String,Object>();
		
		String arr = params.get("userNo");
		String [] usersNo = arr.split(",");
		int cnt = 0;
		System.out.println(Arrays.toString(usersNo));
		
		for(int i =0 ; i <usersNo.length; i++) {
			params.put("userNo",usersNo[i]);
			System.out.println(">>>>>>이거는 포문 안에 파람 값" + params);
			cnt += iManagerService.deleteOneRow(params);
			params.remove("userNo");
		}
		
		System.out.println(params);
		System.out.println(cnt);
		
		
		return mapper.writeValueAsString(modelMap);
	}
	
	@RequestMapping(value = "/out_user_list",
			method = RequestMethod.POST,
			produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String out_user_list(@RequestParam HashMap<String, String> params) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> modelMap = new HashMap<String,Object>();
		
		System.out.println(params);
		
		int page=1;
		
		 if(params.get("page") != null) { 
			 page= Integer.parseInt(params.get("page"));
		  }
		 
		//Total count를 가져온다 T
		int cnt = iManagerService.getOutCnt(params);
		
		PagingBean pb = iPagingService.getPagingBean(page, cnt, 12, 10);
		
		params.put("endCnt", Integer.toString(pb.getEndCount()));
		params.put("startCnt", Integer.toString(pb.getStartCount()));
		
		List<HashMap<String,String>> list = iManagerService.outUserList(params);
		
		System.out.println(list);
		System.out.println(pb);
		
		modelMap.put("pb", pb);
		modelMap.put("list", list);
		
		return mapper.writeValueAsString(modelMap);
	}
	
	@RequestMapping(value = "/user_update",
			method = RequestMethod.POST,
			produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String user_update(@RequestParam HashMap<String, String> params) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> modelMap = new HashMap<String,Object>();
		
		System.out.println("고객 업데이트를 위한 파람 값 >>> "+params);
		
		try {
			 int cnt = iManagerService.updateUser(params); 
			
			 System.out.println("고객을 업데이트했는지 안했는지 >> " + cnt); 
			
			 if(cnt >0 ) { 
				 modelMap.put("msg","success"); 
			 }	 
		}catch(Throwable e){
			e.printStackTrace();
			modelMap.put("msg","error");
		}
		

//		modelMap.put("list", list);
		
		return mapper.writeValueAsString(modelMap);
	}
	
	
	@RequestMapping(value="/gong_board")
	public ModelAndView gong_board(ModelAndView mav,
					@RequestParam HashMap<String,String> params) throws Throwable {
		
		
		mav.addObject("now", "gong");
		mav.setViewName("HD/gong_board");
		return mav;
	}
	
	@RequestMapping(value = "/gong_boardA",
					method = RequestMethod.POST,
					produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String gong_board(@RequestParam HashMap<String, String> params) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> modelMap = new HashMap<String,Object>();
		
		System.out.println(params);
		
		int page =1;
		
		
		try {
			if(params.get("page") != null && params.get("page") != "") { page = Integer.parseInt(params.get("page"));
			}
		}catch(Throwable e){
			e.printStackTrace();
		}
		 
		//총 몇개의 공지사항이 있는지 표시해주기위해 개수를 카운트해서 가져온다.
		int cnt = iManagerService.getGongCnt(params);
		
		PagingBean pb = iPagingService.getPagingBean(page, cnt, 12, 5);
		
		params.put("endCnt", Integer.toString(pb.getEndCount()));
		params.put("startCnt", Integer.toString(pb.getStartCount()));
		
		
		//공지사항 게시판을 만들기 위해 값을 가져온다.
		 List<HashMap<String,String>> list = iManagerService.getGList(params);
		 System.out.println("list를 찍어보자 "+list);
		 
		 System.out.println(list);
		
		modelMap.put("page", page);
		modelMap.put("cnt", cnt);
		modelMap.put("pb", pb);
		modelMap.put("list", list);
		
		return mapper.writeValueAsString(modelMap);
	}
	
	
	@RequestMapping(value = "/gongRowsDel",
			method = RequestMethod.POST,
			produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String rowsDel(@RequestParam HashMap<String, String> params) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> modelMap = new HashMap<String,Object>();
		
		System.out.println(params);
		
		
		int cnt = iManagerService.gongRowsDel(params);
		
		System.out.println(cnt);
		
		return mapper.writeValueAsString(modelMap);
	}
	
	
	
	@RequestMapping(value = "/addGongs",
			method = RequestMethod.POST,
			produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String addGong(HttpSession session,@RequestParam HashMap<String, String> params) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		// httpSession 값 받아오기 그래야 관리자 번호 지정가능
		// 로그인 구현하기
		Map<String, Object> modelMap = new HashMap<String,Object>();
		System.out.println(session.getAttribute("sUserNo"));
		try {
			params.put("adminNo",String.valueOf(session.getAttribute("sUserNo")));
			
			 System.out.println("this is parmas from addGong" + params);
			
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		int cnt = iManagerService.addGong(params);
		
		if(cnt>0) {
			modelMap.put("msg", "success");
		}else {
			modelMap.put("msg", "failed");
		}
		
		return mapper.writeValueAsString(modelMap);
	}
	
	@RequestMapping(value = "/editManagerUpdate",
			method = RequestMethod.POST,
			produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String editManagerUpdate(HttpSession session,@RequestParam HashMap<String, String> params) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		// httpSession 값 받아오기 그래야 관리자 번호 지정가능
		// 로그인 구현하기
		Map<String, Object> modelMap = new HashMap<String,Object>();
		System.out.println(session.getAttribute("sUserNo"));
		try {
			params.put("adminNo",String.valueOf(session.getAttribute("sUserNo")));
			
			System.out.println("this is parmas from addGong" + params);
			
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		int cnt = iManagerService.addGong(params);
		
		if(cnt>0) {
			modelMap.put("msg", "success");
		}else {
			modelMap.put("msg", "failed");
		}
		
		return mapper.writeValueAsString(modelMap);
	}
	
	
	@RequestMapping(value="/tag_board")
	public ModelAndView tag_board(ModelAndView mav, @RequestParam HashMap<String, String> params) throws Throwable {
		
		
		List<HashMap<String, String>> tList = iManagerService.getTList(params);
		
		int cnt = iManagerService.getTagCnt(params);
		
		
		
		mav.addObject("cnt", cnt);
		mav.addObject("tList", tList);
		mav.addObject("now", "tag");
		mav.setViewName("HD/tag_board");
		
		return mav;
	}
	
	@RequestMapping(value="/addTag")
	public ModelAndView addTag(ModelAndView mav, @RequestParam HashMap<String, String> params) throws Throwable {
		

		int cnt = iManagerService.addTag(params);
		
		if(cnt>0) {
			mav.addObject("msg", "success");
		}else {
			mav.addObject("msg", "failed");
		}
		
		
		mav.addObject("now", "tag");
		
		mav.setViewName("redirect:/tag_board");
		
		
		return mav;
	}
	
	@RequestMapping(value="/delTag")
	public ModelAndView delTag(ModelAndView mav, @RequestParam HashMap<String, String> params) throws Throwable {
		
		
		/*
		 * String[] arr = {params.get("tagNo")};
		 * System.out.println(Arrays.toString(arr));
		 */
		
		int cnt = iManagerService.delTag(params); 
		
		if(cnt>0) {
			mav.addObject("msg", "success");
		}else {
			mav.addObject("msg", "failed");
		}
		
		
		mav.addObject("now", "tag");
		
		mav.setViewName("redirect:/tag_board");
		
		
		return mav;
	}
	
	
	
	


	
	
	
	
	
	
	//----------------------------------------------현
	//작품관리
	@RequestMapping(value="/gallaryManage")
	public ModelAndView gallaryManage(ModelAndView mav) throws Throwable {
		
		mav.setViewName("h/gallaryManage");
		return mav;
	}
	

	//리스트 불러오기
	@RequestMapping(value="/entireList",
			method=RequestMethod.POST,
			produces="text/json;charset=UTF-8")
	@ResponseBody
		public String entireList(
		@RequestParam HashMap<String, String> params,
			ModelAndView mav) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		//페이징처리
		int page = 1;
		
		if(params.get("page") != null) {
			page = Integer.parseInt(params.get("page"));
		}
							
		try {
			int cnt = iManagerService.getGallaryMCnt(params);
			PagingBean pb = iPagingService.getPagingBean(page, cnt, 12, 10);
			
			params.put("endCnt", Integer.toString(pb.getEndCount()));
			params.put("startCnt", Integer.toString(pb.getStartCount()));
				
			
			//목록취득
			List<HashMap<String, String>> list = iManagerService.getPostList(params);
			
			System.out.println(params);
			System.out.println(list);
			
			modelMap.put("list", list);
			modelMap.put("pb", pb);
			modelMap.put("cnt", cnt);
			
		} catch (Throwable e) {
			e.printStackTrace();
			modelMap.put("msg", "error");
		}
		
		return mapper.writeValueAsString(modelMap);
	}
	
	
	
	
	//작품관리 상세페이지 보기
	@RequestMapping(value="/drawUserPopup",
			method=RequestMethod.POST,
			produces="text/json;charset=UTF-8")
	@ResponseBody
	public String drawUserPopup(
			@RequestParam HashMap<String, String> params,
			ModelAndView mav) throws Throwable{
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		//페이징처리
		int page = Integer.parseInt(params.get("page"));
		
		try {
			int cnt = iManagerService.getGallaryMCnt(params);
			PagingBean pb = iPagingService.getPagingBean(page, cnt, 12, 10);
			
			params.put("endCnt", Integer.toString(pb.getEndCount()));
			params.put("startCnt", Integer.toString(pb.getStartCount()));
					
			
			//데이터취득
			HashMap<String, String> data = iManagerService.getUserDetail(params);
		
			modelMap.put("data", data);
			modelMap.put("pb", pb);
			
		} catch (Throwable e) {
			e.printStackTrace();
			modelMap.put("msg", "error");
		}		
		
		return mapper.writeValueAsString(modelMap);
	
	}
	
	//업데이트하고 새로고침
	@RequestMapping(value="/drawEdits",
			method=RequestMethod.POST,
			produces = "text/json;charset=UTF-8")

	@ResponseBody
	public String drawEdits(
			@RequestParam HashMap<String, String> params) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
	
	try {
		int cnt = iManagerService.updatePostDetail(params);
		
		if(cnt > 0) {
			modelMap.put("msg", "success");
		} else {
			modelMap.put("msg", "failed");
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
			modelMap.put("msg", "error");
		}
	
		return mapper.writeValueAsString(modelMap);
	}
	
	//삭제하기
	@RequestMapping(value = "/deleteGallary",
			method = RequestMethod.POST,
			produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String deleteGallary(@RequestParam HashMap<String, String> params) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> modelMap = new HashMap<String,Object>();
		
		String checkArr = params.get("checkedArr");
		String [] postArry = checkArr.split(",");
		
		int cnt = 0;
		
		try {
		
			for(int i =0 ; i <postArry.length; i++) {
			params.put("postNo", postArry[i]);
			cnt += iManagerService.deleteG(params);
			params.remove("postNo");
		}
		
			if(cnt > 0) {
				modelMap.put("msg", "success");
			} else {
				modelMap.put("msg", "failed");
				}
			
		} catch (Throwable e) {
			e.printStackTrace();
			modelMap.put("msg", "error");
		}
		
		return mapper.writeValueAsString(modelMap);
	}
	
	//복원하기
	@RequestMapping(value = "/returnDel",
			method = RequestMethod.POST,
			produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String returnDel(@RequestParam HashMap<String, String> params) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> modelMap = new HashMap<String,Object>();
		
		String checkArr = params.get("checkedArr");
		String [] postArry = checkArr.split(",");
		
		int cnt = 0;
		
		try {
		
			for(int i =0 ; i <postArry.length; i++) {
			params.put("postNo", postArry[i]);
			cnt += iManagerService.returnG(params);
			params.remove("postNo");
		}
		
			if(cnt > 0) {
				modelMap.put("msg", "success");
			} else {
				modelMap.put("msg", "failed");
				}
			
		} catch (Throwable e) {
			e.printStackTrace();
			modelMap.put("msg", "error");
		}
		
		return mapper.writeValueAsString(modelMap);
	}
	
	
	
	
	
	//신고관리 페이지
	@RequestMapping(value="/reportManage")
	public ModelAndView reportManage(ModelAndView mav) {
		
		mav.setViewName("h/reportManage");
		return mav;
	}
	
	//신고 리스트 보기
	@RequestMapping(value="/reportList",
			method=RequestMethod.POST,
			produces="text/json;charset=UTF-8")
	@ResponseBody
		public String reportList(
		@RequestParam HashMap<String, String> params,
			ModelAndView mav) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		//페이징처리
		int page = 1;
				
		if(params.get("page") != null) {
			page = Integer.parseInt(params.get("page"));
		}
		
		try {
							
			int cnt = iManagerService.getReportMCnt(params);
			PagingBean pb = iPagingService.getPagingBean(page, cnt, 12, 10);
			
			params.put("endCnt", Integer.toString(pb.getEndCount()));
			params.put("startCnt", Integer.toString(pb.getStartCount()));
					
			
			//목록취득
			List<HashMap<String, String>> list = iManagerService.getReportList(params);
					
			System.out.println(params);
			System.out.println(list);
			
			modelMap.put("list", list);
			modelMap.put("pb", pb);
			modelMap.put("cnt", cnt);			
				
				if (cnt > 0) {
					modelMap.put("msg", "success");
				} else {
					modelMap.put("msg", "failed");
				}
		
		} catch (Throwable e) {
			e.printStackTrace();
			modelMap.put("msg", "error");
		}
		
		
				
		return mapper.writeValueAsString(modelMap);
	}
	
	//삭제하기
	@RequestMapping(value = "/deleteReport",
			method = RequestMethod.POST,
			produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String deleteReport(@RequestParam HashMap<String, String> params) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> modelMap = new HashMap<String,Object>();
		
		String checkArr = params.get("checkedArr");
		String [] postArry = checkArr.split(",");
		
		int cnt = 0;
		
		try {
		
			for(int i =0 ; i <postArry.length; i++) {
			params.put("rNo", postArry[i]);
			cnt += iManagerService.deleteR(params);
			params.remove("rNo");
		}
		
			if(cnt > 0) {
				modelMap.put("msg", "success");
			} else {
				modelMap.put("msg", "failed");
				}
			
		} catch (Throwable e) {
			e.printStackTrace();
			modelMap.put("msg", "error");
		}
		
		return mapper.writeValueAsString(modelMap);
	}
	
	//복원하기
	@RequestMapping(value = "/returnDelr",
			method = RequestMethod.POST,
			produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String returnDelr(@RequestParam HashMap<String, String> params) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> modelMap = new HashMap<String,Object>();
		
		String checkArr = params.get("checkedArr");
		String [] postArry = checkArr.split(",");
		
		int cnt = 0;
		
		try {
		
			for(int i =0 ; i <postArry.length; i++) {
			params.put("rNo", postArry[i]);
			cnt += iManagerService.returnR(params);
			params.remove("rNo");
		}
		
			if(cnt > 0) {
				modelMap.put("msg", "success");
			} else {
				modelMap.put("msg", "failed");
				}
			
		} catch (Throwable e) {
			e.printStackTrace();
			modelMap.put("msg", "error");
		}
		
		return mapper.writeValueAsString(modelMap);
	}
	
	
	//신고내용보기
	@RequestMapping(value="/drawReportPopup",
			method=RequestMethod.POST,
			produces="text/json;charset=UTF-8")
	@ResponseBody
	public String drawReportPopup(
			@RequestParam HashMap<String, String> params,
			ModelAndView mav) throws Throwable{
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		//페이징처리
		int page = Integer.parseInt(params.get("page"));
		
		try {
			int cnt = iManagerService.getReportMCnt(params);
			PagingBean pb = iPagingService.getPagingBean(page, cnt, 12, 10);
			
			params.put("endCnt", Integer.toString(pb.getEndCount()));
			params.put("startCnt", Integer.toString(pb.getStartCount()));
					
			
			//데이터취득
			HashMap<String, String> data = iManagerService.getReportDetail(params);
			
			//메모 목록 취득
			List<HashMap<String, String>> list = iManagerService.getReportMemo(params);
			
			modelMap.put("list", list);
			modelMap.put("data", data);
			modelMap.put("pb", pb);
			
		} catch (Throwable e) {
			e.printStackTrace();
			modelMap.put("msg", "error");
		}		
		
		return mapper.writeValueAsString(modelMap);
	
	}
	
	//신고메모...!
	@RequestMapping(value="/reportMemo",
			method=RequestMethod.POST,
			produces="text/json;charset=UTF-8")
	@ResponseBody
	public String reportMemo(
			@RequestParam HashMap<String, String> params,
			ModelAndView mav) throws Throwable{
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();

		
		try {
			//데이터취득
			HashMap<String, String> memo = iManagerService.getMemoDetail(params);
			
			modelMap.put("memo", memo);
			
		} catch (Throwable e) {
			e.printStackTrace();
			modelMap.put("msg", "error");
		}		
		
		return mapper.writeValueAsString(modelMap);
	
	}
	
	//메모 중요체크
	@RequestMapping(value = "/onStar",
					method = RequestMethod.POST,
					produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String onStar(
			@RequestParam HashMap<String, String> params) throws Throwable {

		ObjectMapper mapper = new ObjectMapper();		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
				
		try {
			int cnt = iManagerService.onStar(params);
			
			if (cnt > 0) {
				modelMap.put("msg", "success");
			} else {
				modelMap.put("msg", "failed");
			}

		} catch (Throwable e) {
			e.printStackTrace();
			modelMap.put("msg", "error");
		}

		return mapper.writeValueAsString(modelMap);
	}
	
	//메모 중요체크
	@RequestMapping(value = "/offStar",
					method = RequestMethod.POST,
					produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String offStar(
			@RequestParam HashMap<String, String> params) throws Throwable {

		ObjectMapper mapper = new ObjectMapper();		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
				
		try {
			int cnt = iManagerService.offStar(params);
			
			if (cnt > 0) {
				modelMap.put("msg", "success");
			} else {
				modelMap.put("msg", "failed");
			}

		} catch (Throwable e) {
			e.printStackTrace();
			modelMap.put("msg", "error");
		}

		return mapper.writeValueAsString(modelMap);
	}
	
	
	//메모 수정하고 나서~
	@RequestMapping(value="/saveReportMemo",
			method=RequestMethod.POST,
			produces="text/json;charset=UTF-8")
	@ResponseBody
	public String saveReportMemo(
			@RequestParam HashMap<String, String> params) throws Throwable{
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();

		
		try {
			int cnt = iManagerService.updateReportMemo(params);
			
			if(cnt > 0) {
				modelMap.put("msg", "success");
		} else {
			modelMap.put("msg", "failed");
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
			modelMap.put("msg", "error");
		}	
		
		return mapper.writeValueAsString(modelMap);
	
	}
	
	//메모 삭제
	@RequestMapping(value="/delReportMemo",
			method=RequestMethod.POST,
			produces="text/json;charset=UTF-8")
	@ResponseBody
	public String delReportMemo(
			@RequestParam HashMap<String, String> params) throws Throwable{
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();

		
		try {
			int cnt = iManagerService.deleteReportMemo(params);
			
			if(cnt > 0) {
				modelMap.put("msg", "success");
		} else {
			modelMap.put("msg", "failed");
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
			modelMap.put("msg", "error");
		}	
		
		return mapper.writeValueAsString(modelMap);
	
	}
	
	//메모 추가하기
	@RequestMapping(value="/addMemo",
				method=RequestMethod.POST,
				produces = "text/json;charset=UTF-8")
	
	@ResponseBody
	public String addMemo(
			@RequestParam HashMap<String, String> params) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int cnt = iManagerService.addMemo(params);
			
			if(cnt > 0) {
				modelMap.put("msg", "success");
			} else {
				modelMap.put("msg", "failed");
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
			modelMap.put("msg", "error");
		}

		return mapper.writeValueAsString(modelMap);
	}
	
	
	
}
