package com.office.toypjt.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MemberDao implements IMemberDao {
	
	final private String CLASS_NAME = "[MemberDao] ";
	
	@Override
	public int insertNewMember(MemberDto memberDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
	

		
		try {
			Class.forName(DRIVER);
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			String sql = "INSERT INTO tbl_member(memId, memPw, memMail, memPhone)"
					+ "VALUES(?, ? , ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberDto.getMemId());
			pstmt.setString(2, memberDto.getMemPw());
			pstmt.setString(3, memberDto.getMemMail());
			pstmt.setString(4, memberDto.getMemPhone());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if (conn != null) conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}
}