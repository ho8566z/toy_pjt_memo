package com.office.toypjt.memo;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("*.memo")
public class MemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MemoController() {
        super();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuffer stringBuffer = request.getRequestURL();
		System.out.println("stringBuffer: " + stringBuffer.toString());
		
		String requestURI = request.getRequestURI();
		System.out.println("requestURI: " + requestURI);
		
		String contextPath = request.getContextPath();
		System.out.println("contextpath: " + contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command: " + command);
		
		String nextPage = null;
		
		
		switch (command) {
		case:
			
			break;

		default:
			break;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
