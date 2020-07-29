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
    auth_boolean: false,
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
            console.log(data);
            this.setData({userData: data.userData})
          }
        }
      }else{
        this.setData({userData: app.globalData.userData});
        console.log(app.globalData);
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
    PostRequest('/mylogout', {id: this.data.userData.id, auth: this.data.auth, openid: app.globalData.userData.openid}, (data)=>{
      if(data.msg == 'SUCCESS'){
        this.setData({msg: "注销成功", dialog: true, show: false});
        app.globalData.userInfo = {};
        app.globalData.login = 2;
        wx.request({
          url: 'http://localhost:8888/logout',
        })
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