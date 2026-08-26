package com.office.toypjt.memo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.office.toypjt.ToyPjtConfig;

public class MemoDao {

	public List<MemoDto> selectMemos() {
		System.out.println("[MemoDao] selectMemos()");
		List<MemoDto> memoDtos = new ArrayList<>();
		String sql = "SELECT * FROM TBL_MEMO ORDER BY memoNo DESC";

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				memoDtos.add(new MemoDto(rs.getInt("memoNo"), rs.getString("memId"),
						rs.getString("memoTitle"), rs.getString("memoComent"),
						rs.getString("memoRegDate"), rs.getString("memoModDate")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return memoDtos;
	}

	public int insertMemo(MemoDto memoDto) {
		System.out.println("[MemoDao] insertMemo()");
		String sql = "INSERT INTO TBL_MEMO(memId, memoTitle, memoComent) VALUES(?, ?, ?)";

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memoDto.getMemId());
			pstmt.setString(2, memoDto.getMemoTitle());
			pstmt.setString(3, memoDto.getMemoComent());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public MemoDto selectMemoByMemoNo(int memoNo, String memId) {
		System.out.println("[MemoDao] selectMemoByMemoNo()");
		String sql = "SELECT * FROM TBL_MEMO WHERE memoNo = ? AND memId = ?";

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, memoNo);
			pstmt.setString(2, memId);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return new MemoDto(rs.getInt("memoNo"), rs.getString("memId"),
							rs.getString("memoTitle"), rs.getString("memoComent"),
							rs.getString("memoRegDate"), rs.getString("memoModDate"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int updateMemo(MemoDto memoDto) {
		System.out.println("[MemoDao] updateMemo()");
		String sql = "UPDATE TBL_MEMO SET memoTitle = ?, memoComent = ?, "
				+ "memoModDate = CURRENT_TIMESTAMP WHERE memoNo = ? AND memId = ?";

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memoDto.getMemoTitle());
			pstmt.setString(2, memoDto.getMemoComent());
			pstmt.setInt(3, memoDto.getMemoNo());
			pstmt.setString(4, memoDto.getMemId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int deleteMemoByMemoNo(int memoNo, String memId) {
		System.out.println("[MemoDao] deleteMemoByMemoNo()");
		String sql = "DELETE FROM TBL_MEMO WHERE memoNo = ? AND memId = ?";

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, memoNo);
			pstmt.setString(2, memId);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	private Connection getConnection() throws Exception {
		Class.forName(ToyPjtConfig.DRIVER);
		return DriverManager.getConnection(
				ToyPjtConfig.URL, ToyPjtConfig.USER, ToyPjtConfig.PASSWORD);
	}
}
