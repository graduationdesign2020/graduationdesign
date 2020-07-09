//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    searchValue: '',
    commonAPPs: [
      {icon: 'star-o', text: '公告', url: '/pages/home/home'},
      {icon: 'comment-o', text: '发送', url: '/pages/SendNotice/SendNotice'},
      {icon: 'chat-o', text: '消息', url: '/pages/noticeDetail/noticeDetail'},
      {icon: 'records', text: '分数', url: '/pages/myScore/myScore'}
    ],
    notices: [
      {title: '毕业设计选题公告', value: '置顶', label: '内容1', url: '/pages/index/index'},
      {title: '优秀论文评选结果', value: '置顶', label: '内容2', url: '/pages/index/index'},
      {title: '中期答辩时间安排', value: '', label: '内容3', url: '/pages/index/index'}
    ],
    messages: [
      {title: '老师私发的消息', label: '内容', url: '/pages/index/index', isRead: true}
    ],
    user: {auth: false, name: "小明"}
  },
  onSearch() {

  },

  onChange(e) {
    console.log(e.detail);
    if(e.detail == "myprofile") {
      wx.navigateTo({
        url: '../myProfile/Center',
      })
    }
    else if(e.detail == "QA") {
      wx.navigateTo({
        url: '../QA/QA',
      })
    }
    else if(e.detail == "procedure") {
      wx.navigateTo({
        url: '../procedure/procedure',
      })
    }
    else if(e.detail == "studentFinished") {
      wx.navigateTo({
        url: '../processList/processList',
      })
    }
  }
})
