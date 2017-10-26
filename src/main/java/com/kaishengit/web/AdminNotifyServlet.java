package com.kaishengit.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Notify;
import com.kaishengit.entity.ResultJson;
import com.kaishengit.service.AdminService;
import com.kaishengit.util.Page;

@WebServlet("/admin/notify")
public class AdminNotifyServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("p");
		
		Page<Notify> page = AdminService.findNotifyPage(p);
	
		req.setAttribute("page", page);
		forward("admin/notify", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Notify> notifyList = AdminService.findUnreadNotify();
		ResultJson json = new ResultJson(notifyList.size());
		sendJson(json, resp);
	}
}
