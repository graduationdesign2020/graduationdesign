# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class StateItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    item_type = scrapy.Field()
    project_id = scrapy.Field()
    teacher_id = scrapy.Field()
    teacher_name = scrapy.Field()
    state = scrapy.Field()
    submit = scrapy.Field()


class StudentItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    item_type = scrapy.Field()
    id = scrapy.Field()
    name = scrapy.Field()
    major = scrapy.Field()
    department = scrapy.Field()


class GradeItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    item_type = scrapy.Field()
    id = scrapy.Field()
    teacher_grade = scrapy.Field()
    review_grade = scrapy.Field()
    defense_grade = scrapy.Field()
    total_grade = scrapy.Field()

class NoticeItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    item_type = scrapy.Field()
    notice_type = scrapy.Field()
    title = scrapy.Field()
    time = scrapy.Field()
    content = scrapy.Field()