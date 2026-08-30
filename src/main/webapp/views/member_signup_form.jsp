<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- request.getContextPath().concat(MemberConfig.MEMBER_SIGNUP_CONFIRM) -->
	<form action="/toy_pjt_memo/member_signup_confirm.mem" method="POST">
		<input type="text" name="userId" required>
		<br>
		<input type="password" name="password" required>
		<br>
		<input type="email" name="email" required>
		<br>
		<input type="tel" name="phone" required>
		<br>
		<input type="submit" value="SUBMIT">
	</form>
	
</body>
</html>