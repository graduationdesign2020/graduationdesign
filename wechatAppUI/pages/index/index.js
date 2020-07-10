//获取应用实例
const app = getApp()

Page({
  data: {
    name: '',
    id: '',
    tab: 0,
    errorMessage: ''
  },
  onClickTab(event) {
    this.setData({
      name: '',
      id: '',
      tab: event.detail.index
    })
  },
  nameInput(event) {
    this.setData({
      name: event.detail.value
    })
  },
  idInput(event) {
    this.setData({
      id: event.detail.value
    })
  },
  onClickButton() {
    wx.navigateTo({
      url: '../home/home'
    })
  }
})