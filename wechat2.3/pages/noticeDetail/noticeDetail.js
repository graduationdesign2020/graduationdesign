// pages/noticeDetail/noticeDetail.js
//获取应用实例
const app = getApp()
import {PostRequest} from "../../utils/ajax";

Page({
  data: {
    type: 0,
    notice: {title: '学生信息收集', type: 0, id: 1, reading_id: 1, isread: false, reading: 10, unread: 2, teachername: '娘口三三', time: '07-01', content: '请在输入框中输入指定信息blablablabla............................'},
  },

  onLoad: function(options) {
    var that = this;
    this.setData({type: options.type})
    switch (options.type) {
      case "0": {
        PostRequest('/getSchoolNotice', {id: options.id}, that.setNotice);
        break;
      }
      case "1": {
        PostRequest('/getDepartmentNotice', {id: options.id}, that.setNotice);
        break;
      }
      case "2": {
        var pages = getCurrentPages();
        var prevPage = pages[pages.length - 2]; //上一个页面
        prevPage.setData({isRefresh: true}) 
        PostRequest('/getTeacherMessage', {id: options.id, reading_id: options.reading_id}, that.setNotice);
        break;
      }
    }
  },
  
  setNotice: function(data){
    this.setData({notice: data});
  }
})