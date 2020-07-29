// pages/postddl/postddl.js
import {PostRequest} from "../../utils/ajax";
var app = getApp()

function formatTimeTamp(timeTamp, type){
  var time = new Date(timeTamp);
  if(type == 1){
    var date = ((time.getFullYear())  + "-" +
    (time.getMonth() + 1) + "-" +
    (time.getDate())
   );
    return date;
  }
  else{
    var cur = (((time.getHours()) > 10? time.getHours() : '0'+time.getHours())  + ":" +
              ((time.getMinutes()) >10? time.getMinutes(): '0'+time.getMinutes()));
    return cur;
  }
}
Page({
  data: {
    currentDate: new Date().getTime(),
    currentDateString: formatTimeTamp(new Date().getTime(), 1),
    minDate: new Date().getTime(),
    currentTime: formatTimeTamp(new Date().getTime(), 2),
    minHour: 0,
    maxHour: 23,
    minMinute: 0,
    maxMinute: 59,
    dateshow: false,
    timeshow: false,
    title: null,
    formatter(type, value) {
      if (type === 'year') {
        return `${value}年`;
      } else if (type === 'month') {
        return `${value}月`;
      }
      return value;
    },
    msg: "",
    waitshow: false,
    diaglog: false,
    userData: {},
    options: [
      { text: '任务书', value: 0 },
      { text: '开题报告', value: 1 },
      { text: '中期检查', value: 2 },
      { text: '论文定稿', value: 3 },
      { text: '论文最终稿', value: 4 },
    ],
    value: 0,
  },

  onLoad: function () {
    if(app.globalData.login == 2){
      wx.redirectTo({
        url: '../register/index',
      })
    }else{
      if(app.globalData.login == 0){
        app.dataCallback = (data) => {
          if(data.msg == "FAIL"){
            wx.redirectTo({
              url: '../register/index',
            })
          }else{
            this.setData({userData: data.userData})
          }
        }
      }else{
        this.setData({userData: app.globalData.userData});
      }
    }
  },

  onTitleChange(event){
    this.setData({
      title: event.detail,
    })
  },

  onChooseDate(event) {
    this.setData({
      currentDate: event.detail,
      currentDateString: formatTimeTamp(event.detail, 1),
      dateshow: false,
    });
    console.log(this.data)
  },

  onChooseTime(event) {
    this.setData({
      currentTime: event.detail,
      timeshow: false
    });
  },

  dateshowPopup() {
    this.setData({ dateshow: true });
  },

  timeshowPopup() {
    this.setData({ timeshow: true });
  },

  onClose() {
    this.setData({ dateshow: false , timeshow: false});
  },

  onStateChange(event){
    this.setData({value: event.detail})
  },

  onSubmit() {
    console.log(this.data.value)
    this.setData({
      waitshow: true,
    })
    PostRequest("/setDeadline", 
      { id: this.data.userData.id, 
        time: this.data.currentDateString + " " + this.data.currentTime + ":00",
        state: this.data.value,
      },
     (data) => {
      if(data.msg == "SUCCESS"){
        this.setData({
          waitshow: false,
          dialog: true,
          msg: "发送成功"
        })
      }else{
        this.setData({
          waitshow: false,
          dialog: true,
          msg: "发送失败"
        })
      }
    })
  },
  confirmError: function(event) {
    this.setData({msg: null, error: false});
  },

  confirm: function(event){
    wx.navigateBack({
      delta: 1,
    })
  },

  cancel: function(event) {
    this.setData({msg: null, dialog: false});
  },
})