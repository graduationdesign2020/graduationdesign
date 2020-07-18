// pages/notices/notices.js
//获取应用实例
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    type: 3,
    notices: [
      {title: '标题', id: 1, is_read: false, read: 10, unread: 2, teacher_id: '12345', student_id: '11111', time: '07-01', content: '内容'},
      {title: '标题', id: 1, is_read: false, read: 10, unread: 2, teacher_id: '12345', student_id: '11111', time: '07-01', content: '内容'}
    ]
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
      that.setData({
        type: options.type
      })
      switch (options.type) {
        case 0:{
          wx.request({
            url: 'http://localhost:8080/getSchoolNotices',
            header: {
              'content-type': 'application/json', // 默认值
              'cookie': wx.getStorageSync("sessionid") //cookie
            },
            success(res) {
              console.log(res);
              that.setData({
                notices: res.data
              })
            }
          });
        }
        case 1:{
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
                notices: res.data
              })
            }
          });
        }
        case 2:{
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
                  notices: res.data
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
                  notices: res.data
                })
              }
            });
          }
        }
        case 3:{
          if(!app.globalData.user.auth) {
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
                  notices: res.data
                })
              }
            });
          }
          else {
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
                  notices: res.data
                })
              }
            });
          }
        }
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

  detail: function(e) {
    var that = this;
    if(that.data.type==2 && app.globalData.user.auth) {
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