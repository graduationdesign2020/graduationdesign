# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
import pymysql
import base64
import pymongo


class BYSJPipeline(object):
    def __init__(self):
        # connection database
        self.connect = pymysql.connect(host='100.25.196.48', user='root', passwd='graduationdesign',
                                       db='GDMS')
        # get cursor
        self.cursor = self.connect.cursor()

        self.client = pymongo.MongoClient(host='100.25.196.48',port=27017)
        self.db = self.client["GDMS"]  # 获得数据库的句柄
        self.coll_dept = self.db["deptnoticecontent"]  # 获得collection的句柄
        self.coll_school = self.db["schoolnoticecontent"]

    def process_item(self, item, spider):
        if not self.cursor or not item:
            return
        if item["item_type"] == 'student':
            insert_sql = """insert into student(id, name, major, department) VALUES (%s,%s,%s,%s)"""

            try:
                self.cursor.execute(insert_sql, (base64.encodebytes(item['id'].encode()), '学生', item['major'], item['department']))
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
                self.cursor.execute(insert_sql,
                                    (base64.encodebytes(item['id'].encode()), item['teacher_grade'],
                                     item['review_grade'],
                                     item['defense_grade'], item['total_grade']))
                self.connect.commit()
            except:
                self.connect.rollback()

            return item

        if item["item_type"] == 'state':
            sql = "INSERT INTO teacher (id, name, department, major) SELECT %s, %s, %s, %s FROM DUAL WHERE NOT EXISTS ( SELECT * FROM teacher WHERE id = %s)"
            try:
                # 执行sql语句
                self.cursor.execute(sql, (
                item['teacher_id'], item['teacher_name'], "电子信息与电气工程学院", "软件工程", item['teacher_id']))
                # 执行sql语句
                self.connect.commit()
            except:
                self.connect.rollback()

            sql = "INSERT INTO project(id, project_name, teacher_id) SELECT %s, %s, %s FROM DUAL WHERE NOT EXISTS ( SELECT * FROM project WHERE id = %s)"

            try:
                # 执行sql语句
                self.cursor.execute(sql, (
                base64.encodebytes(item['project_id'].encode()), '项目名称', item['teacher_id'], base64.encodebytes(item['project_id'].encode())))
                # 执行sql语句
                self.connect.commit()
            except:
                self.connect.rollback()

            insert_sql = """
            insert into state(project_id, state, submit) VALUES (%s,%s,%s)
            """
            try:
                self.cursor.execute(insert_sql, (base64.encodebytes(item['project_id'].encode()), item['state'], item['submit']))
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
                        ('电院', item['title'], item['time'])
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