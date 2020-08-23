# -*- coding: utf-8 -*-
import scrapy

from ..items import GradeItem


class GradeSpider(scrapy.Spider):
    name = 'grade'
    allowed_domains = ['bysj.jwc.sjtu.edu.cn']
    start_urls = ['http://bysj.jwc.sjtu.edu.cn/index.aspx']

    def parse(self, response):
        return scrapy.FormRequest.from_response(
            response,
            formdata={"UserId": "10367",
                      "Pwd": "10367",
                      "LoginButton.x": "13",
                      "LoginButton.y": "6"},
            callback=self.precharacter
        )

    def precharacter(self, response):
        return scrapy.Request(
            url="http://bysj.jwc.sjtu.edu.cn/LoginRole.aspx?sid=Jk7UDmg/cSOg6wXocnYmog....&screen=C3R1RXfU/LYcx2ms2xw7Cg....",
            method='GET',
            callback=self.character,
        )

    def character(self, response):
        return scrapy.FormRequest.from_response(
            response,
            formdata={"ImageButton4.x": "43",
                      "ImageButton4.y": "22"},
            callback=self.prepage
        )

    def prepage(self, response):
        return scrapy.Request(
            url="http://bysj.jwc.sjtu.edu.cn/Main/Main.aspx?sid=Jk7UDmg/cSOg6wXocnYmog....&screen=Qd2HRMrs8oJGtA82doE7Jg....",
            method='GET',
            callback=self.parse_page,
        )

    def parse_page(self, response):
        href_list = response.xpath('//*[@id="divzy3"]/table').css('a::attr(href)').extract()

        return scrapy.Request(
            url=response.urljoin(href_list[9]),
            callback=self.parse_grade,
            method='POST'
        )

    def parse_grade(self, response):
        grade_list = response.xpath('//*[@id="gridview"]/tr[position()>1]')

        for grade in grade_list:
            teacher_grade = grade.xpath('./td[6]/a/text()').get()
            review_grade = grade.xpath('./td[7]/a/text()').get()
            defense_grade = grade.xpath('./td[8]/a/text()').get()
            total_grade = grade.xpath('./td[9]/a/text()').get()
            item = GradeItem()
            item['item_type'] = 'grade'
            item['id'] = grade.xpath('./td[5]/span/text()').re(r'\d+')[0]
            if teacher_grade is not None:
                item['teacher_grade'] = teacher_grade.strip()
            else:
                item['teacher_grade'] = ""
            if review_grade is not None:
                item['review_grade'] = review_grade.strip()
            else:
                item['review_grade'] = ""
            if defense_grade is not None:
                item['defense_grade'] = defense_grade.strip()
            else:
                item['defense_grade'] = ""
            if total_grade is not None:
                item['total_grade'] = total_grade.strip()
            else:
                item['total_grade'] = ""
            yield item

        current_page = response.css("span#LblCurrentIndex::text").re(r'\d+')
        total_page = response.css("span#LblPageCount::text").re(r'\d+')
        if current_page < total_page:
            url = "http://bysj.jwc.sjtu.edu.cn/Admin/StudentLunwenScore.aspx?sid=Jk7UDmg%2fcSOg6wXocnYmog....&screen=Qd2HRMrs8oJGtA82doE7Jg...."
            post_data = {
                "__EVENTTARGET": "btnNext",
                "__EVENTARGUMENT": "",
                "__LASTFOCUS": "",
                "__VIEWSTATE": response.css("input#__VIEWSTATE::attr(value)").get(),
                "__VIEWSTATEGENERATOR": response.css("input#__VIEWSTATEGENERATOR::attr(value)").get(),
                "__EVENTVALIDATION": response.css("input#__EVENTVALIDATION::attr(value)").get(),
                "__VIEWSTATEENCRYPTED": "",
                "UserName": "",
                "UserId": "",
                "ReportName": "",
                "ZhiDao": "",
                "ZhiDaoName": "",
                "SelXueYuan": "0",
                "SelZhuanYe": "0",
                "SelClass": "0",
                "ddlCurrentPage": current_page
            }
            yield scrapy.FormRequest(url=url,
                                     formdata=post_data,
                                     callback=self.parse_grade,
                                     method='POST'
                                     )
