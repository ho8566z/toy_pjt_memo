package com.office.toypjt.memo;

import jakarta.servlet.http.HttpServletRequest;

public class MemoService {
	public static final int INVALID_INPUT = -1;

	private final String CLASS_NAME = "[MemoService] ";
	private final MemoDao memoDao = new MemoDao();

	public MemoDto getMemoForModify(HttpServletRequest request, String memId) {
		System.out.println(CLASS_NAME.concat("getMemoForModify()"));

		int memoNo = getMemoNo(request);
		if (memoNo < 1) return null;

		return memoDao.selectMemoByMemoNo(memoNo, memId);
	}

	public int modifyMemo(HttpServletRequest request, String memId) {
		System.out.println(CLASS_NAME.concat("modifyMemo()"));

		int memoNo = getMemoNo(request);
		String memoTitle = trim(request.getParameter("memoTitle"));
		String memoComent = trim(request.getParameter("memoComent"));

		if (memoNo < 1 || memoTitle.isEmpty() || memoComent.isEmpty()) {
			return INVALID_INPUT;
		}

		MemoDto memoDto = new MemoDto(memoNo, memId, memoTitle, memoComent);
		int result = memoDao.updateMemo(memoDto);

		return result;
	}

	public int deleteMemo(HttpServletRequest request, String memId) {
		System.out.println(CLASS_NAME.concat("deleteMemo()"));

		int memoNo = getMemoNo(request);
		if (memoNo < 1) return INVALID_INPUT;

		return memoDao.deleteMemoByMemoNo(memoNo, memId);
	}

	private int getMemoNo(HttpServletRequest request) {
		String memoNo = trim(request.getParameter("memoNo"));

		if (memoNo.isEmpty() || memoNo.length() > 9 || !memoNo.matches("[0-9]+")) {
			return INVALID_INPUT;
		}

		int parsedMemoNo = Integer.parseInt(memoNo);
		return parsedMemoNo > 0 ? parsedMemoNo : INVALID_INPUT;
	}

	private String trim(String value) {
		return value == null ? "" : value.trim();
	}

}
