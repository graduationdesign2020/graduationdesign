// pages/myScore/myScore.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    procedures:[
      {
        name:"选题确认",
        state:3,
      },
      {
        name:"开题报告",
        state:1,
      },
      {
        name:"第一阶段检查",
        state:4,
        score:90
      },
      {
        name:"中期检查",
        state:4,
        score:90
      },
      {
        name:"设计(论文)定稿",
        state:5
      }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})