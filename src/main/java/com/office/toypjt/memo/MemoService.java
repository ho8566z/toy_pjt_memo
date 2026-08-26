package com.office.toypjt.memo;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

public class MemoService {
	public static final int INVALID_INPUT = -1;

	private static final String CLASS_NAME = "[MemoService] ";
	private final MemoDao memoDao = new MemoDao();

	public List<MemoDto> getMemos() {
		System.out.println(CLASS_NAME.concat("getMemos()"));
		return memoDao.selectMemos();
	}

	public int writeMemo(HttpServletRequest request, String memId) {
		System.out.println(CLASS_NAME.concat("writeMemo()"));

		String memoTitle = trim(request.getParameter("memoTitle"));
		String memoComent = trim(request.getParameter("memoComent"));
		if (!isValidMemo(memoTitle, memoComent)) {
			return INVALID_INPUT;
		}

		MemoDto memoDto = new MemoDto(0, memId, memoTitle, memoComent);
		return memoDao.insertMemo(memoDto);
	}

	public MemoDto getMemoForModify(HttpServletRequest request, String memId) {
		System.out.println(CLASS_NAME.concat("getMemoForModify()"));

		int memoNo = getMemoNo(request);
		if (memoNo < 1) {
			return null;
		}

		return memoDao.selectMemoByMemoNo(memoNo, memId);
	}

	public int modifyMemo(HttpServletRequest request, String memId) {
		System.out.println(CLASS_NAME.concat("modifyMemo()"));

		int memoNo = getMemoNo(request);
		String memoTitle = trim(request.getParameter("memoTitle"));
		String memoComent = trim(request.getParameter("memoComent"));

		if (memoNo < 1 || !isValidMemo(memoTitle, memoComent)) {
			return INVALID_INPUT;
		}

		MemoDto memoDto = new MemoDto(memoNo, memId, memoTitle, memoComent);
		return memoDao.updateMemo(memoDto);
	}

	public int deleteMemo(HttpServletRequest request, String memId) {
		System.out.println(CLASS_NAME.concat("deleteMemo()"));

		int memoNo = getMemoNo(request);
		if (memoNo < 1) {
			return INVALID_INPUT;
		}

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

	private boolean isValidMemo(String memoTitle, String memoComent) {
		return !memoTitle.isEmpty()
				&& !memoComent.isEmpty()
				&& memoTitle.length() <= MemoConfig.MAX_TITLE_LENGTH
				&& memoComent.length() <= MemoConfig.MAX_COMMENT_LENGTH;
	}
}
