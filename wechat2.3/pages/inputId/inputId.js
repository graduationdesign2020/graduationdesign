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
    if(!app.globalData.openid){
      that.setData({faildialog: true, show: false})
      return
    }
    var that = this
    wx.request({
      url: 'http://54.234.98.178:8301/auth/oauth/token?grant_type=password&username='+this.data.id+'&password='+app.globalData.openid,
      method: "POST",
      header: {"Authorization": "Basic d2VjaGF0bWluaWFwcDpnZG1zbWluaXByb2dyYW0="},
      success(res){
        if(res.statusCode == 200){
          wx.setStorageSync('jwt', res.data.access_token)
          PostRequest('/getAuth', {}, (data)=>{
          wx.setStorageSync('auth', data)
          })
          that.setData({successdialog: true, show: false})
        }
        else{
          that.setData({faildialog: true, show: false})
        }
      },
    })
  }
})