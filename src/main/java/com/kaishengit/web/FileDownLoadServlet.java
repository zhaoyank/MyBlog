package com.kaishengit.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

@WebServlet("/file/download")
public class FileDownLoadServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getParameter("fileName");
		File file = new File("d:/upload", fileName);
		
		InputStream input = new FileInputStream(file);
		OutputStream output = resp.getOutputStream();
		
		IOUtils.copy(input, output);
		
		output.flush();
		output.close();
		input.close();
		
	}
	
}
