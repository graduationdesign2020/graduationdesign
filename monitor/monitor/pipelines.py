# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
import pymysql
import pymongo


class MonitorPipeline(object):
    def __init__(self):
        # connection database
        self.connect = pymysql.connect(host='100.25.196.48', user='root', passwd='graduationdesign',
                                       db='GDMS')
        # get cursor
        self.cursor = self.connect.cursor()

        self.client = pymongo.MongoClient(host='100.25.196.48', port=27017)
        self.db = self.client["GDMS"]  # 获得数据库的句柄
        self.coll_dept = self.db["deptnoticecontent"]  # 获得collection的句柄
        self.coll_school = self.db["schoolnoticecontent"]

    def process_item(self, item, spider):
        if not self.cursor or not item:
            return
        if item["item_type"] == 'student':
            query_sql = """select * from student where id=%s and name=%s and major=%s and department=%s"""
            if self.cursor.execute(query_sql,
                                   (str(int(item['id']) + 304718425679), '学生', item['major'], item['department'])) == 0:
                query_sql = """select * from student where id=%s"""
                if self.cursor.execute(query_sql, (str(int(item['id']) + 304718425679))) == 0:
                    print('insert student')
                    insert_sql = """insert into student(id, name, major, department) VALUES (%s,%s,%s,%s)"""
                    try:
                        self.cursor.execute(insert_sql,
                                            (str(int(item['id']) + 304718425679), '学生', item['major'], item['department']))
                        self.connect.commit()
                    except:
                        self.connect.rollback()
                else:
                    print('update student')
                    update_sql = """update student set name=%s, major=%s, department=%s where id=%s"""
                    try:
                        self.cursor.execute(update_sql,
                                            ('学生', item['major'], item['department'], str(int(item['id']) + 304718425679)))
                        self.connect.commit()
                    except:
                        self.connect.rollback()
            else:
                print('right student')

            return item

        if item["item_type"] == 'grade':
            query_sql = """select * from grade 
            where id=%s and teacher_grade=%s and review_grade=%s and defense_grade=%s and total_grade=%s"""
            if self.cursor.execute(query_sql, (str(int(item['id']) + 304718425679), item['teacher_grade'],
                                               item['review_grade'], item['defense_grade'], item['total_grade'])) == 0:
                query_sql = """select * from grade where id=%s"""
                if self.cursor.execute(query_sql, (str(int(item['id']) + 304718425679))) == 0:
                    print('insert grade')
                    insert_sql = """insert into grade(id, teacher_grade, review_grade, defense_grade, total_grade) 
                    VALUES (%s,%s,%s,%s,%s)"""
                    try:
                        self.cursor.execute(insert_sql,
                                            (str(int(item['id']) + 304718425679), item['teacher_grade'],
                                             item['review_grade'], item['defense_grade'], item['total_grade']))
                        self.connect.commit()
                    except:
                        self.connect.rollback()
                else:
                    print('update grade')
                    update_sql = """update grade 
                    set teacher_grade=%s, review_grade=%s, defense_grade=%s, total_grade=%s where id=%s"""
                    try:
                        self.cursor.execute(update_sql,
                                            (item['teacher_grade'], item['review_grade'], item['defense_grade'],
                                             item['total_grade'], str(int(item['id']) + 304718425679)))
                        self.connect.commit()
                    except:
                        self.connect.rollback()
            else:
                print('right grade')

            return item

        if item["item_type"] == 'state':
            # sql = "INSERT INTO teacher (id, name, department, major) SELECT %s, %s, %s, %s FROM DUAL WHERE NOT EXISTS ( SELECT * FROM teacher WHERE id = %s)"
            # try:
            #     # 执行sql语句
            #     self.cursor.execute(sql, (
            #     item['teacher_id'], item['teacher_name'], "电子信息与电气工程学院", "软件工程", item['teacher_id']))
            #     # 执行sql语句
            #     self.connect.commit()
            # except:
            #     self.connect.rollback()

            query_sql = """select * from project where id=%s and project_name=%s and teacher_id=%s"""
            if self.cursor.execute(query_sql,
                                   (str(int(item['project_id']) + 304718425679), '项目名称', item['teacher_id'])) == 0:
                query_sql = """select * from project where id=%s"""
                if self.cursor.execute(query_sql, (str(int(item['project_id']) + 304718425679))) == 0:
                    print('insert project')
                    insert_sql = """insert into project(id, project_name, teacher_id) VALUES (%s,%s,%s)"""
                    try:
                        self.cursor.execute(insert_sql,
                                            (str(int(item['project_id']) + 304718425679), '项目名称', item['teacher_id']))
                        self.connect.commit()
                    except:
                        self.connect.rollback()
                else:
                    print('update project')
                    update_sql = """update project set project_name=%s, teacher_id=%s where id=%s"""
                    try:
                        self.cursor.execute(update_sql,
                                            ('项目名称', item['teacher_id'], str(int(item['project_id']) + 304718425679)))
                        self.connect.commit()
                    except:
                        self.connect.rollback()
            else:
                print('right project')

            query_sql = """select * from state where project_id=%s and state=%s and submit=%s"""
            if self.cursor.execute(query_sql,
                                   (str(int(item['project_id']) + 304718425679), item['state'], item['submit'])) == 0:
                query_sql = """select * from state where project_id=%s and state=%s"""
                if self.cursor.execute(query_sql, (str(int(item['project_id']) + 304718425679), item['state'])) == 0:
                    print('insert state')
                    insert_sql = """insert into state(project_id, state, submit) VALUES (%s,%s,%s)"""
                    try:
                        self.cursor.execute(insert_sql,
                                            (str(int(item['project_id']) + 304718425679), item['state'], item['submit']))
                        self.connect.commit()
                    except:
                        self.connect.rollback()
                else:
                    print('update state')
                    update_sql = """update state set submit=%s where project_id=%s and state=%s"""
                    try:
                        self.cursor.execute(update_sql,
                                            (item['submit'], str(int(item['project_id']) + 304718425679), item['state']))
                        self.connect.commit()
                    except:
                        self.connect.rollback()
            else:
                print('right state')

            return item
        if item["item_type"] == 'notice':
            if not self.client or not item:
                return
            if item["notice_type"] == 'dept':
                query_sql = """select * from deptnotice where department=%s and title=%s and time=%s"""
                if self.cursor.execute(query_sql, ('电子信息与电气工程学院', item['title'], item['time'])) == 0:
                    print('insert notice')
                    try:
                        self.cursor.execute(
                            """insert into deptnotice(department, title, time) value (%s, %s, %s)""",
                            ('电子信息与电气工程学院', item['title'], item['time'])
                        )
                        self.cursor.execute("""select last_insert_id() from deptnotice""")
                        result = self.cursor.fetchone()
                        self.coll_dept.insert_one({"_id": result[0], "content": item['content']})
                        self.connect.commit()
                    except:
                        self.connect.rollback()
                else:
                    print('exist notice')
                return item
            if item["notice_type"] == 'school':
                query_sql = """select * from schoolnotice where title=%s and time=%s"""
                if self.cursor.execute(query_sql, (item['title'], item['time'])) == 0:
                    print('insert notice')
                    try:
                        self.cursor.execute(
                            """insert into schoolnotice(title, time) value (%s, %s)""",
                            (item['title'], item['time'])
                        )
                        self.cursor.execute("""select last_insert_id() from schoolnotice""")
                        result = self.cursor.fetchone()
                        self.coll_school.insert_one({"_id": result[0], "content": item['content']})
                        self.connect.commit()
                    except:
                        self.connect.rollback()
                else:
                    print('exist notice')
                return item

    def close_spider(self, spider):
        if self.client:
            self.client.close()
        if self.connect:
            self.connect.close()
        if self.cursor:
            self.cursor.close()
