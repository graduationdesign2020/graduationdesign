//app.js
var appId = "wx19b7eb8e43f64ebb";
var secret = 'bba19316505d34868fad83b0ccf468d3';

App({
  
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        var code = res.code;
        console.log( "wx login code: " + code)
        this.globalData.code = code
        var that = this
        wx.request({
          url: 'http://54.234.98.178:8301/core/getOpenid',
          data: {
            code: res.code
          },
          method: "POST",
          success(res) {
            var openid = res.data
            that.globalData.openid = openid
          }
        }
      )
      }    
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          wx.getUserInfo({
            success: res => {
              this.globalData.userInfo = res.userInfo;
              
               if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo: "",
    code: "",
    openid: ""
  },
})