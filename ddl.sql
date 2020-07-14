DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS notice;
DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS reading;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS state;

CREATE TABLE teacher(
    id          varchar(10) NOT NULL,
    name        varchar(50) NOT NULL,
    major       varchar(50),
    PRIMARY KEY (id)
);

CREATE TABLE student(
    id          varchar(12) NOT NULL,
    name        varchar(50) NOT NULL,
    major       varchar(50) NOT NULL,
	project_name varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE notice(
    id          int not null auto_increment,
    title       varchar(50) NOT NULL,
    time        datetime not null,
    top         tinyint(1) not null,
    read_number	int not null,
    PRIMARY KEY (id)
);

CREATE TABLE message(
    id          int not null auto_increment,
    title       varchar(50) NOT NULL,
    teacher_id  varchar(5) NOT NULL,
    time        datetime not null DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (teacher_id) REFERENCES teacher (id) on delete cascade
);

-- auth:
-- 0:学生
-- 1:老师
-- 2:专业管理人
CREATE TABLE users 
(
	wechat_id 	varchar(20),
    student_id 	varchar(12) NOT NULL UNIQUE,
    auth 		int NOT NULL,
    PRIMARY KEY (wechat_id),
    FOREIGN KEY (student_id) REFERENCES student(id) on delete cascade
);

CREATE TABLE reading 
(
	message_id 	int,
    student_id 	varchar(12) NOT NULL,
    is_read 	tinyint(1) NOT NULL default 0,
    PRIMARY KEY (message_id,student_id),
    FOREIGN KEY (student_id) REFERENCES student(id) on delete cascade,
    FOREIGN KEY (message_id) REFERENCES message(id) on delete cascade
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
    teacher_id 	varchar(10) NOT NULL,
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
	project_id 	varchar(12) NOT NULL,
    state 		int NOT NULL,
    submit 		int NOT NULL,
    start_time 	datetime,
    end_time	datetime,
    place 		varchar(255),
    grade 		varchar(2),
    PRIMARY KEY (project_id,state),
    FOREIGN KEY (project_id) REFERENCES project(id) on delete cascade
);
    
    