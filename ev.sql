select * from tbl_evstation;
select * from tbl_evcharger;

drop table tbl_evcharger;
drop table tbl_evstation;

select * from tbl_evstation where stat_nm='서귀포보건소';


select count(stat_id) from tbl_evstation;
select count(id) from tbl_evcharger;

select * from tbl_evcharger where stat_id = 'GR000045';
select * from tbl_evcharger where stat_id='ME19B169';