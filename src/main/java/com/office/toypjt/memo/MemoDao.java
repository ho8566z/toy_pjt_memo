package com.office.toypjt.memo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.office.toypjt.ToyPjtConfig;

public class MemoDao {

	public MemoDto selectMemoByMemoNo(int memoNo, String memId) {
		System.out.println("[MemoDao] selectMemoByMemoNo()");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(ToyPjtConfig.DRIVER);
			conn = DriverManager.getConnection(
					ToyPjtConfig.URL, ToyPjtConfig.USER, ToyPjtConfig.PASSWORD);

			String sql = "SELECT * FROM TBL_MEMO "
					+ "WHERE memoNo = ? AND memId = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memoNo);
			pstmt.setString(2, memId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return new MemoDto(
						rs.getInt("memoNo"),
						rs.getString("memId"),
						rs.getString("memoTitle"),
						rs.getString("memoComent"),
						rs.getString("memoRegDate"),
						rs.getString("memoModDate"));
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

		return null;
	}

	public int updateMemo(MemoDto memoDto) {
		System.out.println("[MemoDao] updateMemo()");

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;

		try {
			Class.forName(ToyPjtConfig.DRIVER);
			conn = DriverManager.getConnection(
					ToyPjtConfig.URL, ToyPjtConfig.USER, ToyPjtConfig.PASSWORD);

			String sql = "UPDATE TBL_MEMO "
					+ "SET memoTitle = ?, memoComent = ?, memoModDate = CURRENT_TIMESTAMP "
					+ "WHERE memoNo = ? AND memId = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memoDto.getMemoTitle());
			pstmt.setString(2, memoDto.getMemoComent());
			pstmt.setInt(3, memoDto.getMemoNo());
			pstmt.setString(4, memoDto.getMemId());
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

	public int deleteMemoByMemoNo(int memoNo, String memId) {
		System.out.println("[MemoDao] deleteMemoByMemoNo()");

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;

		try {
			Class.forName(ToyPjtConfig.DRIVER);
			conn = DriverManager.getConnection(
					ToyPjtConfig.URL, ToyPjtConfig.USER, ToyPjtConfig.PASSWORD);

			String sql = "DELETE FROM TBL_MEMO "
					+ "WHERE memoNo = ? AND memId = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memoNo);
			pstmt.setString(2, memId);
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
