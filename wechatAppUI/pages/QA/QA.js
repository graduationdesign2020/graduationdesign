// pages/QA/QA.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: {auth: false, name: "小明"},
    active: "QA"
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

  onChange(e) {
    console.log(e.detail);
    switch (e.detail) {
      case "myprofile": {
        console.log(e.detail)
        wx.navigateTo({
          url: '../myProfile/Center',
        })
      }
      case "QA": {
        wx.navigateTo({
          url: '../QA/QA',
        })
      }
      case "studentFinished": {
        wx.navigateTo({
          url: '../processList/processList',
        })
      }
      case "procedure": {
        wx.navigateTo({
          url: '../procedure/procedure',
        })
      }
      case "home": {
        wx.navigateTo({
          url: '../home/home',
        })
      }
    }
  }
})