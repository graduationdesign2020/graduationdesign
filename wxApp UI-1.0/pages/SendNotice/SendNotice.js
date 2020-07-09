// pages/SentNotice/SendNotice.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    show:  false,
    list: ['小明', '小红', '李华', '小张', '小李', '小王'],
    result: []
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

  showBottom: function() {
    this.setData({show: true})
  },

  hideBottom: function() {
    this.setData({show: false})
  },

  toggle: function(event) {
    const { index } = event.currentTarget.dataset;
    const checkbox = this.selectComponent(`.checkboxes-${index}`);
    checkbox.toggle();
  },

  onChange: function(event) {
    const { key } = event.currentTarget.dataset;
    this.setData({ [key]: event.detail });
  },

  noop: function() {}
})