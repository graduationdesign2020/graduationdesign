import scrapy
import re
from scrapy.selector import Selector
from scrapy.http import Request

from bysjnotice.items import BysjnoticeItem


class NoticeSpider(scrapy.Spider):
    name = 'notice'
    allowed_domains = ['bysj.jwc.sjtu.edu.cn']
    start_urls = ['http://bysj.jwc.sjtu.edu.cn/MoreNews.aspx?NewsType=BzKecs5qaKUKFqqwy4K4nQ....&TypeName'
                  '=MTaBXFQRBQxURZo8Lzsr6A....']

    def parse(self, response):
        url_list = response.css('#AllNews a::attr(href)').extract()

        for url in url_list:
            print(url)
            yield Request(url='http://bysj.jwc.sjtu.edu.cn/' + url, callback=self.parse_item, dont_filter=True)

    def parse_item(self, response):
        sel = Selector(response)
        tr_sel = sel.xpath('//*[@id="form1"]/table[2]/tr/td/div/table/tr[3]/td')
        title_sel = sel.xpath('//*[@id="form1"]/table[2]/tr/td/div/table/tr[1]/td/p[1]/text()')

        item = BysjnoticeItem()
        if len(title_sel.extract()) == 1:
            item['title'] = title_sel.extract()[0].strip()
        else:
            item['title'] = sel.xpath('//*[@id="form1"]/table[2]/tr/td/div/table/tr[1]/td/p[1]/font/text()').extract()[
                0].strip()
        string = sel.xpath('//*[@id="form1"]/table[2]/tr/td/div/table/tr[1]/td/p[2]/text()').extract()[0].strip()
        item['time'] = "".join(re.findall("\d+", string))
        item['content'] = "".join(tr_sel.css('*::text').extract()).strip()

        yield item
