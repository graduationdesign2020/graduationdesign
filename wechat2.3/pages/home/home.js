//index.js

//获取应用实例
const app = getApp();
import {PostRequest} from "../../utils/ajax";
import {NoticeRequest} from "../../utils/ajax";

Page({
  data: {
    searchValue: '',
    teacherCommonAPPs: [
      {icon: 'comment-o', text: '发送', url: "/pages/SendNotice/SendNotice"},
      {icon: 'chat-o', text: '已发送', url: '/pages/notices/notices?type=2'},
      {icon: 'records', text: '成绩', url: '/pages/studentScore/studentScore'},
      {icon: 'clock-o', text: '截止时间' , url: '/pages/postddl/postddl'}
    ],
    studentCommonAPPs: [
      {icon: 'records', text: '成绩', url: '/pages/myScore/myScore'}
    ],
    schoolNotices: [],
    deptNotices: [],
    teacherMessages: [],
    auth: wx.getStorageSync('auth'),
    isRefresh:false
  },
  onLoad: function() {
    console.log("home") 
    console.log(wx.getStorageSync('auth')) 
    this.setData({auth: wx.getStorageSync('auth')})
    console.log(this.data.auth)
    var that = this
    if(this.data.auth === ''){
      PostRequest('/getAuth',{}, (data)=>{
        that.setData({auth: data})
        wx.setStorageSync('auth', data)
      })
    }
    NoticeRequest('/getThreeSchoolNotices', {}, that.setSchoolNotices)
    NoticeRequest('/getThreeDepartmentNotices', {}, that.setDeptNotices)
    PostRequest('/getTeacherMessages', {}, that.setTeacherMessages);
  },

  setSchoolNotices: function(data){
    this.setData({schoolNotices: data});
  },

  setDeptNotices: function(data){
    this.setData({deptNotices: data});
  },

  setTeacherMessages: function(data){
    console.log(data)
    this.setData({teacherMessages: data});
  },

  setSearchResults: function(data) {
    this.setData({
      deptNotices: data.deptNotices,
      schoolNotices: data.schoolNotices,
      teacherMessages: data.teacherMessages
    })
  },
    
  onSearch() {
    console.log(this.data.searchValue)
    wx.navigateTo({
      url: '../search/search?searchValue=' + this.data.searchValue,
    })
    
  },

  onShow:function(e){
    var that=this
    // 返回时刷新页面
    if (that.data.isRefresh==true){
      console.log("refresh")
      PostRequest('/getTeacherMessages', {}, that.setTeacherMessages);
      this.setData({isRefresh: false});
    }   
  }
})