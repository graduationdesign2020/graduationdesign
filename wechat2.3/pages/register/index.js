import {PostRequest} from "../../utils/ajax"
//获取应用实例
const app = getApp()

Page({
  data: {
    name: '',
    id: '',
    tab: 0,
    errorMessage1: '',
    errorMessage2:'',
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    show: false,
    dialog: false,
    msg: "",
  },

  nameInput(event) {
    this.setData({
      name: event.detail.value
    })
  },
  idInput(event) {
    this.setData({
      id: event.detail.value
    })
  },
  onClickButton() {
    this.setData({errorMessage1: "", errorMessage2: ""})
    if(!this.data.name){
      this.setData({errorMessage1: "姓名不能为空"})
    }
    if(!this.data.id){
      this.setData({errorMessage2: "号码不能为空"})
    }
    console.log(this.data)
    if(this.data.id && this.data.name){
      this.setData({show: true});
      if(!app.globalData.openid){
        this.setData({msg: "注册失败", dialog: true, show: false});
        return;
      }
      var that = this
      wx.request({
        url: 'http://54.234.98.178:8301/core/register',
        data:{
          name: this.data.name,
          id: this.data.id, 
          auth: this.handleAuth(), 
          openid: app.globalData.openid
        },
        method: "POST",
        success(res){that.handleMsg(res.data)}
      })
    }
    console.log(this.data);
  },

  handleMsg: function(data) {
    var that=this;
    if(data.msg == 'SUCCESS'){
      wx.setStorageSync('auth', that.data.tab)
      console.log("register") 
      console.log(wx.getStorageSync('auth')) 
      wx.request({
        url: 'http://54.234.98.178:8301/auth/oauth/token?grant_type=password&username='+this.data.id+'&password='+app.globalData.openid,
        method: "POST",
        header: {"Authorization": "Basic d2VjaGF0bWluaWFwcDpnZG1zbWluaXByb2dyYW0="},
        success(res){
          if(res.statusCode == 200){
            wx.setStorageSync('jwt', res.data.access_token)
            that.setData({msg: "注册成功", dialog: true, show: false});
          }
          else{
            that.setData({msg: "登陆失败", dialog: true, show: false});
          }
        },
        fail(res){
          that.setData({msg: "注册失败", dialog: true, show: false});
        }
      })
    }
    if(data.msg == 'REGISTERED'){
      this.setData({msg: "注册失败", dialog: true, show: false});
      this.setData({errorMessage2: "被注册过的号码"})
    }
    if(data.msg == 'WRONG'){
      this.setData({msg: "注册失败", dialog: true, show: false});
      this.setData({errorMessage2: "不匹配的姓名与号码"})
    }
  },

  confirmMsg: function(){
    if(this.data.msg == "注册成功"){
      wx.redirectTo({
        url: '../home/home',
      })
    }else{
      this.setData({dialog: false});
    }
  },

  bindGetUserInfo (event) {
    this.onClickButton();
  },

  onClickTab (event) {
    this.setData({tab: event.detail.name})
  },

  handleAuth: function() {
    switch(this.data.tab) {
      case 0: return 0;      
      case 1: return 1;
    }
  }
})