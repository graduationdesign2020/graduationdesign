use GDMS;
set foreign_key_checks=0;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS schoolnotice;
DROP TABLE IF EXISTS deptnotice;
DROP TABLE IF EXISTS teachermessage;
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
    id          varchar(25) NOT NULL,
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
-- 0:任务书
-- 1:开题报告
-- 2:中期检查
-- 3:论文定稿
-- 4:论文最终稿
CREATE TABLE project
(
	id 			varchar(25) NOT NULL,
	project_name varchar(255),
    teacher_id 	varchar(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES student(id) on delete cascade,
    FOREIGN KEY (teacher_id) REFERENCES teacher(id) on delete cascade
);

-- submit:
-- submit取值从1开始，对应关系详见bysj网站，从统计信息的第三栏开始递增，每个state对应不一样的submit
-- 论文定稿、论文最终稿:
-- 0:未开始
-- 1:未提交论文
-- 2:学生暂存
-- 3:教师审核中
-- 4:教师退回修改	
-- 5:已审
CREATE TABLE state
(
	id     		int NOT NULL auto_increment,
	project_id 	varchar(25) NOT NULL,
    state 		int NOT NULL,
    submit 		int NOT NULL,
    start_time 	datetime,
    end_time	datetime,
    PRIMARY KEY (id),
    FOREIGN KEY (project_id) REFERENCES project(id) on delete cascade
);

CREATE TABLE grade
(
	id				varchar(25) NOT NULL,
	teacher_grade 	varchar(2),  /* 教师 */
    review_grade	varchar(2),  /* 评阅 */
    defense_grade	varchar(2),  /* 答辩 */
    total_grade		varchar(2),  /* 总评 */
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES student(id) on delete cascade
);
    
create table teachermessagereading
(
    id         int auto_increment
        primary key,
    message_id int                  not null,
    student_id varchar(25)          not null,
    is_read    tinyint(1) default 0 not null,
  foreign key (student_id) references student (id)
            on delete cascade,
 foreign key (message_id) references teachermessage (id)
            on delete cascade
);
    
    
