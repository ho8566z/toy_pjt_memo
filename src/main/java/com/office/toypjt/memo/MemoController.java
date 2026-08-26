package com.office.toypjt.memo;

import java.io.IOException;

import com.office.toypjt.ToyPjtConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("*.memo")
public class MemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CLASS_NAME = "[MemoController] ";
	private final MemoService memoService = new MemoService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = getCommand(request);

		switch (command) {
		case MemoConfig.MEMO_LIST_FORM:
			request.setAttribute("memoDtos", memoService.getMemos());
			request.getRequestDispatcher(generateViewName("/memo_list_form"))
					.forward(request, response);
			break;

		case MemoConfig.MEMO_MODIFY_FORM:
			showModifyForm(request, response);
			break;

		default:
			redirectMemoList(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = getCommand(request);
		String memId = getSigninedMemId(request);

		if (memId == null) {
			redirectSignin(request, response);
			return;
		}
		
		int result = MemoService.INVALID_INPUT;
		switch (command) {
		case MemoConfig.MEMO_WRITE_CONFIRM:
			result = memoService.writeMemo(request, memId);
			break;
		case MemoConfig.MEMO_MODIFY_CONFIRM:
			result = memoService.modifyMemo(request, memId);
			break;
		case MemoConfig.MEMO_DELETE:
			result = memoService.deleteMemo(request, memId);
			break;
		default:
			redirectMemoList(request, response);
			return;
		}

		System.out.println(CLASS_NAME + (result > 0 ? "MEMO PROCESS SUCCESS!!" : "MEMO PROCESS FAIL!!"));
		redirectMemoList(request, response);
	}

	private void showModifyForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

	private String getCommand(HttpServletRequest request) {
		return request.getRequestURI().substring(request.getContextPath().length());
	}

	private String getSigninedMemId(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return null;
		}

		Object memId = session.getAttribute(MemoConfig.SIGNINED_MEMBER_ID);
		return memId == null ? null : String.valueOf(memId);
	}

	private void redirectMemoList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + MemoConfig.MEMO_LIST_FORM);
	}

	private void redirectSignin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/member_signin_form.mem");
	}

	private String generateViewName(String viewName) {
		return ToyPjtConfig.DEFAULT_VIEW_PATH + viewName + ToyPjtConfig.DEFAULT_VIEW_SUFFIX;
	}
}
