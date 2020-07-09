// pages/processList/processList.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    process: [
      {title: "提交开题报告", time: "2020-01-01", content: "无", finished: 12, notfinished: 2 },
      {title: "中期检查", time: "2020-03-09", content: "无", finished: 12, notfinished: 0 },
      {title: "答辩", time: "2020-06-09", content: "无", finished: 11, notfinished: 1 }
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

  },

  procedure: function() {
    wx.navigateTo({
      url: '../studentFinished/studentFinished',
    })
  }
})