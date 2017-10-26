package com.kaishengit.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.kaishengit.dao.AdminDao;
import com.kaishengit.dao.NotifyDao;
import com.kaishengit.entity.Admin;
import com.kaishengit.entity.Notify;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.Page;

public class AdminService {

	public static Admin login(String userName, String password) throws ServiceException {
		Admin admin = AdminDao.findAdminByNane(userName);
		String encodePassword = DigestUtils.md5Hex(Config.get("admin.password.salt") + password);

		if (admin != null && admin.getPassword().equals(encodePassword)) {
			return admin;
		} else {
			throw new ServiceException("用户名或者密码错误");
		}
	}

	public static Page<Notify> findNotifyPage(String p) {
		p = StringUtils.isNumeric(p) ? p : "1";
		int notifyCount = NotifyDao.count();
		
		Page<Notify> page = new Page<>(Integer.parseInt(p), notifyCount);
		
		List<Notify> notifyList = NotifyDao.findByPage(page.getStartNo(), page.getPageSize());
		page.setItems(notifyList);
		
		return page;
	}

	public static List<Notify> findUnreadNotify() {
		return NotifyDao.findUnread();
	}

	public static void readNotifyByIds(String ids) {
		String[] idArray = ids.split(",");
		for(String id : idArray) {
			Notify notify = NotifyDao.findById(id);
			notify.setState(Notify.READ_STATE);
			notify.setReadTime(new Timestamp(System.currentTimeMillis()));
			NotifyDao.update(notify);
		}
	}

	public static void readNotifyById(String id) {
		if(StringUtils.isNumeric(id)) {
			Notify notify = NotifyDao.findById(id);
			notify.setState(Notify.READ_STATE);
			notify.setReadTime(new Timestamp(System.currentTimeMillis()));
			NotifyDao.update(notify);
		} else {
			throw new ServiceException("参数异常");
		}
		
	}

}
