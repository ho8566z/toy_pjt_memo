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
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		String command = getCommand(request);


		switch (command) {

		// 메모 목록
		case MemoConfig.MEMO_LIST_FORM:

			System.out.println(
					CLASS_NAME + "MEMO LIST"
			);

			request.setAttribute(
					"memoDtos",
					memoService.getMemos()
			);

			request.getRequestDispatcher(
					generateViewName("/memo_list_form")
			).forward(request, response);

			break;


		// 메모 작성 화면
		case MemoConfig.MEMO_WRITE_FORM:

			System.out.println(
					CLASS_NAME + "MEMO WRITE FORM"
			);

			request.getRequestDispatcher(
					generateViewName("/memo_write_form")
			).forward(request, response);

			break;


		// 메모 수정 화면
		case MemoConfig.MEMO_MODIFY_FORM:

			System.out.println(
					CLASS_NAME + "MEMO MODIFY FORM"
			);

			showModifyForm(request, response);

			break;


		default:

			redirectMemoList(request, response);

			break;
		}
	}


	@Override
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String command = getCommand(request);

		// 로그인한 회원 ID 가져오기
		String memId = getSigninedMemId(request);

//		// 테스트용
//		if (memId == null) {
//		    memId = "gildong";
//		}
//
//		System.out.println("로그인 회원 ID = " + memId);


		System.out.println("로그인 회원 ID = " + memId);

		// 로그인 여부 확인
		if (memId == null) {
		    redirectSignin(request, response);
		    return;
		}
		
		
		
		int result = MemoService.INVALID_INPUT;


		switch (command) {


		// 메모 작성
		case MemoConfig.MEMO_WRITE_CONFIRM:

			System.out.println(
					CLASS_NAME
					+ MemoConfig.MEMO_WRITE_CONFIRM
			);

			result = memoService.writeMemo(
					request,
					memId
			);


			if (result > 0) {

				System.out.println(
						CLASS_NAME
						+ "MEMO INSERT SUCCESS"
				);

				// 작성 성공 → 메모 목록으로 이동
				redirectMemoList(request, response);

				return;

			} else {

				System.out.println(
						CLASS_NAME
						+ "MEMO INSERT FAIL!!"
				);

				// 작성 실패 → 작성 화면으로 돌아가기
				request.getRequestDispatcher(
						generateViewName("/memo_write_form")
				).forward(request, response);

				return;
			}


		// 메모 수정
		case MemoConfig.MEMO_MODIFY_CONFIRM:

			System.out.println(
					CLASS_NAME
					+ MemoConfig.MEMO_MODIFY_CONFIRM
			);

			result = memoService.modifyMemo(
					request,
					memId
			);

			break;


		// 메모 삭제
		case MemoConfig.MEMO_DELETE:

			System.out.println(
					CLASS_NAME
					+ MemoConfig.MEMO_DELETE
			);

			result = memoService.deleteMemo(
					request,
					memId
			);

			break;


		default:

			redirectMemoList(request, response);

			return;
		}


		System.out.println(
				CLASS_NAME
				+ (
						result > 0
						? "MEMO PROCESS SUCCESS!!"
						: "MEMO PROCESS FAIL!!"
				)
		);


		// 수정/삭제 처리 후 메모 목록으로 이동
		redirectMemoList(request, response);
	}


	/**
	 * 메모 수정 화면
	 */
	private void showModifyForm(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		String memId = getSigninedMemId(request);


		// 로그인하지 않은 경우
		if (memId == null) {

			redirectSignin(request, response);

			return;
		}


		MemoDto memoDto =
				memoService.getMemoForModify(
						request,
						memId
				);


		// 수정할 메모가 없는 경우
		if (memoDto == null) {

			redirectMemoList(request, response);

			return;
		}


		request.setAttribute(
				"memoDto",
				memoDto
		);


		request.getRequestDispatcher(
				generateViewName("/memo_modify_form")
		).forward(request, response);
	}


	/**
	 * 요청 URL에서 command 추출
	 */
	private String getCommand(
			HttpServletRequest request) {

		return request.getRequestURI()
				.substring(
						request.getContextPath().length()
				);
	}


	/**
	 * 세션에서 로그인한 회원 ID 가져오기
	 */
	private String getSigninedMemId(
			HttpServletRequest request) {

		HttpSession session =
				request.getSession(false);


		if (session == null) {

			return null;
		}


		Object memId =
				session.getAttribute(
						MemoConfig.SIGNINED_MEMBER_ID
				);


		return memId == null
				? null
				: String.valueOf(memId);
	}


	/**
	 * 메모 목록으로 이동
	 */
	private void redirectMemoList(
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {

		response.sendRedirect(
				request.getContextPath()
				+ MemoConfig.MEMO_LIST_FORM
		);
	}


	/**
	 * 로그인 화면으로 이동
	 */
	private void redirectSignin(
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {

		response.sendRedirect(
				request.getContextPath()
				+ "/member_signin_form.mem"
		);
	}


	/**
	 * JSP View 경로 생성
	 */
	private String generateViewName(
			String viewName) {

		return ToyPjtConfig.DEFAULT_VIEW_PATH
				+ viewName
				+ ToyPjtConfig.DEFAULT_VIEW_SUFFIX;
	}
}