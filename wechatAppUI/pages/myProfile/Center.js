// pages/Center/Center.js
Page({
  data: {
    userInfo: {},
    user: {auth: false, name: "小明"},
    active: "myprofile"
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

  logout: function() {
    wx.navigateTo({
      url: '../index/index',
    })
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
      case "home": {
        wx.navigateTo({
          url: '../home/home',
        })
      }
    }
  }
})