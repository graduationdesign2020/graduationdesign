import scrapy

import re
from urllib import parse
from bysj.items import NoticeItem
from scrapy.selector import Selector


class NoticeSpider(scrapy.Spider):
    name = 'notice'
    allowed_domains = ['bysj.jwc.sjtu.edu.cn']
    start_urls = ['http://bysj.jwc.sjtu.edu.cn/index.aspx']

    def parse(self, response):
        yield scrapy.Request(
            url='http://bysj.jwc.sjtu.edu.cn/MoreNews.aspx?NewsType=BzKecs5qaKUKFqqwy4K4nQ....&TypeName'
                '=MTaBXFQRBQxURZo8Lzsr6A....',
            callback=self.prenotice,
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
            yield scrapy.Request(url=parse.urljoin(response.url, url), callback=self.parse_notice,
                                 meta={'type': notice_type})

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
