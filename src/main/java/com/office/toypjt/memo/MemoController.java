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
	private final MemoService memoService = new MemoService();
       
    public MemoController() {
        super();

    }

    // 역할 : 메모 수정 및 삭제 요청 처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getServletPath();
		try {
			switch (command) {
			case MemoConfig.MODIFY_FORM_URL:
				Integer memNo = requireLogin(request, response);
				if (memNo == null) return;

				MemoDto memoDto = memoService.getMemoForModify(request, memNo);
				if (memoDto == null) {
					response.sendError(HttpServletResponse.SC_NOT_FOUND, "수정할 메모가 없습니다.");
					return;
				}

				request.setAttribute("memoDto", memoDto);
				request.getRequestDispatcher("/WEB-INF/views/memo/memo_modify_form.jsp")
						.forward(request, response);
				break;
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (IllegalArgumentException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
	}

	// 역할 : 메모 수정 및 삭제 요청 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getServletPath();
		try {
			Integer memNo = requireLogin(request, response);
			if (memNo == null) return;

			switch (command) {
			case MemoConfig.MODIFY_CONFIRM_URL:
				int modifyResult = memoService.modifyMemo(request, memNo);
				if (modifyResult != 1) {
					response.sendError(HttpServletResponse.SC_NOT_FOUND,
							"수정할 메모가 없거나 수정 권한이 없습니다.");
					return;
				}
				redirectHome(request, response);
				break;
			case MemoConfig.DELETE_CONFIRM_URL:
				int deleteResult = memoService.deleteMemo(request, memNo);
				if (deleteResult != 1) {
					response.sendError(HttpServletResponse.SC_NOT_FOUND,
							"삭제할 메모가 없거나 삭제 권한이 없습니다.");
					return;
				}
				redirectHome(request, response);
				break;
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (IllegalArgumentException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
	}

	private Integer requireLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer memNo = getLoginMemNo(request);
		if (memNo == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "먼저 로그인해 주세요.");
		}
		return memNo;
	}

	// 역할 : 세션에서 로그인한 회원 번호(memNo)를 가져옴
	private Integer getLoginMemNo(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) return null;

		Object loginMemNo = session.getAttribute(MemoConfig.LOGIN_MEM_NO);
		return loginMemNo instanceof Integer ? (Integer) loginMemNo : null;
	}

	private void redirectHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + MemoConfig.HOME_URL);
	}

}
