<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.office.toypjt.memo.MemoDto" %>
<%
    List<MemoDto> memoDtos = (List<MemoDto>) request.getAttribute("memoDtos");
    String signinedMemId = (String) session.getAttribute("signinedMemId");
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>메모 목록</title>
<style>
    body { margin: 0; padding: 30px; font-family: Arial, sans-serif; }
    .container { width: 900px; margin: 0 auto; }
    .top { display: flex; justify-content: space-between; align-items: center; margin-bottom: 15px; }
    table { width: 100%; border-collapse: collapse; }
    th, td { padding: 12px; border: 1px solid #ddd; text-align: center; }
    th { background-color: #f5f5f5; }
    .title { text-align: left; }
    .empty { padding: 30px; color: #888; }
    .actions { display: flex; justify-content: center; gap: 6px; }
    .actions form { margin: 0; }
    .btn { display: inline-block; padding: 6px 10px; border: 0; border-radius: 4px;
           background: #333; color: #fff; text-decoration: none; cursor: pointer; }
    .delete-btn { background: #c0392b; }
</style>
</head>
<body>
<div class="container">
    <h1>메모 목록</h1>

    <div class="top">
        <span>전체 메모</span>
        <% if (signinedMemId != null) { %>
            <a href="<%= contextPath %>/memo_write_form.memo" class="btn">메모 작성</a>
        <% } %>
    </div>

    <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>등록일</th>
                <th>수정일</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody>
        <% if (memoDtos == null || memoDtos.isEmpty()) { %>
            <tr><td colspan="6" class="empty">등록된 메모가 없습니다.</td></tr>
        <% } else { %>
            <% for (MemoDto memoDto : memoDtos) { %>
            <tr>
                <td><%= memoDto.getMemoNo() %></td>
                <td class="title"><%= memoDto.getMemoTitle() %></td>
                <td><%= memoDto.getMemId() %></td>
                <td><%= memoDto.getMemoRegDate() %></td>
                <td><%= memoDto.getMemoModDate() %></td>
                <td>
                <% if (memoDto.getMemId().equals(signinedMemId)) { %>
                    <div class="actions">
                        <a class="btn" href="<%= contextPath %>/memo_modify_form.memo?memoNo=<%= memoDto.getMemoNo() %>">수정</a>
                        <form action="<%= contextPath %>/memo_delete.memo" method="post"
                              onsubmit="return confirm('이 메모를 삭제하시겠습니까?');">
                            <input type="hidden" name="memoNo" value="<%= memoDto.getMemoNo() %>">
                            <button type="submit" class="btn delete-btn">삭제</button>
                        </form>
                    </div>
                <% } else { %>
                    -
                <% } %>
                </td>
            </tr>
            <% } %>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
