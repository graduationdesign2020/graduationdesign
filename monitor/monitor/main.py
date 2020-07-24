# -*- coding: utf-8 -*-
from datetime import datetime
import os
from apscheduler.schedulers.blocking import BlockingScheduler


def state():
    print('crawl state time is: %s' % datetime.now())
    os.system("scrapy crawl state")

def grade():
    print('crawl grade time is: %s' % datetime.now())
    os.system("scrapy crawl grade")

def notice():
    print('crawl notice time is: %s' % datetime.now())
    os.system("scrapy crawl notice")


if __name__ == '__main__':
    scheduler = BlockingScheduler()
    scheduler.add_job(state, 'cron', hour='0-23', minute='20')
    scheduler.add_job(grade, 'cron', hour='0-23', minute='0')
    scheduler.add_job(notice, 'cron', hour='0-23', minute='10')
    print('Press Ctrl+{0} to exit'.format('Break' if os.name == 'nt' else 'C    '))

    try:
        scheduler.start()
    except (KeyboardInterrupt, SystemExit):
        pass
