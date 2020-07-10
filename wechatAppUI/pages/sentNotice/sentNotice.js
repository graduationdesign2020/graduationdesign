// pages/sentNotice/sentNotice.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    checked: true,
    notices:[
      {
        title:"通知1",
        time:"2020-7-9",
        ifAllRead:0
      },
      {
        title:"通知2",
        time:"2020-7-8",
        ifAllRead:1
      },
      {
        title:"通知3",
        time:"2020-7-7",
        ifAllRead:1
      },
      {
        title:"通知4",
        time:"2020-7-6",
        ifAllRead:1
      },
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
  onChange({ detail }) {
    // 需要手动对 checked 状态进行更新
    this.setData({ checked: detail });
  },
  showPopup() {
    this.setData({ show: true });
  },

  onClose() {
    this.setData({ show: false });
  },
})