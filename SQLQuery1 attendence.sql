use attendence
select * from studentise
select * from studentai
create table studentise(usn varchar(10),student_name char(20),mob_no varchar(10) ,esd int,mces int,dbms int,fafl int,dm int,unix int,elective int)
delete from studentis where usn like '4nm20is12_'
insert into studentise values('4nm20is123','sanith shetty','914169707',0,0,0,0,0,0,0)
insert into studentise values('4nm20is124','shaun paul','444444444',0,0,0,0,0,0,0)
insert into studentise values('4nm20is125','rashwin shetty','696969696',0,0,0,0,0,0,0)
insert into studentise values('4nm20is126','shama','666666666',0,0,0,0,0,0,0)
insert into studentise values('4nm20is127','siya shetty','777777777',0,0,0,0,0,0,0)
insert into studentise values('4nm20is128','shreenidhi','888888888',0,0,0,0,0,0,0)

create table studentai(usn varchar(10),student_name char(20),mob_no varchar(10),neuralnetwork int,datascience int,dbms int,ml int,webprogram int,os int,elective int)

insert into studentai values('4nm20ai123','treenal pinto','914169707',0,0,0,0,0,0,0)
insert into studentai values('4nm20ai124','uttam','444444444',0,0,0,0,0,0,0)
insert into studentai values('4nm20ai125','sumbhram','696969696',0,0,0,0,0,0,0)
insert into studentai values('4nm20ai126','omar','666666666',0,0,0,0,0,0,0)
insert into studentai values('4nm20ai127','rachith','777777777',0,0,0,0,0,0,0)
insert into studentai values('4nm20ai128','suraj','888888888',0,0,0,0,0,0,0)
drop table studentise
delete studentai
select * from studentise


update studentise set mces=0
where usn='4nm20is123'



select * from teacher
create table teacher(faculty_no varchar(20),faculty_name char(20),password char(20),dept char(20))
insert into teacher values('owner123','sanith','sanith9659','is')