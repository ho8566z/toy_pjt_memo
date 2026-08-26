package com.office.toypjt.memo;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("*.memo")
public class MemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String CLASS_NAME = "[MemoController] ";
	private final MemoService memoService = new MemoService();
       
    public MemoController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());

		if (!MemoConfig.MEMO_MODIFY_FORM.equals(command)) {
			redirectMemoList(request, response);
			return;
		}

		System.out.println(CLASS_NAME.concat(MemoConfig.MEMO_MODIFY_FORM));

		String memId = getSigninedMemId(request);
		if (memId == null) {
			redirectSignin(request, response);
			return;
		}

		MemoDto memoDto = memoService.getMemoForModify(request, memId);
		if (memoDto == null) {
			redirectMemoList(request, response);
			return;
		}

		request.setAttribute("memoDto", memoDto);
		request.getRequestDispatcher(generateViewName("/memo_modify_form"))
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());

		String memId = getSigninedMemId(request);
		if (memId == null) {
			redirectSignin(request, response);
			return;
		}

		int result = MemoService.INVALID_INPUT;

		switch (command) {
		case MemoConfig.MEMO_MODIFY_CONFIRM:
			System.out.println(CLASS_NAME.concat(MemoConfig.MEMO_MODIFY_CONFIRM));
			result = memoService.modifyMemo(request, memId);
			break;

		case MemoConfig.MEMO_DELETE:
			System.out.println(CLASS_NAME.concat(MemoConfig.MEMO_DELETE));
			result = memoService.deleteMemo(request, memId);
			break;

		default:
			redirectMemoList(request, response);
			return;
		}

		if (result > 0) {
			System.out.println(CLASS_NAME.concat("MEMO PROCESS SUCCESS!!"));
		} else {
			System.out.println(CLASS_NAME.concat("MEMO PROCESS FAIL!!"));
		}

		redirectMemoList(request, response);
	}

	private String getSigninedMemId(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) return null;

		Object signinedMemId = session.getAttribute(MemoConfig.SIGNINED_MEMBER_ID);
		return signinedMemId == null ? null : String.valueOf(signinedMemId);
	}

	private void redirectMemoList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + MemoConfig.MEMO_LIST_FORM);
	}

	private void redirectSignin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/member_signin_form.mem");
	}

	private String generateViewName(String viewName) {
		return MemoConfig.DEFAULT_VIEW_PATH
				.concat(viewName)
				.concat(MemoConfig.DEFAULT_VIEW_SUFFIX);
	}

}
