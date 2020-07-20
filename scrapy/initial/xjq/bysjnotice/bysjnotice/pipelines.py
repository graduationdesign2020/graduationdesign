# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html


# useful for handling different item types with a single interface
from itemadapter import ItemAdapter
import pymongo
import pymysql.cursors

from scrapy.utils.project import get_project_settings


class BysjnoticePipeline:
    def __init__(self):
        settings = get_project_settings()
        self.client = pymongo.MongoClient(host=settings.get('MONGO_HOST'), port=settings.get('MONGO_PORT'))
        self.db = self.client[settings.get('MONGO_DB')]  # 获得数据库的句柄
        self.coll = self.db[settings.get('MONGO_COLL')]  # 获得collection的句柄
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
        if not self.client or not item:
            return
        self.cursor.execute(
            """insert into deptnotice(department, title, time)
            value (%s, %s, %s)""",
            ('电院', item['title'], item['time'])
        )
        self.connect.commit()
        self.cursor.execute("""select last_insert_id() from deptnotice""")
        result = self.cursor.fetchone()
        self.coll.insert_one({"_id": result[0], "content": item['content']})
        return item

    def __del__(self):
        if self.client:
            self.client.close()
        if self.connect:
            self.connect.close()
        if self.cursor:
            self.cursor.close()
