// pages/studentReply/studentReply.js
//获取应用实例
const app = getApp()
import {PostRequest} from "../../utils/ajax";

Page({

  /**
   * 页面的初始数据
   */
  data: {
    notice: {title: '学生信息收集', id: 1, time: '07-01', content: '请在输入框中输入指定信息blablablabla............................'},
    reply: false,
    unReply: false,
    activeNames: [],
    ReplyInfo: {
      studentsReply: [{
        name: "夏目贵志",
        id: '33333',
        reply: [
          {key: "电话", value: "10086"},
          {key: "邮箱", value: "12345@sjtu.edu.cn"},
          {key: "政治面貌", value: "群众"},
          {key: "技能", value: "夏目破颜拳"},
        ]
      },
      {
        name: "多轨透",
        id: '44444',
        reply: [
          {key: "电话", value: "10086"},
          {key: "邮箱", value: "12345@sjtu.edu.cn"},
          {key: "政治面貌", value: "群众"},
          {key: "技能", value: "撸猫"},
        ]
      }
    ],
      studentsUnreply: [
        {name: "夏目玲子", id: "11111"},
        {name: "名取周一", id: "22222"}
      ],
      reply: 2,
      unReply: 2
    },
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    PostRequest('/teacherGetTeacherMessage', {id: options.id}, that.setNotice);
    PostRequest('/getTeacherMessageReply', {id: options.id}, that.setReplyInfo);
  },

  setNotice: function(data){
    this.setData({notice: data});
  },

  setReplyInfo: function(data){
    this.setData({ReplyInfo: data});
  },

  onChange(event) {
    this.setData({
      activeNames: event.detail,
    });
  },

  unReplyChange({ detail }) {
    this.setData({ unReply: detail });
  },

  replyChange({ detail }) {
    this.setData({ reply: detail });
  }
})