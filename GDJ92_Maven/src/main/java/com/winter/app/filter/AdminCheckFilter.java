package com.winter.app.filter;

import java.io.IOException;
import java.util.Objects;

import com.winter.app.member.MemberVO;
import com.winter.app.member.RoleVO;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		
		boolean flag = false;
		if (memberVO != null) {
			for (RoleVO r : memberVO.getRoleVOs()) {
				if (Objects.equals(r.getRoleName(), "ROLE_ADMIN")) {
					flag = true;
				}
			}
		}
		
		if (!flag) {
			((HttpServletResponse) response).sendRedirect("./list");
		}
		
		chain.doFilter(request, response);
	}
}
