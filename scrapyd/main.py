# -*- coding: utf-8 -*-
import pymysql
import pymongo

# connection database
connect = pymysql.connect(host='54.234.98.178', user='root', passwd='graduationdesign', db='gdms')
# get cursor
cursor = connect.cursor()

client = pymongo.MongoClient("mongodb://54.234.98.178:27017")

db = client["gdms"]  # 获得数据库的句柄
coll_message = db["teachermessagecontent"]  # 获得collection的句柄


def set_ddl(teacher_id, state, end_time):
    try:
        cursor.execute(
            """insert into schedulejob(teacher_id, job_status, state, end_time) SELECT %s, %s, %s, %s FROM DUAL WHERE NOT EXISTS ( SELECT * FROM schedulejob WHERE teacher_id = %s and state = %s)""",
            (teacher_id, '0', state, end_time, teacher_id, state)
        )
        cursor.execute("""SELECT id FROM project WHERE teacher_id=%s""", teacher_id)
        results = cursor.fetchall()
        for row in results:
            id = row[0]
            cursor.execute(
                """UPDATE state SET end_time=%s WHERE project_id=%s and state=%s""",
                (end_time, id, state)
            )
        connect.commit()
    except:
        connect.rollback()


def send_message(teacher_id, title, content):
    try:
        cursor.execute(
            """insert into teachermessage(teacher_id, title, time, type) VALUES (%s,%s,current_timestamp(),0)""",
            (teacher_id, title)
        )
        connect.commit()

        cursor.execute("""select last_insert_id() from teachermessage""")
        result = cursor.fetchone()

        student_id = []
        cursor.execute("""SELECT id FROM project WHERE teacher_id=%s""", teacher_id)
        students = cursor.fetchall()
        for student in students:
            id = student[0]
            student_id.append(id)
            cursor.execute(
                """insert into teachermessagereading(message_id, student_id, is_read) VALUES (%s,%s,%s)""",
                (result[0], id, 0)
            )
        connect.commit()

        coll_message.insert_one(
            {"_id": result[0], "content": content, "title": title, "type": 2, "students": student_id})
    except:
        connect.rollback()


def send_reply_message(teacher_id, title, content, keys):
    try:
        cursor.execute(
            """insert into teachermessage(teacher_id, title, time, type) VALUES (%s,%s,current_timestamp(),1)""",
            (teacher_id, title)
        )
        connect.commit()

        cursor.execute("""select last_insert_id() from teachermessage""")
        result = cursor.fetchone()

        student_id = []
        cursor.execute("""SELECT id FROM project WHERE teacher_id=%s""", teacher_id)
        students = cursor.fetchall()
        for student in students:
            id = student[0]
            student_id.append(id)
            cursor.execute(
                """insert into teachermessagereading(message_id, student_id, is_read) VALUES (%s,%s,%s)""",
                (result[0], id, 0)
            )
        connect.commit()

        coll_message.insert_one(
            {"_id": result[0], "content": content, "title": title, "type": 2, "students": student_id, "keys": keys})
    except:
        connect.rollback()


def set_ddls(teacher_id):
    set_ddl(teacher_id, 0, "20200313000000")
    set_ddl(teacher_id, 1, "20200417000000")
    set_ddl(teacher_id, 2, "20200520000000")
    set_ddl(teacher_id, 3, "20200624000000")
    set_ddl(teacher_id, 4, "20200630000000")


def send_messages(teacher_id):
    send_message(teacher_id, "chapter 1",
                 "有钱的单身汉总要取位太太，这是一条举世公认的真理。\n"
                 "这条真理还真够深入人心的，每逢这样的单身汉新搬到一个地方，四邻八舍的人家尽管对他的心思想法一无所知，却把他视为自己某一个女儿的合法财产。")
    send_message(teacher_id, "chapter 2",
                 "贝内特先生是最先拜访宾利先生的人之一。本来，他早就打算去拜见他，可在太太面前却始终咬定不想去。直到拜访后的当天晚上，贝内特太太"
                 "才知道实情。当时，事情是这样透露出来的。")
    send_message(teacher_id, "chapter 3",
                 "贝内特太太尽管有五个女儿帮腔，宾利先生长宾利先生短地问来问去，可丈夫总不能给她个满意的回答。母女们采取种种方式对付他，"
                 "露骨的盘问，奇异的假想不着边际地猜测，但是，任凭她们手段多么高明，贝内特先生都一一敷衍过去。")
    send_message(teacher_id, "chapter 4",
                 "简本来并不轻易赞扬宾利先生，但是当她和伊丽莎白单独在一起的时候，她却向妹妹表白了自己多么爱慕他。\n"
                 "他是一个典型的好青年，她说到，有见识，脾气好，人又活泼，我从没见过这么讨人喜欢的举止！那么端庄，那么富有教养！")
    send_message(teacher_id, "chapter 5",
                 "离朗伯恩不远住着一户人家，与贝内特家关系特别密切。威廉爵士先前在梅里顿做生意，发了不小一笔财，任镇长期间上书国王，荣获爵士称号。"
                 "也许他把这荣誉看得过重，心里便讨厌做买卖了，讨厌住在这小集镇上。")
    send_message(teacher_id, "chapter 6",
                 "达西开始希望多与她交往。为了争取与她攀谈，他总是留神倾听她与别人的谈话。他这般举动引起了她的注意。那是在威廉爵士家，当时他府上宾客满堂。")
    send_message(teacher_id, "chapter 7",
                 "贝内特先生的财产几乎全包含在一宗房地产上，每年可以得到两千磅的进项。也该他的女儿们倒霉，他因为没有儿子，这宗房地产得由一个远方亲戚来继承。")
    send_message(teacher_id, "chapter 8",
                 "一个女人不能出类拔萃，就不能真正算是多才多艺。一个女人必须精通音乐、唱歌、绘画、舞蹈以及现代语言，才当得起这个称号。除此之外，"
                 "她的仪表步态、嗓音语调、谈吐表情都必须具备一种特质，否则她只能获得一半的资格")
    send_message(teacher_id, "chapter 9",
                 "达西：一般来说，乡下可供进行这项研究的对象很少。在乡下，你的活动范围非常狭窄，非常单调。\n"
                 "伊丽莎白：但人还是有很多变化的，他们身上总是有些新东西值得你去注意。")
    send_message(teacher_id, "chapter 10",
                 "达西先生，你似乎完全否定了友情的作用。如果你尊重向你提要求的人，你往往会不等他来说服你就爽爽快快地接受他的要求。我并不是在特指你所假设的宾利先生的那种情况。")


