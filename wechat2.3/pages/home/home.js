//index.js

//获取应用实例
const app = getApp();
import {PostRequest} from "../../utils/ajax";

const getAuth = () => {
  return new Promise((resolve, reject) => {
    wx.request({
      url: "http://localhost:8888/getAuth",
      header: {"Authorization": wx.getStorageSync('jwt')},
      method: "GET",
      success(res){
        resolve(res.data)
      },
      fail(res){
        console.log("wrong jwt")
        wx.redirectTo({
          url: '/pages/inputId/inputId',
        })
        reject(res)
      }
    })
  })
}

Page({
  data: {
    searchValue: '',
    teacherCommonAPPs: [
      {icon: 'comment-o', text: '发送', url: "/pages/SendNotice/SendNotice"},
      {icon: 'chat-o', text: '消息', url: '/pages/notices/notices?type=2'},
      {icon: 'records', text: '成绩', url: '/pages/studentScore/studentScore'},
      {icon: 'clock-o', text: '截止时间' , url: '/pages/postddl/postddl'}
    ],
    studentCommonAPPs: [
      {icon: 'records', text: '成绩', url: '/pages/myScore/myScore'}
    ],
    schoolNotices: [
      {title: '毕业设计选题公告', id: 1, time: '07-01', content: '内容'}
    ],
    deptNotices: [
      {title: '毕业设计选题公告', id: 1, time: '07-01', content: '内容', department: '专业'}
    ],
    teacherMessages: [
      {title: '标题', id: 1, isread: false, reading: 10, unread: 2, teacher_id: '12345', student_id: '11111', time: '07-01', content: '内容'},
      {title: '标题', id: 1, isread: false, reading: 10, unread: 2, teacher_id: '12345', student_id: '11111', time: '07-01', content: '内容'}  
    ],
    active: "home",
    auth: wx.getStorageSync('auth'),
    isRefresh:false
  },
  onLoad: function() {
    var that = this
    if(this.data.auth == ''){
      PostRequest('/getAuth',{}, (data)=>{
        that.setData({auth: data})
        PostRequest('/getThreeSchoolNotices', {}, that.setSchoolNotices)
        PostRequest('/getThreeDepartmentNotices', {}, that.setDeptNotices)
        if(data) {
          PostRequest('/teacherGetTeacherMessages', {}, that.setTeacherMessages);
        }
        else {
          PostRequest('/getTeacherMessages', {}, that.setTeacherMessages);
        }
      })
    }
    else{
        PostRequest('/getThreeSchoolNotices', {}, that.setSchoolNotices)
        PostRequest('/getThreeDepartmentNotices', {}, that.setDeptNotices)
        PostRequest('/getTeacherMessages', {}, that.setTeacherMessages);
    }
  },

  setSchoolNotices: function(data){
    this.setData({schoolNotices: data});
  },

  setDeptNotices: function(data){
    this.setData({deptNotices: data});
  },

  setTeacherMessages: function(data){
    this.setData({teacherMessages: data});
  },
    
  onSearch() {

  },
  onShow:function(e){
    var that=this
    // 返回时刷新页面
    if (that.data.isRefresh==true){
      console.log("refresh")
      if(that.data.userData.auth) {
        PostRequest('/teacherGetTeacherMessages', {}, that.setTeacherMessages);
      }
      else {
        PostRequest('/getTeacherMessages', {}, that.setTeacherMessages);
      }
    }   
  }
})