// pages/reading/reading.js
//获取应用实例
const app = getApp()
import {PostRequest} from "../../utils/ajax";

Page({

  /**
   * 页面的初始数据
   */
  data: {
    notice: {title: '学生信息收集', type: 0, id: 1, reading_id: 0, isread: false, reading: 10, unread: 2, teachername: '娘口三三', time: '07-01', content: '请在输入框中输入指定信息blablablabla............................'},
    checked: true,
    ReadInfo: {
      studentsRead: [
        {name: "夏目贵志", id: "11111"}
      ],
      studentsUnread: [
        {name: "夏目玲子", id: "22222"},
        {name: "名取周一", id: "33333"}
      ],
      read: 1,
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
    console.log(data)
    this.setData({ReadInfo: data});
  },

  onChange({ detail }) {
    // 需要手动对 checked 状态进行更新
    this.setData({ checked: detail });
  }
})