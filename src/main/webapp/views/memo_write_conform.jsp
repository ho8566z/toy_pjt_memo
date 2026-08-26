<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">

<title>메모 작성</title>

<style>
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

    .form-box {
        border: 1px solid #ddd;
        padding: 20px;
    }

    .form-row {
        margin-bottom: 15px;
    }

    .form-row label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
    }

    .form-row input,
    .form-row textarea {
        width: 100%;
        padding: 10px;
        box-sizing: border-box;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 14px;
    }

    .form-row textarea {
        height: 250px;
        resize: vertical;
    }

    .buttons {
        text-align: right;
        margin-top: 20px;
    }

    .btn {
        display: inline-block;
        padding: 8px 15px;
        border: none;
        border-radius: 4px;
        text-decoration: none;
        cursor: pointer;
        font-size: 14px;
    }

    .submit-btn {
        background-color: #333;
        color: white;
    }

    .cancel-btn {
        background-color: #ddd;
        color: #333;
    }
	
</style>

</head>

<body>

<div class="container">

	<h1>메모 작성</h1>

	<div class="form-box">
	
		<form action="/toy_pjt_memo/memo_write_confirm.memo" method="post">
		
		<div class="form-row">
		    <label for="memoTitle">제목</label>
		    <input type="text" id="memoTitle" name="memoTitle">
		</div>
		
		<div class="form-row">
		    <label for="memoComent">내용</label>
		    <textarea id="memoComent" name="memoComent"></textarea>
		</div>
		
		<div class="buttons">
		
			<a href="/toy_pjt_memo/memo_list_form.memo"
				class="btn cancel-btn">
				취소
			</a>
			
			<button type="submit" class="btn submit-btn">
				작성
			</button>
			
		</div>
		
	</form>
		
	</div>

</div>

</body>
</html>