//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    searchValue: '',
    commonAPPs: [
      {icon: 'star-o', text: '公告', url: '/pages/home/home'},
      {icon: 'comment-o', text: '发送', url: '/pages/SendNotice/SendNotice'},
      {icon: 'chat-o', text: '消息', url: '/pages/notices/notices'},
      {icon: 'records', text: '分数', url: '/pages/myScore/myScore'}
    ],
    notices: [
      {title: '毕业设计选题公告', value: '置顶', label: '内容1', url: '/pages/home/home'},
      {title: '优秀论文评选结果', value: '置顶', label: '内容2', url: '/pages/home/home'},
      {title: '中期答辩时间安排', value: '', label: '内容3', url: '/pages/home/home'}
    ],
    messages: [
      {title: '老师私发的消息', label: '内容', url: '/pages/home/home', isRead: true}
    ],
    user: {auth: true, name: "小明"},
    active: "home"
  },
  onSearch() {

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
    }
  }
})
