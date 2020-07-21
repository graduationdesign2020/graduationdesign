# -*- coding: utf-8 -*-
import scrapy

from bysj.items import StateItem


class State4Spider(scrapy.Spider):
    name = 'state4'
    allowed_domains = ['bysj.jwc.sjtu.edu.cn']

    def start_requests(self):
        url = "http://bysj.jwc.sjtu.edu.cn/index.aspx"
        post_data = {
            "__VIEWSTATE": "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",
            "__VIEWSTATEGENERATOR": "90059987",
            "__EVENTVALIDATION": "/wEWCQLWrY7BCgLr/4HeAgLmmdGVDAL+jNCfDwK4ovrCAQKdw8XHCAL0uc/cCgLW4JOwDwL4oeqeCcVu583ur2LZLPjfOqwHBkUUnfNV",
            "UserId": "10367",
            "Pwd": "10367",
            "LoginButton.x": "13",
            "LoginButton.y": "6"
        }
        yield scrapy.FormRequest(url=url,
                                 formdata=post_data,
                                 callback=self.character,
                                 method='POST'
                                 )

    def character(self, response):
        url = "http://bysj.jwc.sjtu.edu.cn/LoginRole.aspx?sid=Jk7UDmg%2fcSOg6wXocnYmog....&screen=C3R1RXfU%2fLYcx2ms2xw7Cg...."
        post_data = {
            "__VIEWSTATE": "/wEPDwUKMTk2MTAzODE4Mw9kFgICAw9kFgICAw8PFgIeB1Zpc2libGVnZGQYAQUeX19Db250cm9sc1JlcXVpcmVQb3N0QmFja0tleV9fFgIFDEltYWdlQnV0dG9uMwUMSW1hZ2VCdXR0b240/IfDl20NUC4dCF++M3glQRSC1r8=",
            "__VIEWSTATEGENERATOR": "6CA0729C",
            "__EVENTVALIDATION": "/wEWAwL7oojgAgLSwsGJCgLSwv2aBF/cHhCiQ2K1WnQPUn14ruv9Znzy",
            "ImageButton4.x": "43",
            "ImageButton4.y": "22"
        }
        yield scrapy.FormRequest(url=url,
                                 formdata=post_data,
                                 callback=self.parse,
                                 method='POST'
                                 )

    def parse(self, response):
        yield scrapy.Request(
            url="http://bysj.jwc.sjtu.edu.cn/Admin/TotleLunWenZzLook.aspx?type=wtj&sid=Jk7UDmg/cSOg6wXocnYmog....&screen=Qd2HRMrs8oJGtA82doE7Jg....&zhuanyeno=537",
            callback=self.parse_submit1)
        yield scrapy.Request(
            url="http://bysj.jwc.sjtu.edu.cn/Admin/TotleLunWenZzLook.aspx?type=yzc&sid=Jk7UDmg/cSOg6wXocnYmog....&screen=Qd2HRMrs8oJGtA82doE7Jg....&zhuanyeno=537",
            callback=self.parse_submit2)
        yield scrapy.Request(
            url="http://bysj.jwc.sjtu.edu.cn/Admin/TotleLunWenZzLook.aspx?type=zysh&sid=Jk7UDmg/cSOg6wXocnYmog....&screen=Qd2HRMrs8oJGtA82doE7Jg....&zhuanyeno=537",
            callback=self.parse_submit3)
        yield scrapy.Request(
            url="http://bysj.jwc.sjtu.edu.cn/Admin/TotleLunWenZzLook.aspx?type=shtg&sid=Jk7UDmg/cSOg6wXocnYmog....&screen=Qd2HRMrs8oJGtA82doE7Jg....&zhuanyeno=537",
            callback=self.parse_submit5)

    def parse_submit1(self, response):
        state_list = response.xpath('//*[@id="gridview"]/tr[position()>1]')

        for state in state_list:
            item = StateItem()
            item['project_id'] = state.xpath('./td[4]/a/text()').re(r'\d+')
            item['state'] = 4
            item['submit'] = 1
            yield item

        current_page = response.css("span#LblCurrentIndex::text").re(r'\d+')
        total_page = response.css("span#LblPageCount::text").re(r'\d+')
        if current_page < total_page:
            url = "http://bysj.jwc.sjtu.edu.cn/Admin/TotleLunWenZzLook.aspx?type=wtj&sid=Jk7UDmg%2fcSOg6wXocnYmog....&screen=Qd2HRMrs8oJGtA82doE7Jg....&zhuanyeno=537"
            post_data = {
                "__EVENTTARGET": "btnNext",
                "__EVENTARGUMENT": "",
                "__LASTFOCUS": "",
                "__VIEWSTATE": response.css("input#__VIEWSTATE::attr(value)").get(),
                "__VIEWSTATEGENERATOR": response.css("input#__VIEWSTATEGENERATOR::attr(value)").get(),
                "__EVENTVALIDATION": response.css("input#__EVENTVALIDATION::attr(value)").get(),
                "ReportName": "",
                "ZhiDao": "",
                "xname": "",
                "xxh": "",
                "SelClass": "0",
                "ddlCurrentPage": current_page
            }
            yield scrapy.FormRequest(url=url,
                                     formdata=post_data,
                                     callback=self.parse_submit1,
                                     method='POST'
                                     )

    def parse_submit2(self, response):
        state_list = response.xpath('//*[@id="gridview"]/tr[position()>1]')

        for state in state_list:
            item = StateItem()
            item['project_id'] = state.xpath('./td[4]/a/text()').re(r'\d+')
            item['state'] = 4
            item['submit'] = 2
            yield item

        current_page = response.css("span#LblCurrentIndex::text").re(r'\d+')
        total_page = response.css("span#LblPageCount::text").re(r'\d+')
        if current_page < total_page:
            url = "http://bysj.jwc.sjtu.edu.cn/Admin/TotleLunWenZzLook.aspx?type=yzc&sid=Jk7UDmg%2fcSOg6wXocnYmog....&screen=Qd2HRMrs8oJGtA82doE7Jg....&zhuanyeno=537"
            post_data = {
                "__EVENTTARGET": "btnNext",
                "__EVENTARGUMENT": "",
                "__LASTFOCUS": "",
                "__VIEWSTATE": response.css("input#__VIEWSTATE::attr(value)").get(),
                "__VIEWSTATEGENERATOR": response.css("input#__VIEWSTATEGENERATOR::attr(value)").get(),
                "__EVENTVALIDATION": response.css("input#__EVENTVALIDATION::attr(value)").get(),
                "ReportName": "",
                "ZhiDao": "",
                "xname": "",
                "xxh": "",
                "SelClass": "0",
                "ddlCurrentPage": current_page
            }
            yield scrapy.FormRequest(url=url,
                                     formdata=post_data,
                                     callback=self.parse_submit2,
                                     method='POST'
                                     )

    def parse_submit3(self, response):
        state_list = response.xpath('//*[@id="gridview"]/tr[position()>1]')

        for state in state_list:
            item = StateItem()
            item['project_id'] = state.xpath('./td[4]/a/text()').re(r'\d+')
            item['state'] = 4
            item['submit'] = 3
            yield item

        current_page = response.css("span#LblCurrentIndex::text").re(r'\d+')
        total_page = response.css("span#LblPageCount::text").re(r'\d+')
        if current_page < total_page:
            url = "http://bysj.jwc.sjtu.edu.cn/Admin/TotleLunWenZzLook.aspx?type=zysh&sid=Jk7UDmg%2fcSOg6wXocnYmog....&screen=Qd2HRMrs8oJGtA82doE7Jg....&zhuanyeno=537"
            post_data = {
                "__EVENTTARGET": "btnNext",
                "__EVENTARGUMENT": "",
                "__LASTFOCUS": "",
                "__VIEWSTATE": response.css("input#__VIEWSTATE::attr(value)").get(),
                "__VIEWSTATEGENERATOR": response.css("input#__VIEWSTATEGENERATOR::attr(value)").get(),
                "__EVENTVALIDATION": response.css("input#__EVENTVALIDATION::attr(value)").get(),
                "ReportName": "",
                "ZhiDao": "",
                "xname": "",
                "xxh": "",
                "SelClass": "0",
                "ddlCurrentPage": current_page
            }
            yield scrapy.FormRequest(url=url,
                                     formdata=post_data,
                                     callback=self.parse_submit3,
                                     method='POST'
                                     )

    def parse_submit5(self, response):
        state_list = response.xpath('//*[@id="gridview"]/tr[position()>1]')

        for state in state_list:
            item = StateItem()
            item['project_id'] = state.xpath('./td[4]/a/text()').re(r'\d+')
            item['state'] = 4
            item['submit'] = 5
            yield item

        current_page = response.css("span#LblCurrentIndex::text").re(r'\d+')
        total_page = response.css("span#LblPageCount::text").re(r'\d+')
        if current_page < total_page:
            url = "http://bysj.jwc.sjtu.edu.cn/Admin/TotleLunWenZzLook.aspx?type=shtg&sid=Jk7UDmg%2fcSOg6wXocnYmog....&screen=Qd2HRMrs8oJGtA82doE7Jg....&zhuanyeno=537"
            post_data = {
                "__EVENTTARGET": "btnNext",
                "__EVENTARGUMENT": "",
                "__LASTFOCUS": "",
                "__VIEWSTATE": response.css("input#__VIEWSTATE::attr(value)").get(),
                "__VIEWSTATEGENERATOR": response.css("input#__VIEWSTATEGENERATOR::attr(value)").get(),
                "__EVENTVALIDATION": response.css("input#__EVENTVALIDATION::attr(value)").get(),
                "ReportName": "",
                "ZhiDao": "",
                "xname": "",
                "xxh": "",
                "SelClass": "0",
                "ddlCurrentPage": current_page
            }
            yield scrapy.FormRequest(url=url,
                                     formdata=post_data,
                                     callback=self.parse_submit5,
                                     method='POST'
                                     )