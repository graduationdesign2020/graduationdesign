package org.graduationdesign.gdmsservercore.daoimpl;

import org.graduationdesign.gdmsservercore.dao.TeacherMessageDao;
import org.graduationdesign.gdmsservercore.entity.TeacherMessage;
import org.graduationdesign.gdmsservercore.entity.TeacherMessageContent;
import org.graduationdesign.gdmsservercore.repository.TeacherMessageContentRepository;
import org.graduationdesign.gdmsservercore.repository.TeacherMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TeacherMessageDaoImpl implements TeacherMessageDao {
    @Autowired
    private TeacherMessageRepository teacherMessageRepository;
    @Autowired
    private TeacherMessageContentRepository teacherMessageContentRepository;

    @Override
    public TeacherMessage getTeacherMessageById(Integer id){
        Optional<TeacherMessage> teacherMessage=teacherMessageRepository.getById(id);
        if(teacherMessage.isPresent()) {
            TeacherMessage t = teacherMessage.get();
            Optional<TeacherMessageContent> teacherMessageContent = teacherMessageContentRepository.findById(id*10+2);
            if (teacherMessageContent.isPresent()) {
                TeacherMessageContent s = teacherMessageContent.get();
                t.setTeacherMessageContent(s);
            } else {
                t.setTeacherMessageContent(null);
            }
            return t;
        }
        else return null;
    }

    @Override
    public TeacherMessage getTeacherMessage(Integer id){
        return teacherMessageRepository.getOne(id);
    }

    @Override
    public List<TeacherMessage> getTeacherMessagesByTeacher(String teacher_id) {
        return teacherMessageRepository.findAllByTeacher_id(teacher_id);
    }

    @Override
    public void sentTeacherMessage(TeacherMessage teacherMessage){
        teacherMessageRepository.save(teacherMessage);
        TeacherMessageContent teacherMessageContent=teacherMessage.getTeacherMessageContent();
        teacherMessageContent.setId(teacherMessage.getId()*10+2);
        teacherMessageContentRepository.save(teacherMessageContent);
    }

    @Override
    public List<String> getKeysById(int id){
        Optional<TeacherMessageContent> teacherMessageContent=teacherMessageContentRepository.findById(id*10+2);
        if(teacherMessageContent.isPresent()){
            TeacherMessageContent t=teacherMessageContent.get();
            return t.getKeys();
        }
        else return null;
    }
}
