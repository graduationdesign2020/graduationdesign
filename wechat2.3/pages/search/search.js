// pages/search/search.js
const app = getApp()
import {PostRequest} from '../../utils/ajax'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    auth: wx.getStorageSync('auth'),
    userData: {},
    searchValue: '',
    currentPage: 1,
    searchMessages: [{id: 1,type:1, title: '参数的传递', time: '2020-08-19', content: '啊擦都洞的佛v觉得覅v哦地方v哦v地方v那地方v你吧v官方撒吃撒地产市场的食不果腹DVD是'}]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({searchValue: options.searchValue})
    var that = this
    PostRequest('/getUserData', {}, (data)=>{that.setData({userData: data}); that.onSearch()})
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    var page = this.data.currentPage + 1
    var data = {dept: this.data.userData.dept, id: this.data.userData.id, keywords: this.data.searchValue, pageNo: page, pageSize: 5}
    var that = this
    PostRequest('search', data, (res)=>{that.setData({currentPage: page, ["searchMassages["+page+"]"]: res})})
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  onSearch: function () {
    var data = {dept: this.data.userData.dept, id: this.data.userData.id, keywords: this.data.searchValue, pageNo: 1, pageSize: 5}
    var that = this
    //PostRequest('search', data, (res)=> {that.setData({["searchMessages["+0+"]"]: res})})
  },

  detail: function(e) {
    console.log(e.currentTarget.dataset)
    var that = this;
    if(e.currentTarget.dataset.type==2) {
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