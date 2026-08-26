package com.office.toypjt.member;

import java.io.IOException;

import com.office.toypjt.memo.MemoConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({ "/temp_login.mem", "/temp_logout.mem" })
public class TempLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String command = request.getServletPath();

		try {
			switch (command) {
			case "/temp_login.mem":
				int memNo = Integer.parseInt(request.getParameter("memNo"));
				if (memNo < 1) throw new NumberFormatException();

				request.getSession().setAttribute(MemoConfig.LOGIN_MEM_NO, memNo);
				break;

			case "/temp_logout.mem":
				HttpSession session = request.getSession(false);
				if (session != null) session.invalidate();
				break;

			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}

			response.sendRedirect(request.getContextPath() + MemoConfig.HOME_URL);

		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"회원번호는 1 이상의 숫자로 입력해 주세요.");
		}
	}
}
