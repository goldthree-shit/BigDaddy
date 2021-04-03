drop DATABASE IF EXISTS upload;
CREATE DATABASE IF NOT EXISTS upload DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use upload;
create table user
(
	uid int auto_increment,
	username varchar(25) null,
	password varchar(25) null,
	constraint user_pk
	primary key (uid)
)DEFAULT CHARACTER SET = utf8 COLLATE utf8_general_ci;
create table user_file
(
    fid      int auto_increment,
    uid      int         ,
    filename varchar(100),
    destpath varchar(120),
    primary key(fid)
)DEFAULT CHARACTER SET = utf8 COLLATE utf8_general_ci;