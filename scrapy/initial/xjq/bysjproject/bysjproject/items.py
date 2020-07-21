# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class BysjprojectItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    projectname = scrapy.Field()
    teachername = scrapy.Field()
    teachernum = scrapy.Field()
    studentnum = scrapy.Field()
    pass
