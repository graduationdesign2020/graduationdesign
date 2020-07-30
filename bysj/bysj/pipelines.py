# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
import pymysql
import pymongo


class BYSJPipeline(object):
    def __init__(self):
        # connection database
        self.connect = pymysql.connect(host='localhost', user='root', passwd='graduationdesign',
                                       db='GDMS')
        # get cursor
        self.cursor = self.connect.cursor()

        self.client = pymongo.MongoClient(host='localhost', port=27017)
        self.db = self.client["GDMS"]  # 获得数据库的句柄
        self.coll_dept = self.db["deptnoticecontent"]  # 获得collection的句柄
        self.coll_school = self.db["schoolnoticecontent"]

    def process_item(self, item, spider):
        if not self.cursor or not item:
            return
        if item["item_type"] == 'student':
            insert_student = """insert into student(id, name, major, department) VALUES (%s,%s,%s,%s)"""
            insert_user = """insert into wechatusers(wechat_id, id, auth) VALUES (%s,%s,%s)"""

            try:
                self.cursor.execute(insert_student, (str(int(item['id']) + 300218425679), '学生', '软件工程', '电子信息与电气工程学院'))
                self.cursor.execute(insert_student, (str(int(item['id']) + 301218425679), '学生', 'IEEE', '电子信息与电气工程学院'))
                self.cursor.execute(insert_student,
                                    (str(int(item['id']) + 302218425679), '学生', '测控技术与仪器', '电子信息与电气工程学院'))
                self.cursor.execute(insert_student, (str(int(item['id']) + 303218425679), '学生', '电气工程', '电子信息与电气工程学院'))
                self.cursor.execute(insert_student, (str(int(item['id']) + 304218425679), '学生', '自动化', '电子信息与电气工程学院'))
                self.cursor.execute(insert_student,
                                    (str(int(item['id']) + 305218425679), '学生', '计算机科学与工程', '电子信息与电气工程学院'))
                self.cursor.execute(insert_student, (str(int(item['id']) + 306218425679), '学生', '电子工程', '电子信息与电气工程学院'))
                self.cursor.execute(insert_student, (str(int(item['id']) + 307218425679), '学生', '信息工程', '电子信息与电气工程学院'))
                self.cursor.execute(insert_student, (str(int(item['id']) + 308218425679), '学生', '信息安全', '电子信息与电气工程学院'))
                self.cursor.execute(insert_student, (str(int(item['id']) + 309218425679), '学生', '微电子', '电子信息与电气工程学院'))
                self.cursor.execute(insert_student, (str(int(item['id']) + 310218425679), '学生', '电气信息', '电子信息与电气工程学院'))
                self.cursor.execute(insert_student, (str(int(item['id']) + 311218425679), '学生', '人工智能', '电子信息与电气工程学院'))
                self.cursor.execute(insert_user, (
                    str(int(item['id']) + 300218425679), str(int(item['id']) + 300218425679), 'ROLE_STUDENT'))
                self.cursor.execute(insert_user, (
                    str(int(item['id']) + 301218425679), str(int(item['id']) + 301218425679), 'ROLE_STUDENT'))
                self.cursor.execute(insert_user, (
                    str(int(item['id']) + 302218425679), str(int(item['id']) + 302218425679), 'ROLE_STUDENT'))
                self.cursor.execute(insert_user, (
                    str(int(item['id']) + 303218425679), str(int(item['id']) + 303218425679), 'ROLE_STUDENT'))
                self.cursor.execute(insert_user, (
                    str(int(item['id']) + 304218425679), str(int(item['id']) + 304218425679), 'ROLE_STUDENT'))
                self.cursor.execute(insert_user, (
                    str(int(item['id']) + 305218425679), str(int(item['id']) + 305218425679), 'ROLE_STUDENT'))
                self.cursor.execute(insert_user, (
                    str(int(item['id']) + 306218425679), str(int(item['id']) + 306218425679), 'ROLE_STUDENT'))
                self.cursor.execute(insert_user, (
                    str(int(item['id']) + 307218425679), str(int(item['id']) + 307218425679), 'ROLE_STUDENT'))
                self.cursor.execute(insert_user, (
                    str(int(item['id']) + 308218425679), str(int(item['id']) + 308218425679), 'ROLE_STUDENT'))
                self.cursor.execute(insert_user, (
                    str(int(item['id']) + 309218425679), str(int(item['id']) + 309218425679), 'ROLE_STUDENT'))
                self.cursor.execute(insert_user, (
                    str(int(item['id']) + 310218425679), str(int(item['id']) + 310218425679), 'ROLE_STUDENT'))
                self.cursor.execute(insert_user, (
                    str(int(item['id']) + 311218425679), str(int(item['id']) + 311218425679), 'ROLE_STUDENT'))
                self.connect.commit()
            except:
                self.connect.rollback()

            return item

        if item["item_type"] == 'grade':
            # sql语句
            insert_sql = """
            insert into grade(id, teacher_grade, review_grade, defense_grade, total_grade) VALUES (%s,%s,%s,%s,%s)
            """

            try:
                self.cursor.execute(insert_sql, (
                    str(int(item['id']) + 300218425679), item['teacher_grade'], item['review_grade'],
                    item['defense_grade'],
                    item['total_grade']))
                self.cursor.execute(insert_sql, (
                    str(int(item['id']) + 301218425679), item['teacher_grade'], item['review_grade'],
                    item['defense_grade'],
                    item['total_grade']))
                self.cursor.execute(insert_sql, (
                    str(int(item['id']) + 302218425679), item['teacher_grade'], item['review_grade'],
                    item['defense_grade'],
                    item['total_grade']))
                self.cursor.execute(insert_sql, (
                    str(int(item['id']) + 303218425679), item['teacher_grade'], item['review_grade'],
                    item['defense_grade'],
                    item['total_grade']))
                self.cursor.execute(insert_sql, (
                    str(int(item['id']) + 304218425679), item['teacher_grade'], item['review_grade'],
                    item['defense_grade'],
                    item['total_grade']))
                self.cursor.execute(insert_sql, (
                    str(int(item['id']) + 305218425679), item['teacher_grade'], item['review_grade'],
                    item['defense_grade'],
                    item['total_grade']))
                self.cursor.execute(insert_sql, (
                    str(int(item['id']) + 306218425679), item['teacher_grade'], item['review_grade'],
                    item['defense_grade'],
                    item['total_grade']))
                self.cursor.execute(insert_sql, (
                    str(int(item['id']) + 307218425679), item['teacher_grade'], item['review_grade'],
                    item['defense_grade'],
                    item['total_grade']))
                self.cursor.execute(insert_sql, (
                    str(int(item['id']) + 308218425679), item['teacher_grade'], item['review_grade'],
                    item['defense_grade'],
                    item['total_grade']))
                self.cursor.execute(insert_sql, (
                    str(int(item['id']) + 309218425679), item['teacher_grade'], item['review_grade'],
                    item['defense_grade'],
                    item['total_grade']))
                self.cursor.execute(insert_sql, (
                    str(int(item['id']) + 310218425679), item['teacher_grade'], item['review_grade'],
                    item['defense_grade'],
                    item['total_grade']))
                self.cursor.execute(insert_sql, (
                    str(int(item['id']) + 311218425679), item['teacher_grade'], item['review_grade'],
                    item['defense_grade'],
                    item['total_grade']))
                self.connect.commit()
            except:
                self.connect.rollback()

            return item

        if item["item_type"] == 'state':
            sql = "INSERT INTO teacher (id, name, department, major) SELECT %s, %s, %s, %s FROM DUAL WHERE NOT EXISTS ( SELECT * FROM teacher WHERE id = %s)"
            insert_user = """insert into wechatusers(wechat_id, id, auth) SELECT %s, %s, %s FROM DUAL WHERE NOT EXISTS ( SELECT * FROM wechatusers WHERE wechat_id = %s)"""
            try:
                # 执行sql语句
                self.cursor.execute(sql, (
                    item['teacher_id']+'a', item['teacher_name'], "电子信息与电气工程学院", "软件工程", item['teacher_id']+'a'))
                self.cursor.execute(sql, (
                    item['teacher_id'] + 'b', item['teacher_name'], "电子信息与电气工程学院", "IEEE", item['teacher_id'] + 'b'))
                self.cursor.execute(sql, (
                    item['teacher_id'] + 'c', item['teacher_name'], "电子信息与电气工程学院", "测控技术与仪器", item['teacher_id'] + 'c'))
                self.cursor.execute(sql, (
                    item['teacher_id'] + 'd', item['teacher_name'], "电子信息与电气工程学院", "电气工程", item['teacher_id'] + 'd'))
                self.cursor.execute(sql, (
                    item['teacher_id'] + 'e', item['teacher_name'], "电子信息与电气工程学院", "自动化", item['teacher_id'] + 'e'))
                self.cursor.execute(sql, (
                    item['teacher_id'] + 'f', item['teacher_name'], "电子信息与电气工程学院", "计算机科学与工程", item['teacher_id'] + 'f'))
                self.cursor.execute(sql, (
                    item['teacher_id'] + 'g', item['teacher_name'], "电子信息与电气工程学院", "电子工程", item['teacher_id'] + 'g'))
                self.cursor.execute(sql, (
                    item['teacher_id'] + 'h', item['teacher_name'], "电子信息与电气工程学院", "信息工程", item['teacher_id'] + 'h'))
                self.cursor.execute(sql, (
                    item['teacher_id'] + 'i', item['teacher_name'], "电子信息与电气工程学院", "信息安全", item['teacher_id'] + 'i'))
                self.cursor.execute(sql, (
                    item['teacher_id'] + 'j', item['teacher_name'], "电子信息与电气工程学院", "微电子", item['teacher_id'] + 'j'))
                self.cursor.execute(sql, (
                    item['teacher_id'] + 'k', item['teacher_name'], "电子信息与电气工程学院", "电气信息", item['teacher_id'] + 'k'))
                self.cursor.execute(sql, (
                    item['teacher_id'] + 'l', item['teacher_name'], "电子信息与电气工程学院", "人工智能", item['teacher_id'] + 'l'))
                self.cursor.execute(insert_user, (
                    item['teacher_id'] + 'a', item['teacher_id'] + 'a', 'ROLE_TEACHER', item['teacher_id'] + 'a'))
                self.cursor.execute(insert_user, (
                    item['teacher_id'] + 'b', item['teacher_id'] + 'b', 'ROLE_TEACHER', item['teacher_id'] + 'b'))
                self.cursor.execute(insert_user, (
                    item['teacher_id'] + 'c', item['teacher_id'] + 'c', 'ROLE_TEACHER', item['teacher_id'] + 'c'))
                self.cursor.execute(insert_user, (
                    item['teacher_id'] + 'd', item['teacher_id'] + 'd', 'ROLE_TEACHER', item['teacher_id'] + 'd'))
                self.cursor.execute(insert_user, (
                    item['teacher_id'] + 'e', item['teacher_id'] + 'e', 'ROLE_TEACHER', item['teacher_id'] + 'e'))
                self.cursor.execute(insert_user, (
                    item['teacher_id'] + 'f', item['teacher_id'] + 'f', 'ROLE_TEACHER', item['teacher_id'] + 'f'))
                self.cursor.execute(insert_user, (
                    item['teacher_id'] + 'g', item['teacher_id'] + 'g', 'ROLE_TEACHER', item['teacher_id'] + 'g'))
                self.cursor.execute(insert_user, (
                    item['teacher_id'] + 'h', item['teacher_id'] + 'h', 'ROLE_TEACHER', item['teacher_id'] + 'h'))
                self.cursor.execute(insert_user, (
                    item['teacher_id'] + 'i', item['teacher_id'] + 'i', 'ROLE_TEACHER', item['teacher_id'] + 'i'))
                self.cursor.execute(insert_user, (
                    item['teacher_id'] + 'j', item['teacher_id'] + 'j', 'ROLE_TEACHER', item['teacher_id'] + 'j'))
                self.cursor.execute(insert_user, (
                    item['teacher_id'] + 'k', item['teacher_id'] + 'k', 'ROLE_TEACHER', item['teacher_id'] + 'k'))
                self.cursor.execute(insert_user, (
                    item['teacher_id'] + 'l', item['teacher_id'] + 'l', 'ROLE_TEACHER', item['teacher_id'] + 'l'))
                self.connect.commit()
            except:
                self.connect.rollback()

            sql = "INSERT INTO project(id, project_name, teacher_id) SELECT %s, %s, %s FROM DUAL WHERE NOT EXISTS ( SELECT * FROM project WHERE id = %s)"

            try:
                # 执行sql语句
                self.cursor.execute(sql, (
                    str(int(item['project_id']) + 300218425679), '项目名称', item['teacher_id']+'a',
                    str(int(item['project_id']) + 300218425679)))
                self.cursor.execute(sql, (
                    str(int(item['project_id']) + 301218425679), '项目名称', item['teacher_id'] + 'b',
                    str(int(item['project_id']) + 301218425679)))
                self.cursor.execute(sql, (
                    str(int(item['project_id']) + 302218425679), '项目名称', item['teacher_id'] + 'c',
                    str(int(item['project_id']) + 302218425679)))
                self.cursor.execute(sql, (
                    str(int(item['project_id']) + 303218425679), '项目名称', item['teacher_id'] + 'd',
                    str(int(item['project_id']) + 303218425679)))
                self.cursor.execute(sql, (
                    str(int(item['project_id']) + 304218425679), '项目名称', item['teacher_id'] + 'e',
                    str(int(item['project_id']) + 304218425679)))
                self.cursor.execute(sql, (
                    str(int(item['project_id']) + 305218425679), '项目名称', item['teacher_id'] + 'f',
                    str(int(item['project_id']) + 305218425679)))
                self.cursor.execute(sql, (
                    str(int(item['project_id']) + 306218425679), '项目名称', item['teacher_id'] + 'g',
                    str(int(item['project_id']) + 306218425679)))
                self.cursor.execute(sql, (
                    str(int(item['project_id']) + 307218425679), '项目名称', item['teacher_id'] + 'h',
                    str(int(item['project_id']) + 307218425679)))
                self.cursor.execute(sql, (
                    str(int(item['project_id']) + 308218425679), '项目名称', item['teacher_id'] + 'i',
                    str(int(item['project_id']) + 308218425679)))
                self.cursor.execute(sql, (
                    str(int(item['project_id']) + 309218425679), '项目名称', item['teacher_id'] + 'j',
                    str(int(item['project_id']) + 309218425679)))
                self.cursor.execute(sql, (
                    str(int(item['project_id']) + 310218425679), '项目名称', item['teacher_id'] + 'k',
                    str(int(item['project_id']) + 310218425679)))
                self.cursor.execute(sql, (
                    str(int(item['project_id']) + 311218425679), '项目名称', item['teacher_id'] + 'l',
                    str(int(item['project_id']) + 311218425679)))
                self.connect.commit()
            except:
                self.connect.rollback()

            insert_sql = """
            insert into state(project_id, state, submit) VALUES (%s,%s,%s)
            """
            try:
                self.cursor.execute(insert_sql,
                                    (str(int(item['project_id']) + 300218425679), item['state'], item['submit']))
                self.cursor.execute(insert_sql,
                                    (str(int(item['project_id']) + 301218425679), item['state'], item['submit']))
                self.cursor.execute(insert_sql,
                                    (str(int(item['project_id']) + 302218425679), item['state'], item['submit']))
                self.cursor.execute(insert_sql,
                                    (str(int(item['project_id']) + 303218425679), item['state'], item['submit']))
                self.cursor.execute(insert_sql,
                                    (str(int(item['project_id']) + 304218425679), item['state'], item['submit']))
                self.cursor.execute(insert_sql,
                                    (str(int(item['project_id']) + 305218425679), item['state'], item['submit']))
                self.cursor.execute(insert_sql,
                                    (str(int(item['project_id']) + 306218425679), item['state'], item['submit']))
                self.cursor.execute(insert_sql,
                                    (str(int(item['project_id']) + 307218425679), item['state'], item['submit']))
                self.cursor.execute(insert_sql,
                                    (str(int(item['project_id']) + 308218425679), item['state'], item['submit']))
                self.cursor.execute(insert_sql,
                                    (str(int(item['project_id']) + 309218425679), item['state'], item['submit']))
                self.cursor.execute(insert_sql,
                                    (str(int(item['project_id']) + 310218425679), item['state'], item['submit']))
                self.cursor.execute(insert_sql,
                                    (str(int(item['project_id']) + 311218425679), item['state'], item['submit']))
                self.connect.commit()
            except:
                self.connect.rollback()

            return item
        if item["item_type"] == 'notice':
            if item["notice_type"] == 'dept':
                try:
                    self.cursor.execute(
                        """insert into deptnotice(department, title, time)
                        value (%s, %s, %s)""",
                        ('电子信息与电气工程学院', item['title'], item['time'])
                    )
                    self.connect.commit()
                    self.cursor.execute("""select last_insert_id() from deptnotice""")
                    result = self.cursor.fetchone()
                    self.coll_dept.insert_one({"_id": result[0], "content": item['content']})
                except:
                    self.connect.rollback()
                return item
            if item["notice_type"] == 'school':
                try:
                    self.cursor.execute(
                        """insert into schoolnotice(title, time)
                        value (%s, %s)""",
                        (item['title'], item['time'])
                    )
                    self.connect.commit()
                    self.cursor.execute("""select last_insert_id() from schoolnotice""")
                    result = self.cursor.fetchone()
                    self.coll_school.insert_one({"_id": result[0], "content": item['content']})
                except:
                    self.connect.rollback()
            return item

    def close_spider(self, spider):
        if self.client:
            self.client.close()
        if self.connect:
            self.connect.close()
        if self.cursor:
            self.cursor.close()
