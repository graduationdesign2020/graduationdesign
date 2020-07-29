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
      console.log(this.handleAuth())
      var that = this
      wx.request({
        url: 'http://localhost:8888/register',
        data:{
          name: this.data.name, 
          id: this.data.id, 
          auth: this.handleAuth(), 
          openid: app.globalData.userData.openid
        },
        method: "POST",
        success(res){that.handleMsg(res.data)}
      })
    }
    console.log(this.data);
  },

  handleMsg: function(data) {
    if(data.msg == 'SUCCESS'){
      app.globalData.userData = data.userData;
      app.globalData.login = 1;
      this.setData({msg: "注册成功", dialog: true, show: false});
      var logindata = {openid: data.userData.openid, id: data.userData.id}
      var that = app
          wx.request({
            url: 'http://localhost:8888/login',
            method: 'POST',
            header: {'Content-Type': 'application/json'},
            data: logindata,
            success(res) {
                var c = JSON.stringify(res.cookies)
                var s = c.substring(2, c.length-2);
                console.log(s)
                wx.setStorageSync('cookies', s.toString())
                console.log(wx.getStorageSync('cookies'))
                that.globalData.get_cookie = 1
                if(that.cookieCallbacks){
                  console.log(that.cookieCallbacks)
                  for ( var i = 0; i <that.cookieCallbacks.length; i++){
                    console.log(that.cookieCallbacks[i]);
                    console.log(that.cookieCallbacks[i].url)
                    PostRequest(that.cookieCallbacks[i].url, that.cookieCallbacks[i].data, that.cookieCallbacks[i].callback)
                }
                }
                that.cookieCallbacks = null
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