package com.office.toypjt.memo;

public class MemoDto {

	private int memoNo;
	private int memNo;
	private String memoTitle;
	private String memoContent;
	private String memoRegDate;
	private String memoModDate;

	public MemoDto() {
	}

	public MemoDto(int memoNo, int memNo, String memoTitle, String memoContent) {
		this.memoNo = memoNo;
		this.memNo = memNo;
		this.memoTitle = memoTitle;
		this.memoContent = memoContent;
	}

	public int getMemoNo() {
		return memoNo;
	}

	public void setMemoNo(int memoNo) {
		this.memoNo = memoNo;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getMemoTitle() {
		return memoTitle;
	}

	public void setMemoTitle(String memoTitle) {
		this.memoTitle = memoTitle;
	}

	public String getMemoContent() {
		return memoContent;
	}

	public void setMemoContent(String memoContent) {
		this.memoContent = memoContent;
	}

	public String getMemoRegDate() {
		return memoRegDate;
	}

	public void setMemoRegDate(String memoRegDate) {
		this.memoRegDate = memoRegDate;
	}

	public String getMemoModDate() {
		return memoModDate;
	}

	public void setMemoModDate(String memoModDate) {
		this.memoModDate = memoModDate;
	}

}
