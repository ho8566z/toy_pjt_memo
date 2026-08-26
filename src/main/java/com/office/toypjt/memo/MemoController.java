package com.office.toypjt.memo;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("*.memo")
public class MemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private String CLASS_NAME = "[MemoController]";
   
    public MemoController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		case MemoConfig.MEMO_LIST_FORM:
			System.out.println(CLASS_NAME.concat(MemoConfig.MEMO_LIST_FORM));
			nextPage = generateViewName("/memo_list_form");
			
			break;

		case MemoConfig.MEMO_WRITE_FORM:
			
			
			break;
			
		case MemoConfig.MEMO_WRITE_CONFIRM:
			
			break;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
		
		private String generateViewName(String viewName) {
			
			return MemoConfig.DEFAULT_VIEW_PATH
					.concat(viewName)
					.concat(MemoConfig.DEFAULT_VIEW_SUFFIX);
		
	}

}
