//app.js
var appId = "wxcea5614102cbce8d";
var secret = '59fee20c47bd73cb780a34409f4d5ca1';

import {PostRequest} from "./utils/ajax";

App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        var code = res.code;
        PostRequest("/login", {code: res.code}, (data)=>{
          if(data.msg == "SUCCESS"){
            this.globalData.login = 1;
            this.globalData.userData = data.userData;
          }
          if(data.msg == "FAIL"){
            this.globalData.login = 2;
            this.globalData.userData = data.userData;
          }
          if (this.dataCallback){
            this.dataCallback(data);
           }
        })   
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
    userData: "",
    login: 0, // 0: unchecked 1: success 2: fail 
  }
})