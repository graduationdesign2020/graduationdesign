DROP TABLE IF EXISTS users;
CREATE TABLE users 
(
	wechat_id varchar(20),
    student_id varchar(12) NOT NULL UNIQUE,
    auth int NOT NULL,
    PRIMARY KEY (wechat_id),
    FOREIGN KEY (student_id) REFERENCES student(id)
);

DROP TABLE IF EXISTS reading_state;
CREATE TABLE reading_state 
(
	message_id int,
    student_id varchar(12) NOT NULL,
    is_read INT NOT NULL DEFAULT 0,
    PRIMARY KEY (message_id,student_id),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (message_id) REFERENCES message(id)
);

-- state:
-- 0:开题报告
-- 1:第一阶段检查
-- 2:中期检查
-- 3:答辩
-- 4:论文定稿
DROP TABLE IF EXISTS project;
CREATE TABLE project
(
	id varchar(12) NOT NULL,
    teacher_id varchar(5) NOT NULL,
    project_name varchar(255),
    state int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES student(id),
    FOREIGN KEY (teacher_id) REFERENCES teacher(id)
);

-- submit:
-- 0:未提交
-- 1:审核中
-- 2:审核通过
-- 3:审核未通过
DROP TABLE IF EXISTS state;
CREATE TABLE state
(
	project_id int NOT NULL,
    state int NOT NULL,
    submit int NOT NULL,
    time datetime,
    place varchar(255),
    grade varchar(2),
    PRIMARY KEY (project_id,state),
    FOREIGN KEY (project_id) REFERENCES project(id),
    FOREIGN KEY (state) REFERENCES project(state)
);
    
    