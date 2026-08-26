package com.office.toypjt.member;

public interface IMemberDao {
	
	public final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public final String URL = "jdbc:mysql://localhost:3306/db_memo";
	public final String USER ="root";
	public final String PASSWORD = "1234";
	
	public int insertNewMember(MemberDto memberDto);

	public MemberDto selectMemberByMemId(String memId);

	public int updateMemberByMemNo(MemberDto memberDto);

}
