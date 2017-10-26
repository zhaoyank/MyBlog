package com.kaishengit.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Admin;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.AdminService;

@WebServlet("/login")
public class LoginServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("admin".equals(cookie.getName())) {
					req.setAttribute("username", cookie.getValue());
				}
			}
		}

		String callback = req.getParameter("callback");
		req.setAttribute("callback", callback);
		forward("admin/login", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userName = req.getParameter("username");
		String password = req.getParameter("password");

		String remember = req.getParameter("remember");

		Map<String, Object> map = new HashMap<>();

		try {
			Admin admin = AdminService.login(userName, password);

			HttpSession session = req.getSession();
			session.setAttribute("curr_admin", admin);

			map.put("state", "success");
			map.put("admin", admin);

			if (StringUtils.isNotEmpty(remember)) {
				Cookie cookie = new Cookie("admin", userName);

//				cookie.setDomain("localhost");
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 7);
				cookie.setHttpOnly(true);

				resp.addCookie(cookie);
			} else {
				Cookie[] cookies = req.getCookies();
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if ("admin".equals(cookie.getName())) {
							cookie.setDomain("127.0.0.1");
							cookie.setPath("/");
							cookie.setMaxAge(0);
							cookie.setHttpOnly(true);

							resp.addCookie(cookie);
						}
					}
				}
			}
			
		} catch (ServiceException e) {
			map.put("state", "error");
			map.put("message", e.getMessage());
		}

		sendJson(map, resp);
	}
}
