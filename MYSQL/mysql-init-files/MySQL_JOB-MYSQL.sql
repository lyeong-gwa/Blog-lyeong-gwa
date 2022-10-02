create database Blog_db;

use Blog_db;

CREATE TABLE IF NOT EXISTS job 
   (JOBID VARCHAR(10) NOT NULL, 
	JOBTITLE VARCHAR(35), 
	MINSAL INT(6), 
	MAXSAL INT(6)
   );


Insert into job values ('AD_PRES','President',20080,40000);
Insert into job values ('AD_VP','Administration Vice President',15000,30000);
Insert into job values ('AD_ASST','Administration Assistant',3000,6000);