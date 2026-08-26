<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>메모 수정</title>
</head>
<body>
    <h1>메모 수정</h1>

    <form action="${pageContext.request.contextPath}/modify_confirm.memo" method="post">
        <input type="hidden" name="memoNo" value="${memoDto.memoNo}">

        <div>
            <label for="memoTitle">제목</label>
            <input type="text" id="memoTitle" name="memoTitle"
                   value="${memoDto.memoTitle}" maxlength="100" required>
        </div>

        <div>
            <label for="memoContent">내용</label>
            <textarea id="memoContent" name="memoContent" rows="10" required>${memoDto.memoContent}</textarea>
        </div>

        <button type="submit">수정하기</button>
        <a href="${pageContext.request.contextPath}/">취소</a>
    </form>
</body>
</html>
