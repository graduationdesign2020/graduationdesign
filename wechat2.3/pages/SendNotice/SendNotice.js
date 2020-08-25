// pages/SentNotice/SendNotice.js
import {PostRequest} from "../../utils/ajax";
var app = getApp();  

Page({
  data: {
    show:  false,
    list: [{id: 1, name: "董思成"},
     {id: 2, name: "李东赫"}, 
     {id: 3, name: "李马克"},
     {id: 4, name: "中本悠太"}],
    result: [],
    msg: "",
    dialog: false,
    waitshow: false,
    error: false,
    text: "",
    title: "",
    all:[],
    troggleAll: false,
    num: 0,
    task: "",
    tasks: [],
    userData: null
  },

  onLoad: function () {
    var that = this;
    PostRequest('/teacherGetStudents',{}, that.getList);
    PostRequest('/getUserData', {}, (data)=>{that.setData({userData: data})})
  },

  getList: function(data) {
    this.setData({list: data});
  },

  showBottom: function() {
    this.setData({show: true})
  },

  hideBottom: function() {
    this.setData({show: false})
  },

  toggle: function(event) {
    const { index } = event.currentTarget.dataset;
    const checkbox = this.selectComponent(`.checkboxes-${index}`);
    checkbox.toggle();
  },

  onChange: function(event) {
    const { key } = event.currentTarget.dataset;
    this.setData({ [key]: event.detail });
    if(!this.data.troggleAll && key != "all" && this.data.all == "all"){
      const checkall = this.selectComponent(`.all`);
      checkall.toggle();
    }
    console.log(this.data.result.length)
  },

  noop: function() {},

  sendNotice: function(event) {
    if(!this.data.title){
      this.setData({
        error: true,
        msg: "标题不可为空"
      })
      return;
    }
    if(!this.data.text){
      this.setData({
        error: true,
        msg: "正文不可为空"
      })
      return;
    }
    if(this.data.result.length == 0){
      this.setData({
        error: true,
        msg: "请至少选择一个学生"
      })
      return;
    }
    this.setData({
      waitshow: true,
    })
    PostRequest("/sendMessages", {id: this.data.userData.id,students: this.data.result, title: this.data.title, content: this.data.text, tasks: this.data.tasks}, (data) => {
      if(data.msg == "SUCCESS"){
        this.setData({
          waitshow: false,
          dialog: true,
          msg: "发送成功"
        })
        var pages = getCurrentPages();
        var prevPage = pages[pages.length - 2]; //上一个页面
        prevPage.setData({isRefresh: true}) 
      }else{
        this.setData({
          waitshow: false,
          dialog: true,
          msg: "发送失败"
        })
      }
    })
  },
  confirmError: function(event) {
    this.setData({msg: null, error: false});
  },

  confirm: function(event){
    wx.navigateBack({
      delta: 1,
    })
  },

  cancel: function(event) {
    this.setData({msg: null, dialog: false});
  },

  textChange: function(event) {
    this.setData({text: event.detail});
  },

  taskChange: function(event) {
    this.setData({task: event.detail});
  },

  setTask: function() {
    if(!this.data.task){
      this.setData({
        error: true,
        msg: "回复不可为空"
      })
      return;
    }
    this.data.tasks.push(this.data.task)
    this.setData({tasks: this.data.tasks, task: ""});
  },

  titleChange: function(event) {
    this.setData({title: event.detail});
  },

  onClose: function(event) {
    this.data.tasks.splice(event.currentTarget.dataset.index, 1)
    this.setData({tasks: this.data.tasks});
  },

  chooseAll: function() {
    if(this.data.all == "all"){
      const checkall = this.selectComponent(`.all`);
      checkall.toggle();
      for (let index = 0; index < this.data.list.length; index++) {
       if (this.data.result.indexOf(this.data.list[index].id.toString()) != -1){
          var checkbox = this.selectComponent(`.checkboxes-${index}`);
         checkbox.toggle();
        }
      }
    }else{
      const checkall = this.selectComponent(`.all`);
      checkall.toggle();
      this.setData({troggleAll: true});
      for (let index = 0; index < this.data.list.length; index++) {
       if (this.data.result.indexOf(this.data.list[index].id.toString()) == -1){
          var checkbox = this.selectComponent(`.checkboxes-${index}`);
         checkbox.toggle();
        }
      }
      this.setData({troggleAll: false});
    }
    
  }
})