select * from tbl_member;
select * from tbl_board;
select * from attach;
select * from tbl_reply;
select * from tbl_reaction where reaction_type='like';
select * from tbl_reaction;

select * from tbl_board;
select * from attach;

alter table tbl_reply drop column writer_date;
alter table tbl_board drop column likes;
alter table tbl_board drop column dislikes;
alter table tbl_reply drop column likes;
alter table tbl_reply drop column dislikes;

SELECT * FROM tbl_user LIMIT 10 OFFSET 0;


select * from tbl_board;
select * from tbl_reply;
select * from tbl_attach;

select * from maincate;
delete from maincate;
delete from tbl_reaction;


drop table tbl_reply;
drop table tbl_attach;
drop table tbl_board;
drop table tbl_user;

delete from tbl_reply;


