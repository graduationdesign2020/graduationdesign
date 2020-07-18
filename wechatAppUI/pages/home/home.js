//index.js
//获取应用实例
const app = getApp();

Page({
  data: {
    searchValue: '',
    teacherCommonAPPs: [
      {icon: 'comment-o', text: '发送', url: '/pages/SendNotice/SendNotice'},
      {icon: 'chat-o', text: '消息', url: '/pages/notices/notices?type=2'},
      {icon: 'records', text: '成绩', url: '/pages/studentScore/studentScore'}
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
      {title: '标题', id: 1, is_read: false, read: 10, unread: 2, teacher_id: '12345', student_id: '11111', time: '07-01', content: '内容'},
      {title: '标题', id: 1, is_read: false, read: 10, unread: 2, teacher_id: '12345', student_id: '11111', time: '07-01', content: '内容'}  
    ],
    sysMessages: [
      {title: '标题', id: 1, is_read: false, type: 1, student_id: '11111', time: '07-01', content: '内容'}
    ],
    active: "home",
    userData: {name: "小明", dept: "SE", auth: 1, id: 12345}
  },

   /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function() {
    var that = this;
    if(app.globalData.login == 2){
      wx.redirectTo({
        url: '../register/index',
      })
    }else{
      if(app.globalData.login == 0){
        app.dataCallback = (data) => {
          if(data.msg == "FAIL"){
            wx.redirectTo({
              url: '../register/index',
            })
          }else{
            this.setData({userData: data.userData})
            PostRequest('/getThreeSchoolNotices', {}, that.setSchoolNotices);
            PostRequest('/getThreeDepartmentNotices', {dept: this.data.userData.dept}, that.setDeptNotices);
            if(this.data.userData.auth) {
              PostRequest('/teacherGetTeacherMessages', {teacher_id: this.data.userData.id}, that.setTeacherMessages);
              PostRequest('/teacherGetSystemMessages', {teacher_id: this.data.userData.id}, that.setSysMessages);
            }
            else {
              PostRequest('/getTeacherMessages', {student_id: this.data.userData.id}, that.setTeacherMessages);
              PostRequest('/getSystemMessages', {student_id: this.data.userData.id}, that.setSysMessages);
            }
          }
        }
      }else{
        this.setData({userData: app.globalData.userData});
        PostRequest('/getThreeSchoolNotices', {}, that.setSchoolNotices);
        PostRequest('/getThreeDepartmentNotices', {dept: this.data.userData.dept}, that.setDeptNotices);
        if(this.data.userData.auth) {
          PostRequest('/teacherGetTeacherMessages', {teacher_id: this.data.userData.id}, that.setTeacherMessages);
          PostRequest('/teacherGetSystemMessages', {teacher_id: this.data.userData.id}, that.setSysMessages);
        }
        else {
          PostRequest('/getTeacherMessages', {student_id: this.data.userData.id}, that.setTeacherMessages);
          PostRequest('/getSystemMessages', {student_id: this.data.userData.id}, that.setSysMessages);
        }
      }
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

  setSysMessages: function(data){
    this.setData({sysMessages: data});
  },
    
  onSearch() {

  }
})