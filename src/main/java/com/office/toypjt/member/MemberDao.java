package com.office.toypjt.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.office.toypjt.ToyPjtConfig;

public class MemberDao implements IMemberDao {
	
	final private String CLASS_NAME = "[MemberDao] ";
	
	@Override
	public int insertNewMember(MemberDto memberDto) {
		System.out.println(CLASS_NAME.concat(" insertNewMember()"));
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
	

		
		try {
			Class.forName(ToyPjtConfig.DRIVER);
			
			conn = DriverManager.getConnection(ToyPjtConfig.URL, ToyPjtConfig.USER, ToyPjtConfig.PASSWORD);
			
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
		
		return result;
		
	}
	
	@Override
	public MemberDto selectMemberByMemId(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberDto> memberDtos = new ArrayList<MemberDto>();
		
		try {
			Class.forName(ToyPjtConfig.DRIVER);
			
			conn = DriverManager.getConnection(ToyPjtConfig.URL, ToyPjtConfig.USER, ToyPjtConfig.PASSWORD);
			
			String sql = "SELECT * FROM TBL_MEMBER "
					+ "WHERE memId = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				int memNo = rs.getInt("memNo");
				String memId = rs.getString("memId");
				String memPw = rs.getString("memPw");
				String memMail = rs.getString("memMail");
				String memPhone = rs.getString("memPhone");
				String memRegDate = rs.getString("memRegDate");
				String memModDate = rs.getString("memModDate");
				
				MemberDto dto = 
						new MemberDto(
								memNo,
								memId,
								memPw,
								memMail,
								memPhone,
								memRegDate,
								memModDate);
				
				memberDtos.add(dto);		
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return memberDtos.size() > 0 ? memberDtos.get(0) : null;
		
	}

	@Override
	public int updateMemberByMemNo(MemberDto memberDto) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	
}