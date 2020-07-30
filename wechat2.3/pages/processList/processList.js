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
    activeNames: [],
  },

  onLoad: function (options) {
    var that = this;
    PostRequest('/checkProcess', {}, that.setProcesses);
  },

  setProcesses: function(data) {
    this.setData({processes: data});
    console.log(this.data.processes)
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