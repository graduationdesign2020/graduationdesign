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
      this.setData({msg: "注册成功", dialog: true, show: false});
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