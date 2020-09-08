// pages/search/search.js
const app = getApp()
import {SearchRequest, PostRequest} from '../../utils/ajax'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    auth: wx.getStorageSync('auth'),
    userData: {},
    searchValue: '',
    query:'',
    currentPage: 1,
    searchMessages: [{id: 1,type:1, title: '参数的传递', content: '啊擦都洞的佛v觉得覅v哦地方v哦v地方v那地方v你吧v官方撒吃撒地产市场的食不果腹DVD是'}]
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
  onReachBottom: function () {
        var page = this.data.currentPage + 1
        getQuery(page);
        var that = this
        console.log("reach bottom")
        SearchRequest('/_search', that.data.query, (res)=>{
          console.log(res)
          that.setData({
            ["searchMessages["+page+"]"]: res.hits.hits, currentPage: page
          })
        })
      },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  onSearch: function () {
        this.getQuery(0);
        var that = this
        SearchRequest('/_search', that.data.query, (res)=> {
          console.log("init", res)
          that.setData({
            ["searchMessages["+ 0 +"]"]: res.hits.hits, 
            currentPage: 0
          })
        })
      },
      getQuery: function(page){
      var that = this
          that.setData("query","{\"size\":"+6+",\"from\":"+6*page+",\"query\": {\"bool\": {\"must\":[{\"bool\":{\"should\": [{\"match\": {\"title\":\""+this.data.searchValue+"\"}},{\"match\":{\"content\": \""+this.data.searchValue+"\"}}]}},{\"bool\":{\"should\":[{ \"term\": {\"type\": 0}},{\"bool\":{\"must\": [{\"term\": {\"type\": 1}},{\"match\": {\"department\":\""+this.data.userData.dept+"\"}}]}},{\"bool\":{\"must\": [{\"term\": {\"type\": 2}},{\"match\": {\"student\":\""+this.data.userData.id+"\"}}]}}]}}]}}}")
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