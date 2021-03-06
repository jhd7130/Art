package com.gdj35.art.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

@Aspect
public class CommonAOP {
	//Pointcut -> 적용범위
	//@Pointcut(범위설정)
	/*
	 * 범위
	 * execution -> include필터
	 * !execution -> exclude필터
	 * * -> 모든것
	 * *(..) -> 모든 메소드
	 * .. -> 모든 경로
	 * && -> 필터 추가
	 */
	@Pointcut("execution(* com.gdj35.art..*Controller.*(..))"
			+ "&&!execution(* com.gdj35.art..*Controller.*agree(..))"
			+ "&&!execution(* com.gdj35.art..*Controller.*signUp(..))"
			+ "&&!execution(* com.gdj35.art..*Controller.*idfind(..))"
			+ "&&!execution(* com.gdj35.art..*Controller.*findId(..))"
			+ "&&!execution(* com.gdj35.art..*Controller.*passwordfind(..))"
			+ "&&!execution(* com.gdj35.art..*Controller.*findPw(..))"
			+ "&&!execution(* com.gdj35.art..*Controller.*detail(..))"
			+ "&&!execution(* com.gdj35.art..*Controller.*searchGallaryPage(..))"
			+ "&&!execution(* com.gdj35.art..*Controller.*gallary(..))"
			+ "&&!execution(* com.gdj35.art..*Controller.*othergallary(..))"
			+ "&&!execution(* com.gdj35.art..*Controller.*main(..))"
			+ "&&!execution(* com.gdj35.art..*Controller.*gongji(..))"
			+ "&&!execution(* com.gdj35.art..*Controller.*login(..))"
			+ "&&!execution(* com.gdj35.art..*Controller.*Ajax(..))")
	public void artAOP() {}
	
	//ProceedingJoinPoint -> 대상 적용 이벤트 필터
	/*
	 * @Before -> 메소드 실행 전
	 * @After -> 메소드 실행 후
	 * @After-returning -> 메소드 정상실행 후
	 * @After-throwing -> 메소드 예외 발생 후
	 * @Around -> 모든 동작시점
	 */
	@Around("artAOP()")
	public ModelAndView artAOP(ProceedingJoinPoint joinPoint)
														throws Throwable {
		ModelAndView mav = new ModelAndView();
		
		//Request 객체 취득
		HttpServletRequest request
		= ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("sUserNo") != null || session.getAttribute("sAdminNo") != null) { // 로그인 상태
			
			mav = (ModelAndView) joinPoint.proceed(); // 기존 이벤트 처리 행위를 이어서 진행
			
		} else { // 비로그인 상태
			mav.setViewName("redirect:login");
		}
		
				
		return mav;
	}
}













