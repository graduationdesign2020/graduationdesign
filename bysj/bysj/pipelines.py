# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
import pymysql


class BYSJPipeline(object):
    def __init__(self):
        # connection database
        self.connect = pymysql.connect(host='100.25.196.48', user='root', passwd='graduationdesign',
                                       db='GDMS')
        # get cursor
        self.cursor = self.connect.cursor()
        print("连接数据库成功")

    def process_item(self, item, spider):
        if spider.name == 'student':
            # sql语句
            insert_sql = """
            insert into student(id, name, major, department) VALUES (%s,%s,%s,%s)
            """
            # 执行插入数据到数据库操作
            self.cursor.execute(insert_sql, (item['id'], item['name'], item['major'], item['department']))
            # 提交，不进行提交无法保存到数据库
            self.connect.commit()

        if spider.name == 'grade':
            # sql语句
            insert_sql = """
            insert into grade(id, teacher_grade, review_grade, defense_grade, total_grade) VALUES (%s,%s,%s,%s,%s)
            """
            # 执行插入数据到数据库操作
            self.cursor.execute(insert_sql, (item['id'], item['teacher_grade'], item['review_grade'],
                                             item['defense_grade'], item['total_grade']))
            # 提交，不进行提交无法保存到数据库
            self.connect.commit()

        if spider.name in ['state3', 'state4']:
            # sql语句
            insert_sql = """
            insert into state(project_id, state, submit) VALUES (%s,%s,%s)
            """
            # 执行插入数据到数据库操作
            self.cursor.execute(insert_sql, (item['project_id'], item['state'], item['submit']))
            # 提交，不进行提交无法保存到数据库
            self.connect.commit()

    def close_spider(self, spider):
        # 关闭游标和连接
        self.cursor.close()
        self.connect.close()