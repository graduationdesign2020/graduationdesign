// pages/notices/notices.js
//获取应用实例
const app = getApp()
import {PostRequest} from "../../utils/ajax";

Page({
  data: {
    type: 2,
    notices: [
      {title: '学生信息收集', type: 0, id: 1, reading_id: 1, isread: false, reading: 10, unread: 2, teachername: '娘口三三', time: '07-01', content: '请在输入框中输入指定信息blablablabla............................'},
      {title: '学生信息收集', type: 0, id: 3, reading_id: 3, isread: false, reading: 10, unread: 2, teachername: '娘口三三', time: '07-01', content: '请在输入框中输入指定信息blablablabla............................'},
      {title: '学生信息收集', type: 1, id: 2, reading_id: 2, isread: false, reading: 10, unread: 2, teachername: '娘口三三', time: '07-01', content: '请在输入框中输入指定信息blablablabla............................'}
    ],
    auth: wx.getStorageSync('auth'),
    isRefresh:false,
  },

  onLoad: function(options) {
    var that = this;
    that.setData({type: options.type})
    if(this.data.auth === ''){
      PostRequest('/getAuth',{}, (data)=>{
        that.setData({auth: data})
        wx.setStorageSync('auth', data)
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
      this.setData({isRefresh: false});
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
        if(e.currentTarget.dataset.type==0) {
          wx.navigateTo({
            url: '../reading/reading?id=' + e.currentTarget.dataset.id,
          })
        }
        else {
          wx.navigateTo({
            url: '../studentReply/studentReply?id=' + e.currentTarget.dataset.id,
          })
        }
      }
      else {
        if(e.currentTarget.dataset.type==0) {
          wx.navigateTo({
            url: '../noticeDetail/noticeDetail?type=' + that.data.type + '&id=' + e.currentTarget.dataset.id + '&reading_id=' + e.currentTarget.dataset.reading_id,
          })
        }
        else {
          wx.navigateTo({
            url: '../reply/reply?id=' + e.currentTarget.dataset.id + '&reading_id=' + e.currentTarget.dataset.reading_id,
          })
        }
      }
    }
    else {
      wx.navigateTo({
        url: '../noticeDetail/noticeDetail?type=' + that.data.type + '&id=' + e.currentTarget.dataset.id,
      })
    }
  }
})