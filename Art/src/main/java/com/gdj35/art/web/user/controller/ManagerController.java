package com.gdj35.art.web.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gdj35.art.common.bean.PagingBean;
import com.gdj35.art.common.service.IPagingService;
import com.gdj35.art.web.user.service.IManagerService;

import com.fasterxml.jackson.databind.ObjectMapper;


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
	
	@RequestMapping(value="/editManager")
	public ModelAndView editManager(ModelAndView mav) {
		
		mav.setViewName("HD/editManager");
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
		System.out.println(params.get("page"));
		
		
		 if(params.get("page") != null) { 
			 page= Integer.parseInt(params.get("page"));
		  }
		 
		System.out.println(page);
		//Total count를 가져온다 T
		int cnt = iManagerService.getTCnt(params);
		System.out.println("===> cnt " + cnt);
		
		PagingBean pb = iPagingService.getPagingBean(page, cnt, 13, 10);
		
		System.out.println(pb.getEndPcount());
		
		if(pb.getEndPcount() ==page) {
			page = page -1;
		}
		
		int endP = pb.getEndPcount();
		
		params.put("endCnt", Integer.toString(pb.getEndCount()));
		
		System.out.println(params);
		
		//Main list를 가져온다. 그래서 M으로 지정
		List<HashMap<String,String>> list = 
					iManagerService.getMList(params);
		
		
		System.out.println(list);
		System.out.println(page);
		mav.addObject("page", page);
		mav.addObject("endP", endP);
		mav.addObject("list", list);
		mav.addObject("now", "member");
		mav.setViewName("HD/user_board");
		return mav;
	}
	
	@RequestMapping(value="/gong_board")
	public ModelAndView gong_board(ModelAndView mav) {
		
		mav.addObject("now", "gong");
		mav.setViewName("HD/gong_board");
		return mav;
	}
	
	@RequestMapping(value="/tag_board")
	public ModelAndView tag_board(ModelAndView mav) {
		
		mav.addObject("now", "tag");
		mav.setViewName("HD/tag_board");
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/gallaryManage")
	public ModelAndView gallaryManage(ModelAndView mav) {
		
		mav.setViewName("h/gallaryManage");
		return mav;
	}
	
	
	@RequestMapping(value="/entire",
			method=RequestMethod.POST,
			produces="text/json;charset=UTF-8")
	@ResponseBody
	public String entire(
			@RequestParam HashMap<String, String> params) throws Throwable{
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		//목록취득
		List<HashMap<String, String>> list = iManagerService.getPostList(params);
		
		modelMap.put("list", list);
		return mapper.writeValueAsString(modelMap);
	}
	
	
	
	
	@RequestMapping(value="/reportManage")
	public ModelAndView reportManage(ModelAndView mav) {
		
		mav.setViewName("h/reportManage");
		return mav;
	}
	
}
