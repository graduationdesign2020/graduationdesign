// pages/reply/reply.js
//获取应用实例
const app = getApp()
import {PostRequest} from "../../utils/ajax";

Page({
  data: {
    notice: {title: '学生信息收集', type: 1, id: 1, reading_id: 1, isread: false, reading: 10, unread: 2, teachername: '娘口三三', time: '07-01', content: '请在输入框中输入指定信息blablablabla............................'},
    reply: [
      {key: "电话", value: "10086"},
      {key: "邮箱", value: "12345@sjtu.edu.cn"},
      {key: "政治面貌", value: "群众"},
      {key: "技能", value: "夏目破颜拳"}
    ],
    msg: "",
    dialog: false,
    waitshow: false,
    error: false,
  },

  onLoad: function(options) {
    var that = this;
    PostRequest('/getReplyMessage', {id: options.id, reading_id: options.reading_id}, that.setNotice);
  },
  
  setNotice: function(data){
    this.setData({notice: data.MessageInfo, reply: data.reply});
  },

  onChange: function(event) {
    this.data.reply[event.currentTarget.dataset.index].value = event.detail;
    this.setData({reply: this.data.reply});
  },

  sendReply: function(event) {
    for (let index = 0; index < this.data.reply.length; index++){
      if(!this.data.reply[index].value){
        this.setData({
          error: true,
          msg: this.data.reply[index].key + "不可为空"
        })
        return;
      }
    }
    this.setData({
      waitshow: true,
    })
    PostRequest("/sendReply", {id: this.data.notice.id, reading_id: this.data.notice.reading_id, reply: this.data.reply}, (data) => {
      if(data.msg == "SUCCESS"){
        var pages = getCurrentPages();
        var prevPage = pages[pages.length - 2]; //上一个页面
        prevPage.setData({isRefresh: true}) 
        this.data.notice.isread = true;
        this.setData({
          waitshow: false,
          dialog: true,
          msg: "发送成功",
          notice: this.data.notice,
        })
        PostRequest('/getReplyMessage', {id: this.data.notice.id, reading_id: this.data.notice.reading_id}, this.setNotice);
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

  confirm: function(event) {
    this.setData({msg: null, dialog: false});
  },
})