// pages/Center/Center.js
var app = getApp();
import {PostRequest} from "../../utils/ajax";

Page({
  data: {
    userInfo: {},
    userData: {},
    show: false,
    dialog: false,
    msg: "",
  },

  onLoad: function () {
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
            this.setData({userData: data.userData})
          }
        }
      }else{
        this.setData({userData: app.globalData.userData});
      }
    }
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
      })
    } else {
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
        })
      }
    } 
  },

  logout: function() {
    this.setData({show: true});
    PostRequest('/logout', {id: this.data.userData.id, auth: this.data.auth, code: app.globalData.code}, (data)=>{
      if(data.msg == 'SUCCESS'){
        this.setData({msg: "注销成功", dialog: true, show: false});
        app.globalData.userInfo = {};
        app.globalData.login = 2;
      } else {
        this.setData({msg: "注销失败", dialog: true, show: false});
      }
    })
  },

  confirmMsg: function(){
    if(this.data.msg == "注销成功"){
      wx.redirectTo({
        url: '../register/index',
      })
    }else{
      this.setData({dialog: false});
    }
  },
})