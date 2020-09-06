package org.gdms.test.serviceimpl;

import org.gdms.test.dao.*;
import org.gdms.test.entity.*;
import org.gdms.test.service.ReplyService;
import org.gdms.test.util.*;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.gdms.test.constant.ReturnMsg.*;

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
        List<StudentReply> studentReplyList=new ArrayList<>();
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

//    @Override
//    public void export(int id, ServletOutputStream out) throws Exception{
//        try {
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            HSSFSheet hssfSheet = workbook.createSheet("sheet1");
//            HSSFRow row = hssfSheet.createRow(0);
//            HSSFCell hssfCell = null;
//            TeacherMessage teacherMessage = teacherMessageDao.getTeacherMessageById(id);
//
//            //set table head
//            List<String> keys = teacherMessage.getTeacherMessageContent().getKeys();
//            hssfCell = row.createCell(0);
//            hssfCell.setCellValue("学号");
//            hssfCell = row.createCell(1);
//            hssfCell.setCellValue("姓名");
//            for (int i = 0; i < keys.size(); i++) {
//                hssfCell = row.createCell(2+i);
//                hssfCell.setCellValue(keys.get(i));
//            }
//
//            //get all replied student
//            List<TeacherMessageReading> teacherMessageReplyList=teacherMessageReadingDao.findReplyByMessage_id(id);
//            List<StudentReply> studentReplyList=new ArrayList<>();
//            for (TeacherMessageReading teacherMessageReply : teacherMessageReplyList) {
//                if (teacherMessageReply.is_read()) {
//                    StudentReply studentReply = new StudentReply();
//                    studentReply.setId(teacherMessageReply.getStudent_id());
//                    studentReply.setName(studentDao.getOne(studentReply.getId()).getName());
//                    Optional<ReplyMessage> replyMessage = replyMessageDao.getById(teacherMessageReply.getId());
//                    if (replyMessage.isPresent()) {
//                        ReplyMessage r = replyMessage.get();
//                        studentReply.setReply(r.getReply());
//                    }
//                    studentReplyList.add(studentReply);
//                }
//            }
//
//            //set table row
//            for(int i=0;i<studentReplyList.size();i++){
//                row=hssfSheet.createRow(i+1);
//                StudentReply studentReply=studentReplyList.get(i);
//                row.createCell(0).setCellValue(studentReply.getId());
//                row.createCell(1).setCellValue(studentReply.getName());
//                List<Reply> replies=studentReply.getReply();
//                for(int j=0;j<replies.size();j++){
//                    row.createCell(j+2).setCellValue(replies.get(j).getValue());
//                }
//            }
//            try {
//                workbook.write(out);
//                out.flush();
//                out.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            throw new Exception("Fail to export!");
//        }
//    }


}
