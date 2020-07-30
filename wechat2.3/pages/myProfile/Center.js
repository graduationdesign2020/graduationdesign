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
    auth: wx.getStorageSync('auth'),
  },

  onLoad: function () {
    var that = this
    PostRequest('/getUserData', {}, (data)=>{that.setData({userData: data})})
    if(this.data.auth === ''){
      PostRequest('/getAuth',{}, (data)=>{
        that.setData({auth: data}) 
        wx.setStorageSync('auth', data)
      })
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
    PostRequest('/mylogout', {}, (data)=>{
      if(data.msg == 'SUCCESS'){
        this.setData({msg: "注销成功", dialog: true, show: false});
        wx.setStorageSync('jwt', "")
        wx.setStorageSync('auth', "") 
        console.log(1) 
        console.log(wx.getStorageSync('auth'))  
        app.globalData.userInfo = {};
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