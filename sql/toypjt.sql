-- DB_MEMO ---------------------------------------------------------------
CREATE DATABASE DB_MEMO;

USE DB_MEMO;

SHOW TABLES;


-- TBL_MEMBER ------------------------------------------------------------
CREATE TABLE TBL_MEMBER(
	memNo		INT				NOT NULL	AUTO_INCREMENT	COMMENT '회원번호',
	memId		VARCHAR(50)		NOT NULL	UNIQUE			COMMENT '회원 아이디',
	memPw		VARCHAR(100)	NOT NULL					COMMENT '회원 비밀번호',
	memMail		VARCHAR(50)									COMMENT '회원 이메일',
	memPhone	VARCHAR(50)									COMMENT '회원 연락처',
	memRegDate	TIMESTAMP DEFAULT CURRENT_TIMESTAMP()		COMMENT	'회원 등록일',
	memModDate	TIMESTAMP DEFAULT CURRENT_TIMESTAMP() 
							ON UPDATE CURRENT_TIMESTAMP 	COMMENT '회원 수정일',
	PRIMARY KEY(memNo)
)

DESC TBL_MEMBER;
SELECT * FROM TBL_MEMBER;




-- TBL_MEMO --------------------------------------------------------------
CREATE TABLE TBL_MEMO(
	memoNo		INT				NOT NULL	AUTO_INCREMENT	COMMENT '메모번호',
	memId		VARCHAR(50)		NOT NULL	UNIQUE			COMMENT '회원 아이디',
	memoTitle	VARCHAR(50)		NOT NULL					COMMENT '메모 제목',
	memoComent	VARCHAR(100)	NOT NULL					COMMENT '메모 내용',
	memoRegDate	TIMESTAMP DEFAULT CURRENT_TIMESTAMP()		COMMENT	'메모 등록일',	
	memoModDate	TIMESTAMP DEFAULT CURRENT_TIMESTAMP() 
							ON UPDATE CURRENT_TIMESTAMP 	COMMENT '메모 수정일',
	PRIMARY KEY(memoNo)
)

DESC TBL_MEMO;
SELECT * FROM TBL_MEMO;



