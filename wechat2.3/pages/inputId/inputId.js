// pages/inputId/inputId.js

const { PostRequest } = require("../../utils/ajax")

const app = getApp()

Page({
  data: {
    errormessage: "",
    id: "",
    show: false,
    successdialog: false,
    faildialog: false
  },

  idInput: function(event){
    this.setData({id: event.detail})
  },

  confirmSuccess: function(event){
    wx.redirectTo({
      url: '/pages/home/home',
    })
  },

  confirmFail: function(event){
    this.setData({faildialog: false})
  },

  onClickRegister: function(event){
    wx.redirectTo({
      url: '/pages/register/index',
    })
  },

  onClick: function(event){
    this.setData({errormessage: ""})
    if(!this.data.id){
      this.setData({errorMessage: "不能为空"})
      return
    }
    this.setData({show: true});
    console.log('show')
    var that = this
    wx.request({
      url: 'http://localhost:8888/login',
      method: "POST",
      data: {
        id: this.data.id,
        openid: app.globalData.openid
      },
      success(res){
        wx.setStorageSync('jwt', res.header['Authorization'])
        PostRequest('/getAuth', {}, (data)=>{
          wx.setStorageSync('auth', data)
        })
        that.setData({successdialog: true, show: false})
      },
      fail(res){
        that.setData({faildialog: true, show: false})
      }
    })
  }
})