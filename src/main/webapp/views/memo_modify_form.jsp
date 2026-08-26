<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>메모 수정</title>
</head>
<body>
    <h1>메모 수정</h1>

    <form action="${pageContext.request.contextPath}/memo_modify_confirm.memo" method="post">
        <input type="hidden" name="memoNo" value="${memoDto.memoNo}">

        <div>
            <label for="memoTitle">제목</label>
            <input type="text" id="memoTitle" name="memoTitle"
                   value="${memoDto.memoTitle}" maxlength="50" required>
        </div>

        <div>
            <label for="memoComent">내용</label>
            <textarea id="memoComent" name="memoComent" rows="10"
                      maxlength="100" required>${memoDto.memoComent}</textarea>
        </div>

        <button type="submit">수정하기</button>
        <a href="${pageContext.request.contextPath}/memo_list_form.memo">취소</a>
    </form>
</body>
</html>
