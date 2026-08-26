package com.office.toypjt.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberService {

	final private String CLASS_NAME = "[MemberService] ";
	private MemberDao memberDao = new MemberDao();

	public int addMember(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(CLASS_NAME.concat(" addMember()"));

		String memId = request.getParameter("userId");
		String memPw = request.getParameter("password");
		String memMail = request.getParameter("email");
		String memPhone = request.getParameter("phone");

		MemberDto dto = new MemberDto(memId, memPw, memMail, memPhone);

		int result = memberDao.insertNewMember(dto);

		return result;
	}
}