package org.graduationdesign.gdmsservercore.daoimpl;

import org.graduationdesign.gdmsservercore.dao.TeacherMessageReadingDao;
import org.graduationdesign.gdmsservercore.entity.ReplyMessage;
import org.graduationdesign.gdmsservercore.entity.TeacherMessageReading;
import org.graduationdesign.gdmsservercore.repository.ReplyMessageRepository;
import org.graduationdesign.gdmsservercore.repository.TeacherMessageReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TeacherMessageReadingDaoImpl implements TeacherMessageReadingDao {
    @Autowired
    private TeacherMessageReadingRepository teacherMessageReadingRepository;

    @Autowired
    private ReplyMessageRepository replyMessageRepository;

    @Override
    public List<TeacherMessageReading> getReading(String stu_id){
        return teacherMessageReadingRepository.getTeacherMessagesByStudent_id(stu_id);
    }

    @Override
    public TeacherMessageReading addReader(TeacherMessageReading teacherMessageReading)
    {
        return teacherMessageReadingRepository.save(teacherMessageReading);
    }

    @Override
    public int setRead(int id){
        return teacherMessageReadingRepository.setRead(id);
    }

    @Override
    public int getTeacherMessageReadingsByMessage_id(int message_id){
        return teacherMessageReadingRepository.getTeacherMessageReadingsByMessage_id(message_id);
    }

    @Override
    public int getUnReadingsByMessage_id(int message_id){
        return teacherMessageReadingRepository.getUnReadingsByMessage_id(message_id);
    }

    @Override
    public List<TeacherMessageReading> findAllByMessage_id(int id){
        return teacherMessageReadingRepository.findAllByMessage_id(id);
    }

    @Override
    public List<TeacherMessageReading> findReplyByMessage_id(int id){
        List<TeacherMessageReading> teacherMessageReplies=teacherMessageReadingRepository.findAllByMessage_id(id);
        for (TeacherMessageReading teacherMessageReply : teacherMessageReplies) {
            Optional<ReplyMessage> replyMessage = replyMessageRepository.findById(teacherMessageReply.getId());
            if (replyMessage.isPresent()) {
                ReplyMessage r = replyMessage.get();
                teacherMessageReply.setReply(r);
            }
        }
        return teacherMessageReplies;
    }
}
