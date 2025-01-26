select * from tbl_user;
SELECT * FROM tbl_user LIMIT 10 OFFSET 0;
delete from tbl_user;

select * from tbl_cate
select * from tbl_board;
select * from tbl_reply;
select * from tbl_attach;
select * from tbl_evchargerstation where stat_Nm ='서귀포보건소';
select * from tbl_evchargerstation;
select * from tbl_evcharger; 
select count(*) from tbl_evchargerstation;
select count(*) from tbl_evcharger;
select * from maincate;

drop table tbl_cate;
drop table tbl_reply;
drop table tbl_reply_reaction;
drop table tbl_attach;
drop table tbl_board;
drop table tbl_board_reaction;
drop table tbl_user;
drop table tbl_evchargerstation;
drop table tbl_evcharger;

delete from tbl_reply;
delete from tbl_evchargerstation;
delete from tbl_evcharger;
delete from maincate;

DESCRIBE tbl_evchargerstation;

SELECT * FROM tbl_evchargerstation 
WHERE ('stat_nm' = 'stat_nm' AND stat_nm LIKE '%someValue%') 
   OR ('addr' = 'addr' AND addr LIKE '%someOtherValue%') 
ORDER BY stat_id 
LIMIT 10 OFFSET 0;


ALTER TABLE tbl_cate DROP COLUMN c_eng_name;
