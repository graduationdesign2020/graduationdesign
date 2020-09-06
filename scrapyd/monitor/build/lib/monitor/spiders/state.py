# -*- coding: utf-8 -*-
import scrapy

from monitor.monitor.items import StateItem


class StateSpider(scrapy.Spider):
    name = 'state'
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

        yield scrapy.Request(
            url=response.urljoin(href_list[3]),
            callback=self.parse_form,
            meta={'state': '0'},
            method='POST'
        )

        yield scrapy.Request(
            url=response.urljoin(href_list[4]),
            callback=self.parse_form,
            meta={'state': '1'},
            method='POST'
        )

        yield scrapy.Request(
            url=response.urljoin(href_list[6]),
            callback=self.parse_form,
            meta={'state': '2'},
            method='POST'
        )

        yield scrapy.Request(
            url=response.urljoin(href_list[7]),
            callback=self.parse_form,
            meta={'state': '3'},
            method='POST'
        )

        yield scrapy.Request(
            url=response.urljoin(href_list[8]),
            callback=self.parse_form,
            meta={'state': '4'},
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
                yield scrapy.Request(url=response.urljoin(url),
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
