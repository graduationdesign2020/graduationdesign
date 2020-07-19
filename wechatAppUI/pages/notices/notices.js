// pages/notices/notices.js
//获取应用实例
const app = getApp()

Page({
  data: {
    type: 2,
    notices: [
      {title: '标题', id: 1, is_read: false, read: 10, unread: 2, teachername: '娘口三三', time: '07-01', content: '内容'},
      {title: '标题', id: 1, is_read: false, read: 10, unread: 2, teachername: '猫咪老师', time: '07-01', content: '内容'}
    ],
    userData: {name: "小明", dept: "SE", auth: 0, id: 12345}
  },

  onLoad: function(options) {
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
            this.setData({userData: data.userData, type: options.type})
            switch (options.type) {
              case 0: {
                PostRequest('/getSchoolNotices', {}, that.setNotices);
              }
              case 1: {
                PostRequest('/getDepartmentNotices', {dept: this.data.userData.dept}, that.setNotices);
              }
              case 2: {
                if(this.data.userData.auth){
                  PostRequest('/teacherGetTeacherMessages', {teacher_id: this.data.userData.id}, that.setNotices);
                }
                else{
                  PostRequest('/getTeacherMessages', {student_id: this.data.userData.id}, that.setNotices);
                }
              }
              case 3: {
                if(this.data.userData.auth){
                  PostRequest('/teacherGetSystemMessages', {teacher_id: this.data.userData.id}, that.setNotices);
                }
                else{
                  PostRequest('/getSystemMessages', {student_id: this.data.userData.id}, that.setNotices);
                }
              }
            }
          }
        }
      }else{
        this.setData({userData: app.globalData.userData, type: options.type});
        switch (options.type) {
          case 0: {
            PostRequest('/getSchoolNotices', {}, that.setNotices);
          }
          case 1: {
            PostRequest('/getDepartmentNotices', {dept: this.data.userData.dept}, that.setNotices);
          }
          case 2: {
            if(this.data.userData.auth){
              PostRequest('/teacherGetTeacherMessages', {teacher_id: this.data.userData.id}, that.setNotices);
            }
            else{
              PostRequest('/getTeacherMessages', {student_id: this.data.userData.id}, that.setNotices);
            }
          }
          case 3: {
            if(this.data.userData.auth){
              PostRequest('/teacherGetSystemMessages', {teacher_id: this.data.userData.id}, that.setNotices);
            }
            else{
              PostRequest('/getSystemMessages', {student_id: this.data.userData.id}, that.setNotices);
            }
          }
        }
      }
    }
  },
    
  setNotices: function(data){
    this.setData({notices: data});
  },

  detail: function(e) {
    var that = this;
    if(this.data.type==2 && this.data.userData.auth) {
      wx.navigateTo({
        url: '../reading/reading?id=' + e.target.dataset.id,
      })
    }
    else {
      wx.navigateTo({
        url: '../noticeDetail/noticeDetail?type=' + that.data.type + '&id=' + e.target.dataset.id,
      })
    }
  }
})