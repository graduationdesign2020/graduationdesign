// pages/processList/processList.js
import {PostRequest} from "../../utils/ajax";
var app = getApp();

Page({
  data: {
    processes: [
      {name: "任务书", end_time: "2020-01-01", finished: 12, unfinished: 2,
      unfinishedStu:[{id: 1, name: "董思成"},{id: 2, name: "李马克"},{id: 3, name: "李东赫"}],
      finishedStu:[{id: 4, name: "中本悠太"},{id: 5, name: "罗哉民"},{id: 6, name: "李帝努"}],
      },
      {name: "开题报告", end_time: "2020-03-09", finished: 12, unfinished: 0,
      unfinishedStu:[{id: 1, name: "董思成"},{id: 2, name: "李马克"},{id: 3, name: "李东赫"}],
      finishedStu:[{id: 4, name: "中本悠太"},{id: 5, name: "罗哉民"},{id: 6, name: "李帝努"}],
      },
      {name: "中期检查", end_time: "2020-06-09", finished: 11, unfinished: 1,
      unfinishedStu:[{id: 1, name: "董思成"},{id: 2, name: "李马克"},{id: 3, name: "李东赫"}],
      finishedStu:[{id: 4, name: "中本悠太"},{id: 5, name: "罗哉民"},{id: 6, name: "李帝努"}],
      },
      {name: "论文定稿", end_time: "2020-03-09", finished: 12, unfinished: 0,
      unfinishedStu:[{id: 1, name: "董思成"},{id: 2, name: "李马克"},{id: 3, name: "李东赫"}],
      finishedStu:[{id: 4, name: "中本悠太"},{id: 5, name: "罗哉民"},{id: 6, name: "李帝努"}],
      },
      {name: "论文最终稿", end_time: "2020-06-09", finished: 11, unfinished: 1,
      unfinishedStu:[{id: 1, name: "董思成"},{id: 2, name: "李马克"},{id: 3, name: "李东赫"}],
      finishedStu:[{id: 4, name: "中本悠太"},{id: 5, name: "罗哉民"},{id: 6, name: "李帝努"}],
      }
    ],
    switch: [false, false, false, false, false],
    userData: {},
    activeNames: [],
  },

  onLoad: function (options) {
    var that = this;
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
            that.setData({userData: data.userData})
            PostRequest('/checkProcess', {tea_id: data.userData.id}, that.setProcesses)
          }
        }
      }else{
        that.setData({userData: app.globalData.userData});
        PostRequest('/checkProcess', {tea_id: app.globalData.userData.id}, that.setProcesses);
      }
    }
  },

  setProcesses: function(data) {
    this.setData({processes: data});
    let initial = new Array(data.length);
    initial.fill(false);
    this.setData({switch: initial});
  },

  onChange(event) {
    this.setData({
      activeNames: event.detail,
    });
  },

  onSwitch(event) {
    const { index } = event.currentTarget.dataset;
    let past = this.data.switch;
    past[index] = !past[index];
    this.setData({switch: past});
  }
})