<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>메모 서비스</title>
<style>
body { max-width: 720px; margin: 40px auto; font-family: sans-serif; line-height: 1.6; }
section { margin: 24px 0; padding: 20px; border: 1px solid #dadada; border-radius: 8px; }
input { padding: 6px; }
button { padding: 7px 12px; cursor: pointer; }
.login { color: #1565c0; font-weight: bold; }
</style>
</head>
<body>

	<h1>메모 서비스</h1>

	<% if (session.getAttribute("loginMemNo") == null) { %>
	<section>
		<h2>임시 로그인</h2>
		<form action="<%= request.getContextPath() %>/temp_login.mem" method="post">
			<label for="loginMemNo">회원번호</label>
			<input type="number" id="loginMemNo" name="memNo" min="1" required>
			<button type="submit">로그인</button>
		</form>
	</section>
	<% } else { %>
	<p class="login">로그인 회원번호: <%= session.getAttribute("loginMemNo") %></p>
	<form action="<%= request.getContextPath() %>/temp_logout.mem" method="post">
		<button type="submit">로그아웃</button>
	</form>

	<section>
		<h2>메모 수정</h2>
		<form action="<%= request.getContextPath() %>/modify_form.memo" method="get">
			<label for="modifyMemoNo">메모번호</label>
			<input type="number" id="modifyMemoNo" name="memoNo" min="1" required>
			<button type="submit">수정 화면 열기</button>
		</form>
	</section>

	<section>
		<h2>메모 삭제</h2>
		<form action="<%= request.getContextPath() %>/delete_confirm.memo" method="post"
			  onsubmit="return confirm('이 메모를 정말 삭제할까요?');">
			<label for="deleteMemoNo">메모번호</label>
			<input type="number" id="deleteMemoNo" name="memoNo" min="1" required>
			<button type="submit">삭제</button>
		</form>
	</section>
	<% } %>

</body>
</html>
