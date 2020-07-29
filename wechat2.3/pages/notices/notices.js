// pages/notices/notices.js
//获取应用实例
const app = getApp()
import {PostRequest} from "../../utils/ajax";

Page({
  data: {
    type: 2,
    notices: [
      {title: '标题', id: 1, isread: false, reading: 10, unread: 2, teachername: '娘口三三', time: '07-01', content: '内容'},
      {title: '标题', id: 1, isread: false, reading: 10, unread: 2, teachername: '猫咪老师', time: '07-01', content: '内容'}
    ],
    auth: wx.getStorageSync('auth'),
    isRefresh:false,
  },

  onLoad: function(options) {
    var that = this;
    console.log(this.data.auth)
    if(this.data.auth == ''){
      PostRequest('/getAuth',{}, (data)=>{
      that.setData({auth: data})
      })
    }
    switch (options.type) {
    case "0": {
      PostRequest('/getSchoolNotices', {}, that.setNotices);
      break;
    }
    case "1": {
      PostRequest('/getDepartmentNotices', {}, that.setNotices);
      break;
    }
    case "2": {
      var pages = getCurrentPages();
      var prevPage = pages[pages.length - 2]; //上一个页面
      prevPage.setData({isRefresh: true})
      PostRequest('/getTeacherMessages', {}, that.setNotices);
      break;
    }
  }
 },
    
  onShow:function(e){
    // 返回时刷新页面
    var that=this
    if (that.data.isRefresh==true){
      console.log("refresh")
      PostRequest('/getTeacherMessages', {}, that.setNotices);
    }   
  },

  setNotices: function(data){
    this.setData({notices: data});
    console.log(this.data.notices)
  },

  detail: function(e) {
    var that = this;
    if(this.data.type=="2") {
      if(this.data.auth) {
        wx.navigateTo({
          url: '../reading/reading?id=' + e.target.dataset.id,
        })
      }
      else {
        wx.navigateTo({
          url: '../noticeDetail/noticeDetail?type=' + that.data.type + '&id=' + e.target.dataset.id + '&reading_id=' + e.target.dataset.reading_id,
        })
      }
    }
    else {
      wx.navigateTo({
        url: '../noticeDetail/noticeDetail?type=' + that.data.type + '&id=' + e.target.dataset.id,
      })
    }
  }
})