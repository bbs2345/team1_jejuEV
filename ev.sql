select * from tbl_evstation;
select * from tbl_evcharger;
select * from tbl_csvdt;


drop table tbl_evcharger;
drop table tbl_evstation;
drop table tbl_csvdt;



select * from tbl_evstation where stat_nm='서귀포 제2청사';
select * from tbl_evstation where stat_id='JD000098';


select count(stat_id) from tbl_evstation;
select count(id) from tbl_evcharger;
select count(*) from tbl_csvdt;

select * from tbl_evcharger where stat_id = 'JD000098';
select * from tbl_evcharger where stat_id='JD000098';


SELECT 
    CONSTRAINT_NAME,
    CONSTRAINT_TYPE
FROM 
    INFORMATION_SCHEMA.TABLE_CONSTRAINTS
WHERE 
    TABLE_SCHEMA = 'mbcdb10' 
    AND TABLE_NAME = 'tbl_csvdt';