def send_reply_messages(teacher_id):
    send_reply_message(teacher_id, "chapter 1",
                       "有钱的单身汉总要取位太太，这是一条举世公认的真理。\n"
                       "这条真理还真够深入人心的，每逢这样的单身汉新搬到一个地方，四邻八舍的人家尽管对他的心思想法一无所知，却把他视为自己某一个女儿的合法财产。",
                       ["回复1", "回复2"])
    send_reply_message(teacher_id, "chapter 2",
                       "贝内特先生是最先拜访宾利先生的人之一。本来，他早就打算去拜见他，可在太太面前却始终咬定不想去。直到拜访后的当天晚上，贝内特太太"
                       "才知道实情。当时，事情是这样透露出来的。",
                       ["回复1", "回复2"])
    send_reply_message(teacher_id, "chapter 3",
                       "贝内特太太尽管有五个女儿帮腔，宾利先生长宾利先生短地问来问去，可丈夫总不能给她个满意的回答。母女们采取种种方式对付他，"
                       "露骨的盘问，奇异的假想不着边际地猜测，但是，任凭她们手段多么高明，贝内特先生都一一敷衍过去。",
                       ["回复1", "回复2"])
    send_reply_message(teacher_id, "chapter 4",
                       "简本来并不轻易赞扬宾利先生，但是当她和伊丽莎白单独在一起的时候，她却向妹妹表白了自己多么爱慕他。\n"
                       "他是一个典型的好青年，她说到，有见识，脾气好，人又活泼，我从没见过这么讨人喜欢的举止！那么端庄，那么富有教养！",
                       ["回复1", "回复2"])
    send_reply_message(teacher_id, "chapter 5",
                       "离朗伯恩不远住着一户人家，与贝内特家关系特别密切。威廉爵士先前在梅里顿做生意，发了不小一笔财，任镇长期间上书国王，荣获爵士称号。"
                       "也许他把这荣誉看得过重，心里便讨厌做买卖了，讨厌住在这小集镇上。",
                       ["回复1", "回复2"])
    send_reply_message(teacher_id, "chapter 6",
                       "达西开始希望多与她交往。为了争取与她攀谈，他总是留神倾听她与别人的谈话。他这般举动引起了她的注意。那是在威廉爵士家，当时他府上宾客满堂。",
                       ["回复1", "回复2"])
    send_reply_message(teacher_id, "chapter 7",
                       "贝内特先生的财产几乎全包含在一宗房地产上，每年可以得到两千磅的进项。也该他的女儿们倒霉，他因为没有儿子，这宗房地产得由一个远方亲戚来继承。",
                       ["回复1", "回复2"])
    send_reply_message(teacher_id, "chapter 8",
                       "一个女人不能出类拔萃，就不能真正算是多才多艺。一个女人必须精通音乐、唱歌、绘画、舞蹈以及现代语言，才当得起这个称号。除此之外，"
                       "她的仪表步态、嗓音语调、谈吐表情都必须具备一种特质，否则她只能获得一半的资格",
                       ["回复1", "回复2"])
    send_reply_message(teacher_id, "chapter 9",
                       "达西：一般来说，乡下可供进行这项研究的对象很少。在乡下，你的活动范围非常狭窄，非常单调。\n"
                       "伊丽莎白：但人还是有很多变化的，他们身上总是有些新东西值得你去注意。",
                       ["回复1", "回复2"])
    send_reply_message(teacher_id, "chapter 10",
                       "达西先生，你似乎完全否定了友情的作用。如果你尊重向你提要求的人，你往往会不等他来说服你就爽爽快快地接受他的要求。我并不是在特指你所假设的宾利先生的那种情况。",
                       ["回复1", "回复2"])


if __name__ == '__main__':
    cursor.execute("""SELECT id FROM teacher""")
    teachers = cursor.fetchall()
    for teacher in teachers:
        id = teacher[0]
        set_ddls(id)
        send_messages(id)
        send_reply_messages(id)

client.close()
connect.close()
cursor.close()
