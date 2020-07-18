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
    if(!name){
      this.setData({errorMessage1: "姓名不能为空"})
    }
    if(!id){
      this.setData({errorMessage2: "姓名不能为空"})
    }
    if(id && name){
      this.setData({show: true});
      PostRequest('/register', {name: this.data.name, id: this.data.id, auth: this.data.tab}, handleData)
    }
  },

  handleMsg: function(data) {
    if(data.msg == 'SUCCESS'){
      app.globalData.userData = data.userData;
      app.globalData.login = 1;
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
})