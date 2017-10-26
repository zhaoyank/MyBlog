package com.kaishengit.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Admin;

@WebFilter("/admin/*")
public class LoginFilter extends AbstractFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		
		HttpSession session = req.getSession();
		Admin admin = (Admin)session.getAttribute("curr_admin");
		if(uri.equals("/admin/logout") || admin != null) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect("/login?callback=" + uri);
		}
	}

}
