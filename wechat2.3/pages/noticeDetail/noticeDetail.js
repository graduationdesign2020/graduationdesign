// pages/noticeDetail/noticeDetail.js
//获取应用实例
const app = getApp()
import {PostRequest} from "../../utils/ajax";

Page({
  data: {
    type: 0,
    notice: {
      title: '标题', id: 1, is_read: false, teacher_id: '12345', student_id: '11111', time: '07-01', content: '内容'
    },
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