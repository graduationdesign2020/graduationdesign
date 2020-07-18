//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    searchValue: '',
    teacherCommonAPPs: [
      {icon: 'comment-o', text: '发送', url: '/pages/SendNotice/SendNotice'},
      {icon: 'chat-o', text: '消息', url: '/pages/notices/notices?type=2'},
      {icon: 'records', text: '成绩', url: '/pages/myScore/myScore'}
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
    active: "home"
  },
  
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    if (!app.globalData.userLogged){
      wx.redirectTo({
        url: '../index/index'
      })
    }else{
      wx.request({
        url: 'http://localhost:8080/getSchoolNotices',
        header: {
          'content-type': 'application/json', // 默认值
          'cookie': wx.getStorageSync("sessionid") //cookie
        },
        success(res) {
          console.log(res);
          that.setData({
            schoolNotices: res.data
          })
        }
      });
      wx.request({
        url: 'http://localhost:8080/getDepartmentNotices',
        data: {
          dept: app.globalData.user.dept
        },
        header: {
          'content-type': 'application/json', // 默认值
          'cookie': wx.getStorageSync("sessionid") //cookie
        },
        success(res) {
          console.log(res);
          that.setData({
            deptNotices: res.data
          })
        }
      });
      if(!app.globalData.user.auth) {
        wx.request({
          url: 'http://localhost:8080/getTeacherMessagesByTeacher',
          data: {
            teacher_id: app.globalData.user.id
          },
          header: {
            'content-type': 'application/json', // 默认值
            'cookie': wx.getStorageSync("sessionid") //cookie
          },
          success(res) {
            console.log(res);
            that.setData({
              teacherMessages: res.data
            })
          }
        });
        wx.request({
          url: 'http://localhost:8080/getSystemMessagesByTeacher',
          data: {
            id: app.globalData.user.id
          },
          header: {
            'content-type': 'application/json', // 默认值
            'cookie': wx.getStorageSync("sessionid") //cookie
          },
          success(res) {
            console.log(res);
            that.setData({
              sysMessages: res.data
            })
          }
        });
      }
      else {
        wx.request({
          url: 'http://localhost:8080/getTeacherMessages',
          data: {
            id: app.globalData.user.id
          },
          header: {
            'content-type': 'application/json', // 默认值
            'cookie': wx.getStorageSync("sessionid") //cookie
          },
          success(res) {
            console.log(res);
            that.setData({
              teacherMessages: res.data
            })
          }
        });
        wx.request({
          url: 'http://localhost:8080/getSystemMessages',
          data: {
            id: app.globalData.user.id
          },
          header: {
            'content-type': 'application/json', // 默认值
            'cookie': wx.getStorageSync("sessionid") //cookie
          },
          success(res) {
            console.log(res);
            that.setData({
              sysMessages: res.data
            })
          }
        });
      }
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  onSearch() {

  },

  onChange(e) {
    console.log(e.detail);
    switch (e.detail) {
      case "myprofile": {
        console.log(e.detail)
        wx.navigateTo({
          url: '../myProfile/Center',
        })
      }
      case "QA": {
        wx.navigateTo({
          url: '../QA/QA',
        })
      }
      case "studentFinished": {
        wx.navigateTo({
          url: '../processList/processList',
        })
      }
      case "procedure": {
        wx.navigateTo({
          url: '../procedure/procedure',
        })
      }
    }
  }
})
