use GDMS;
set foreign_key_checks=0;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS schoolnotice;
DROP TABLE IF EXISTS deptnotice;
DROP TABLE IF EXISTS teachermessage;
DROP TABLE IF EXISTS sysmessage;
DROP TABLE IF EXISTS users;
drop table if exists principleusers;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS state;
drop table if exists grade;
set foreign_key_checks=1;

CREATE TABLE teacher(
    id          varchar(20) NOT NULL,
    name        varchar(50) NOT NULL,
    department 	varchar(50),
    major 	varchar(50),
    PRIMARY KEY (id)
);

CREATE TABLE student(
    id          varchar(12) NOT NULL,
    name        varchar(50) NOT NULL,
    major       varchar(50) NOT NULL,
     department 	varchar(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE deptnotice(
    id          int not null auto_increment,
    department 	varchar(50) NOT NULL,
    title       varchar(50) NOT NULL,
    time        datetime not null,
    PRIMARY KEY (id)
);

CREATE TABLE schoolnotice(
    id          int not null auto_increment,
    title       varchar(50) NOT NULL,
    time        datetime not null,
    PRIMARY KEY (id)
);

CREATE TABLE teachermessage(
    id          int not null auto_increment,
    title       varchar(50) NOT NULL,
    teacher_id  varchar(50) NOT NULL,
    time        datetime not null,
    PRIMARY KEY (id),
    FOREIGN KEY (teacher_id) REFERENCES teacher (id) on delete cascade
);

CREATE TABLE sysmessage(
    id          int not null auto_increment,
    title       varchar(50) NOT NULL,
    type		int not null,
    student_id 	varchar(12) NOT NULL,
    is_read 	boolean NOT NULL default 0,
    time        datetime not null,
    PRIMARY KEY (id),
    FOREIGN KEY (student_id) REFERENCES student (id) on delete cascade
);

-- auth:
-- 0:学生
-- 1:老师
-- 2:专业管理人
CREATE TABLE wechatusers 
(
	wechat_id 	varchar(20),
    id 			varchar(50) NOT NULL,
    auth 		int NOT NULL,
    PRIMARY KEY (wechat_id)
);

CREATE TABLE principleusers 
(
	teacher_id	varchar(50),
    password    varchar(50),
    department	varchar(50),
    name		varchar(50),
    PRIMARY KEY (teacher_id)
);


-- state:
-- 0:开题报告
-- 1:第一阶段检查
-- 2:中期检查
-- 3:答辩
-- 4:论文定稿
CREATE TABLE project
(
	id 			varchar(12) NOT NULL,
	project_name varchar(255),
    teacher_id 	varchar(50) NOT NULL,
    state 		int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES student(id) on delete cascade,
    FOREIGN KEY (teacher_id) REFERENCES teacher(id) on delete cascade
);

-- submit:
-- 0:未提交
-- 1:审核中
-- 2:审核通过
-- 3:审核未通过
CREATE TABLE state
(
	id     		int NOT NULL auto_increment,
	project_id 	varchar(12) NOT NULL,
    state 		int NOT NULL,
    submit 		int NOT NULL,
    start_time 	datetime,
    end_time	datetime,
    PRIMARY KEY (id),
    FOREIGN KEY (project_id) REFERENCES project(id) on delete cascade
);

CREATE TABLE grade
(
	id				varchar(12) NOT NULL,
	teacher_grade 	varchar(5),
    reply_grade		varchar(5),
    total_grade		varchar(5),
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES student(id) on delete cascade
);
    
create table teachermessagereading
(
    id         int auto_increment
        primary key,
    message_id int                  not null,
    student_id varchar(12)          not null,
    is_read    tinyint(1) default 0 not null,
  foreign key (student_id) references student (id)
            on delete cascade,
 foreign key (message_id) references teachermessage (id)
            on delete cascade
);
    
    