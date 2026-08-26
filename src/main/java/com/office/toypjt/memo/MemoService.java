package com.office.toypjt.memo;

import jakarta.servlet.http.HttpServletRequest;

public class MemoService {

	private final MemoDao memoDao = new MemoDao();

	// 역할 : 메모 수정 폼 요청 처리
	public MemoDto getMemoForModify(HttpServletRequest request, int memNo) {
		int memoNo = getMemoNo(request);
		return memoDao.selectMemoByMemoNo(memoNo, memNo);
	}

	// 역할 : 메모 수정 요청 처리
	public int modifyMemo(HttpServletRequest request, int memNo) {
		int memoNo = getMemoNo(request);
		String memoTitle = trim(request.getParameter("memoTitle"));
		String memoContent = trim(request.getParameter("memoContent"));

		if (memoTitle.isEmpty() || memoContent.isEmpty()) {
			throw new IllegalArgumentException("제목과 내용을 모두 입력해 주세요.");
		}

		MemoDto memoDto = new MemoDto(memoNo, memNo, memoTitle, memoContent);
		return memoDao.updateMemo(memoDto);
	}

	// 역할 : 메모 삭제 요청 처리
	public int deleteMemo(HttpServletRequest request, int memNo) {
		int memoNo = getMemoNo(request);
		return memoDao.deleteMemoByMemoNo(memoNo, memNo);
	}

	// 역할 : 메모 번호를 요청에서 가져오는 메서드
	private int getMemoNo(HttpServletRequest request) {
		try {
			int memoNo = Integer.parseInt(request.getParameter("memoNo"));
			if (memoNo < 1) throw new NumberFormatException();
			return memoNo;

		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("올바른 메모 번호를 입력해 주세요.");
		}
	}
	// 역할 : trim을 이용해서 문자열의 앞뒤 공백을 제거하고, null인 경우 빈 문자열로 반환함.
	private String trim(String value) {
		return value == null ? "" : value.trim();
	}

}
