package com.kaishengit.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

@WebServlet("/file/upload")
@MultipartConfig
public class FileUploadServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part part = req.getPart("file");
		
		String content = part.getHeader("Content-Disposition");
		String fileName = content.substring(content.lastIndexOf("=")+2, content.length()-1);
		
		String newName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
		
		File file = new File("d:/upload", newName);
		
		InputStream input = part.getInputStream();
		OutputStream output = new FileOutputStream(file);
		
		IOUtils.copy(input, output);
		
		output.flush();
		output.close();
		input.close();
		
		Map<String, Object> map = new HashMap<>();
		map.put("success", true);
		map.put("file_path", "http://127.0.0.1/file/download?fileName=" + newName);
		
		sendJson(map, resp);
		
	}
	
}
