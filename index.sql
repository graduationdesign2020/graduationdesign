CREATE unique INDEX check_end_time ON schedulejob (teacher_id,state);
CREATE unique INDEX check_state ON state (project_id,state);
CREATE unique INDEX check_user ON wechatusers (id,auth);

CREATE INDEX check_department ON deptnotice (department);
CREATE INDEX check_read ON teachermessagereading (message_id,is_read);

CREATE INDEX check_dept_time ON deptnotice (time desc);
CREATE INDEX check_school_time ON schoolnotice (time desc);
CREATE INDEX check_teacher_time ON teachermessage (time desc);