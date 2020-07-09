// pages/Center/Center.js
Page({
  data: {
    userInfo: {},
  },

  onLoad: function () {
    var that = this;
      wx.getUserInfo({
        success: res => {
          that.setData({
            userInfo: res.userInfo
          })
        }
      });
  },
})