# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html


# useful for handling different item types with a single interface
from itemadapter import ItemAdapter
import pymysql

class BysjstatePipeline:
    def __init__(self):
        self.connect = pymysql.connect(
            host='100.25.196.48',  # 数据库地址
            port=3306,  # 数据库端口
            db='GDMS',  # 数据库名
            user='root',  # 数据库用户名
            passwd='graduationdesign',  # 数据库密码
            charset='utf8',  # 编码方式
            use_unicode=True)
        self.cursor = self.connect.cursor()

    def process_item(self, item, spider):
        if not self.cursor or not item:
            return
        sql = "INSERT INTO state(project_id, state, submit) values (%s, %s, %s)"
        # self.cursor.execute(sql, (item['id'], item['state'], item['submit']))
        self.connect.commit()
        try:
            self.cursor.execute(sql, (item['id'], item['state'], item['submit']))
            self.connect.commit()
        except:
            self.connect.rollback()

        return item

    def __del__(self):
        if self.connect:
            self.connect.close()
        if self.cursor:
            self.cursor.close()