select * from board;

create table board(
	num number(5) primary key,
	pass varchar2(30),   -- 게시물의 수정 삭제를 위한 비밀번호
	name varchar2(30),
	email varchar2(30),
	title varchar2(50),
	content varchar2(1000),
	readcount number(4) default 0,   --조회수
	writedate date default sysdate    -- 작성일자
	replycnt number(3) difault 0,
	imgfilename varchar2(50)
);

insert into board(num, userid, email, pass, title, content)
values( board_seq.nextVal, 'hong', 'abc@naver.com', '1234', '첫방문입니다', '반갑습니다. 앞으로 많으 격려와 지도편달 부탁드립니다.' );
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'somi', 'adddnaver.com', '1234', '게시판 개설', '축하드립니다.  무궁한 발전을 기원할께요');
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'light', 'bnbn@naver.com', '1234', '돼지골마을', '돼지 삼겹살이 맛있습니다');
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'hana', 'hana@daum.net', '1234', '2022년 겨울' , '몸시 추울꺼 같아요... 다들 건강 유의 하세요....');
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'hana', 'hana@daum.net', '1234', '코로나바이러스' ,	'사회적 거리두기4단계 .... 백신접종 등등등');


select * from member;
create table member(
	name varchar2(30),
	userid varchar2(30) primary key,
	pwd varchar2(30),   -- 게시물의 수정 삭제를 위한 비밀번호
	email varchar2(30),
	phone varchar2(30),
	admin char(1) default 0,
	zip_num varchar2(10),
	address varchar2(100)
);

select * from reply order by replynum desc;
create table reply(
	num number(7) primary key,    -- 댓글 순번
	boardnum number(5),                --  댓글의 해당 게시물 번호
	userid varchar2(20),                   -- 댓굴 쓰니
	writedate date default sysdate, -- 작성일
	content varchar2(1000)               -- 작성 내용
);

create sequence board_seq start with 1 increment by 1;
create sequence reply_seq start with 1 increment by 1;



