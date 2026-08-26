package com.office.toypjt.memo;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

public class MemoService {

	public static final int INVALID_INPUT = -1;

	private static final String CLASS_NAME = "[MemoService] ";

	private final MemoDao memoDao = new MemoDao();


	// 메모 목록 조회
	public List<MemoDto> getMemos() {

		System.out.println(CLASS_NAME + "getMemos()");

		return memoDao.selectMemos();
	}


	// 메모 작성
	public int writeMemo(HttpServletRequest request, String memId) {

	    System.out.println(CLASS_NAME + "writeMemo()");

	    String memoTitle = trim(request.getParameter("memoTitle"));
	    String memoComent = trim(request.getParameter("memoComent"));

	    // ★ 여기 추가
	    System.out.println("memId = " + memId);
	    System.out.println("memoTitle = " + memoTitle);
	    System.out.println("memoComent = " + memoComent);

	    if (!isValidMemo(memoTitle, memoComent)) {

	        // ★ 이것도 추가
	        System.out.println("메모 입력값 검증 실패");

	        return INVALID_INPUT;
	    }

	    MemoDto memoDto = new MemoDto(
	            0,
	            memId,
	            memoTitle,
	            memoComent
	    );

	    return memoDao.insertMemo(memoDto);
	}

	// 메모 수정 화면에 필요한 메모 조회
	public MemoDto getMemoForModify(
			HttpServletRequest request,
			String memId) {

		System.out.println(CLASS_NAME + "getMemoForModify()");

		int memoNo = getMemoNo(request);

		if (memoNo < 1) {
			return null;
		}

		return memoDao.selectMemoByMemoNo(memoNo, memId);
	}


	// 메모 수정
	public int modifyMemo(
			HttpServletRequest request,
			String memId) {

		System.out.println(CLASS_NAME + "modifyMemo()");

		int memoNo = getMemoNo(request);

		String memoTitle = trim(request.getParameter("memoTitle"));
		String memoComent = trim(request.getParameter("memoComent"));

		if (memoNo < 1 || !isValidMemo(memoTitle, memoComent)) {
			return INVALID_INPUT;
		}

		MemoDto memoDto = new MemoDto(
				memoNo,
				memId,
				memoTitle,
				memoComent
		);

		return memoDao.updateMemo(memoDto);
	}


	// 메모 삭제
	public int deleteMemo(
			HttpServletRequest request,
			String memId) {

		System.out.println(CLASS_NAME + "deleteMemo()");

		int memoNo = getMemoNo(request);

		if (memoNo < 1) {
			return INVALID_INPUT;
		}

		return memoDao.deleteMemoByMemoNo(
				memoNo,
				memId
		);
	}


	// memoNo 검증
	private int getMemoNo(HttpServletRequest request) {

		String memoNo = trim(
				request.getParameter("memoNo")
		);

		if (memoNo.isEmpty()
				|| memoNo.length() > 9
				|| !memoNo.matches("[0-9]+")) {

			return INVALID_INPUT;
		}

		int parsedMemoNo = Integer.parseInt(memoNo);

		return parsedMemoNo > 0
				? parsedMemoNo
				: INVALID_INPUT;
	}


	// 문자열 공백 제거
	private String trim(String value) {

		return value == null
				? ""
				: value.trim();
	}


	// 메모 입력값 검증
	private boolean isValidMemo(
			String memoTitle,
			String memoComent) {

		return !memoTitle.isEmpty()
				&& !memoComent.isEmpty()
				&& memoTitle.length()
						<= MemoConfig.MAX_TITLE_LENGTH
				&& memoComent.length()
						<= MemoConfig.MAX_COMMENT_LENGTH;
	}
}
