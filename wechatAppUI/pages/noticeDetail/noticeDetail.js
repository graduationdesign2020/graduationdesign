// pages/noticeDetail/noticeDetail.js
//获取应用实例
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    type: 0,
    notice: {
      title: '标题', id: 1, is_read: false, teacher_id: '12345', student_id: '11111', time: '07-01', content: '内容'
    }
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
            url: 'http://localhost:8080/getSchoolNotice',
            data: {
              id: options.id
            },
            header: {
              'content-type': 'application/json', // 默认值
              'cookie': wx.getStorageSync("sessionid") //cookie
            },
            success(res) {
              console.log(res);
              that.setData({
                notice: res.data
              })
            }
          });
        }
        case 1:{
          wx.request({
            url: 'http://localhost:8080/getDepartmentNotice',
            data: {
              id: options.id
            },
            header: {
              'content-type': 'application/json', // 默认值
              'cookie': wx.getStorageSync("sessionid") //cookie
            },
            success(res) {
              console.log(res);
              that.setData({
                notice: res.data
              })
            }
          });
        }
        case 2:{
          wx.request({
            url: 'http://localhost:8080/getTeacherMessage',
            data: {
              id: options.id
            },
            header: {
              'content-type': 'application/json', // 默认值
              'cookie': wx.getStorageSync("sessionid") //cookie
            },
            success(res) {
              console.log(res);
              that.setData({
                notice: res.data
              })
            }
          });
        }
        case 3:{
          if(!app.globalData.user.auth) {
            wx.request({
              url: 'http://localhost:8080/getSystemMessageByTeacher',
              data: {
                id: options.id
              },
              header: {
                'content-type': 'application/json', // 默认值
                'cookie': wx.getStorageSync("sessionid") //cookie
              },
              success(res) {
                console.log(res);
                that.setData({
                  notice: res.data
                })
              }
            });
          }
          else {
            wx.request({
              url: 'http://localhost:8080/getSystemMessage',
              data: {
                id: options.id
              },
              header: {
                'content-type': 'application/json', // 默认值
                'cookie': wx.getStorageSync("sessionid") //cookie
              },
              success(res) {
                console.log(res);
                that.setData({
                  notice: res.data
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

  }
})