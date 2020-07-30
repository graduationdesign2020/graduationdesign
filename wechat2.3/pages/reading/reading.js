// pages/reading/reading.js
//获取应用实例
const app = getApp()
import {PostRequest} from "../../utils/ajax";

Page({

  /**
   * 页面的初始数据
   */
  data: {
    notice: {title: '毕业设计选题公告', id: 1, time: '07-01', content: '内容'},
    checked: true,
    ReadInfo: {
      studentsRead: [
        {name: "夏目贵志"}
      ],
      studentsUnread: [
        {name: "夏目玲子"},
        {name: "名取周一"}
      ],
      Read: 1,
      unRead: 2
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    PostRequest('/teacherGetTeacherMessage', {id: options.id}, that.setNotice);
    PostRequest('/getTeacherMessageRead', {id: options.id}, that.setReadInfo);
  },

  setNotice: function(data){
    this.setData({notice: data});
  },

  setReadInfo: function(data){
    this.setData({ReadInfo: data});
  },

  onChange({ detail }) {
    // 需要手动对 checked 状态进行更新
    this.setData({ checked: detail });
  }
})