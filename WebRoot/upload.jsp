<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.jspsmart.upload.Files"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.jspsmart.upload.File"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	SmartUpload su = new SmartUpload();

	su.initialize(pageContext);
	su.upload();

	Files files = su.getFiles();
	for (int i = 0; i < files.getCount(); i++) {
		File file = su.getFiles().getFile(i);
		String fileName = file.getFileName();
		System.out.print(fileName);
		fileName = fileName.substring(fileName.lastIndexOf("."),
				fileName.length());
		fileName = System.currentTimeMillis() + fileName;
		String path = pageContext.getServletContext().getRealPath("");

		path = path.substring(0, path.lastIndexOf("\\"));
		path = path + "/Demo/upload/";//上传文件路径  
		java.io.File file1 = new java.io.File(path);//在服务器上新建目录  
		if (!file1.isDirectory()) {
			file1.mkdirs();
		}
		path = path + fileName;//文件的完整路径  
		file.saveAs(path);
	}
	/* 	File file = su.getFiles().getFile(0);
	 String aa=URLEncoder.encode(file.getFileName(), "gbk");

	
	 String fileName = file.getFileName();
	 System.out.print(fileName);
	 fileName = fileName.substring(fileName.lastIndexOf("."),
	 fileName.length());
	 fileName = System.currentTimeMillis() + fileName;
	 String path = pageContext.getServletContext().getRealPath("");

	 path = path.substring(0, path.lastIndexOf("\\"));
	 path = path + "/Demo/upload/";//上传文件路径  
	 java.io.File file1 = new java.io.File(path);//在服务器上新建目录  
	 if (!file1.isDirectory()) {
	 file1.mkdirs();
	 }
	 path = path + fileName;//文件的完整路径  
	 file.saveAs(path); */
%>
