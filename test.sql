select * from tbl_user;
SELECT * FROM tbl_user LIMIT 10 OFFSET 0;
delete from tbl_user;

select * from tbl_board;
select * from tbl_reply;
select * from tbl_attach;
select * from tbl_evchargerstation;
select * from tbl_evcharger;
select count(*) from tbl_evchargerstation;
select count(*) from tbl_evcharger;
select * from maincate;

drop table tbl_reply;
drop table tbl_attach;
drop table tbl_board;
drop table tbl_user;
drop table tbl_evchargerstation;
drop table tbl_evcharger;

delete from tbl_reply;
delete from tbl_evchargerstation;
delete from tbl_evcharger;
delete from maincate;


