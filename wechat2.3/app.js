//app.js
var appId = "wxcea5614102cbce8d";
var secret = '59fee20c47bd73cb780a34409f4d5ca1';

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
          url: 'http://54.167.148.196:8888/getOpenid',
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