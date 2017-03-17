package com.demo.servlet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.orbutil.fsm.Input;

@SuppressWarnings("serial")
public class IOTestServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Protocol: " + request.getProtocol());
		System.out.println("Scheme: " + request.getScheme());
		System.out.println("Server Name: " + request.getServerName());
		System.out.println("Server Port: " + request.getServerPort());
		System.out.println("Protocol: " + request.getProtocol());
		System.out.println("Server Info: "
				+ getServletConfig().getServletContext().getServerInfo());
		System.out.println("Remote Addr: " + request.getRemoteAddr());
		System.out.println("Remote Host: " + request.getRemoteHost());
		System.out.println("Character Encoding: "
				+ request.getCharacterEncoding());
		System.out.println("Content Length: " + request.getContentLength());
		System.out.println("Content Type: " + request.getContentType());
		System.out.println("Auth Type: " + request.getAuthType());
		System.out.println("HTTP Method: " + request.getMethod());
		System.out.println("Path Info: " + request.getPathInfo());
		System.out.println("Path Trans: " + request.getPathTranslated());
		System.out.println("Query String: " + request.getQueryString());
		System.out.println("Remote User: " + request.getRemoteUser());
		System.out.println("Session Id: " + request.getRequestedSessionId());
		System.out.println("Request URI: " + request.getRequestURI());
		System.out.println("Servlet Path: " + request.getServletPath());
		System.out.println("Accept: " + request.getHeader("Accept"));
		System.out.println("Host: " + request.getHeader("Host"));
		System.out.println("Referer : " + request.getHeader("Referer"));
		System.out.println("Accept-Language : "
				+ request.getHeader("Accept-Language"));
		System.out.println("Accept-Encoding : "
				+ request.getHeader("Accept-Encoding"));
		System.out.println("User-Agent : " + request.getHeader("User-Agent"));
		System.out.println("Connection : " + request.getHeader("Connection"));
		System.out.println("Cookie : " + request.getHeader("Cookie"));
		System.out.println("Created : "
				+ request.getSession().getCreationTime());
		System.out.println("LastAccessed : "
				+ request.getSession().getLastAccessedTime());
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String url = request.getParameter("file");
		System.out.println(url);

		String filename = "a.html";
		System.out.println(this.getServletContext().getMimeType(filename));
		// 客户端导出 设置响应头类型
		response.setContentType(this.getServletContext().getMimeType(filename));
		// 判断浏览器IE,火狐
		String ages = request.getHeader("user-agent");
		if (ages.contains("MSIE")) {
			filename = URLEncoder.encode(filename, "utf-8");
		} else {
			filename = URLEncoder.encode(filename, "utf-8");
		}

		// 设置响应头下载附件格式
		response.setHeader("Content-Disposition", "attachment;filename="
				+ filename);

		InputStream inputStream = new FileInputStream(url);
		// OutputStream outputStream=new FileOutputStream("D:\\"+filename);
		// InputStream inputStream=request.getInputStream();
		OutputStream outputStream = response.getOutputStream();
		byte b[] = new byte[1024 * 5];
		while ((inputStream.read(b)) != -1) {
			outputStream.write(b);
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();

	}

}
