// pages/noticeDetail/noticeDetail.js
//获取应用实例
const app = getApp()
import {PostRequest} from "../../utils/ajax";

Page({

  /**
   * 页面的初始数据
   */
  data: {
    type: 0,
    notice: {
      title: '标题', id: 1, is_read: false, teacher_id: '12345', student_id: '11111', time: '07-01', content: '内容'
    },
    userData: {name: "小明", dept: "SE", auth: 1, id: 12345}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    if(app.globalData.login == 2){
      wx.redirectTo({
        url: '../register/index',
      })
    }else{
      if(app.globalData.login == 0){
        app.dataCallback = (data) => {
          if(data.msg == "FAIL"){
            wx.redirectTo({
              url: '../register/index',
            })
          }else{
            this.setData({userData: data.userData, type: options.type})
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
          }
        }
      }else{
        this.setData({userData: app.globalData.userData, type: options.type});
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
      }
    }
  },
  
  setNotice: function(data){
    this.setData({notice: data});
  }
})