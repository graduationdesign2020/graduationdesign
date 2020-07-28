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
    if(this.data.id && this.data.name){
      this.setData({show: true});
      console.log(this.handleAuth())
      PostRequest('/register', {name: this.data.name, id: this.data.id, auth: this.handleAuth(), openid: app.globalData.userData.openid}, this.handleMsg)
    }
    console.log(this.data);
  },

  handleMsg: function(data) {
    if(data.msg == 'SUCCESS'){
      app.globalData.userData = data.userData;
      app.globalData.login = 1;
      this.setData({msg: "注册成功", dialog: true, show: false});
      wx.login({
        success: res => {
          // 发送 res.code 到后台换取 openId, sessionKey, unionId
          var code = res.code;
          PostRequest("/mylogin", {code: res.code}, (data)=>{
            if(data.msg == "SUCCESS"){
              app.globalData.login = 1;
              app.globalData.userData = data.userData;
            }
            if(data.msg == "FAIL"){
              app.globalData.login = 2;
              app.globalData.userData = data.userData;
            }
            if (app.dataCallback){
              app.dataCallback(data);
             }
          })   
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