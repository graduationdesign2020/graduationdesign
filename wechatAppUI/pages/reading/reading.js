// pages/reading/reading.js
//获取应用实例
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    notice: {title: '毕业设计选题公告', id: 1, time: '07-01', content: '内容'},
    checked: true,
    ReadInfo: {
      studentsRead: [
        {name: "小明"}
      ],
      studentsUnread: [
        {name: "小红"},
        {name: "李华"}
      ],
      Read: 1,
      unRead: 2
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
      wx.request({
        url: 'http://localhost:8080/teacherGetTeacherMessage',
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
      wx.request({
        url: 'http://localhost:8080/getTeacherMessageRead',
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
            ReadInfo: res.data
          })
        }
      });
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
  onChange({ detail }) {
    // 需要手动对 checked 状态进行更新
    this.setData({ checked: detail });
  }
})