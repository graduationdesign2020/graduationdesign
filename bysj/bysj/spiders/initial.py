import scrapy
from scrapy.selector import Selector

import re

from bysj.items import StateItem
from bysj.items import StudentItem
from bysj.items import GradeItem
from bysj.items import NoticeItem
from urllib import parse

class InitialSpider(scrapy.Spider):
    name = 'initial'
    allowed_domains = ['bysj.jwc.sjtu.edu.cn']
    start_urls = ['http://bysj.jwc.sjtu.edu.cn/index.aspx']

    def parse(self, response):
        yield scrapy.FormRequest.from_response(
            response,
            formdata={"UserId": "10367",
                      "Pwd": "10367",
                      "LoginButton.x": "13",
                      "LoginButton.y": "6"},
            callback=self.precharacter
        )

        yield scrapy.Request(
            url='http://bysj.jwc.sjtu.edu.cn/MoreNews.aspx?NewsType=BzKecs5qaKUKFqqwy4K4nQ....&TypeName'
                  '=MTaBXFQRBQxURZo8Lzsr6A....',
            callback= self.prenotice,
            meta={'type': 'dept'}
        )

        yield scrapy.Request(
            url='http://bysj.jwc.sjtu.edu.cn/MoreNews.aspx?NewsType=RMpImViAIzgyF49O8gCVAA....&TypeName=rwU1vt1Ox9ucqrjQjx2qDQ....',
            callback=self.prenotice,
            meta={'type': 'school'}
        )

    def prenotice(self, response):
        notice_type = response.meta['type']
        url_list = response.css('#AllNews a::attr(href)').extract()

        for url in url_list:
            yield scrapy.Request(url=parse.urljoin(response.url, url), callback=self.parse_notice, meta={'type': notice_type})

    def parse_notice(self, response):
        notice_type = response.meta['type']
        sel = Selector(response)
        tr_sel = sel.xpath('//*[@id="form1"]/table[2]/tr/td/div/table/tr[3]/td')
        title_sel = sel.xpath('//*[@id="form1"]/table[2]/tr/td/div/table/tr[1]/td/p[1]/text()')

        item = NoticeItem()
        item['item_type'] = 'notice'
        item['notice_type'] = notice_type
        if len(title_sel.extract()) == 1:
            item['title'] = title_sel.extract()[0].strip()
        else:
            item['title'] = sel.xpath('//*[@id="form1"]/table[2]/tr/td/div/table/tr[1]/td/p[1]/font/text()').extract()[
                0].strip()
        string = sel.xpath('//*[@id="form1"]/table[2]/tr/td/div/table/tr[1]/td/p[2]/text()').extract()[0].strip()
        item['time'] = "".join(re.findall("\d+", string))
        item['content'] = "".join(tr_sel.css('*::text').extract()).strip()

        yield item

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

        yield scrapy.Request(
            url=parse.urljoin(response.url, href_list[0]),
            callback=self.pre_student,
            method='POST'
        )

        yield scrapy.Request(
            url=parse.urljoin(response.url, href_list[3]),
            callback=self.parse_form,
            meta={'state': '0'},
            method='POST'
        )

        yield scrapy.Request(
            url=parse.urljoin(response.url, href_list[4]),
            callback=self.parse_form,
            meta={'state': '1'},
            method='POST'
        )

        yield scrapy.Request(
            url=parse.urljoin(response.url, href_list[6]),
            callback=self.parse_form,
            meta={'state': '2'},
            method='POST'
        )

        yield scrapy.Request(
            url=parse.urljoin(response.url, href_list[7]),
            callback=self.parse_form,
            meta={'state': '3'},
            method='POST'
        )

        yield scrapy.Request(
            url=parse.urljoin(response.url, href_list[8]),
            callback=self.parse_form,
            meta={'state': '4'},
            method='POST'
        )

        yield scrapy.Request(
            url=parse.urljoin(response.url, href_list[9]),
            callback=self.parse_grade,
            method='POST'
        )


    def parse_form(self, response):
        state = response.meta['state']

        td_list = response.xpath('//*[@id="gridview"]/tr[2]/td').extract()
        for i in range(3, len(td_list) + 1):
            url = response.xpath('//*[@id="gridview"]/tr[2]/td[' + str(i) + ']').css('a::attr(href)').extract()[0]
            num = response.xpath('//*[@id="gridview"]/tr[2]/td[' + str(i) + ']').css('a::text').extract()[0].strip()
            if int(num) == 0:
                continue
            else:
                yield scrapy.Request(url=parse.urljoin(response.url, url),
                                     callback=self.parse_submit,
                                     meta={'state': state, 'submit': str(i - 2)},
                                     method='GET'
                                     )

    def parse_submit(self, response):
        state = response.meta['state']
        submit = response.meta['submit']
        list = response.xpath('//*[@id="gridview"]/tr[position()>1]')

        for listitem in list:
            item = StateItem()
            item['item_type'] = 'state'
            item['project_id'] = listitem.xpath('./td[4]').css('*::text').re(r'\d+')[0]
            item['teacher_id'] = listitem.xpath('./td[3]').css('*::text').re(r'[(](.*?)[)]')[0]
            item['teacher_name'] = listitem.xpath('./td[3]').css('*::text').extract()[0].strip()
            item['state'] = state
            item['submit'] = submit
            yield item

        current_page = response.css("span#LblCurrentIndex::text").re(r'\d+')
        total_page = response.css("span#LblPageCount::text").re(r'\d+')
        if int(current_page[0]) < int(total_page[0]):
            yield scrapy.FormRequest.from_response(
                response=response,
                formdata={'__EVENTTARGET': 'btnNext', 'ddlCurrentPage': str(int(current_page[0]) + 1)},
                callback=self.parse_submit,
                meta={'state': state, 'submit': submit}
            )

    def pre_student(self, response):
        href = response.xpath('//*[@id="gridview"]/tr[2]/td[4]/a/@href').extract()[0]

        return scrapy.Request(
            url=parse.urljoin(response.url, href),
            callback=self.parse_student,
            method='GET'
        )

    def parse_student(self, response):
        student_list = response.xpath('//*[@id="gridview"]/tr[position()>1]')

        for student in student_list:
            item = StudentItem()
            item['item_type'] = 'student'
            item['id'] = student.xpath('./td[2]/text()').get()
            item['name'] = student.xpath('./td[3]/text()').get()
            item['major'] = "软件工程"
            item['department'] = "电子信息与电气工程学院"
            yield item

        current_page = response.css("span#LblCurrentIndex::text").re(r'\d+')
        total_page = response.css("span#LblPageCount::text").re(r'\d+')
        if int(current_page[0]) < int(total_page[0]):
            yield scrapy.FormRequest.from_response(
                response=response,
                formdata={'__EVENTTARGET': 'btnNext', 'ddlCurrentPage': str(int(current_page[0]) + 1)},
                callback=self.parse_student,
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