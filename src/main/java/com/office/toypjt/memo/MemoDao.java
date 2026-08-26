package com.office.toypjt.memo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemoDao {

	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/DB_MEMO";
	private final String USER = "root";
	private final String PASSWORD = "1234";

	public MemoDto selectMemoByMemoNo(int memoNo, int memNo) {
		System.out.println("[MemoDao] selectMemoByMemoNo()");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemoDto memoDto = null;

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = "SELECT memo.*, member.memNo "
					+ "FROM TBL_MEMO memo "
					+ "JOIN TBL_MEMBER member ON memo.memId = member.memId "
					+ "WHERE memo.memoNo = ? AND member.memNo = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memoNo);
			pstmt.setInt(2, memNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				memoDto = new MemoDto();
				memoDto.setMemoNo(rs.getInt("memoNo"));
				memoDto.setMemNo(rs.getInt("memNo"));
				memoDto.setMemoTitle(rs.getString("memoTitle"));
				memoDto.setMemoContent(rs.getString("memoComent"));
				memoDto.setMemoRegDate(rs.getString("memoRegDate"));
				memoDto.setMemoModDate(rs.getString("memoModDate"));
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return memoDto;
	}

	public int updateMemo(MemoDto memoDto) {
		System.out.println("[MemoDao] updateMemo()");

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = "UPDATE TBL_MEMO memo "
					+ "JOIN TBL_MEMBER member ON memo.memId = member.memId "
					+ "SET memo.memoTitle = ?, memo.memoComent = ?, "
					+ "memo.memoModDate = CURRENT_TIMESTAMP "
					+ "WHERE memo.memoNo = ? AND member.memNo = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memoDto.getMemoTitle());
			pstmt.setString(2, memoDto.getMemoContent());
			pstmt.setInt(3, memoDto.getMemoNo());
			pstmt.setInt(4, memoDto.getMemNo());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int deleteMemoByMemoNo(int memoNo, int memNo) {
		System.out.println("[MemoDao] deleteMemoByMemoNo()");

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = "DELETE memo FROM TBL_MEMO memo "
					+ "JOIN TBL_MEMBER member ON memo.memId = member.memId "
					+ "WHERE memo.memoNo = ? AND member.memNo = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memoNo);
			pstmt.setInt(2, memNo);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
