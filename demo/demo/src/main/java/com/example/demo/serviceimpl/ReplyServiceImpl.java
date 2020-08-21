package com.example.demo.serviceimpl;

import com.example.demo.dao.*;
import com.example.demo.entity.*;
import com.example.demo.service.ReplyService;
import com.example.demo.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.constant.ReturnMsg.Msg0;
import static com.example.demo.constant.ReturnMsg.Msg1;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private TeacherMessageReadingDao teacherMessageReadingDao;
    @Autowired
    private TeacherMessageReplyDao teacherMessageReplyDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherMessageDao teacherMessageDao;
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public ReturnInfo sentReply(int reading_id, List<Reply> replies){
        ReturnInfo returnInfo=new ReturnInfo();
        teacherMessageReadingDao.setRead(reading_id);
        int i;
        i=teacherMessageReplyDao.setReply(replies,reading_id);
        if (i==1)
            returnInfo.setMsg(Msg1);
        else returnInfo.setMsg(Msg0);
        return returnInfo;
    }

    @Override
    public ReplyInfo getRepliesById(int id){
        ReplyInfo replyInfo=new ReplyInfo();
        List<TeacherMessageReply> teacherMessageReplyList=teacherMessageReplyDao.findAllByMessage_id(id);
        List<StudentReply> studentReplyList=new ArrayList<>();
        List<StudentUnreply> studentUnreplyList=new ArrayList<>();
        for (TeacherMessageReply teacherMessageReply : teacherMessageReplyList) {
            if (teacherMessageReply.is_reply()) {
                StudentReply studentReply = new StudentReply();
                studentReply.setId(teacherMessageReply.getStudent_id());
                studentReply.setName(studentDao.getOne(studentReply.getId()).getName());
                Optional<ReplyMessage> replyMessage = teacherMessageReplyDao.getById(teacherMessageReply.getId());
                if (replyMessage.isPresent()) {
                    ReplyMessage r = replyMessage.get();
                    studentReply.setReply(r.getReply());
                }
                studentReplyList.add(studentReply);
            }
            else {
                StudentUnreply studentUnreply=new StudentUnreply();
                studentUnreply.setId(teacherMessageReply.getStudent_id());
                studentUnreply.setName(studentDao.getOne(teacherMessageReply.getStudent_id()).getName());
                studentUnreplyList.add(studentUnreply);
            }
        }
        replyInfo.setStudentsReply(studentReplyList);
        replyInfo.setStudentsUnreply(studentUnreplyList);
        replyInfo.setReply(teacherMessageReplyDao.getRepliesByMessage_id(id));
        replyInfo.setUnReply(teacherMessageReplyDao.getUnRepliesByMessage_id(id));
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
            Optional<ReplyMessage> replyMessage=teacherMessageReplyDao.getById(reading_id);
            if(replyMessage.isPresent()){
                ReplyMessage r=replyMessage.get();
                messageInfo.setReply(r.getReply());
            }
            teacherMessageReadingDao.setRead(reading_id);
            return messageInfo;
        }
        else {
            return null;
        }
    }

    
}
