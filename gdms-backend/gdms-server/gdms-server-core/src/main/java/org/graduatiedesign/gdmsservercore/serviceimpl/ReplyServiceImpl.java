package org.graduatiedesign.gdmsservercore.serviceimpl;

import org.graduatiedesign.gdmsservercore.dao.*;
import org.graduatiedesign.gdmsservercore.entity.*;
import org.graduatiedesign.gdmsservercore.service.ReplyService;
import org.graduatiedesign.gdmsservercore.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.graduatiedesign.gdmsservercore.constant.ReturnMsg.Msg0;
import static org.graduatiedesign.gdmsservercore.constant.ReturnMsg.Msg1;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private TeacherMessageReadingDao teacherMessageReadingDao;
    @Autowired
    private ReplyMessageDao replyMessageDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherMessageDao teacherMessageDao;
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public ReturnInfo sentReply(int reading_id, List<Reply> replies){
        ReturnInfo returnInfo=new ReturnInfo();
        ReplyMessage replyMessage=new ReplyMessage();
        replyMessage.setId(reading_id);
        replyMessage.setReply(replies);
        replyMessageDao.saveReply(replyMessage);
        int i;
        i=teacherMessageReadingDao.setRead(reading_id);
        if (i==1)
            returnInfo.setMsg(Msg1);
        else returnInfo.setMsg(Msg0);
        return returnInfo;
    }

    @Override
    public ReplyInfo getRepliesById(int id){
        ReplyInfo replyInfo=new ReplyInfo();
        List<TeacherMessageReading> teacherMessageReplyList=teacherMessageReadingDao.findReplyByMessage_id(id);
        List<org.graduatiedesign.gdmsservercore.utils.StudentReply> studentReplyList=new ArrayList<>();
        List<StudentReply> studentUnreplyList=new ArrayList<>();
        List<String> keys=teacherMessageDao.getKeysById(id);
        List<Reply> replies=new ArrayList<>();
        for (String key : keys) {
            Reply reply = new Reply();
            reply.setKey(key);
            replies.add(reply);
        }
        for (TeacherMessageReading teacherMessageReply : teacherMessageReplyList) {
            StudentReply studentReply = new StudentReply();
            studentReply.setId(teacherMessageReply.getStudent_id());
            studentReply.setName(studentDao.getOne(studentReply.getId()).getName());
            if (teacherMessageReply.is_read()) {
                Optional<ReplyMessage> replyMessage = replyMessageDao.getById(teacherMessageReply.getId());
                if (replyMessage.isPresent()) {
                    ReplyMessage r = replyMessage.get();
                    studentReply.setReply(r.getReply());
                }
                studentReplyList.add(studentReply);
            }
            else {
                studentReply.setReply(replies);
                studentUnreplyList.add(studentReply);
            }
        }
        replyInfo.setStudentsReply(studentReplyList);
        replyInfo.setStudentsUnreply(studentUnreplyList);
        replyInfo.setReply(teacherMessageReadingDao.getTeacherMessageReadingsByMessage_id(id));
        replyInfo.setUnReply(teacherMessageReadingDao.getUnReadingsByMessage_id(id));
        return replyInfo;
    }

    @Override
    public ReplyMessageInfo getReplyMessage(int id,int reading_id){
        TeacherMessage t=teacherMessageDao.getTeacherMessageById(id);
        if(t!=null)
        {
            ReplyMessageInfo messageInfo=new ReplyMessageInfo();
            messageInfo.setId(t.getId());
            messageInfo.setType(0);
            messageInfo.setTitle(t.getTitle());
            messageInfo.setContent(t.getTeacherMessageContent().getContent());
            messageInfo.setTime(t.getTime());
            messageInfo.setReading_id(reading_id);
            Teacher teacher= teacherDao.getTeacherById(t.getTeacher_id());
            messageInfo.setTeachername(teacher.getName());
            Optional<ReplyMessage> replyMessage=replyMessageDao.getById(reading_id);
            if(replyMessage.isPresent()){
                ReplyMessage r=replyMessage.get();
                messageInfo.setReply(r.getReply());
            }
            return messageInfo;
        }
        else {
            return null;
        }
    }
}
