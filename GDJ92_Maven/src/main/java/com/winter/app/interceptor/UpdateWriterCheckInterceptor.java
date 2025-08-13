package com.winter.app.interceptor;

import java.util.Objects;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.winter.app.board.BoardVO;
import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateWriterCheckInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
		if (request.getMethod().toUpperCase().equals("POST")) {
			return;
		}
		
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("member");
		
		BoardVO boardVO = (BoardVO) modelAndView.getModel().get("board");
		
		if (!Objects.equals(memberVO.getUsername(), boardVO.getBoardWriter())) {
			modelAndView.addObject("msg", "다른 사용자입니다.");
			modelAndView.addObject("url", "./list");
			modelAndView.setViewName("/common/result");
		}
	}
}
