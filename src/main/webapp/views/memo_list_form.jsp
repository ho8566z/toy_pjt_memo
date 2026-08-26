

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모 목록</title>

<style>
    body {
        margin: 0;
        padding: 30px;
        font-family: Arial, sans-serif;
    }

    .container {
        width: 800px;
        margin: 0 auto;
    }

    h1 {
        margin-bottom: 20px;
    }

    .top {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 15px;
    }

    .write-btn {
        padding: 8px 15px;
        background-color: #333;
        color: white;
        text-decoration: none;
        border-radius: 4px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
    }

    th, td {
        padding: 12px;
        border: 1px solid #ddd;
        text-align: center;
    }

    th {
        background-color: #f5f5f5;
    }

    .title {
        text-align: left;
    }

    .empty {
        padding: 30px;
        color: #888;
    }
</style>

</head>

<body>

<div class="container">

    <h1>메모 목록</h1>

    <div class="top">
        <span>전체 메모</span>
        <a href="/toy_pjt_memo/memo_write_form.memo" class="write-btn">
            메모 작성
        </a>
    </div>

    <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>등록일</th>
                <th>수정일</th>
            </tr>
        </thead>

        <tbody>

            <tr>
                <td>3</td>
                <td class="title">
    				<a href="/toy_pjt_memo/memo_detail.memo">세 번째 메모입니다.</a>
				</td>
                <td>gildong</td>
                <td>gildong</td>
                <td>gildong</td>
            </tr>

            <tr>
                <td>2</td>
                <td class="title">
   					<a href="/toy_pjt_memo/memo_detail.memo">두 번째 메모입니다.</a>
				</td>
                <td>gildong</td>
                <td>2026-08-25</td>
                <td>2026-08-25</td>
            </tr>

            <tr>
                <td>1</td>
                <td class="title">
   					<a href="/toy_pjt_memo/memo_detail.memo">첫 번째 메모입니다.</a>
				</td>
                <td>gildong</td>
                <td>2026-08-24</td>
                <td>2026-08-24</td>
            </tr>

        </tbody>
    </table>

</div>

</body>
</html>