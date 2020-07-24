import scrapy

from bysj.items import StudentItem
from urllib import parse

class StudentSpider(scrapy.Spider):
    name = 'student'
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
            url=parse.urljoin(response.url, href_list[0]),
            callback=self.pre_student,
            method='POST'
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